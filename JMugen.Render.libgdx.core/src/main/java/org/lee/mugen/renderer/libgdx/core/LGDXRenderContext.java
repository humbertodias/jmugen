package org.lee.mugen.renderer.libgdx.core;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.lee.mugen.renderer.GameWindow;

/**
 * Platform-neutral LibGDX rendering context used by the shared drawer.
 */
public interface LGDXRenderContext {
    GameWindow getGameWindow();
    SpriteBatch getBatch();
    Matrix4 getWorldProjectionSnapshot();
    OrthographicCamera getCamera();
    Viewport getViewport();
    int getGameWidth();
    int getGameHeight();
}
