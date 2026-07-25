package org.lee.mugen.renderer.libgdx.core.shader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.util.Logger;

/**
 * Palette Effects Shader for LibGDX
 * Implements color manipulation and palette effects
 * Compatible with LWJGL's PalFx shader (uniforms: add, mul, ampl, alpha)
 */
public class PalFxShader extends GDXShader {

    private static final String VERT_PATH =
        "/org/lee/mugen/renderer/libgdx/core/shader/palfx.vert";
    private static final String FRAG_PATH =
        "/org/lee/mugen/renderer/libgdx/core/shader/palfx.frag";
    private static final String VERT_CLASSPATH =
        "org/lee/mugen/renderer/libgdx/core/shader/palfx.vert";
    private static final String FRAG_CLASSPATH =
        "org/lee/mugen/renderer/libgdx/core/shader/palfx.frag";

    /** Embedded fallback for TeaVM / environments where classpath streams are unavailable. */
    private static final String VERT_SOURCE =
        "#ifdef GL_ES\n"
            + "precision mediump float;\n"
            + "#endif\n"
            + "\n"
            + "attribute vec4 a_position;\n"
            + "attribute vec4 a_color;\n"
            + "attribute vec2 a_texCoord0;\n"
            + "\n"
            + "uniform mat4 u_projTrans;\n"
            + "\n"
            + "varying vec4 v_color;\n"
            + "varying vec2 v_texCoords;\n"
            + "\n"
            + "void main() {\n"
            + "    v_color = a_color;\n"
            + "    v_texCoords = a_texCoord0;\n"
            + "    gl_Position = u_projTrans * a_position;\n"
            + "}\n";

    private static final String FRAG_SOURCE =
        "#ifdef GL_ES\n"
            + "precision mediump float;\n"
            + "#endif\n"
            + "\n"
            + "uniform sampler2D u_texture;\n"
            + "uniform vec4 add;\n"
            + "uniform vec4 mul;\n"
            + "uniform vec4 ampl;\n"
            + "uniform float alpha;\n"
            + "\n"
            + "varying vec4 v_color;\n"
            + "varying vec2 v_texCoords;\n"
            + "\n"
            + "void main() {\n"
            + "    vec4 sprColor = texture2D(u_texture, v_texCoords);\n"
            + "    vec3 adjustedRGB = (sprColor.rgb + add.rgb + ampl.rgb) * mul.rgb;\n"
            + "    adjustedRGB = clamp(adjustedRGB, 0.0, 1.0);\n"
            + "    float finalAlpha = sprColor.a * alpha;\n"
            + "    vec4 outColor = vec4(adjustedRGB, finalAlpha) * v_color;\n"
            + "    gl_FragColor = outColor;\n"
            + "}\n";

    public PalFxShader() {
        compile();
    }

    @Override
    public void compile() {
        try {
            String vert = loadShaderSource(VERT_PATH, VERT_CLASSPATH, VERT_SOURCE);
            String frag = loadShaderSource(FRAG_PATH, FRAG_CLASSPATH, FRAG_SOURCE);
            if (vert == null || frag == null) {
                Logger.error(
                    "PalFxShader: shader sources not found. Expected resources:\n  %s\n  %s",
                    VERT_PATH,
                    FRAG_PATH
                );
                isCompiled = false;
                return;
            }

            ShaderProgram.pedantic = false;
            shaderProgram = new ShaderProgram(vert, frag);

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

    private static String loadShaderSource(
            String resourcePath,
            String classpathPath,
            String embedded) {
        String fromStream = loadResourceStream(resourcePath);
        if (fromStream != null) {
            return fromStream;
        }
        String fromGdx = loadGdxClasspath(classpathPath);
        if (fromGdx != null) {
            return fromGdx;
        }
        return embedded;
    }

    private static String loadResourceStream(String path) {
        try (InputStream is = PalFxShader.class.getResourceAsStream(path)) {
            if (is == null) {
                return null;
            }
            BufferedReader br = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8)
            );
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append('\n');
            }
            return sb.toString();
        } catch (IOException e) {
            Logger.error(
                "Failed to load shader resource %s: %s",
                path,
                e.getMessage()
            );
            return null;
        }
    }

    private static String loadGdxClasspath(String classpathPath) {
        try {
            if (Gdx.files == null) {
                return null;
            }
            FileHandle fh = Gdx.files.classpath(classpathPath);
            if (fh.exists()) {
                return fh.readString();
            }
        } catch (Throwable ignored) {
            // Gdx not ready yet (desktop compile) or unavailable.
        }
        return null;
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
