package org.lee.mugen.renderer.lwjgl.shader;

import org.lee.mugen.renderer.RGB;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class PalFxShader extends Shader {

	private int addUniPos;
	private int mulUniPos;
	private int amplUniPos;
	private int alphaUniPos;

	public PalFxShader() {
		super("palfx");
	}

	@Override
	public void compileShader() {
		super.compileShader();

		int status = GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS);
		if (status == GL11.GL_FALSE) {
			System.err.println("A linking error occurred in the shader program.");
			System.err.println(GL20.glGetProgramInfoLog(programID));
		}

		addUniPos = GL20.glGetUniformLocation(programID, "add");
		mulUniPos = GL20.glGetUniformLocation(programID, "mul");
		amplUniPos = GL20.glGetUniformLocation(programID, "ampl");
		alphaUniPos = GL20.glGetUniformLocation(programID, "alpha");
	}

	public void render(RGB add, RGB mul, RGB ampl, float alpha) {
		GL20.glUseProgram(programID);

		GL20.glUniform4f(addUniPos, add.getR(), add.getG(), add.getB(), add.getA());
		GL20.glUniform4f(mulUniPos, mul.getR(), mul.getG(), mul.getB(), mul.getA());
		GL20.glUniform4f(amplUniPos, ampl.getR(), ampl.getG(), ampl.getB(), ampl.getA());
		GL20.glUniform1f(alphaUniPos, alpha);
	}
}
