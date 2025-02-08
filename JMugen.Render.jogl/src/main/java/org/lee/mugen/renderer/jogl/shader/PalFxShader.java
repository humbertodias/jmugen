package org.lee.mugen.renderer.jogl.shader;

import javax.media.opengl.GL;

import org.lee.mugen.renderer.RGB;

public class PalFxShader extends Shader {

	private int addUniPos;
	private int mulUniPos;
	private int amplUniPos;
	private int alphaUniPos;
	
	public PalFxShader() {
		super("palfx");
	}
	
	@Override
	public void compileShader(GL gl) {
		super.compileShader(gl);
		gl.glGetObjectParameterivARB(programID,
				GL.GL_OBJECT_LINK_STATUS_ARB, programBuffer);
		if (programBuffer.get(0) == GL.GL_FALSE)
			System.err.println("A linking error occured in a shader program.");

		addUniPos = getUniformLocation(gl, programID, "add");
		mulUniPos = getUniformLocation(gl, programID, "mul");
		amplUniPos = getUniformLocation(gl, programID, "ampl");
		alphaUniPos = getUniformLocation(gl, programID, "alpha");
	}
	public void render(GL gl, RGB add, RGB mul, RGB ampl, float alpha) {
		gl.glUseProgramObjectARB(programID);
		
		gl.glUniform4fARB(addUniPos, add.getR(), add.getG(), add.getB(), add.getA());
		gl.glUniform4fARB(mulUniPos, mul.getR(), mul.getG(), mul.getB(), mul.getA());
		gl.glUniform4fARB(amplUniPos, ampl.getR(), ampl.getG(), ampl.getB(), ampl.getA());
		gl.glUniform1f(alphaUniPos, alpha);
	}
}
