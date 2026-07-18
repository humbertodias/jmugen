## Run

### If Maven says `Could not find artifact JMugen.Render.libgdx.core` (or `…:jar:sources`)

That happens when only the **web** project is built (IDE run, `cd JMugen.Render.libgdx.web && mvn …`, or `-f` that web `pom.xml` **before** sibling modules exist in `~/.m2`). Those `JMugen.*` JARs are **not** published to Maven Central; they must be built in the **same reactor** from the repo root, or **installed** once.

From the **repository root**:

```bash
cd /path/to/jmugen
mvn -pl JMugen.Render.libgdx.web -am package -DskipTests
```

To allow builds that use **only** the web `pom.xml` path afterward:

```bash
mvn -pl JMugen.Render.libgdx.web -am install -DskipTests
```

### Without `install` (recommended)

Run Maven from the **repository root** (aggregator `pom.xml`).

**Why two commands:** for a multi-module build, Maven runs `gwt:run` on **every** project in the reactor. The first project is always the root `JMugen.Parent` (`packaging` `pom`). That POM does not configure GWT, so `RunMojo` fails with *The parameters 'runTarget' … are missing or invalid*. Passing `--resume-from` (`-rf`) the web module shrinks the reactor to `JMugen.Render.libgdx.web` only, so `gwt:run` is not applied to the aggregator.

**Compile dependencies** (`-am` = *also make*; no GWT on the root POM here):

```bash
cd /path/to/jmugen
mvn -pl JMugen.Render.libgdx.web -am package -DskipTests
```

**GWT Super Dev / DevMode** (reactor = web module only):

```bash
mvn -pl JMugen.Render.libgdx.web -rf :JMugen.Render.libgdx.web gwt:run
```

After the first `package`, you can re-run only the second line while iterating. Plain `mvn gwt:run` from the repo root is not useful for the same reason as `-am gwt:run` without `-rf`.

**Stale GWT sources:** the module compiles Java from dependency **`-sources`** JARs (e.g. `JMugen.Common`). If you change `JMugen.Common` but the code server still reports old line numbers or errors you already fixed on disk, rebuild those artifacts so the JAR under `JMugen.Common/target/` (and `~/.m2` after `install`) updates, then use **Recompile** in the code server UI or restart `gwt:run`:

```bash
mvn -pl JMugen.Render.libgdx.web -am clean package -DskipTests
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
mvn -pl JMugen.Render.libgdx.web -am package -DskipTests
mvn -pl JMugen.Render.libgdx.web -rf :JMugen.Render.libgdx.web gwt:run
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

`LGDXWebApplication` runs **`LGDXWebSmokeGame`** (drawer + cena de teste). **`GameMenu`** (jogo completo) ainda **não compila no GWT**: o módulo `JMugen` falha com centenas de erros (`FileInputStream`, `clone()`, classes fora do `JMugenCommon.gwt.xml`, etc.). Quando `org.lee.mugen.JMugen` estiver limpo para GWT, descomenta `<inherits name="org.lee.mugen.JMugen"/>` em `JMugenWeb.gwt.xml`, repõe as dependências `JMugen` no `pom.xml` e troca o callback para `GameMenu.getInstance()`.

`LGDXWebPlatform` (dados Gdx, `MugenDataStreams`, drawer) fica pronto para esse passo. Os ficheiros do jogo copiam-se de `../data` para `target/gdx-assets/data/` no `package`.


Dist
```
mvn -pl JMugen.Render.libgdx.web -am package -DskipTests
mvn -pl JMugen.Render.libgdx.web -rf :JMugen.Render.libgdx.web gwt:compile

cp JMugen.Render.libgdx.web/src/main/webapp/index.html JMugen.Render.libgdx.web/target/webapp/index.html
cp -r JMugen.Render.libgdx.web/src/main/webapp/assets JMugen.Render.libgdx.web/target/webapp/

python3 -m http.server -d JMugen.Render.libgdx.web/target/webapp/
```


How to gwt:run
```
mvn -pl JMugen.Render.libgdx.web gwt:run
```