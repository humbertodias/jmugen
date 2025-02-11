package org.lee.mugen.renderer.jogl.shader;

import javax.media.opengl.GL;

import com.sun.opengl.util.texture.Texture;

public class BlendShader extends Shader {

	public BlendShader(String name) {
		super(name);
	}
	private int opacityUniPos;
	private int baseUniPos;
	private int blendUniPos;
	
	public void compileShader(GL gl) {
		super.compileShader(gl);
		
		gl.glGetObjectParameterivARB(programID,
				GL.GL_OBJECT_LINK_STATUS_ARB, programBuffer);
		if (programBuffer.get(0) == GL.GL_FALSE)
			System.err.println("A linking error occured in a shader program.");
		opacityUniPos = getUniformLocation(gl, programID, "Opacity");
		baseUniPos = getUniformLocation(gl, programID, "BaseImage");
		blendUniPos = getUniformLocation(gl, programID, "BlendImage");
		
	}
	public void render(GL gl, float opacity, Texture base, Texture blend) {
		gl.glUseProgramObjectARB(programID);
		gl.glUniform1fARB(opacityUniPos, opacity);
		
		gl.glActiveTexture(GL.GL_TEXTURE1);
		base.bind();
		gl.glUniform1i(baseUniPos, 1);

		gl.glActiveTexture(GL.GL_TEXTURE0);
		blend.bind();
		gl.glUniform1i(blendUniPos, 0);

	
	}
}
