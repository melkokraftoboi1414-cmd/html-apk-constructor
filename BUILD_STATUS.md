# Cloud build status

## Android upload-to-build flow
- The constructor page accepts one .html or .htm file (ZIP is not supported yet).
- It converts the file to Base64 in the browser and provides a copy action.
- The user pastes that value into the required html_base64 input in the Android APK GitHub Actions workflow.
- The workflow decodes the uploaded HTML into www/index.html, builds a Capacitor Android project, and uploads the debug APK as a GitHub Actions artifact.

## Limits and remaining work
- GitHub workflow_dispatch has a limited input payload; the page currently limits HTML files to 40 KB.
- This workflow has been committed but must be run in GitHub Actions and its logs/artifact inspected before a successful APK build can be claimed.
- Output is a debug APK, not a signed Play Store release.
- ZIP/multi-file HTML projects, automatic API-triggered builds, Windows/macOS packaging of the uploaded project, and iOS IPA signing are not implemented yet.
- iOS IPA requires Apple Developer signing credentials and macOS/Xcode export configuration.
