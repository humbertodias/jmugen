[![CI](https://github.com/humbertodias/jmugen/actions/workflows/ci.yml/badge.svg)](https://github.com/humbertodias/jmugen/actions/workflows/ci.yml)
[![CD](https://github.com/humbertodias/jmugen/actions/workflows/cd.yml/badge.svg)](https://github.com/humbertodias/jmugen/actions/workflows/cd.yml)
[![gh-pages](https://github.com/humbertodias/jmugen/actions/workflows/gh-pages.yml/badge.svg)](https://github.com/humbertodias/jmugen/actions/workflows/gh-pages.yml)
[![Ask DeepWiki](https://deepwiki.com/badge.svg)](https://deepwiki.com/humbertodias/jmugen)
![GitHub all releases](https://img.shields.io/github/downloads/humbertodias/jmugen/total)


# JMugen

Java recreation of the classic 2D fighting engine [M.U.G.E.N](https://www.elecbyte.com/mugendocs-11b1/mugen.html), rendered with **LibGDX** (desktop, Android, and browser via GWT).

Forked from [code.google/jmugen](https://code.google.com/archive/p/jmugen/).

See [features](./FEATURES.md) and help implement the missing ones.

![JMugen Gameplay](https://github.com/user-attachments/assets/1b7c5e84-4abc-4200-9580-c0d00d7397b8)

## Project layout

```text
jmugen/
├── JMugen/                      # Engine (fight, menu, select, parsers)
├── JMugen.Common/               # Shared APIs + GWT-safe common types
├── JMugen.Properties/           # Config / resource bundles
├── JMugen.plugin.audio.adx/     # ADX audio plugin (desktop engine)
├── JMugen.Render.libgdx/        # LibGDX backends (Maven aggregator)
│   ├── core/                    # Shared drawer, timer, keys, audio glue
│   ├── desktop/                 # LWJGL3 — LGDXDesktopMain
│   ├── android/                 # Android launcher + asset sync
│   └── web/                     # GWT — JMugenWeb + JMugenWebClient
├── tools/                       # Optional / legacy tooling
│   ├── JMugen.Debug/
│   ├── JMugen.Launcher/
│   └── Syntax/
└── data/                        # Mugen content (copied into backends)
```

Maven artifactIds keep the long names (e.g. `JMugen.Render.libgdx.web`); use path selectors with `-pl JMugen.Render.libgdx/web` or `:JMugen.Render.libgdx.web`.

**Web**

- Shared GWT-translatable types → `JMugen` / `JMugen.Common` / `JMugen.Properties` (`.gwt.xml`, no `gwt/` tree)
- All browser FQN overrides + `Gwt*` → `JMugen.Render.libgdx/web` (`JMugenWebClient.gwt.xml`, `org/lee/mugen/gwt/`)

Details: [desktop README](./JMugen.Render.libgdx/desktop/README.md), [web README](./JMugen.Render.libgdx/web/README.md), [DEV.md](./DEV.md).

## Run

From the **repository root**. Compile with `-am`, then run only the target module with `-rf` (avoids applying the goal on the parent POM).

### Desktop

Use `exec:exec` (not `exec:java`) on macOS so GLFW gets `-XstartOnFirstThread`.

```bash
mvn -pl JMugen.Render.libgdx/desktop -am compile -DskipTests
mvn -pl JMugen.Render.libgdx/desktop -rf :JMugen.Render.libgdx.desktop exec:exec
```

### Web

```bash
mvn -pl JMugen.Render.libgdx/web -am package -DskipTests
mvn -pl JMugen.Render.libgdx/web -rf :JMugen.Render.libgdx.web gwt:run
```

Then open http://127.0.0.1:8888/index.html

## Controls

| Player | Movement | Punch | Kick | Debug help (in fight) |
|:------:|:--------:|:-----:|:----:|:---------------------:|
| **P1** | `WASD` | `U P I` | `; J K` | `F1` or `Ctrl+H` |
| **P2** | `Arrows` | `7 4 5` | `8 0 2` | — |

On macOS, F-keys often need **`Fn`+`F1`**; prefer **`Ctrl`+`H`**. Bindings: [keys.properties](JMugen.Properties/src/main/resources/keys.properties).

## Debug mode

Available during a fight (not on the title menu).

<img width="640" height="520" alt="image" src="https://github.com/user-attachments/assets/d38851cd-88f2-4249-915e-40fda655310c" />

![jmuge-statecontroller-debugger](https://github.com/user-attachments/assets/63908d34-83b8-462b-aecc-21ba9c237a5b)

## Supported platforms

LibGDX backends:

- Desktop (Linux / macOS / Windows, x64 and ARM where natives allow)
- Android
- Web (GWT / browser)

Release artifacts: [releases](https://github.com/humbertodias/jmugen/releases). Pages build: [gh-pages](https://github.com/humbertodias/jmugen/actions/workflows/gh-pages.yml).

## Contribute

- Clone from [the repository](https://github.com/humbertodias/jmugen)
- Report issues or request features on [the issues page](https://github.com/humbertodias/jmugen/issues)

## Resources

- [Development Guide](./DEV.md)
- [License](./LICENSE)
