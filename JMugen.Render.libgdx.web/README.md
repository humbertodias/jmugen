## Run

### Without `install` (recommended)

Run Maven from the **repository root** (aggregator `pom.xml`). `-am` (*also make*) builds every module the web module needs (`core`, `Common`, …) in one reactor session, so nothing has to be published to `~/.m2` first.

```bash
cd /path/to/jmugen
mvn -pl JMugen.Render.libgdx.web -am gwt:run
```

### Avoid running only the web `pom.xml`

```bash
mvn -f JMugen.Render.libgdx.web/pom.xml gwt:run
```

In that mode Maven does **not** see sibling modules in the reactor; it resolves `JMugen.*` from the local repo / Central only, so you typically need `install` (or another flow that publishes artifacts).

### If you need artifacts in `~/.m2`

(e.g. another tool or project consuming these JARs)

```bash
mvn -pl JMugen.Render.libgdx.web -am install -DskipTests
mvn -f JMugen.Render.libgdx.web/pom.xml gwt:run
```

### Stale parent / resolution cache

If you still see an old parent resolution error, clear the local group cache and rebuild from the root with `-am`:

```bash
rm -rf ~/.m2/repository/JMugen
mvn -pl JMugen.Render.libgdx.web -am gwt:run
```

### Ports

Configured in `pom.xml` as `gwt.port` and `gwt.codeServerPort`. Override from the CLI with `-Dgwt.port=…` and `-Dgwt.codeServerPort=…` if needed.

Open the hosted app:

```text
http://127.0.0.1:8888/index.html
```

Code server (Super Dev):

```text
http://127.0.0.1:9876/
```

### Debugging

- **8888** is HTTP (embedded server). It is not a JVM debug port.
- GWT **client** code runs as **JavaScript** in the browser. IDE breakpoints on those Java sources are not hit by a JVM remote debugger attached to the DevMode process.
- Use **Chrome DevTools** (Sources) with Super Dev / source maps. The build uses GWT `-style PRETTY` for more readable JS.
- To debug **shared Java** on a real JVM, run the desktop harness `LGDXDesktopWebGameDebugMain` in `JMugen.Render.libgdx.desktop` (see that module’s README).

## Current state

The GWT host page starts the LibGDX/GWT backend and shows a small smoke scene through the shared `JMugen.Render.libgdx.core` drawer.

The full JMugen game is not wired into the browser runtime yet. Remaining work includes the web platform layer for assets, audio, and filesystem-style loading that still depends on desktop APIs such as `FileInputStream` and `javax.sound`.
