## Run

Install the reactor dependencies first, then run the web module directly:

```bash
mvn -pl JMugen.Render.libgdx.web -am install -DskipTests
mvn -f JMugen.Render.libgdx.web/pom.xml gwt:run
```

Ports are set in `pom.xml` (`gwt.port`, `gwt.codeServerPort`). Override on the CLI with `-Dgwt.port=…` and `-Dgwt.codeServerPort=…` if needed.

Open:

```text
http://127.0.0.1:8888/index.html
```

The code server is available at:

```text
http://127.0.0.1:9876/
```

## Current State

The GWT host page now starts the LibGDX/GWT backend and renders a visible smoke scene through the shared `JMugen.Render.libgdx.core` drawer.

The full JMugen game is not wired into the browser runtime yet. The remaining work is the platform layer for assets, audio, and filesystem-style loading that currently depends on desktop APIs such as `FileInputStream` and `javax.sound`.