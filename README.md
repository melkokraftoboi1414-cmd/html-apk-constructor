# HTML APK Constructor

## Purpose

This repo is the starting point for a browser-based app constructor that helps users:

- edit HTML/JS/CSS in a browser
- preview it instantly
- export HTML or ZIP assets
- submit a build request for an Android adapter
- extend to additional platforms later

## Included

- professional HTML editor UI in `public/index.html`
- a small Express API with build job handling in `server.js`
- a Docker scaffold for Android WebView packaging in `builder/`
- a README with setup steps

## Run locally

```bash
npm install
npm start
```

Then open:

```text
http://localhost:3000
```

## Docker builder

```bash
docker build -t html-apk-builder ./builder
```

Then run:

```bash
docker run --rm -v "$(pwd)/builder:/workspace" html-apk-builder
```

This prepares an Android WebView template scaffold in `builder/AndroidWebViewTemplate`.

## Limitations

This is still a scaffolding project and not a full app store pipeline. For real APK builds you need:

- Android SDK + Java + Gradle installed
- Android signing keystore for release builds
- iOS requires macOS + Apple Developer account and Xcode signing
- Unity / Unreal / Godot / Python exports require their own toolchains and licenses

## Recommended next step

The next logical expansion is:

- real build queue (Redis/BullMQ or a lightweight worker service)
- Android SDK integration and release build pipeline
- iOS pipeline via Mac runner + Fastlane
- custom adapters for Godot / Unity / Unreal / Python apps

## Repo status

This is a practical starting point, sufficient for a demo and to continue iterating toward a production build system.
