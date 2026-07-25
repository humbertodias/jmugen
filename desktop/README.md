# JMugen LibGDX — Desktop

Run from the **repository root** so `./assets/` resolves.

```bash
./gradlew :desktop:run
```

Install a distribution:

```bash
./gradlew :desktop:installDist
./desktop/build/install/desktop/bin/desktop
```

**macOS:** `-XstartOnFirstThread` is set automatically on the `run` task.

Custom assets path:

```bash
./gradlew :desktop:run --args='' -Djmugen.resource=/path/to/assets/
```

Or set VM options in your IDE:

- Main: `org.lee.mugen.renderer.libgdx.LGDXDesktopMain`
- Working directory: repository root
- macOS VM option: `-XstartOnFirstThread`

Debug harness: `LGDXDesktopWebGameDebugMain` in the same module.
