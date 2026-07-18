# JMugen Development Guide

## Requirements

- [SDKMAN](https://sdkman.io/install) (optional but convenient)
- **JDK 8** for GWT / web (`gwt:compile`, `gwt:run`, gh-pages)
- **JDK 17+** is fine for desktop / Android LibGDX builds when not compiling GWT
- Maven 3.6.3 or higher

### SDKMAN example

```sh
sdk install java 8.0.472-amzn   # web / GWT
sdk install java 17.0.14-amzn  # desktop / Android if preferred
sdk install maven 3.9.9
```

## Project layout (LibGDX)

```text
JMugen/  JMugen.Common/  JMugen.Properties/  JMugen.plugin.audio.adx/
JMugen.Render.libgdx/{core,desktop,android,web}
tools/{JMugen.Debug,JMugen.Launcher,Syntax}
data/
```

Always run Maven from the **repo root** so `-am` / `-rf` and `data/` paths resolve correctly.

## Desktop

```sh
mvn -pl JMugen.Render.libgdx/desktop -am compile -DskipTests
mvn -pl JMugen.Render.libgdx/desktop -rf :JMugen.Render.libgdx.desktop exec:exec
```

- Prefer **`exec:exec`** over `exec:java` (macOS needs a forked JVM with `-XstartOnFirstThread`).
- Working directory should be the repo root so `./data/...` is found.
- More detail: [JMugen.Render.libgdx/desktop/README.md](./JMugen.Render.libgdx/desktop/README.md).

### IDE

- Main: `org.lee.mugen.renderer.libgdx.LGDXDesktopMain`
- macOS VM option: `-XstartOnFirstThread`
- Working directory: repository root (or `-Djmugen.resource=…/data/`)

### Debug help (in fight)

- `F1` (on macOS often `Fn`+`F1`)
- or `Ctrl`+`H`

## Web (GWT)

```sh
mvn -pl JMugen.Render.libgdx/web -am package -DskipTests
mvn -pl JMugen.Render.libgdx/web -rf :JMugen.Render.libgdx.web gwt:run
```

Open http://127.0.0.1:8888/index.html

- Shared GWT sources: `JMugen/…/JMugen.gwt.xml`
- Browser-only code: `JMugen.Render.libgdx/web` → `JMugenWebClient.gwt.xml` + `org/lee/mugen/gwt/` + `Gwt*.java`
- Full guide: [JMugen.Render.libgdx/web/README.md](./JMugen.Render.libgdx/web/README.md)

### Force GWT recompile

```sh
mvn -pl JMugen.Render.libgdx/web -rf :JMugen.Render.libgdx.web gwt:compile -Dgwt.compiler.force=true
```

## Android

See `JMugen.Render.libgdx/android` and its asset sync scripts. Build from the repo root with the Android module in the reactor (CI excludes it when needed).

## Legacy modules

`tools/JMugen.Launcher` / `tools/JMugen.Debug` may still exist for older flows. The supported LibGDX path is **desktop / Android / web** above.

## References

- [Mugen Engine (Wikipedia)](https://en.wikipedia.org/wiki/Mugen_(game_engine))
- [Google Code Archive: JMugen](https://code.google.com/archive/p/jmugen/)
- [JMugen Live Debug and Expression Watch (YouTube)](https://www.youtube.com/watch?v=6uVFrC91OU8)
