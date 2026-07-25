# JMugen LibGDX — Desktop

Run from the **repository root** so `./data/` resolves.

```bash
./gradlew :libgdx-desktop:run
```

Install a distribution:

```bash
./gradlew :libgdx-desktop:installDist
./JMugen.Render.libgdx/desktop/build/install/libgdx-desktop/bin/libgdx-desktop
```

**macOS:** `-XstartOnFirstThread` is set automatically on the `run` task.

Custom data path:

```bash
./gradlew :libgdx-desktop:run --args='' -Djmugen.resource=/path/to/data/
```

Or set VM options in your IDE:

- Main: `org.lee.mugen.renderer.libgdx.LGDXDesktopMain`
- Working directory: repository root
- macOS VM option: `-XstartOnFirstThread`

Debug harness: `LGDXDesktopWebGameDebugMain` in the same module.
