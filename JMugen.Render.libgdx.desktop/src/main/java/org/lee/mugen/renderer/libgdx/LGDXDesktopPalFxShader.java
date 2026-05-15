package org.lee.mugen.renderer.libgdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.renderer.libgdx.core.LGDXPalFxShader;
import org.lee.mugen.renderer.libgdx.core.shader.PalFxShader;

/**
 * Desktop adapter around the existing shared PalFx shader implementation.
 */
class LGDXDesktopPalFxShader implements LGDXPalFxShader {

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
