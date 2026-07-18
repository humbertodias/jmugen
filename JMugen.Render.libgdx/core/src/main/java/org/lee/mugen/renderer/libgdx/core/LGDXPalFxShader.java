package org.lee.mugen.renderer.libgdx.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.lee.mugen.renderer.RGB;

/**
 * Optional platform shader hook. Web builds can leave it disabled while desktop
 * keeps the existing PalFx shader implementation.
 */
public interface LGDXPalFxShader {
    void apply(SpriteBatch batch);
    void reset(SpriteBatch batch);
    boolean isCompiled();
    void setUniforms(RGB addColor, RGB mulColor, RGB amplColor, float alphaVal);
}
