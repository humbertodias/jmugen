# JMugen LibGDX — Web (TeaVM)

Build from the **repository root** (Gradle multi-project).

## Compile and serve

```bash
./gradlew :html:distWeb
python3 -m http.server -d html/build/webapp 8888
```

Open http://127.0.0.1:8888/index.html

## Recompile JS only

```bash
./gradlew :html:gdx_teavm_web_js_build
```

## Dev server (Jetty + live reload)

```bash
./gradlew :html:gdx_teavm_web_js_run
```

Output: `html/build/dist/js/webapp/` (copied to `html/build/webapp/` by `distWeb`).

## Layout

- Entry point: `org.lee.mugen.renderer.libgdx.web.TeaVMWebLauncher`
- Browser-safe engine: `:engine-web` (GWT-equivalent overrides + curated sources)
- Source curation: `engine/web/teavm-sources.gradle` (mirrors `JMugen.gwt.xml` rules)
- Game assets: repo `assets/` (bundled by gdx-teavm)

## Debug shared Java on JVM

Run `LGDXDesktopWebGameDebugMain` in `:desktop` (see desktop README).
