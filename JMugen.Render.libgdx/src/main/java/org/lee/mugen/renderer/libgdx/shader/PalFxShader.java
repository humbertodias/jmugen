package org.lee.mugen.renderer.libgdx.shader;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import org.lee.mugen.util.Logger;

/**
 * Palette Effects Shader for LibGDX
 * Implements color manipulation and palette effects
 * Similar to PalFxShader in LWJGL and JOGL backends
 */
public class PalFxShader extends GDXShader {
    
    // Vertex shader - standard 2D projection
    private static final String VERTEX_SHADER = 
        "#ifdef GL_ES\n" +
        "precision mediump float;\n" +
        "#endif\n" +
        "\n" +
        "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" +
        "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" +
        "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" +
        "\n" +
        "uniform mat4 u_projTrans;\n" +
        "\n" +
        "varying vec4 v_color;\n" +
        "varying vec2 v_texCoords;\n" +
        "\n" +
        "void main() {\n" +
        "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" +
        "   v_color.a = v_color.a * (255.0/254.0);\n" +
        "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" +
        "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" +
        "}\n";
    
    // Fragment shader - applies palette effects (color mapping, screen effects, etc.)
    private static final String FRAGMENT_SHADER = 
        "#ifdef GL_ES\n" +
        "precision mediump float;\n" +
        "#endif\n" +
        "\n" +
        "uniform sampler2D u_texture;\n" +
        "uniform vec4 u_add;          // Add color effect\n" +
        "uniform vec4 u_mul;          // Multiply color effect\n" +
        "uniform float u_intensity;   // Effect intensity\n" +
        "uniform int u_effectType;    // 0: none, 1: add, 2: mul, 3: screen\n" +
        "\n" +
        "varying vec4 v_color;\n" +
        "varying vec2 v_texCoords;\n" +
        "\n" +
        "void main() {\n" +
        "   vec4 texColor = texture2D(u_texture, v_texCoords);\n" +
        "   vec4 color = texColor * v_color;\n" +
        "\n" +
        "   if (u_effectType == 1) {\n" +
        "       // Add effect\n" +
        "       color += u_add * u_intensity;\n" +
        "   } else if (u_effectType == 2) {\n" +
        "       // Multiply effect\n" +
        "       color *= mix(vec4(1.0), u_mul, u_intensity);\n" +
        "   } else if (u_effectType == 3) {\n" +
        "       // Screen blend\n" +
        "       color = vec4(1.0) - (vec4(1.0) - color) * (vec4(1.0) - u_mul * u_intensity);\n" +
        "   }\n" +
        "\n" +
        "   gl_FragColor = color;\n" +
        "}\n";
    
    public PalFxShader() {
        compile();
    }
    
    @Override
    public void compile() {
        try {
            shaderProgram = new ShaderProgram(VERTEX_SHADER, FRAGMENT_SHADER);
            
            if (!shaderProgram.isCompiled()) {
                Logger.error("Failed to compile PalFxShader: " + shaderProgram.getLog());
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
     * Set add color effect
     */
    public void setAddColor(float r, float g, float b, float a) {
        if (isCompiled()) {
            shaderProgram.setUniformf("u_add", r, g, b, a);
        }
    }
    
    /**
     * Set multiply color effect
     */
    public void setMultiplyColor(float r, float g, float b, float a) {
        if (isCompiled()) {
            shaderProgram.setUniformf("u_mul", r, g, b, a);
        }
    }
    
    /**
     * Set effect intensity (0.0 to 1.0)
     */
    public void setIntensity(float intensity) {
        if (isCompiled()) {
            shaderProgram.setUniformf("u_intensity", Math.max(0, Math.min(1, intensity)));
        }
    }
    
    /**
     * Set effect type
     * 0: none, 1: add, 2: multiply, 3: screen blend
     */
    public void setEffectType(int type) {
        if (isCompiled()) {
            shaderProgram.setUniformi("u_effectType", type);
        }
    }
    
    @Override
    public void dispose() {
        super.dispose();
        Logger.log("PalFxShader disposed");
    }
}
