# JMugen Development Guide

## Requirements

- **JDK 17+** (Gradle / LibGDX / Android / GWT compiler)
- Gradle wrapper: `./gradlew` (included in repo)

### SDKMAN example

```sh
sdk install java 17.0.14-amzn
```

## Build

From the **repository root**:

```sh
./gradlew build              # everything except Android
./gradlew -Pandroid build    # include Android APK (needs ANDROID_HOME)
```

## Desktop

```sh
./gradlew :libgdx-desktop:run
```

- Working directory is the repo root (so `./data/` resolves).
- macOS: `-XstartOnFirstThread` is applied automatically.

## Web (GWT)

```sh
./gradlew :libgdx-web:distWeb
python3 -m http.server -d JMugen.Render.libgdx/web/build/webapp 8888
```

Open http://127.0.0.1:8888/index.html

## Android

Optional — enable with `-Pandroid` and set `ANDROID_HOME`:

```sh
export ANDROID_HOME=/path/to/Android/Sdk
echo "sdk.dir=$ANDROID_HOME" > local.properties
./gradlew -Pandroid :libgdx-android:assembleDebug
```

APK: `JMugen.Render.libgdx/android/build/outputs/apk/debug/`

## Release jar (shaded desktop)

```sh
./gradlew :jmugen-debug:shadowJar
# → tools/JMugen.Debug/build/libs/JMugen.Debug-0.0.1-SNAPSHOT.jar
```

## Gradle modules

| Gradle project | Directory |
|----------------|-----------|
| `:jmugen-properties` | `JMugen.Properties/` |
| `:jmugen-common` | `JMugen.Common/` |
| `:jmugen-plugin-audio-adx` | `JMugen.plugin.audio.adx/` |
| `:jmugen-engine` | `JMugen/` |
| `:libgdx-core` | `JMugen.Render.libgdx/core/` |
| `:libgdx-desktop` | `JMugen.Render.libgdx/desktop/` |
| `:libgdx-web` | `JMugen.Render.libgdx/web/` |
| `:libgdx-android` | `JMugen.Render.libgdx/android/` (with `-Pandroid`) |
| `:jmugen-syntax` | `tools/Syntax/` |
| `:jmugen-debug` | `tools/JMugen.Debug/` |
| `:jmugen-launcher` | `tools/JMugen.Launcher/` |
