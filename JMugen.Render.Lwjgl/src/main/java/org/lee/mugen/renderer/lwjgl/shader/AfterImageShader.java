package org.lee.mugen.renderer.lwjgl.shader;

import org.lee.mugen.renderer.RGB;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class AfterImageShader extends Shader {

	private int palbrightUniPos;
	private int palcontrastUniPos;
	private int palpostbrightUniPos;

	private int mulUniPos;
	private int addUniPos;

	private int typeUniPos;
	private int alphaUniPos;

	public AfterImageShader() {
		super("afterimage");
	}

	@Override
	public void compileShader() {
		super.compileShader();

		int status = GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS);
		if (status == GL11.GL_FALSE) {
			System.err.println("A linking error occurred in a shader program.");
			System.err.println(GL20.glGetProgramInfoLog(programID));
		}

		palbrightUniPos = GL20.glGetUniformLocation(programID, "palbright");
		palcontrastUniPos = GL20.glGetUniformLocation(programID, "palcontrast");
		palpostbrightUniPos = GL20.glGetUniformLocation(programID, "palpostbright");

		mulUniPos = GL20.glGetUniformLocation(programID, "mul");
		addUniPos = GL20.glGetUniformLocation(programID, "add");
		typeUniPos = GL20.glGetUniformLocation(programID, "type");
		alphaUniPos = GL20.glGetUniformLocation(programID, "alpha");
	}

	public void render(RGB palbright, RGB palcontrast, RGB palpostbright, RGB add, RGB mul, float type, float alpha) {
		GL20.glUseProgram(programID);

		GL20.glUniform4f(palbrightUniPos, palbright.getR(), palbright.getG(), palbright.getB(), palbright.getA());
		GL20.glUniform4f(palcontrastUniPos, palcontrast.getR(), palcontrast.getG(), palcontrast.getB(), palcontrast.getA());
		GL20.glUniform4f(palpostbrightUniPos, palpostbright.getR(), palpostbright.getG(), palpostbright.getB(), palpostbright.getA());

		GL20.glUniform4f(addUniPos, add.getR(), add.getG(), add.getB(), add.getA());
		GL20.glUniform4f(mulUniPos, mul.getR(), mul.getG(), mul.getB(), mul.getA());

		GL20.glUniform1f(typeUniPos, type);
		GL20.glUniform1f(alphaUniPos, alpha);
	}
}
