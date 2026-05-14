package org.lee.mugen.renderer.libgdx.shader;

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

    public PalFxShader() {
        compile();
    }

    @Override
    public void compile() {
        try {
            // Require external shader files in resources (no fallback allowed)
            String vert = loadResource(
                "/org/lee/mugen/renderer/libgdx/shader/palfx.vert"
            );
            String frag = loadResource(
                "/org/lee/mugen/renderer/libgdx/shader/palfx.frag"
            );
            if (vert == null || frag == null) {
                Logger.error(
                    "PalFxShader: external shader files not found. Expected resources:\n  %s\n  %s",
                    "/org/lee/mugen/renderer/libgdx/shader/palfx.vert",
                    "/org/lee/mugen/renderer/libgdx/shader/palfx.frag"
                );
                isCompiled = false;
                return;
            }

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

    private static String loadResource(String path) {
        try (InputStream is = PalFxShader.class.getResourceAsStream(path)) {
            if (is == null) return null;
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
