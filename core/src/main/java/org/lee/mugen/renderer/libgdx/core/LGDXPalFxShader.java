package org.lee.mugen.renderer.libgdx.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.lee.mugen.renderer.RGB;

/**
 * Optional platform shader hook. Desktop, Android, and TeaVM web use {@link org.lee.mugen.renderer.libgdx.core.shader.PalFxShader}.
 */
public interface LGDXPalFxShader {
    void apply(SpriteBatch batch);
    void reset(SpriteBatch batch);
    boolean isCompiled();
    void setUniforms(RGB addColor, RGB mulColor, RGB amplColor, float alphaVal);
}
