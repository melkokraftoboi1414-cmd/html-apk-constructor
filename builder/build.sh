#!/usr/bin/env bash
set -euo pipefail

WORKDIR="${1:-/workspace}"
JOB_ID="${2:-demo}"
TEMPLATE_DIR="$WORKDIR/AndroidWebViewTemplate"
INPUT_DIR="$WORKDIR/input"
INPUT_HTML="$INPUT_DIR/index.html"
OUTPUT_DIR="$WORKDIR/outputs/$JOB_ID"
LOGFILE="$OUTPUT_DIR/build.log"

mkdir -p "$OUTPUT_DIR"
mkdir -p "$INPUT_DIR"

echo "Build started: $(date)" > "$LOGFILE"

echo "Preparing workspace..." | tee -a "$LOGFILE"

if [ -f "$INPUT_HTML" ]; then
  echo "Using uploaded HTML: $INPUT_HTML" | tee -a "$LOGFILE"
  cp "$INPUT_HTML" "$TEMPLATE_DIR/app/src/main/assets/index.html"
else
  echo "No input HTML found at $INPUT_HTML — using template default" | tee -a "$LOGFILE"
fi

cd "$TEMPLATE_DIR"

echo "Ensuring Gradle wrapper or system gradle available..." | tee -a "$LOGFILE"

# Accept SDK licenses if sdkmanager available
if [ -x "${ANDROID_SDK_ROOT:-/opt/android-sdk}/cmdline-tools/latest/bin/sdkmanager" ]; then
  echo "Accepting Android SDK licenses (if any)..." | tee -a "$LOGFILE"
  yes | ${ANDROID_SDK_ROOT:-/opt/android-sdk}/cmdline-tools/latest/bin/sdkmanager --sdk_root=${ANDROID_SDK_ROOT:-/opt/android-sdk} --licenses 2>&1 | tee -a "$LOGFILE" || true
fi

# Run gradle build
echo "Running gradle assembleDebug..." | tee -a "$LOGFILE"
if [ -x "./gradlew" ]; then
  ./gradlew assembleDebug --no-daemon 2>&1 | tee -a "$LOGFILE"
else
  gradle assembleDebug --no-daemon 2>&1 | tee -a "$LOGFILE"
fi

APK_PATH="$(find . -type f -name '*-debug.apk' | head -n1 || true)"
if [ -z "$APK_PATH" ]; then
  echo "ERROR: APK not found after build" | tee -a "$LOGFILE"
  exit 2
fi

mkdir -p "$OUTPUT_DIR"
cp "$APK_PATH" "$OUTPUT_DIR/app-debug.apk"

echo "APK saved to $OUTPUT_DIR/app-debug.apk" | tee -a "$LOGFILE"
echo "Build finished: $(date)" | tee -a "$LOGFILE"

exit 0
