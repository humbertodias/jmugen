# JMugen Development Guide

## Requirements

- **JDK 17+** (Gradle / LibGDX / Android / TeaVM web)
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
./gradlew :desktop:run
```

- Working directory is the repo root (so `./assets/` resolves).
- macOS: `-XstartOnFirstThread` is applied automatically.

## Web (TeaVM)

```sh
./gradlew :html:distWeb
# or live dev:
./gradlew :html:gdx_teavm_web_js_run
```

Static hosting after `distWeb`:

```sh
python3 -m http.server -d html/build/webapp 8888
```

Open http://127.0.0.1:8888/index.html

## Android

Optional — enable with `-Pandroid` and set `ANDROID_HOME`:

```sh
export ANDROID_HOME=/path/to/Android/Sdk
echo "sdk.dir=$ANDROID_HOME" > local.properties
./gradlew -Pandroid :android:assembleDebug
```

APK: `android/build/outputs/apk/debug/`

## Release jar (shaded desktop)

```sh
./gradlew :tools-debug:shadowJar
# → tools/JMugen.Debug/build/libs/JMugen.Debug-0.0.1-SNAPSHOT.jar
```

## Gradle modules

| Gradle project | Directory |
|----------------|-----------|
| `:engine-properties` | `engine/properties/` |
| `:engine-common` | `engine/common/` |
| `:engine-adx` | `engine/adx/` |
| `:engine` | `engine/main/` |
| `:engine-web` | `engine/web/` |
| `:core` | `core/` |
| `:desktop` | `desktop/` |
| `:html` | `html/` |
| `:android` | `android/` (with `-Pandroid`) |
| `:tools-syntax` | `tools/Syntax/` |
| `:tools-debug` | `tools/JMugen.Debug/` |
| `:tools-launcher` | `tools/JMugen.Launcher/` |
