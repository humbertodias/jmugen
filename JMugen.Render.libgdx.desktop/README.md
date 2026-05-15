## Run (LibGDX desktop / LWJGL3)

This module is **not** GWT. There is no `gwt:run` here. You run a normal Java `main`.

### From the repository root (no `install` required)

```bash
cd /path/to/jmugen
mvn -pl JMugen.Render.libgdx.desktop -am compile exec:java -DskipTests
```

`exec:java` runs `org.lee.mugen.renderer.libgdx.LGDXDesktopWebGameDebugMain` (same smoke `Game` path as the web module, on LWJGL3).

### From this module’s POM only

Requires `JMugen.Render.libgdx.core` (and its transitive deps) to already be available in `~/.m2`, or run `install` / use the root command above.

```bash
mvn -f JMugen.Render.libgdx.desktop/pom.xml compile exec:java
```

### macOS (GLFW / main thread)

On macOS, LWJGL3 needs **`-XstartOnFirstThread`**. The `macos-lwjgl` Maven profile (auto-activated on Mac) adds it for `exec:java`. If you start from the IDE, add **`-XstartOnFirstThread`** to the VM options of your run configuration.

### Full game / menu

The main JMugen entry points live in **`JMugen.Debug`** / **`JMugen.Launcher`**, for example:

```bash
mvn package
java -XstartOnFirstThread -jar JMugen.Launcher/target/JMugen.Launcher-0.0.1-SNAPSHOT.jar
```

(Adjust the JAR name/version if your build differs.)
