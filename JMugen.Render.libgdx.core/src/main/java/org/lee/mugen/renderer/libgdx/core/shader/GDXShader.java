package org.lee.mugen.renderer.libgdx.core.shader;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Base class for LibGDX shader implementations
 * Extend this to create custom visual effects
 */
public abstract class GDXShader {
    
    protected ShaderProgram shaderProgram;
    protected boolean isCompiled = false;
    
    /**
     * Compile the shader program
     * Must be called before using the shader
     */
    public abstract void compile();
    
    /**
     * Apply the shader to the batch
     */
    public void apply(SpriteBatch batch) {
        if (isCompiled && shaderProgram != null && shaderProgram.isCompiled()) {
            batch.setShader(shaderProgram);
        }
    }
    
    /**
     * Reset to default shader
     */
    public void reset(SpriteBatch batch) {
        batch.setShader(null);
    }
    
    /**
     * Dispose shader resources
     */
    public void dispose() {
        if (shaderProgram != null) {
            shaderProgram.dispose();
        }
    }
    
    public boolean isCompiled() {
        return isCompiled && shaderProgram != null && shaderProgram.isCompiled();
    }
    
    public ShaderProgram getShaderProgram() {
        return shaderProgram;
    }
}
