# LWJGL v2 to v3 Migration Guide for JMugen

This document describes the migration of JMugen from LWJGL 2.9.3 to LWJGL 3.3.5.

## Overview

LWJGL 3 represents a complete rewrite of LWJGL 2, with significant API changes. The main differences are:

1. **Window Management**: LWJGL 2 used the `Display` class, LWJGL 3 uses GLFW
2. **Input Handling**: LWJGL 2 had `Keyboard` and `Mouse` classes, LWJGL 3 uses GLFW callbacks
3. **OpenGL Bindings**: LWJGL 3 uses static imports and direct function calls
4. **Buffer Management**: LWJGL 2 used `BufferUtils`, LWJGL 3 uses `MemoryUtil`
5. **Timing**: LWJGL 2 had `Timer` utility, LWJGL 3 uses GLFW timing functions

## Changes Made

### 1. Dependencies (pom.xml)

**Before (LWJGL 2.9.3):**
```xml
<dependency>
    <groupId>org.lwjgl.lwjgl</groupId>
    <artifactId>lwjgl</artifactId>
    <version>2.9.3</version>
</dependency>
```

**After (LWJGL 3.3.5):**
```xml
<dependency>
    <groupId>org.lwjgl</groupId>
    <artifactId>lwjgl</artifactId>
    <version>3.3.5</version>
</dependency>
<dependency>
    <groupId>org.lwjgl</groupId>
    <artifactId>lwjgl-glfw</artifactId>
    <version>3.3.5</version>
</dependency>
<dependency>
    <groupId>org.lwjgl</groupId>
    <artifactId>lwjgl-opengl</artifactId>
    <version>3.3.5</version>
</dependency>
```

### 2. Window Management (LGameWindow.java)

**Before:**
```java
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

Display.setDisplayMode(new DisplayMode(width, height));
Display.create();
Display.update();
Display.isCloseRequested();
```

**After:**
```java
import static org.lwjgl.glfw.GLFW.*;

glfwInit();
window = glfwCreateWindow(width, height, title, NULL, NULL);
glfwMakeContextCurrent(window);
GL.createCapabilities();
glfwSwapBuffers(window);
glfwWindowShouldClose(window);
```

### 3. Input Handling

**Before:**
```java
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

Keyboard.isKeyDown(Keyboard.KEY_ESCAPE);
Mouse.getX();
Mouse.isButtonDown(0);
```

**After:**
```java
// Setup callbacks in window initialization
glfwSetKeyCallback(window, (win, key, scancode, action, mods) -> {
    if (action == GLFW_PRESS) {
        keyStates.put(key, true);
    } else if (action == GLFW_RELEASE) {
        keyStates.put(key, false);
    }
});

// Check key state
boolean isPressed = keyStates.getOrDefault(key, false);
```

### 4. Key Code Mapping (GLFWKeyMapper.java)

Created a new class to map LWJGL 2 key constants to GLFW:

```java
public class GLFWKeyMapper {
    public static final int KEY_ESCAPE = GLFW_KEY_ESCAPE;
    public static final int KEY_SPACE = GLFW_KEY_SPACE;
    public static final int KEY_A = GLFW_KEY_A;
    // ... etc
}
```

### 5. Buffer Management

**Before:**
```java
import org.lwjgl.BufferUtils;

ByteBuffer buffer = BufferUtils.createByteBuffer(size);
IntBuffer intBuffer = BufferUtils.createIntBuffer(count);
```

**After:**
```java
import org.lwjgl.system.MemoryUtil;

ByteBuffer buffer = MemoryUtil.memAlloc(size);
IntBuffer intBuffer = MemoryUtil.memAllocInt(count);

// Don't forget to free!
MemoryUtil.memFree(buffer);
```

### 6. Timing

**Before:**
```java
import org.lwjgl.util.Timer;

Timer timer = new Timer();
Timer.tick();
float time = timer.getTime();
```

**After:**
```java
import static org.lwjgl.glfw.GLFW.glfwGetTime;

double time = glfwGetTime();
```

### 7. OpenGL Calls

**Before:**
```java
import org.lwjgl.opengl.GL11;

GL11.glEnable(GL11.GL_TEXTURE_2D);
GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
```

**After:**
```java
import static org.lwjgl.opengl.GL11.*;

glEnable(GL_TEXTURE_2D);
glClear(GL_COLOR_BUFFER_BIT);
```

## Files Modified

1. `pom.xml` - Updated LWJGL dependencies
2. `LGameWindow.java` - Complete rewrite of window and input management
3. `LMugenTimer.java` - Updated to use GLFW timing
4. `TextureLoader.java` - Updated buffer management and removed GLU dependency
5. `GLFWKeyMapper.java` - NEW: Maps LWJGL 2 key codes to GLFW
6. `Shader.java` - Updated buffer allocation

## Building and Testing

To build the project:
```bash
mvn clean package
```

To run the application:
```bash
java -jar target/JMugen-*.jar
```

## Potential Issues and Solutions

### Issue: "Failed to create GLFW window"
**Solution**: Ensure you have compatible graphics drivers and OpenGL support.

### Issue: "Native library not found"
**Solution**: Make sure the LWJGL natives for your platform are correctly included in the dependencies.

### Issue: Key inputs not working
**Solution**: Verify that GLFWKeyMapper constants are correctly mapped and callbacks are properly registered.

### Issue: Texture loading fails
**Solution**: Check that MemoryUtil buffers are properly freed after use to avoid memory leaks.

## Platform-Specific Notes

### Linux
- Requires X11 or Wayland support
- Make sure GLFW can access the display server

### macOS
- May require `-XstartOnFirstThread` JVM argument for GLFW to work correctly
- Updated in Makefile: `JAVA_OPTS+= -XstartOnFirstThread`

### Windows
- Should work out of the box with included native libraries

## Performance Considerations

LWJGL 3 is generally more efficient than LWJGL 2:
- Better memory management with explicit allocation/deallocation
- More modern OpenGL support
- Improved GLFW window management

However, be aware:
- Manual memory management with MemoryUtil requires careful tracking
- GLFW callbacks may have different threading behavior than LWJGL 2 polling

## Further Reading

- [LWJGL 3 Documentation](https://www.lwjgl.org/guide)
- [GLFW Documentation](https://www.glfw.org/documentation.html)
- [LWJGL 2 to 3 Migration Guide](https://github.com/LWJGL/lwjgl3-wiki/wiki/2.6.6-LWJGL3-migration)

## Support

For issues related to this migration, please refer to:
- LWJGL Forums: https://github.com/LWJGL/lwjgl3/discussions
- Project Issues: https://github.com/humbertodias/jmugen/issues
