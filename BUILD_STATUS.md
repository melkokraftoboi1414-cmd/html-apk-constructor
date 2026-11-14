# Cloud build status

## Workflows added
- Android debug APK: manual GitHub Actions workflow.
- Windows EXE installer: manual GitHub Actions workflow.
- macOS DMG: manual GitHub Actions workflow.

## Important limitations
- The current browser page is a starter UI, not yet connected to file upload or the build API.
- Android workflow currently packages the repository's sample web interface. Uploading a user's selected HTML/ZIP into a build job is a later integration step.
- Android output is a debug APK, not a signed Play Store release.
- Windows/macOS workflows package the starter desktop wrapper.
- iOS IPA is not enabled yet. A real IPA requires an Apple Developer team, signing certificate, provisioning profile, and macOS/Xcode export configuration.
- Workflow results must be inspected in GitHub Actions before any build can be called successful.
