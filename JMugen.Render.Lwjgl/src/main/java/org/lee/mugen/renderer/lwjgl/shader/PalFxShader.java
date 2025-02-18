package org.lee.mugen.renderer.lwjgl.shader;

import org.lee.mugen.renderer.RGB;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11C.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class PalFxShader extends Shader {

	private int addUniPos;
	private int mulUniPos;
	private int amplUniPos;
	private int alphaUniPos;
	
	public PalFxShader() {
		super("palfx");
	}
	
	@Override
//	public void compileShader() {
//		super.compileShader();
//		ARBShaderObjects.glGetObjectParameterARB(programID,
//				ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB, programBuffer);
//		if (programBuffer.get(0) == GL11.GL_FALSE)
//			System.err.println("A linking error occured in a shader program.");
//
//		addUniPos = getUniformLocation(programID, "add");
//		mulUniPos = getUniformLocation(programID, "mul");
//		amplUniPos = getUniformLocation(programID, "ampl");
//		alphaUniPos = getUniformLocation(programID, "alpha");
//	}

	public void compileShader() {
		// Supondo que o shader tenha sido compilado corretamente

		super.compileShader(); // Chamando o método da classe base (se houver)

		// Verificar se o programa foi linkado corretamente
		int[] linkStatus = new int[1];
		glGetProgramiv(programID, GL_LINK_STATUS, linkStatus);

		if (linkStatus[0] == GL_FALSE) {
			System.err.println("A linking error occurred in the shader program.");
			String infoLog = glGetProgramInfoLog(programID);
			System.err.println(infoLog);
		}

		// Obter a localização dos uniforms
		addUniPos = glGetUniformLocation(programID, "add");
		mulUniPos = glGetUniformLocation(programID, "mul");
		amplUniPos = glGetUniformLocation(programID, "ampl");
		alphaUniPos = glGetUniformLocation(programID, "alpha");
	}

	public void render(RGB add, RGB mul, RGB ampl, float alpha) {
		ARBShaderObjects.glUseProgramObjectARB(programID);
		
		ARBShaderObjects.glUniform4fARB(addUniPos, add.getR(), add.getG(), add.getB(), add.getA());
		ARBShaderObjects.glUniform4fARB(mulUniPos, mul.getR(), mul.getG(), mul.getB(), mul.getA());
		ARBShaderObjects.glUniform4fARB(amplUniPos, ampl.getR(), ampl.getG(), ampl.getB(), ampl.getA());
		ARBShaderObjects.glUniform1fARB(alphaUniPos, alpha);
	}
}
