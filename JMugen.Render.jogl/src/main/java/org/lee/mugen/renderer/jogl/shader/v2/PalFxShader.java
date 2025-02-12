package org.lee.mugen.renderer.jogl.shader.v2;


import com.jogamp.opengl.GL2;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.util.Logger;


public class PalFxShader extends Shader {

	private int addUniPos;
	private int mulUniPos;
	private int amplUniPos;
	private int alphaUniPos;

	public PalFxShader() {
		super("palfx");
	}

	@Override
	public void compileShader(GL2 gl) {
		super.compileShader(gl);
		gl.glGetObjectParameterivARB(programID,
				GL2.GL_OBJECT_LINK_STATUS_ARB, programBuffer);
		if (programBuffer.get(0) == GL2.GL_FALSE)
			Logger.error("A linking error occurred in a shader program.");

		addUniPos = getUniformLocation(gl, programID, "add");
		mulUniPos = getUniformLocation(gl, programID, "mul");
		amplUniPos = getUniformLocation(gl, programID, "ampl");
		alphaUniPos = getUniformLocation(gl, programID, "alpha");
	}
	public void render(GL2 gl, RGB add, RGB mul, RGB ampl, float alpha) {
		gl.glUseProgramObjectARB(programID);

		gl.glUniform4fARB(addUniPos, add.getR(), add.getG(), add.getB(), add.getA());
		gl.glUniform4fARB(mulUniPos, mul.getR(), mul.getG(), mul.getB(), mul.getA());
		gl.glUniform4fARB(amplUniPos, ampl.getR(), ampl.getG(), ampl.getB(), ampl.getA());
		gl.glUniform1f(alphaUniPos, alpha);
	}
}
