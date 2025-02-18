package org.lee.mugen.renderer.lwjgl.shader;

import org.lee.mugen.renderer.RGB;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11C.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

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

//	public void compileShader() {
//		super.compileShader();
//
//		ARBShaderObjects.glGetObjectParameterARB(programID,
//				ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB, programBuffer);
//		if (programBuffer.get(0) == GL11.GL_FALSE)
//			System.err.println("A linking error occured in a shader program.");
//		palbrightUniPos = getUniformLocation(programID, "palbright");
//		palcontrastUniPos = getUniformLocation(programID, "palcontrast");
//		palpostbrightUniPos = getUniformLocation(programID, "palpostbright");
//
//		mulUniPos = getUniformLocation(programID, "mul");
//		addUniPos = getUniformLocation(programID, "add");
//		typeUniPos = getUniformLocation(programID, "type");
//		alphaUniPos = getUniformLocation(programID, "alpha");
//	}

	public void compileShader() {
		super.compileShader();  // Supondo que o método da classe base seja necessário

		// Verificar se o programa foi linkado corretamente
		int[] linkStatus = new int[1];
		glGetProgramiv(programID, GL_LINK_STATUS, linkStatus);

		if (linkStatus[0] == GL_FALSE) {
			System.err.println("A linking error occurred in the shader program.");
			String infoLog = glGetProgramInfoLog(programID);
			System.err.println(infoLog);  // Mostrar o log de erro de linkagem
		}

		// Obter a localização dos uniforms
		palbrightUniPos = glGetUniformLocation(programID, "palbright");
		palcontrastUniPos = glGetUniformLocation(programID, "palcontrast");
		palpostbrightUniPos = glGetUniformLocation(programID, "palpostbright");

		mulUniPos = glGetUniformLocation(programID, "mul");
		addUniPos = glGetUniformLocation(programID, "add");
		typeUniPos = glGetUniformLocation(programID, "type");
		alphaUniPos = glGetUniformLocation(programID, "alpha");
	}

	public void render(RGB palbright, RGB palcontrast, RGB palpostbright, RGB add, RGB mul, float type, float alpha) {
		ARBShaderObjects.glUseProgramObjectARB(programID);
		
		ARBShaderObjects.glUniform4fARB(palbrightUniPos, palbright.getR(), palbright.getG(), palbright.getB(), palbright.getA());
		ARBShaderObjects.glUniform4fARB(palcontrastUniPos, palcontrast.getR(), palcontrast.getG(), palcontrast.getB(), palcontrast.getA());
		ARBShaderObjects.glUniform4fARB(palpostbrightUniPos, palpostbright.getR(), palpostbright.getG(), palpostbright.getB(), palpostbright.getA());

		ARBShaderObjects.glUniform4fARB(addUniPos, add.getR(), add.getG(), add.getB(), add.getA());
		ARBShaderObjects.glUniform4fARB(mulUniPos, mul.getR(), mul.getG(), mul.getB(), mul.getA());
		
		ARBShaderObjects.glUniform1fARB(typeUniPos, type);

		ARBShaderObjects.glUniform1fARB(alphaUniPos, alpha);
	}


}
