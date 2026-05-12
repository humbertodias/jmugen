# LibGDX Renderer Integration Guide

## Quick Start

### 1. Add the Module to Your Build

The module has already been added to the parent `pom.xml`. Build the entire project:

```bash
cd /Users/cex/dev/repository/personal/jmugen
mvn clean install
```

### 2. Configure the Renderer

Create or update your `render.properties` configuration file:

```properties
# Use LibGDX renderer backend
renderClass=org.lee.mugen.renderer.libgdx.LGDXMugenDrawer
```

### 3. Use in Your Application

```java
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.MugenDrawer;

public class MyGame {
    public static void main(String[] args) throws Exception {
        // Initialize the graphics wrapper with LibGDX backend
        GraphicsWrapper.init();
        
        // Get the drawer
        MugenDrawer drawer = GraphicsWrapper.getInstance();
        
        // Get the game window
        GameWindow window = drawer.getInstanceOfGameWindow();
        
        // Set window properties
        window.setTitle("My JMugen Game");
        window.setResolution(640, 480);
        
        // Initialize game callback
        window.setGameWindowCallback(new MyGameCallback());
        
        // Start the game loop
        window.start();
    }
}
```

## LibGDX Configuration Options

### Window Configuration

Modify `LGDXGameWindow.start()` to customize:

```java
Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

// Display settings
config.setWindowedMode(640, 480);      // Resolution
config.setResizable(true);              // Allow window resize
config.setFullscreenMode(null);         // Or use fullscreen

// Performance
config.setForegroundFPS(60);            // Target FPS
config.useVsync(true);                  // Enable vertical sync
config.setIdleFPS(30);                  // FPS when window is not focused

// Graphics
config.setBackBufferConfig(8, 8, 8, 8, 16, 0, 4); // R,G,B,A,Depth,Stencil,Samples

// Window hints
config.setTitle("JMugen - LibGDX Renderer");
```

## Performance Tuning

### Batch Size
The `SpriteBatch` automatically manages batch size. For large numbers of sprites, consider:

```java
batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
```

### Texture Atlasing
For better performance with many sprites, group textures into atlases:

```java
// The LGDXImageContainer supports texture atlasing
// Implement texture packing in a future enhancement
```

### Culling
The renderer automatically culls off-screen sprites through LibGDX's batching system.

## Debugging

### Enable Debug Rendering

Add shape rendering for debug visualization:

```java
drawer.drawRect(x, y, width, height);  // Draw rectangle outline
drawer.drawLine(x1, y1, x2, y2);       // Draw line
drawer.fillRect(x, y, width, height);  // Draw filled rectangle
```

### Logging

The module uses `org.lee.mugen.util.Logger`:

```java
Logger.info("Renderer initialized");
Logger.error("Error loading texture", exception);
```

## Input Handling

### Keyboard Input

Register a sprite command processor:

```java
window.addSpriteKeyProcessor(new ISpriteCmdProcess() {
    @Override
    public void keyPressed(int keyCode) {
        // Handle key press
    }
    
    @Override
    public void keyReleased(int keyCode) {
        // Handle key release
    }
});
```

### Mouse Input

Get mouse status:

```java
GameWindow.MouseCtrl mouse = window.getMouseStatus();
int mouseX = mouse.x;
int mouseY = mouse.y;
boolean leftPressed = mouse.isLeftPress();
```

## Shader Support

The module provides a `shader/` directory for advanced effects:

### Creating a Custom Shader

1. Create a shader class in `shader/` subdirectory
2. Extend LibGDX's shader framework
3. Integrate with `LGDXMugenDrawer`

Example structure:
```java
public class MyEffectShader {
    private ShaderProgram shaderProgram;
    
    public MyEffectShader() {
        // Compile shader
    }
    
    public void apply(SpriteBatch batch) {
        batch.setShader(shaderProgram);
    }
}
```

## Troubleshooting

### Issue: "Unable to initialize GLFW"
- Ensure you have a compatible graphics card with OpenGL 2.1+ support
- Check that you're not in a headless environment

### Issue: "LibGDX resources not found"
- Verify that native libraries are properly downloaded by Maven
- Check `~/.m2/repository/com/badlogicgames/gdx/`

### Issue: "Texture not rendering"
- Verify texture format is supported (RGBA8888)
- Check texture dimensions are power-of-two for older GPUs
- Ensure texture data is not null

### Issue: "Input not responding"
- Ensure window has focus
- Verify `ISpriteCmdProcess` is properly registered
- Check key codes are correct (use Gdx.input key constants)

## Platform-Specific Notes

### macOS
- Requires Apple Silicon or Intel CPU with OpenGL support
- Use `natives-desktop` classifier for proper library loading

### Linux
- Ensure libgdx LWJGL3 natives are installed
- May require development headers for graphics libraries

### Windows
- LWJGL3 handles DirectX interop automatically
- No additional configuration needed

## Migration from Other Backends

### From LWJGL 3 directly
- LibGDX abstracts LWJGL, so you get the same performance with better compatibility

### From JOGL
- Similar rendering API, but LibGDX has broader platform support

### From Swing
- Significantly better performance
- More flexible for advanced graphics

## Example: Complete Renderer Setup

```java
import org.lee.mugen.renderer.*;
import org.lee.mugen.core.Game;

public class LibGDXGameExample implements Game {
    
    public void init(GameWindow window) throws Exception {
        System.out.println("Game initialized with LibGDX backend");
    }
    
    public void update() {
        // Update game logic
    }
    
    public static void main(String[] args) throws Exception {
        // Initialize
        GraphicsWrapper.init();
        MugenDrawer drawer = GraphicsWrapper.getInstance();
        GameWindow window = drawer.getInstanceOfGameWindow();
        
        // Configure
        window.setTitle("LibGDX JMugen Example");
        window.setResolution(640, 480);
        window.setGameWindowCallback(new LibGDXGameExample());
        
        // Run
        window.start();
    }
}
```

## Next Steps

1. **Test the renderer** with your existing game code
2. **Compare performance** with other backends
3. **Report issues** or suggest improvements
4. **Extend shader support** for advanced effects
5. **Add mobile backend** for cross-platform deployment

## Resources

- [LibGDX Documentation](https://libgdx.com/wiki)
- [LWJGL 3 Guide](https://www.lwjgl.org)
- [LibGDX GitHub](https://github.com/libgdx/libgdx)
