# GWT → TeaVM migration

**Status: complete.** The `:html` module uses [gdx-teavm](https://github.com/xpenatan/gdx-teavm) **1.6.0** / TeaVM **0.15.0** instead of GWT.

## Architecture

| Piece | Location |
|-------|----------|
| TeaVM entry + web glue | `html/src/main/java/.../web/` |
| Browser FQN overrides + `Gwt*` | `engine/web/src/main/java/` |
| Curated engine sources | `engine/web/teavm-sources.gradle` → `engine/web/src/teavmCurated/java/` |
| Gradle plugin | `html/build.gradle` (`com.github.xpenatan.gdx-teavm`) |

GWT `.gwt.xml` files remain in `engine/` as documentation of the original allow/exclude lists; they are no longer used by the web build.

## Build tasks

```bash
./gradlew :html:gdx_teavm_web_js_build   # compile to JS
./gradlew :html:gdx_teavm_web_js_run     # dev server
./gradlew :html:distWeb                  # webapp/ for static hosting / gh-pages
```

## TeaVM-specific notes

1. **Source curation** — `teavm-sources.gradle` mirrors `JMugen.gwt.xml` (explicit includes + catch-all with excludes).
2. **Classpath** — TeaVM excludes desktop JARs (`engine/main`, `engine/common`, `engine/properties`, `engine/adx`); uses `:engine-web` merged classes instead.
3. **LibGDX stubs** — `html/src/main/java/com/badlogic/gdx/...` replaces desktop-only types that reference `ThreadFactory` (`NetJavaImpl`, `AsyncExecutor`).
4. **Filtered gdx.jar** — `prepareGdxTeavmJar` removes those classes from the LibGDX JAR on the TeaVM classpath.
