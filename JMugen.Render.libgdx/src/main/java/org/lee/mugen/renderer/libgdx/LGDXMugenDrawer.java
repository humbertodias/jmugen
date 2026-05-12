package org.lee.mugen.renderer.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import org.lee.mugen.imageIO.PCXLoader;
import org.lee.mugen.imageIO.PCXPalette;
import org.lee.mugen.imageIO.RawPCXImage;
import org.lee.mugen.imageIO.PCXLoader.PCXHeader;
import org.lee.mugen.object.RawImage;
import org.lee.mugen.object.Rectangle;
import org.lee.mugen.renderer.*;
import org.lee.mugen.util.Logger;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * LibGDX implementation of MugenDrawer
 * Provides 2D sprite rendering with support for transformations, blending, and effects
 */
public class LGDXMugenDrawer extends MugenDrawer {
    
    private LGDXGameWindow gameWindow;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private float currentAlpha = 1.0f;
    private Color currentColor = new Color(1, 1, 1, 1);
    private RGB rgba = new RGB();
    
    private List<ImageContainer> imagesToProcess = new ArrayList<>();
    private AtomicInteger imageProcessingCounter = new AtomicInteger(0);
    
    private Matrix4 projectionMatrix;
    private Matrix4 viewMatrix;
    
    // Image cache
    private Map<Object, ImageContainer> imageCache = new HashMap<>();
    
    public LGDXMugenDrawer() {
        if (!LibGDXRendererFactory.isInitialized()) {
            try {
                LibGDXRendererFactory.initialize();
            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize LibGDX renderer factory", e);
            }
        }
        this.gameWindow = LibGDXRendererFactory.getGameWindow();
    }

