## Run (LibGDX desktop / LWJGL3)

This module is **not** GWT. There is no `gwt:run` here. You run a normal Java `main`.

Default entry: **`LGDXDesktopMain`** → full `GameMenu` (same flow as Android / web).

The old **`LGDXDesktopWebGameDebugMain`** only draws a pulsing rectangle (drawer smoke test). Do not use it if you want the game.

### From the repository root (no `install` required)

**Why two steps:** with `-am`, Maven also runs the goal on `JMugen.Parent`, which has no exec config. Compile with `-am`, then run only the desktop module with `-rf`.

**Use `exec:exec` (not `exec:java`)** on macOS: `exec:java` runs inside the Maven JVM, so `-XstartOnFirstThread` cannot be applied and GLFW fails. `exec:exec` forks a new JVM; the `macos-lwjgl` profile (auto on Mac) adds `-XstartOnFirstThread`.

```bash
cd /path/to/jmugen
mvn -pl JMugen.Render.libgdx/desktop -am compile -DskipTests
mvn -pl JMugen.Render.libgdx/desktop -rf :JMugen.Render.libgdx.desktop exec:exec
```

### Drawer smoke test only

```bash
mvn -pl JMugen.Render.libgdx/desktop -rf :JMugen.Render.libgdx.desktop exec:exec \
  -Ddesktop.mainClass=org.lee.mugen.renderer.libgdx.LGDXDesktopWebGameDebugMain
```

(That property only works if you pass it into the plugin arguments; easier: run that class from the IDE.)

### From this module’s POM only

```bash
mvn -f JMugen.Render.libgdx/desktop/pom.xml compile exec:exec
```

### IDE

Main class: `org.lee.mugen.renderer.libgdx.LGDXDesktopMain`  
On macOS, add **`-XstartOnFirstThread`** to VM options.  
Working directory (or VM `-Djmugen.resource=/path/to/jmugen/data/`) must see `data/data/system.def`. The main also walks up from `user.dir` to find it.

### Full game via tools/JMugen.Debug

`render.properties` already points at `LGDXMugenDrawer`. You can also run `org.lee.mugen.test.TestMenu` from **tools/JMugen.Debug** (same menu path; still needs `-XstartOnFirstThread` on macOS).
