package org.lee.mugen.renderer.libgdx.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.HdpiUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import org.lee.mugen.object.RawImage;
import org.lee.mugen.object.Rectangle;
import org.lee.mugen.renderer.*;

/**
 * LibGDX implementation of MugenDrawer
 * Provides 2D sprite rendering with support for transformations, blending, and effects
 */
public class LGDXMugenDrawer extends MugenDrawer {

    private LGDXRenderContext renderContext;
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

    /** Projects Mugen world corners to logical screen pixels using the same matrix as {@link SpriteBatch} at frame start. */
    private final Vector3 clipProjTmp = new Vector3();
    private final com.badlogic.gdx.math.Rectangle tmpClipScreen =
        new com.badlogic.gdx.math.Rectangle();
    private boolean clipActive;
    private float clipPivotX;
    private float clipPivotY;
    /** When true with an active clip, uniform shrink/zoom scales about {@link #clipPivotX}/{@link #clipPivotY}. */
    private boolean uniformScaleAboutClipCorner;

    public LGDXMugenDrawer(LGDXRenderContext renderContext) {
        this.renderContext = renderContext;
    }

    private void ensureRendererResources() {
        if (projectionMatrix == null) {
            if (renderContext != null) {
                projectionMatrix = new Matrix4().setToOrtho2D(
                    0,
                    0,
                    renderContext.getGameWidth(),
                    renderContext.getGameHeight()
                );
                viewMatrix = new Matrix4()
                    .translate(0, renderContext.getGameHeight(), 0)
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
        if (batch == null && renderContext != null) {
            batch = renderContext.getBatch();
        }
        return batch;
    }

    @Override
    public GameWindow getInstanceOfGameWindow() {
        return renderContext == null ? null : renderContext.getGameWindow();
    }

    @Override
    public void scale(float x, float y) {
        SpriteBatch batch = getBatch();
        if (batch == null) {
            return;
        }
        Matrix4 m = batch.getTransformMatrix();
        if (
            clipActive &&
            uniformScaleAboutClipCorner &&
            shouldScaleAboutClipPivot(x, y)
        ) {
            m.translate(clipPivotX, clipPivotY, 0);
            m.scale(x, y, 1);
            m.translate(-clipPivotX, -clipPivotY, 0);
        } else {
            m.scale(x, y, 1);
        }
    }

    @Override
    public void translate(float x, float y) {
        SpriteBatch batch = getBatch();
        if (batch != null) {
            batch.getTransformMatrix().translate(x, y, 0);
        }
    }

    @Override
    public void setUniformScaleAboutClipCorner(boolean enabled) {
        uniformScaleAboutClipCorner = enabled;
    }

    /**
     * Uniform shrink/zoom about the clip corner (only when {@link #uniformScaleAboutClipCorner} is on),
     * matching LWJGL's clip+scale behaviour for the select-screen stage preview. Clipped menu fonts keep
     * normal scaling about the batch origin unless that flag is set.
     */
    private static boolean shouldScaleAboutClipPivot(float x, float y) {
        if (Math.abs(x - 1f) < 1e-4f && Math.abs(y - 1f) < 1e-4f) {
            return false;
        }
        return (x < 1f && y < 1f) || (x > 1f && y > 1f);
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

        Texture texture = resolveTextureForDraw(imageContainer);
        if (texture == null) {
            return;
        }

        SpriteBatch batch = getBatch();
        if (batch == null) {
            return;
        }
        batch.setColor(currentColor);

        Trans trans = drawProperties.getTrans();
        boolean restoreBlend = false;
        int prevSrc = GL20.GL_SRC_ALPHA;
        int prevDst = GL20.GL_ONE_MINUS_SRC_ALPHA;
        if (trans == Trans.SUB) {
            batch.flush();
            prevSrc = batch.getBlendSrcFunc();
            prevDst = batch.getBlendDstFunc();
            //            batch.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_SRC_ALPHA);
            batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
            restoreBlend = true;
        } else if (
            trans == Trans.ADD || trans == Trans.ADDALPHA || trans == Trans.ADD1
        ) {
            batch.flush();
            prevSrc = batch.getBlendSrcFunc();
            prevDst = batch.getBlendDstFunc();
            //            batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_DST_ALPHA);
            batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
            restoreBlend = true;
        }

        float x = Math.min(
            drawProperties.getXLeftDst(),
            drawProperties.getXRightDst()
        );
        float y = Math.min(
            drawProperties.getYTopDst(),
            drawProperties.getYBottomDst()
        );
        float width = Math.abs(
            drawProperties.getXRightDst() - drawProperties.getXLeftDst()
        );
        float height = Math.abs(
            drawProperties.getYBottomDst() - drawProperties.getYTopDst()
        );
        float sx = drawProperties.getXScaleFactor();
        float sy = drawProperties.getYScaleFactor();

        int srcX = Math.min(
            (int) drawProperties.getXLeftSrc(),
            (int) drawProperties.getXRightSrc()
        );
        int srcY = Math.min(
            (int) drawProperties.getYTopSrc(),
            (int) drawProperties.getYBottomSrc()
        );
        int srcWidth = Math.abs(
            (int) (drawProperties.getXRightSrc() - drawProperties.getXLeftSrc())
        );
        int srcHeight = Math.abs(
            (int) (drawProperties.getYBottomSrc() - drawProperties.getYTopSrc())
        );

        if (srcWidth == 0 || srcHeight == 0) {
            return;
        }

        int tw = texture.getWidth();
        int th = texture.getHeight();
        if (srcX >= tw || srcY >= th) {
            return;
        }
        if (srcX + srcWidth > tw) {
            srcWidth = tw - srcX;
        }
        if (srcY + srcHeight > th) {
            srcHeight = th - srcY;
        }
        if (srcWidth <= 0 || srcHeight <= 0) {
            return;
        }

        TextureRegion region = new TextureRegion(
            texture,
            srcX,
            srcY,
            srcWidth,
            srcHeight
        );
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

        // Match LMugenDrawer#drawImage: destination quad scales by DrawProperties factors
        // and by AngleDrawProperties when present.
        if (drawProperties.getAngleDrawProperties() != null) {
            AngleDrawProperties angleProps =
                drawProperties.getAngleDrawProperties();
            sprite.setOrigin(angleProps.getXAnchor(), angleProps.getYAnchor());
            sprite.setRotation(angleProps.getAngleset());
            sprite.setScale(
                angleProps.getXScale() * sx,
                angleProps.getYScale() * sy
            );
        } else {
            sprite.setScale(sx, sy);
        }

        // Palette effects (PalFx) - use shader to match LWJGL behavior
        boolean usedPalFx = false;
        org.lee.mugen.renderer.PalFxSub palfx = drawProperties.getPalfx();
        LGDXPalFxShader ps = null;
        if (palfx != null && renderContext != null) {
            ps = renderContext.getPalFxShader();
            if (ps != null && ps.isCompiled()) {
                float phase = 0f;
                if (
                    palfx.getSinadd() != null &&
                    palfx.getSinadd().getPeriod() != 0
                ) {
                    phase = (float) ((Math.PI * palfx.getTimeActivate()) /
                        palfx.getSinadd().getPeriod());
                }
                int rPlus = 0;
                int gPlus = 0;
                int bPlus = 0;
                if (palfx.getSinadd() != null) {
                    rPlus = (int) (palfx.getSinadd().getAmpl_r() *
                        Math.sin(2 * phase));
                    gPlus = (int) (palfx.getSinadd().getAmpl_g() *
                        Math.sin(2 * phase));
                    bPlus = (int) (palfx.getSinadd().getAmpl_b() *
                        Math.sin(2 * phase));
                }
                org.lee.mugen.renderer.RGB ampl =
                    new org.lee.mugen.renderer.RGB(rPlus, gPlus, bPlus, 255f);
                org.lee.mugen.renderer.RGB bits =
                    new org.lee.mugen.renderer.RGB(
                        1f / 255f,
                        1f / 255f,
                        1f / 255f,
                        1f / 255f
                    );
                org.lee.mugen.renderer.RGB addNorm = palfx.getAdd().mul(bits);
                org.lee.mugen.renderer.RGB mulNorm = palfx.getMul().mul(bits);
                org.lee.mugen.renderer.RGB amplNorm = ampl.mul(bits);

                boolean wasDrawing = pauseBatchIfNecessary();
                ps.apply(batch);
                resumeBatchIfNecessary(wasDrawing);
                ps.setUniforms(
                    addNorm,
                    mulNorm,
                    amplNorm,
                    drawProperties.getAlpha()
                );
                usedPalFx = true;
            }
        }

        sprite.draw(batch);

        if (usedPalFx && ps != null) {
            batch.flush();
            ps.reset(batch);
        }

        if (restoreBlend) {
            batch.flush();
            batch.setBlendFunction(prevSrc, prevDst);
        }
    }

    /**
     * Match {@link org.lee.mugen.renderer.lwjgl.LMugenDrawer#toTexture}: {@link ImageContainer} may hold
     * a {@link Texture} or another format handled by {@link #getImageContainer(Object, int)}.
     */
    private Texture resolveTextureForDraw(ImageContainer imageContainer) {
        Object imgObj = imageContainer.getImg();
        if (imgObj instanceof Texture) {
            return (Texture) imgObj;
        }
        ImageContainer converted = getImageContainer(imgObj, 0);
        if (converted == null || converted.getImg() == null) {
            return null;
        }
        if (converted.getImg() instanceof Texture) {
            return (Texture) converted.getImg();
        }
        return null;
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
        float rw = width;
        float rh = height;
        if (
            renderContext != null &&
            renderContext.getViewport() != null &&
            x <= 0 &&
            y <= 0
        ) {
            Viewport vp = renderContext.getViewport();
            float vw = vp.getWorldWidth();
            float vh = vp.getWorldHeight();
            if (rw >= vw && rh >= vh) {
                rw = vw;
                rh = vh;
            }
        }
        shapeRenderer.rect(x, y, rw, rh);
        shapeRenderer.end();
        resumeBatchIfNecessary(wasDrawing);
    }

    @Override
    public void setColor(float r, float g, float b) {
        setColor(r, g, b, currentAlpha);
    }

    @Override
    public void setColor(float r, float g, float b, float a) {
        // Accept both 0..1 and 0..255 color ranges. If any component appears > 1,
        // assume the caller used 0..255 and normalize to 0..1.
        float nr = r > 1f ? r / 255f : r;
        float ng = g > 1f ? g / 255f : g;
        float nb = b > 1f ? b / 255f : b;
        float na = a > 1f ? a / 255f : a;
        currentColor.set(nr, ng, nb, na);
        currentAlpha = na;
    }

    @Override
    public void setAlpha(float a) {
        float na = a > 1f ? a / 255f : a;
        currentAlpha = na;
        currentColor.a = na;
    }

    @Override
    public float getAlpha() {
        return currentAlpha;
    }

    /**
     * Map a Mugen-world axis-aligned rectangle to OpenGL window pixels (bottom-left origin, y up), matching
     * {@link com.badlogic.gdx.graphics.Camera#project(Vector3, float, float, float, float)} with the FitViewport
     * screen bounds and the frame-start {@link LGDXRenderContext#getWorldProjectionSnapshot()}.
     */
    private void computeClipScreenBounds(
        float minWx,
        float minWy,
        float maxWx,
        float maxWy
    ) {
        Viewport vp = renderContext.getViewport();
        Matrix4 base = renderContext.getWorldProjectionSnapshot();
        float vpx = vp.getScreenX();
        float vpy = vp.getScreenY();
        float vpw = vp.getScreenWidth();
        float vph = vp.getScreenHeight();

        float minPx = Float.POSITIVE_INFINITY;
        float maxPx = Float.NEGATIVE_INFINITY;
        float minPy = Float.POSITIVE_INFINITY;
        float maxPy = Float.NEGATIVE_INFINITY;
        float[][] corners = {
            { minWx, minWy },
            { maxWx, minWy },
            { maxWx, maxWy },
            { minWx, maxWy },
        };
        for (float[] p : corners) {
            clipProjTmp.set(p[0], p[1], 0);
            clipProjTmp.prj(base);
            float px = (vpw * (clipProjTmp.x + 1f)) / 2f + vpx;
            float py = (vph * (clipProjTmp.y + 1f)) / 2f + vpy;
            minPx = Math.min(minPx, px);
            maxPx = Math.max(maxPx, px);
            minPy = Math.min(minPy, py);
            maxPy = Math.max(maxPy, py);
        }
        tmpClipScreen.x = minPx;
        tmpClipScreen.y = minPy;
        tmpClipScreen.width = maxPx - minPx;
        tmpClipScreen.height = maxPy - minPy;
    }

    /** Restore FitViewport glViewport + full 320×240 ortho (matches frame start after {@link LGDXRenderContext render}). */
    private void restoreDefaultViewportAndCamera(SpriteBatch batch) {
        Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
        if (
            renderContext == null ||
            renderContext.getViewport() == null ||
            renderContext.getCamera() == null
        ) {
            clipActive = false;
            clipPivotX = 0f;
            clipPivotY = 0f;
            return;
        }
        Viewport vp = renderContext.getViewport();
        OrthographicCamera cam = renderContext.getCamera();
        vp.apply(false);
        cam.setToOrtho(true, 320, 240);
        cam.update();
        if (batch != null) {
            batch.setProjectionMatrix(cam.combined);
        }
        clipActive = false;
        clipPivotX = 0f;
        clipPivotY = 0f;
    }

    @Override
    public void setClip(Rectangle r) {
        SpriteBatch batch = getBatch();
        boolean wasDrawing = pauseBatchIfNecessary();
        try {
            if (r == null) {
                if (clipActive) {
                    restoreDefaultViewportAndCamera(batch);
                }
                clipPivotX = 0f;
                clipPivotY = 0f;
                uniformScaleAboutClipCorner = false;
                return;
            }
            uniformScaleAboutClipCorner = false;
            if (
                renderContext == null ||
                renderContext.getViewport() == null ||
                renderContext.getCamera() == null
            ) {
                return;
            }

            float wx1 = r.getX1();
            float wy1 = r.getY1();
            float wx2 = r.getX2();
            float wy2 = r.getY2();
            float minWx = Math.min(wx1, wx2);
            float maxWx = Math.max(wx1, wx2);
            float minWy = Math.min(wy1, wy2);
            float maxWy = Math.max(wy1, wy2);
            float clipW = maxWx - minWx;
            float clipH = maxWy - minWy;
            if (clipW < 1e-3f || clipH < 1e-3f) {
                if (clipActive) {
                    restoreDefaultViewportAndCamera(batch);
                }
                return;
            }

            computeClipScreenBounds(minWx, minWy, maxWx, maxWy);
            int sx = Math.round(tmpClipScreen.x);
            int sy = Math.round(tmpClipScreen.y);
            int sw = Math.round(tmpClipScreen.width);
            int sh = Math.round(tmpClipScreen.height);
            if (sw < 1 || sh < 1) {
                if (clipActive) {
                    restoreDefaultViewportAndCamera(batch);
                }
                return;
            }

            OrthographicCamera cam = renderContext.getCamera();
            HdpiUtils.glViewport(sx, sy, sw, sh);
            cam.setToOrtho(true, clipW, clipH);
            cam.update();
            if (batch != null) {
                batch.setProjectionMatrix(cam.combined);
            }
            clipActive = true;
            clipPivotX = minWx;
            clipPivotY = minWy;
        } finally {
            resumeBatchIfNecessary(wasDrawing);
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

        if (imageData instanceof Texture) {
            Texture texture = (Texture) imageData;
            LGDXImageContainer container = new LGDXImageContainer(
                texture,
                texture.getWidth(),
                texture.getHeight()
            );
            imageCache.put(imageData, container);
            return container;
        }

        if (imageData instanceof RawImage) {
            RawImage rawImage = (RawImage) imageData;
            int width = rawImage.getWidth();
            int height = rawImage.getHeight();
            Pixmap pixmap = new Pixmap(
                width,
                height,
                Pixmap.Format.RGBA8888
            );
            int[] pixels = rawImage.getData();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int argb = pixels[y * width + x];
                    pixmap.drawPixel(x, y, argbToRgba(argb));
                }
            }
            Texture texture = new Texture(pixmap);
            pixmap.dispose();
            LGDXImageContainer container = new LGDXImageContainer(
                texture,
                width,
                height
            );
            imageCache.put(imageData, container);
            return container;
        }

        if (renderContext != null && renderContext.getImageLoader() != null) {
            ImageContainer container = renderContext
                .getImageLoader()
                .getImageContainer(imageData, colors);
            if (container != null) {
                imageCache.put(imageData, container);
                return container;
            }
        }

        return null;
    }

    public static int argbToRgba(int argb) {
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