    public LGDXMugenDrawer(LGDXGameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    private void ensureRendererResources() {
        if (projectionMatrix == null) {
            if (gameWindow != null) {
                projectionMatrix = new Matrix4().setToOrtho2D(0, 0,
                    gameWindow.getGameWidth(), gameWindow.getGameHeight());
                viewMatrix = new Matrix4().translate(0, gameWindow.getGameHeight(), 0)
                    .scale(1, -1, 1);
            } else {
                projectionMatrix = new Matrix4();
                viewMatrix = new Matrix4();
            }
        }
        if (shapeRenderer == null) {
            try {
                shapeRenderer = new ShapeRenderer();
            } catch (Exception e) {
                // Delay instantiation until GL context is available.
                shapeRenderer = null;
            }
        }
    }

    private SpriteBatch getBatch() {
        if (batch == null && gameWindow != null) {
            batch = gameWindow.getBatch();
        }
        return batch;
    }

    @Override
    public GameWindow getInstanceOfGameWindow() {
        return gameWindow;
    }
    
    @Override
    public void scale(float x, float y) {
        SpriteBatch batch = getBatch();
        if (batch == null) {
            return;
        }
        batch.setTransformMatrix(batch.getTransformMatrix().scale(x, y, 1));
    }
    
    @Override
    public void draw(DrawProperties drawProperties) {
        if (drawProperties == null) {
            return;
        }
        
        ImageContainer imageContainer = drawProperties.getIc();
        if (imageContainer == null || imageContainer.getImg() == null) {
            return;
        }
        
        Texture texture = (Texture) imageContainer.getImg();
        if (texture == null) {
            return;
        }
        
        SpriteBatch batch = getBatch();
        if (batch == null) {
            return;
        }
        batch.setColor(currentColor);
        
        float x = Math.min(drawProperties.getXLeftDst(), drawProperties.getXRightDst());
        float y = Math.min(drawProperties.getYTopDst(), drawProperties.getYBottomDst());
        float width = Math.abs(drawProperties.getXRightDst() - drawProperties.getXLeftDst());
        float height = Math.abs(drawProperties.getYBottomDst() - drawProperties.getYTopDst());
        
        int srcX = Math.min((int) drawProperties.getXLeftSrc(), (int) drawProperties.getXRightSrc());
        int srcY = Math.min((int) drawProperties.getYTopSrc(), (int) drawProperties.getYBottomSrc());
        int srcWidth = Math.abs((int) (drawProperties.getXRightSrc() - drawProperties.getXLeftSrc()));
        int srcHeight = Math.abs((int) (drawProperties.getYBottomSrc() - drawProperties.getYTopSrc()));
        
        if (srcWidth == 0 || srcHeight == 0) {
            return;
        }

        TextureRegion region = new TextureRegion(texture, srcX, srcY, srcWidth, srcHeight);
        // Flip Y by default because LibGDX texture origin is bottom-left while image data is top-left
        region.flip(false, true);
        if (drawProperties.isFlipH()) {
            region.flip(true, false);
        }
        if (drawProperties.isFlipV()) {
            region.flip(false, true);
        }

        Sprite sprite = new Sprite(region);
        sprite.setPosition(x, y);
        sprite.setSize(width, height);
        sprite.setOrigin(0, 0);
        sprite.setAlpha(currentAlpha * drawProperties.getAlpha());

        if (drawProperties.getAngleDrawProperties() != null) {
            AngleDrawProperties angleProps = drawProperties.getAngleDrawProperties();
            sprite.setOrigin(angleProps.getXAnchor(), angleProps.getYAnchor());
            sprite.setRotation(angleProps.getAngleset());
            sprite.setScale(angleProps.getXScale(), angleProps.getYScale());
        }
        
        sprite.draw(batch);
    }
    
    private boolean pauseBatchIfNecessary() {
        SpriteBatch batch = getBatch();
        if (batch == null) {
            return false;
        }
        if (batch.isDrawing()) {
            batch.end();
            return true;
        }
        return false;
    }
    
    private void resumeBatchIfNecessary(boolean wasDrawing) {
        SpriteBatch batch = getBatch();
        if (batch != null && wasDrawing) {
            batch.begin();
        }
    }
    
    @Override
    public void drawRect(float x1, float y1, float width, float height) {
        SpriteBatch batch = getBatch();
        if (batch == null) {
            return;
        }
        boolean wasDrawing = pauseBatchIfNecessary();
        ensureRendererResources();
        if (shapeRenderer == null) {
            resumeBatchIfNecessary(wasDrawing);
            return;
        }
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(currentColor);
        shapeRenderer.rect(x1, y1, width, height);
        shapeRenderer.end();
        resumeBatchIfNecessary(wasDrawing);
    }
    
    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        SpriteBatch batch = getBatch();
        if (batch == null) {
            return;
        }
        boolean wasDrawing = pauseBatchIfNecessary();
        ensureRendererResources();
        if (shapeRenderer == null) {
            resumeBatchIfNecessary(wasDrawing);
            return;
        }
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(currentColor);
        shapeRenderer.line(x1, y1, x2, y2);
        shapeRenderer.end();
        resumeBatchIfNecessary(wasDrawing);
    }
    
    @Override
    public void fillRect(float x, float y, float width, float height) {
        SpriteBatch batch = getBatch();
        if (batch == null) {
            return;
        }
        boolean wasDrawing = pauseBatchIfNecessary();
        ensureRendererResources();
        if (shapeRenderer == null) {
            resumeBatchIfNecessary(wasDrawing);
            return;
        }
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(currentColor);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
        resumeBatchIfNecessary(wasDrawing);
    }
    
    @Override
    public void setColor(float r, float g, float b) {
        setColor(r, g, b, currentAlpha);
    }
    
    @Override
    public void setColor(float r, float g, float b, float a) {
        currentColor.set(r, g, b, a);
        currentAlpha = a;
    }
    
    @Override
    public void setAlpha(float a) {
        currentAlpha = a;
        currentColor.a = a;
    }
    
    @Override
    public float getAlpha() {
        return currentAlpha;
    }
    
