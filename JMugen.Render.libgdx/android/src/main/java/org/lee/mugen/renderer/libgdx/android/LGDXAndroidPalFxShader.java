package org.lee.mugen.renderer.libgdx.android;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.renderer.libgdx.core.LGDXPalFxShader;
import org.lee.mugen.renderer.libgdx.core.shader.PalFxShader;

/**
 * Android adapter around the shared PalFx shader (same approach as desktop).
 */
final class LGDXAndroidPalFxShader implements LGDXPalFxShader {

    private PalFxShader shader;

    private PalFxShader shader() {
        if (shader == null) {
            shader = new PalFxShader();
        }
        return shader;
    }

    @Override
    public void apply(SpriteBatch batch) {
        shader().apply(batch);
    }

    @Override
    public void reset(SpriteBatch batch) {
        shader().reset(batch);
    }

    @Override
    public boolean isCompiled() {
        return shader().isCompiled();
    }

    @Override
    public void setUniforms(RGB addColor, RGB mulColor, RGB amplColor, float alphaVal) {
        shader().setUniforms(addColor, mulColor, amplColor, alphaVal);
    }
}
