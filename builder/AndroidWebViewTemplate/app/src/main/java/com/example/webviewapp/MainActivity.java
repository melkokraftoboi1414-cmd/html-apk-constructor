package com.example.webviewapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private final String PATCH_URL = "https://raw.githubusercontent.com/melkokraftoboi1414-cmd/html-apk-constructor/main/public/patches/latest/index.html";
    private File localPatchFile;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        setContentView(webView);

        localPatchFile = new File(getFilesDir(), "patch_index.html");

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        // Expose a method to JS so the page can trigger update checks
        webView.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public void checkForPatch() {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Patch tekshirilmoqda...", Toast.LENGTH_SHORT).show());
                checkForPatchInternal(true);
            }
        }, "AppNative");

        // Load local patch if exists, otherwise asset
        if (localPatchFile.exists()) {
            webView.loadUrl("file://" + localPatchFile.getAbsolutePath());
        } else {
            webView.loadUrl("file:///android_asset/index.html");
        }

        // Also check for patch in background on startup
        checkForPatchInternal(false);
    }

    private void checkForPatchInternal(boolean notifyUser) {
        new Thread(() -> {
            try {
                URL url = new URL(PATCH_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(8000);
                conn.setReadTimeout(8000);
                conn.setRequestProperty("User-Agent", "HTML-APK-Constructor/1.0");
                int code = conn.getResponseCode();
                if (code != 200) {
                    conn.disconnect();
                    if (notifyUser) runOnUiThread(() -> Toast.makeText(MainActivity.this, "Hech qanday yangilanish topilmadi.", Toast.LENGTH_SHORT).show());
                    return;
                }

                InputStream in = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) sb.append(line).append("\n");
                reader.close();
                conn.disconnect();

                String remoteHtml = sb.toString();
                String currentHtml = null;

                if (localPatchFile.exists()) {
                    currentHtml = readFile(localPatchFile);
                } else {
                    currentHtml = readAsset("index.html");
                }

                if (!remoteHtml.trim().equals(currentHtml.trim())) {
                    writeFile(localPatchFile, remoteHtml.getBytes("UTF-8"));
                    // Load the new file on UI thread
                    handler.post(() -> {
                        webView.loadUrl("file://" + localPatchFile.getAbsolutePath());
                        if (notifyUser) Toast.makeText(MainActivity.this, "Yangilanish o‘rnatildi.", Toast.LENGTH_SHORT).show();
                    });
                } else {
                    if (notifyUser) runOnUiThread(() -> Toast.makeText(MainActivity.this, "Sizda eng so‘nggi versiya mavjud.", Toast.LENGTH_SHORT).show());
                }

            } catch (Exception e) {
                e.printStackTrace();
                if (notifyUser) runOnUiThread(() -> Toast.makeText(MainActivity.this, "Yangilanishni tekshirishda xato: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        }).start();
    }

    private String readAsset(String name) {
        try {
            InputStream is = getAssets().open(name);
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) sb.append(line).append("\n");
            r.close();
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

    private String readFile(File f) {
        try {
            InputStream is = openFileInput(f.getName());
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) sb.append(line).append("\n");
            r.close();
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

    private void writeFile(File f, byte[] data) {
        try (FileOutputStream fos = new FileOutputStream(f)) {
            fos.write(data);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
