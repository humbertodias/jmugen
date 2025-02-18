package org.lee.mugen.renderer.lwjgl.shader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.apache.commons.io.IOUtils;
import org.lee.mugen.util.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL20.*;


public class Shader {
	private static final IntBuffer int_buffer = BufferUtils.createIntBuffer(16);
	protected static IntBuffer programBuffer = BufferUtils.createIntBuffer(1);
	protected static ByteBuffer fileBuffer = BufferUtils.createByteBuffer(1024 * 10);
	
	protected int fshID;
	protected int programID;
	protected String name;
	protected String shaderPrg; 
	
	public Shader(String name) {
		shaderPrg = getShaderText(name);
		this.name = name;
	
	}
	protected static String getShaderText(String file) {
		ClassLoader loader = Shader.class.getClassLoader();
		String shaderPath = "org/lee/mugen/renderer/lwjgl/shader/" + file;
		Logger.log("Loading shader file: %s", shaderPath);
		InputStream inputStream = loader.getResourceAsStream(shaderPath);

		String shaderText;
		try {
			shaderText = IOUtils.toString(inputStream);
			return shaderText;
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}


	}
	
//	public void compileShader() {
//		fshID = ARBShaderObjects.glCreateShaderObjectARB(ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
//		ByteBuffer b = ByteBuffer.wrap(shaderPrg.getBytes());
//		b.rewind();
//		ARBShaderObjects.glShaderSourceARB(fshID, b);
//		ARBShaderObjects.glCompileShaderARB(fshID);
//		programID = ARBShaderObjects.glCreateProgramObjectARB();
//		ARBShaderObjects.glAttachObjectARB(programID, fshID);
//		ARBShaderObjects.glLinkProgramARB(programID);
//		ARBShaderObjects.glValidateProgramARB(programID);
//	}
//

	public void compileShader() {
		// Criar o ID do Fragment Shader
		fshID = glCreateShader(GL_FRAGMENT_SHADER);

		// Carregar o código-fonte do shader
		glShaderSource(fshID, shaderPrg); // Usar o código-fonte diretamente como String

		// Compilar o shader
		glCompileShader(fshID);

		// Verificar erros de compilação
		if (glGetShaderi(fshID, GL_COMPILE_STATUS) == GL_FALSE) {
			String errorLog = glGetShaderInfoLog(fshID);
			System.out.println("Shader compilation failed: " + errorLog);
			return;
		}

		// Criar o programa de shader
		programID = glCreateProgram();

		// Anexar o fragment shader ao programa
		glAttachShader(programID, fshID);

		// Linkar o programa
		glLinkProgram(programID);

		// Verificar erros de linkagem
		if (glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE) {
			String errorLog = glGetProgramInfoLog(programID);
			System.out.println("Program linking failed: " + errorLog);
			return;
		}

		// Validar o programa
		glValidateProgram(programID);
		if (glGetProgrami(programID, GL_VALIDATE_STATUS) == GL_FALSE) {
			String errorLog = glGetProgramInfoLog(programID);
			System.out.println("Program validation failed: " + errorLog);
			return;
		}

		// Shader compilado e programa vinculado com sucesso
		System.out.println("Shader compiled and program linked successfully!");
	}


	public void render() {
		ARBShaderObjects.glUseProgramObjectARB(fshID);
		
	}

	public void endRender() {
		ARBShaderObjects.glUseProgramObjectARB(0);
		
	}
	public void clean() {
		ARBShaderObjects.glDetachObjectARB(programID, fshID);
		ARBShaderObjects.glDeleteObjectARB(fshID);
		ARBShaderObjects.glDeleteObjectARB(programID);

	}
	protected static int getUniformLocation(int id, String name) {
		fileBuffer.clear();
		int length = name.length();

		char[] charArray = new char[length];
		name.getChars(0, length, charArray, 0);

		for ( int i = 0; i < length; i++ )
			fileBuffer.put((byte)charArray[i]);
		fileBuffer.put((byte)0); // Must be null-terminated.
		fileBuffer.flip();
		final int location = ARBShaderObjects.glGetUniformLocationARB(id, fileBuffer);

		if ( location == -1 )
			throw new IllegalArgumentException("The uniform \"" + name + "\" does not exist in the Shader Program.");

		return location;
	}


}
