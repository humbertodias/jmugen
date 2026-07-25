# JMugen LibGDX — Web (GWT)

Build from the **repository root** (Gradle multi-project).

## Compile and serve

```bash
./gradlew :libgdx-web:distWeb
python3 -m http.server -d JMugen.Render.libgdx/web/build/webapp 8888
```

Open http://127.0.0.1:8888/index.html

## Recompile GWT only

```bash
./gradlew :libgdx-web:gwtCompile
```

## Dev mode (Super Dev + embedded server)

```bash
./gradlew :libgdx-web:gwtDevMode
```

Output: `JMugen.Render.libgdx/web/build/gwt/`

## Layout

- GWT module: `org.lee.mugen.renderer.libgdx.web.JMugenWeb`
- Browser FQN overrides: `JMugenWebClient.gwt.xml` + `org/lee/mugen/gwt/`
- Game data staged to `build/gdx-assets/` before GWT compile

## Debug shared Java on JVM

Run `LGDXDesktopWebGameDebugMain` in `:libgdx-desktop` (see desktop README).
