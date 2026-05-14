package org.lee.mugen.renderer.libgdx.shader;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.util.Logger;

/**
 * Palette Effects Shader for LibGDX
 * Implements color manipulation and palette effects
 * Compatible with LWJGL's PalFx shader (uniforms: add, mul, ampl, alpha)
 */
public class PalFxShader extends GDXShader {

    // Vertex shader - standard 2D projection
    private static final String VERTEX_SHADER =
        "#ifdef GL_ES\n" +
        "precision mediump float;\n" +
        "#endif\n" +
        "\n" +
        "attribute vec4 " +
        ShaderProgram.POSITION_ATTRIBUTE +
        ";\n" +
        "attribute vec4 " +
        ShaderProgram.COLOR_ATTRIBUTE +
        ";\n" +
        "attribute vec2 " +
        ShaderProgram.TEXCOORD_ATTRIBUTE +
        "0;\n" +
        "\n" +
        "uniform mat4 u_projTrans;\n" +
        "\n" +
        "varying vec4 v_color;\n" +
        "varying vec2 v_texCoords;\n" +
        "\n" +
        "void main() {\n" +
        "   v_color = " +
        ShaderProgram.COLOR_ATTRIBUTE +
        ";\n" +
        "   v_texCoords = " +
        ShaderProgram.TEXCOORD_ATTRIBUTE +
        "0;\n" +
        "   gl_Position =  u_projTrans * " +
        ShaderProgram.POSITION_ATTRIBUTE +
        ";\n" +
        "}\n";

    // Fragment shader - emulate LWJGL palfx behavior
    private static final String FRAGMENT_SHADER =
        "#ifdef GL_ES\n" +
        "precision mediump float;\n" +
        "#endif\n" +
        "\n" +
        "uniform sampler2D u_texture;\n" +
        "uniform vec4 add;\n" +
        "uniform vec4 mul;\n" +
        "uniform vec4 ampl;\n" +
        "uniform float alpha;\n" +
        "\n" +
        "varying vec4 v_color;\n" +
        "varying vec2 v_texCoords;\n" +
        "\n" +
        "void main() {\n" +
        "   vec4 sprColor = texture2D(u_texture, v_texCoords);\n" +
        "   vec3 adjustedRGB = (sprColor.rgb + add.rgb + ampl.rgb) * mul.rgb;\n" +
        "   adjustedRGB = clamp(adjustedRGB, 0.0, 1.0);\n" +
        "   float finalAlpha = sprColor.a * alpha;\n" +
        "   vec4 outColor = vec4(adjustedRGB, finalAlpha) * v_color;\n" +
        "   gl_FragColor = outColor;\n" +
        "}\n";

    public PalFxShader() {
        compile();
    }

    @Override
    public void compile() {
        try {
            shaderProgram = new ShaderProgram(VERTEX_SHADER, FRAGMENT_SHADER);
            if (!shaderProgram.isCompiled()) {
                Logger.error(
                    "Failed to compile PalFxShader: " + shaderProgram.getLog()
                );
                isCompiled = false;
            } else {
                isCompiled = true;
                Logger.log("PalFxShader compiled successfully");
            }
        } catch (Exception e) {
            Logger.error("Error creating PalFxShader", e);
            isCompiled = false;
        }
    }

    /**
     * Set uniforms and prepare shader. Must call apply(batch) before drawing.
     * Values are expected in 0..1 range for RGB components.
     */
    public void setUniforms(
        RGB addColor,
        RGB mulColor,
        RGB amplColor,
        float alphaVal
    ) {
        if (!isCompiled()) return;
        try {
            shaderProgram.setUniformf(
                "add",
                addColor.getR(),
                addColor.getG(),
                addColor.getB(),
                addColor.getA()
            );
            shaderProgram.setUniformf(
                "mul",
                mulColor.getR(),
                mulColor.getG(),
                mulColor.getB(),
                mulColor.getA()
            );
            shaderProgram.setUniformf(
                "ampl",
                amplColor.getR(),
                amplColor.getG(),
                amplColor.getB(),
                amplColor.getA()
            );
            shaderProgram.setUniformf("alpha", alphaVal);
        } catch (Exception e) {
            Logger.error(
                "Failed to set PalFxShader uniforms: %s",
                e.getMessage()
            );
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        Logger.log("PalFxShader disposed");
    }
}
