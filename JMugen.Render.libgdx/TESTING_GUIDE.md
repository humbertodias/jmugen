# Testing Guide for LibGDX Renderer

## Unit Testing

### Setup

Ensure you have the test dependencies in your pom.xml:

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>

<!-- LibGDX Test Fixtures -->
<dependency>
    <groupId>com.badlogicgames.gdx</groupId>
    <artifactId>gdx</artifactId>
    <version>1.12.0</version>
    <classifier>sources</classifier>
    <scope>test</scope>
</dependency>
```

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=LGDXMugenDrawerTest

# Run with detailed output
mvn test -DargLine="-Xmx1024m"
```

## Integration Testing

### Prerequisites

1. Build the project: `mvn clean install`
2. Ensure graphics drivers are up-to-date
3. For headless environments, use a virtual X server or offscreen rendering

### Test Scenarios

#### 1. Basic Window Creation
```java
@Test
public void testWindowCreation() {
    LGDXGameWindow window = new LGDXGameWindow();
    window.setTitle("Test");
    window.setResolution(640, 480);
    assertNotNull(window.getTimer());
    assertNotNull(window.getMouseStatus());
}
```

#### 2. Renderer Initialization
```java
@Test
public void testRendererInit() throws Exception {
    GraphicsWrapper.init();
    MugenDrawer drawer = GraphicsWrapper.getInstance();
    assertNotNull(drawer);
    GameWindow window = drawer.getInstanceOfGameWindow();
    assertNotNull(window);
}
```

#### 3. Input Handling
```java
@Test
public void testInputHandling() {
    LGDXGameWindow window = new LGDXGameWindow();
    window.addSpriteKeyProcessor(keyCode -> {
        System.out.println("Key pressed: " + keyCode);
    });
    
    GameWindow.MouseCtrl mouse = window.getMouseStatus();
    assertNotNull(mouse);
}
```

#### 4. Timer Functionality
```java
@Test
public void testTimer() {
    LGDXMugenTimer timer = new LGDXMugenTimer();
    long time1 = timer.getTime();
    
    // Simulate delay
    Thread.sleep(100);
    
    long time2 = timer.getTime();
    assertTrue(time2 >= time1);
}
```

#### 5. Drawing Operations
```java
@Test
public void testDrawing() throws Exception {
    GraphicsWrapper.init();
    LGDXMugenDrawer drawer = (LGDXMugenDrawer) GraphicsWrapper.getInstance();
    
    drawer.setColor(1.0f, 0.0f, 0.0f, 1.0f);
    assertEquals(1.0f, drawer.getAlpha(), 0.0f);
    
    drawer.setAlpha(0.5f);
    assertEquals(0.5f, drawer.getAlpha(), 0.0f);
}
```

## Performance Testing

### FPS Benchmark

```java
public class FPSBenchmark {
    public static void main(String[] args) throws Exception {
        // Create window
        LGDXGameWindow window = new LGDXGameWindow();
        window.setTitle("FPS Benchmark");
        
        // Run for 10 seconds and measure frames
        long startTime = System.currentTimeMillis();
        int frameCount = 0;
        
        while (System.currentTimeMillis() - startTime < 10000) {
            // Simulate rendering
            frameCount++;
        }
        
        double averageFPS = frameCount / 10.0;
        System.out.println("Average FPS: " + averageFPS);
    }
}
```

### Memory Usage

```bash
# Monitor memory during game execution
jstat -gc -h10 <process_id> 1000

# Profile with async-profiler
profiler.sh -d 60 -f profile.html <process_id>
```

## Compatibility Testing

### Platform Testing Matrix

```
Platform       | Java  | OpenGL | Status
---------------|-------|--------|--------
Windows 10/11  | 8-21  | 3.0+   | ✓ Supported
macOS 10.13+   | 8-21  | 4.1    | ✓ Supported
Ubuntu 20.04   | 8-21  | 3.0+   | ✓ Supported
Debian 11      | 8-21  | 3.0+   | ✓ Supported
```

### JVM Version Testing

```bash
# Test with different Java versions
JAVA_HOME=/path/to/java8 mvn test
JAVA_HOME=/path/to/java11 mvn test
JAVA_HOME=/path/to/java17 mvn test
JAVA_HOME=/path/to/java21 mvn test
```

## Visual Testing

### Screenshot Verification

```java
public class VisualTest {
    
    public static void captureFrame(SpriteBatch batch, String filename) {
        // Capture current frame to file
        Pixmap pixmap = Pixmap.createFromFrameBuffer(0, 0, 640, 480);
        PixmapIO.writePNG(new FileHandle(filename), pixmap);
        pixmap.dispose();
    }
}
```

### Comparison Tests

Compare rendered output between:
- LibGDX backend vs LWJGL backend
- LibGDX backend vs JOGL backend
- Different quality settings

## Debugging

### Enable Debug Logging

```properties
# In application.properties
logging.level=DEBUG
debug.rendering=true
debug.input=true
```

### Visual Debugging

```java
// Draw debug information
drawer.setColor(1, 1, 1, 1);
drawer.drawRect(0, 0, 640, 480);
drawer.drawLine(0, 0, 640, 480);
drawer.drawLine(640, 0, 0, 480);
```

## Common Issues & Solutions

### Issue: "Unable to initialize GLFW"
**Solution**: Check graphics driver and OpenGL support
```bash
glxinfo | grep -i opengl  # Linux
system_profiler SPDisplaysDataType  # macOS
```

### Issue: Slow Performance
**Solution**: Check FPS, batch size, and texture usage
```bash
# Monitor GPU usage
nvidia-smi -l 1  # NVIDIA GPUs
```

### Issue: Input Not Responding
**Solution**: Verify window focus and input processor registration

## Continuous Integration

### GitHub Actions Example

```yaml
name: Test LibGDX Renderer

on: [push, pull_request]

jobs:
  test:
    runs-on: ${{ matrix.os }}
    strategy:
      matrix:
        os: [ubuntu-latest, macos-latest, windows-latest]
        java: [8, 11, 17, 21]
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: ${{ matrix.java }}
      - name: Run tests
        run: mvn clean test
```

## Reporting Issues

When reporting test failures, include:

1. Platform and Java version: `java -version`
2. Graphics hardware: `glxinfo | grep -i renderer`
3. Full error stack trace
4. Steps to reproduce
5. Expected vs actual behavior

## Test Coverage

Target: **80%+ code coverage**

```bash
# Generate coverage report
mvn clean test jacoco:report
# View report at: target/site/jacoco/index.html
```

## Performance Targets

- **FPS**: 60+ on modern hardware
- **Memory**: < 500MB for basic scene
- **Load Time**: < 2 seconds for initialization
- **Frame Time**: < 16ms per frame at 60 FPS
