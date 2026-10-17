<!doctype html>
<html lang="uz">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width,initial-scale=1" />
  <title>HTML → APK Konstruktor — Professional</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.65.13/codemirror.min.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.65.13/theme/material-darker.min.css" />
  <script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.65.13/codemirror.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.65.13/mode/htmlmixed/htmlmixed.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.65.13/mode/xml/xml.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.65.13/mode/javascript/javascript.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.65.13/mode/css/css.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/dompurify/2.4.0/purify.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/js-beautify/1.14.7/beautify.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/js-beautify/1.14.7/beautify-html.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/2.0.5/FileSaver.min.js"></script>
  <style>
    :root {
      --bg: #0b1220;
      --card: #0f2434;
      --muted: #9fb7cf;
      --accent: #36bdf8;
      --panel: #102935;
      --input-bg: #071422;
      --input-border: #244058;
    }
    * { box-sizing: border-box; }
    html, body { height: 100%; margin: 0; background: var(--bg); color: #eef5ff; font: 15px/1.45 system-ui, Segoe UI, Roboto, Helvetica, Arial; }
    header { padding: 18px; border-bottom: 1px solid rgba(255,255,255,0.04); display: flex; gap: 12px; align-items: center; }
    header h1 { margin: 0; font-size: 18px; }
    header p { margin: 0; color: var(--muted); font-size: 13px; }
    .wrap { max-width: 1200px; margin: 18px auto; padding: 12px; }
    .layout { display: grid; grid-template-columns: 1fr 460px; gap: 14px; }
    @media (max-width: 1100px) { .layout { grid-template-columns: 1fr; } }
    .card { background: var(--card); border-radius: 12px; padding: 12px; border: 1px solid rgba(255,255,255,0.03); }
    .editor-header { display: flex; gap: 10px; align-items: center; flex-wrap: wrap; }
    label { font-size: 13px; color: var(--muted); }
    select, input[type="text"] { padding: 8px; border-radius: 8px; background: var(--input-bg); border: 1px solid var(--input-border); color: white; }
    .controls { display: flex; gap: 8px; flex-wrap: wrap; }
    .btn { background: var(--accent); border: none; padding: 8px 12px; border-radius: 8px; font-weight: 700; color: #071522; cursor: pointer; }
    .btn.secondary { background: #253a54; color: white; }
    .btn.ghost { background: transparent; border: 1px solid rgba(255,255,255,0.06); color: var(--muted); }
    .btn.small { padding: 6px 8px; font-weight: 600; }
    .editor { height: 520px; border-radius: 8px; overflow: hidden; border: 1px solid rgba(255,255,255,0.03); }
    .preview-panel { display: flex; flex-direction: column; height: 100%; }
    .preview-toolbar { display: flex; gap: 8px; align-items: center; padding-bottom: 8px; }
    .devices { display: flex; gap: 6px; align-items: center; }
    .device { padding: 6px 8px; border-radius: 6px; background: rgba(255,255,255,0.03); cursor: pointer; font-size: 13px; }
    .device.active { outline: 2px solid rgba(54,189,248,0.18); }
    .preview-frame { border: 1px solid rgba(255,255,255,0.03); border-radius: 8px; overflow: hidden; background: white; flex: 1; }
    iframe { width: 100%; height: 100%; border: 0; }
    .muted { color: var(--muted); font-size: 13px; }
    .row { display: flex; gap: 8px; align-items: center; }
    .templates { min-width: 180px; }
    footer { margin-top: 14px; text-align: center; color: var(--muted); font-size: 13px; }
    .note { background: #0f2b38; border-radius: 8px; padding: 8px; color: #cfefff; font-size: 13px; border: 1px solid rgba(54,189,248,0.06); }
    .split { display: flex; gap: 8px; }
    .left { flex: 1; display: flex; flex-direction: column; gap: 12px; }
    .right { display: flex; flex-direction: column; gap: 12px; }
    .small-muted { font-size: 12px; color: var(--muted); }
    .kbd { background: rgba(255,255,255,0.03); padding: 4px 6px; border-radius: 6px; border: 1px solid rgba(255,255,255,0.02); font-weight: 700; }
  </style>
</head>
<body>
  <header>
    <div>
      <h1>HTML → APK Konstruktor</h1>
      <p>Professional build helper for HTML prototypes and Android packaging</p>
    </div>
  </header>

  <main class="wrap">
    <div class="layout">
      <section class="card left" aria-labelledby="editorTitle">
        <div class="editor-header">
          <div style="flex:1">
            <label id="editorTitle">HTML muharriri</label>
            <div class="small-muted">Sintaksis, satr raqamlari, avtomatik indent</div>
          </div>

          <div class="controls" role="toolbar" aria-label="Editor controls">
            <select id="templateSelect" class="templates" aria-label="Shablonlar">
              <option value="">— Shablon tanlang —</option>
              <option value="blank">Blank</option>
              <option value="hello">Hello App</option>
              <option value="landing">Landing Page</option>
              <option value="docs">Documentation</option>
            </select>

            <button class="btn small" id="formatBtn" title="Format (Ctrl/Cmd+B)">Format</button>
            <button class="btn small" id="copyBtn" title="Nusxalash">Nusxala</button>
            <button class="btn secondary small" id="saveHtmlBtn" title="HTML faylni yuklab olish (Ctrl/Cmd+S)">Saqlash</button>
            <button class="btn ghost small" id="exportProjectBtn" title="Android WebView ZIP">Export APK-proj</button>
            <button class="btn ghost small" id="backupBtn" title="Project eksport/import">Backup</button>
          </div>
        </div>

        <div class="editor" id="editorWrap" role="region" aria-label="Kod muharriri">
          <textarea id="code" style="display:none"></textarea>
          <div id="cm" style="height:100%"></div>
        </div>

        <div style="display:flex;justify-content:space-between;align-items:center;margin-top:8px">
          <div class="small-muted">Autosave (localStorage) yoqilgan — har 1s da saqlanadi</div>
          <div class="small-muted">Hotkeys: <span class="kbd">Ctrl/Cmd+S</span> <span class="kbd">Ctrl/Cmd+Enter</span> <span class="kbd">Ctrl/Cmd+B</span></div>
        </div>
      </section>

      <aside class="card right" aria-labelledby="previewTitle">
        <div style="display:flex;justify-content:space-between;align-items:center">
          <div>
            <label id="previewTitle">Oldindan ko‘rish</label>
            <div class="small-muted">Sandbox: <span id="previewModeLabel">Full</span></div>
          </div>
          <div class="row">
            <div class="devices" role="tablist" aria-label="Device previews">
              <div class="device" data-w="360" data-h="800" title="Mobile">Mobile</div>
              <div class="device active" data-w="768" data-h="1024" title="Tablet">Tablet</div>
              <div class="device" data-w="100%" data-h="100%" title="Desktop">Desktop</div>
            </div>
          </div>
        </div>

        <div class="preview-toolbar" style="margin-top:8px">
          <div class="controls">
            <button class="btn small" id="previewBtn" title="Preview (Ctrl/Cmd+Enter)">Ko‘rish</button>
            <button class="btn secondary small" id="openNewBtn" title="Open in new tab">Yangi oynada ochish</button>
            <button class="btn ghost small" id="toggleSafeBtn" title="Toggle sanitized preview">Sanitize</button>
          </div>
        </div>

        <div class="preview-frame" id="frameWrap" style="height:520px">
          <iframe id="previewFrame" title="Preview iframe" sandbox="allow-same-origin allow-forms allow-scripts"></iframe>
        </div>

        <div style="display:flex;gap:8px;flex-wrap:wrap;margin-top:10px;align-items:center">
          <button class="btn ghost small" id="importBtn">Import HTML/ZIP</button>
          <button class="btn ghost small" id="restoreBtn" title="Restore last saved">Restore</button>
          <button class="btn ghost small" id="clearBtn" title="Clear">Tozalash</button>
          <button class="btn secondary small" id="buildBtn" title="Qurishni boshlash">APK build</button>
        </div>

        <div style="margin-top:10px" class="note" role="note">
          Eslatma: "Full" preview iframe ichida qo‘llanilayotgan skriptlar ishlaydi — bu XSS xavfini bildiradi. Agar siz skriptlarni o‘chirib ko‘rmoqchi bo‘lsangiz "Sanitize" tugmasini bosing.
        </div>
      </aside>
    </div>

    <footer>
      <div class="muted">Tayyorlangan: professional prototip — APK yaratish uchun ZIP eksport va build job API</div>
    </footer>
  </main>

  <script>
    (function(){
      const DEFAULT_KEY = 'pro_html_apk_constructor_v1';
      const TEMPLATES = {
        blank: "<!doctype html>\n<html>\n  <head>\n    <meta charset=\"utf-8\">\n    <meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">\n    <title>Blank</title>\n  </head>\n  <body>\n    <h1>Blank</h1>\n  </body>\n</html>",
        hello: "<!doctype html>\n<html>\n  <head>\n    <meta charset=\"utf-8\">\n    <meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">\n    <title>Salom!</title>\n    <style>body{font-family:system-ui;padding:24px}</style>\n  </head>\n  <body>\n    <h1>Salom!</h1>\n    <p>Mening birinchi HTML ilovam.</p>\n  </body>\n</html>",
        landing: "<!doctype html>\n<html>\n  <head>\n    <meta charset=\"utf-8\">\n    <meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">\n    <title>Landing</title>\n    <style>body{font-family:system-ui;margin:0}.hero{padding:80px;text-align:center;background:linear-gradient(135deg,#36bdf8,#2aa7db);color:#062033}</style>\n  </head>\n  <body>\n    <section class=\"hero\"><h1>Mening xizmatim</h1><p>Landing sahifa namunasi</p></section>\n  </body>\n</html>",
        docs: "<!doctype html>\n<html>\n  <head>\n    <meta charset=\"utf-8\">\n    <meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">\n    <title>Docs</title>\n    <style>body{font-family:system-ui;padding:24px;max-width:900px;margin:auto}</style>\n  </head>\n  <body>\n    <h1>Documentation</h1>\n    <p>Bu yerga hujjat yozing.</p>\n  </body>\n</html>"
      };

      const previewFrame = document.getElementById('previewFrame');
      const previewBtn = document.getElementById('previewBtn');
      const saveHtmlBtn = document.getElementById('saveHtmlBtn');
      const copyBtn = document.getElementById('copyBtn');
      const formatBtn = document.getElementById('formatBtn');
      const openNewBtn = document.getElementById('openNewBtn');
      const toggleSafeBtn = document.getElementById('toggleSafeBtn');
      const importBtn = document.getElementById('importBtn');
      const exportProjectBtn = document.getElementById('exportProjectBtn');
      const templateSelect = document.getElementById('templateSelect');
      const devices = document.querySelectorAll('.device');
      const previewModeLabel = document.getElementById('previewModeLabel');
      const backupBtn = document.getElementById('backupBtn');
      const restoreBtn = document.getElementById('restoreBtn');
      const clearBtn = document.getElementById('clearBtn');
      const buildBtn = document.getElementById('buildBtn');

      let useSanitizedPreview = false;
      let currentDevice = document.querySelector('.device.active');
      let autosaveTimer = null;

      const editor = CodeMirror(document.getElementById('cm'), {
        value: localStorage.getItem(DEFAULT_KEY) || TEMPLATES.hello,
        mode: 'htmlmixed',
        theme: 'material-darker',
        lineNumbers: true,
        indentUnit: 2,
        tabSize: 2,
        lineWrapping: true,
        autofocus: true
      });

      function scheduleAutosave(){
        clearTimeout(autosaveTimer);
        autosaveTimer = setTimeout(() => {
          localStorage.setItem(DEFAULT_KEY, editor.getValue());
        }, 1000);
      }

      editor.on('change', scheduleAutosave);

      function renderPreview(){
        const html = editor.getValue();
        if (useSanitizedPreview) {
          previewFrame.srcdoc = DOMPurify.sanitize(html, { SAFE_FOR_TEMPLATES: true });
        } else {
          previewFrame.srcdoc = html;
        }
      }

      function applyDevice(deviceElem){
        devices.forEach(d => d.classList.remove('active'));
        deviceElem.classList.add('active');
        currentDevice = deviceElem;
        const w = deviceElem.dataset.w;
        const h = deviceElem.dataset.h;
        const wrapper = document.getElementById('frameWrap');
        if (w === '100%') {
          wrapper.style.width = '100%';
          wrapper.style.height = '520px';
        } else {
          wrapper.style.width = w + 'px';
          wrapper.style.height = h + 'px';
        }
      }

      devices.forEach(d => d.addEventListener('click', () => applyDevice(d)));

      previewBtn.addEventListener('click', renderPreview);

      toggleSafeBtn.addEventListener('click', () => {
        useSanitizedPreview = !useSanitizedPreview;
        previewModeLabel.textContent = useSanitizedPreview ? 'Sanitized' : 'Full';
        toggleSafeBtn.textContent = useSanitizedPreview ? 'Full' : 'Sanitize';
        renderPreview();
      });

      openNewBtn.addEventListener('click', () => {
        const blob = new Blob([editor.getValue()], { type: 'text/html' });
        const url = URL.createObjectURL(blob);
        window.open(url, '_blank', 'noopener');
        setTimeout(() => URL.revokeObjectURL(url), 2000);
      });

      function downloadHtml(filename='app.html'){
        const blob = new Blob([editor.getValue()], { type: 'text/html;charset=utf-8' });
        saveAs(blob, filename);
      }

      saveHtmlBtn.addEventListener('click', () => downloadHtml('my-app.html'));

      copyBtn.addEventListener('click', async () => {
        try {
          await navigator.clipboard.writeText(editor.getValue());
          copyBtn.textContent = 'Nusxalandi ✓';
          setTimeout(() => copyBtn.textContent = 'Nusxala', 1200);
        } catch (e) {
          alert('Clipboard-ga nusxalash mumkin emas');
        }
      });

      formatBtn.addEventListener('click', () => {
        try {
          const formatted = html_beautify(editor.getValue(), { indent_size: 2, wrap_line_length: 120 });
          editor.setValue(formatted);
        } catch (e) {
          console.error(e);
          alert('Formatlashda xatolik');
        }
      });

      templateSelect.addEventListener('change', () => {
        const val = templateSelect.value;
        if (!val) return;
        if (confirm('Shablonni yuklash — mavjud ish o‘zgarmasdan yangi kod bilan almashtiriladi. Davom etilsinmi?')) {
          editor.setValue(TEMPLATES[val]);
          renderPreview();
        }
        templateSelect.value = '';
      });

      backupBtn.addEventListener('click', () => {
        const project = { html: editor.getValue(), meta: { exportedAt: new Date().toISOString() } };
        const json = JSON.stringify(project, null, 2);
        const blob = new Blob([json], { type: 'application/json' });
        saveAs(blob, 'project-backup.json');
      });

      importBtn.addEventListener('click', () => {
        const input = document.createElement('input');
        input.type = 'file';
        input.accept = '.json,.html,.zip';
        input.addEventListener('change', async (e) => {
          const file = e.target.files[0];
          if (!file) return;
          const name = file.name.toLowerCase();
          if (name.endsWith('.json')) {
            const text = await file.text();
            try {
              const obj = JSON.parse(text);
              if (obj.html) {
                if (confirm('Projectni import qilish — mavjud ish o‘chadi. Davom etilsinmi?')) {
                  editor.setValue(obj.html);
                  renderPreview();
                }
              } else {
                alert('JSON ichida project topilmadi');
              }
            } catch (err) {
              alert('JSON pars qilishda xato');
            }
          } else if (name.endsWith('.html')) {
            const text = await file.text();
            editor.setValue(text);
            renderPreview();
          } else {
            alert('ZIP import qilish hozircha qo‘llab-quvvatlanmaydi.');
          }
        });
        input.click();
      });

      restoreBtn.addEventListener('click', () => {
        const saved = localStorage.getItem(DEFAULT_KEY);
        if (saved && confirm('LocalStorage-dan oxirgi saqlangan holat tiklansinmi?')) {
          editor.setValue(saved);
          renderPreview();
        } else {
          alert('Hech nima topilmadi yoki bekor qilindi.');
        }
      });

      clearBtn.addEventListener('click', () => {
        if (confirm('Muharrirni tozalash va default shablon yuklash?')) {
          editor.setValue(TEMPLATES.blank);
          renderPreview();
        }
      });

      exportProjectBtn.addEventListener('click', async () => {
        const zip = new JSZip();
        const html = editor.getValue();
        zip.file('index.html', html);
        zip.file('README.txt', 'This zip contains the generated HTML prototype.');
        const blob = await zip.generateAsync({ type: 'blob' });
        saveAs(blob, 'webview-project.zip');
      });

      buildBtn.addEventListener('click', async () => {
        try {
          const response = await fetch('/api/build', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ html: editor.getValue() })
          });

          if (!response.ok) {
            throw new Error('Build request failed.');
          }

          const result = await response.json();
          alert(`Build started. Job ID: ${result.job.id}`);
          console.log('Build job created:', result.job);
        } catch (err) {
          alert('Build API xatosi: ' + err.message);
        }
      });

      window.addEventListener('keydown', (e) => {
        const mac = navigator.platform.toUpperCase().indexOf('MAC') >= 0;
        const ctrl = mac ? e.metaKey : e.ctrlKey;
        if (ctrl && e.key.toLowerCase() === 's') {
          e.preventDefault();
          downloadHtml('my-app.html');
          return;
        }
        if (ctrl && e.key === 'Enter') {
          e.preventDefault();
          renderPreview();
          return;
        }
        if (ctrl && e.key.toLowerCase() === 'b') {
          e.preventDefault();
          formatBtn.click();
        }
      });

      applyDevice(currentDevice);
      renderPreview();
    })();
  </script>
</body>
</html>
