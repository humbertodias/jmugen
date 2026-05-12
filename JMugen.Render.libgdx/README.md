# JMugen.Render.libgdx

## Overview

This module provides a **LibGDX** rendering backend for JMugen. LibGDX is a cross-platform, high-performance Java game development framework that abstracts over multiple graphics APIs (OpenGL, DirectX, etc.) through a unified interface.

## Features

- **2D Sprite Rendering**: Efficient sprite drawing with transformation support
- **Cross-Platform**: Works on Windows, macOS, Linux, iOS, Android, and Web
- **High Performance**: Uses batch rendering for optimal GPU utilization
- **Shader Support**: Built-in framework for advanced visual effects
- **Input Handling**: Keyboard and mouse input processing
- **Timer System**: Frame-rate independent game timing

## Architecture

The module implements two core interfaces:

### 1. **LGDXGameWindow** (implements `GameWindow`)
- Manages the game window and event loop
- Handles input (keyboard, mouse)
- Provides rendering context via `SpriteBatch`
- Uses LibGDX's `ApplicationListener` lifecycle

### 2. **LGDXMugenDrawer** (extends `MugenDrawer`)
- Implements sprite drawing and transformations
- Handles color and alpha blending
- Supports shape rendering (rectangles, lines)
- Manages image containers and texture caching

### 3. **LGDXMugenTimer** (implements `MugenTimer`)
- Provides frame-rate independent timing
- Supports pause/resume functionality
- Handles time scaling for slow-motion effects

## Configuration

The module uses **LWJGL3** as the desktop backend for LibGDX. Configuration can be customized in `LGDXGameWindow.start()` method:

```java
Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
config.setWindowedMode(640, 480);  // Resolution
config.setTitle("JMugen - LibGDX");
config.useVsync(true);              // V-Sync
config.setForegroundFPS(60);        // Target FPS
```

## Dependencies

- **gdx**: Core LibGDX library
- **gdx-backend-lwjgl3**: LWJGL3 backend for desktop rendering
- **gdx-freetype**: Font rendering support
- Platform-specific natives for current OS

## Project Structure

```
JMugen.Render.libgdx/
├── src/main/java/org/lee/mugen/renderer/libgdx/
│   ├── LGDXGameWindow.java     # Window and event management
│   ├── LGDXMugenDrawer.java    # Rendering implementation
│   ├── LGDXMugenTimer.java     # Timing system
│   └── shader/                 # Shader implementations (optional)
└── pom.xml                     # Maven configuration
```

## Usage

To use this rendering backend, set the renderer class in your configuration file:

```properties
# render.properties
renderClass=org.lee.mugen.renderer.libgdx.LGDXMugenDrawer
```

## Comparison with Other Backends

| Feature | JOGL | LWJGL | Swing | LibGDX |
|---------|------|-------|-------|--------|
| Performance | ⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐ | ⭐⭐⭐⭐ |
| Cross-Platform | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ |
| Mobile Support | ✗ | ✗ | ✗ | ✓ |
| Web Support | ✗ | ✗ | ✗ | ✓ |
| Abstraction Level | Low | Low | High | High |
| Learning Curve | Steep | Steep | Shallow | Shallow |

## Notes

- LibGDX uses a coordinate system where (0,0) is at the top-left corner by default
- The renderer automatically handles Y-axis flipping for compatibility with JMugen's coordinate system
- Shader support can be extended in the `shader/` subdirectory
- Texture caching is implemented to optimize memory usage

## Future Enhancements

- [ ] Advanced shader support (PalFx, AfterImage effects)
- [ ] Particle effects
- [ ] Sound backend integration
- [ ] Mobile platform optimization
- [ ] Web (GWT) backend support