    @Override
    public void setClip(Rectangle r) {
        if (r != null) {
            int x = r.getX1();
            int y = r.getY1();
            int width = r.getX2() - r.getX1();
            int height = r.getY2() - r.getY1();
            
            // Note: LibGDX scissor test (clipping) can be used here if needed
            // Gdx.gl.glScissor(x, gameWindow.getGameHeight() - y - height, width, height);
            // Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
        } else {
            // Disable clipping
            // Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
        }
    }
    
    @Override
    public ImageContainer getImageContainer(Object imageData) {
        return getImageContainer(imageData, 0);
    }
    
    @Override
    public ImageContainer getImageContainer(Object imageData, int colors) {
        if (imageData == null) {
            return null;
        }
        
        // Check cache first
        if (imageCache.containsKey(imageData)) {
            return imageCache.get(imageData);
        }
        
        try {
            if (imageData instanceof RawPCXImage) {
                RawPCXImage pcxImage = (RawPCXImage) imageData;
                byte[] data = pcxImage.getData();
                PCXHeader header = new PCXHeader(data);
                
                int width = header.getWidth();
                int height = header.getHeight();
                
                // Convert PCX data to Pixmap using the requested color bank offset
                BufferedImage bufferedImage = PCXLoader.loadImage(
                        BufferedImage.TYPE_INT_ARGB,
                        new ByteArrayInputStream(data),
                        new PCXPalette(),
                        false,
                        false,
                        false,
                        false,
                        colors);
                Pixmap pixmap = gdxPixmapFromBufferedImage(bufferedImage);
                Texture texture = new Texture(pixmap);
                pixmap.dispose();
                
                LGDXImageContainer container = new LGDXImageContainer(texture, width, height);
                imageCache.put(imageData, container);
                return container;
            } else if (imageData instanceof RawImage) {
                RawImage rawImage = (RawImage) imageData;
                int width = rawImage.getWidth();
                int height = rawImage.getHeight();
                Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
                int[] pixels = rawImage.getData();
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        int argb = pixels[y * width + x];
                        pixmap.drawPixel(x, y, argbToRgba(argb));
                    }
                }
                Texture texture = new Texture(pixmap);
                pixmap.dispose();
                LGDXImageContainer container = new LGDXImageContainer(texture, width, height);
                imageCache.put(imageData, container);
                return container;
            }
        } catch (IOException e) {
            Logger.error("Error loading image container", e);
        }
        
        return null;
    }
    
    /**
     * Convert BufferedImage to LibGDX Pixmap
     */
    private Pixmap gdxPixmapFromBufferedImage(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = bufferedImage.getRGB(x, y);
                pixmap.drawPixel(x, y, argbToRgba(argb));
            }
        }
        
        return pixmap;
    }

    private int argbToRgba(int argb) {
        int a = (argb >> 24) & 0xff;
        int r = (argb >> 16) & 0xff;
        int g = (argb >> 8) & 0xff;
        int b = argb & 0xff;
        return (r << 24) | (g << 16) | (b << 8) | a;
    }
    
    /**
     * Internal ImageContainer implementation for LibGDX textures
     */
    private static class LGDXImageContainer extends ImageContainer {
        private Texture texture;
        
        public LGDXImageContainer(Texture texture, int width, int height) {
            super(texture, width, height);
            this.texture = texture;
        }
        
        @Override
        public void free() {
            if (texture != null) {
                texture.dispose();
            }
        }
        
        @Override
        public void reload(ImageContainer img) {
            if (img instanceof LGDXImageContainer) {
                LGDXImageContainer other = (LGDXImageContainer) img;
                this.img = other.img;
                this.texture = other.texture;
                this.width = other.width;
                this.height = other.height;
            } else {
                super.reload(img);
            }
        }
    }
    
    private void addToImageToProcess(ImageContainer imageContainer) {
        imagesToProcess.add(imageContainer);
        imageProcessingCounter.incrementAndGet();
    }
    
    public void dispose() {
        if (shapeRenderer != null) {
            shapeRenderer.dispose();
            shapeRenderer = null;
        }
        for (ImageContainer container : imageCache.values()) {
            container.free();
        }
        imageCache.clear();
    }
}
