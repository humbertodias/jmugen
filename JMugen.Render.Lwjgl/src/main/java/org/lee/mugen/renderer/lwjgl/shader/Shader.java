package org.lee.mugen.renderer.lwjgl.shader;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;


public class Shader {
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
		System.out.println("getShaderText:" + shaderPath);
		InputStream inputStream = loader.getResourceAsStream(shaderPath);

		String shaderText;
		try {
			shaderText = IOUtils.toString(inputStream);
			return shaderText;
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}


	}

	public void compileShader2() {
		fshID = ARBShaderObjects.glCreateShaderObjectARB(ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
		// In LWJGL 3, glShaderSourceARB takes CharSequence instead of ByteBuffer
		ARBShaderObjects.glShaderSourceARB(fshID, shaderPrg);
		ARBShaderObjects.glCompileShaderARB(fshID);
		programID = ARBShaderObjects.glCreateProgramObjectARB();
		ARBShaderObjects.glAttachObjectARB(programID, fshID);
		ARBShaderObjects.glLinkProgramARB(programID);
		ARBShaderObjects.glValidateProgramARB(programID);
	}

	public void compileShader() {
		// Create the shader object (fragment shader)
		fshID = ARBShaderObjects.glCreateShaderObjectARB(ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);

		// In LWJGL 3, glShaderSourceARB takes CharSequence (String) instead of ByteBuffer
		ARBShaderObjects.glShaderSourceARB(fshID, shaderPrg);

		// Compile the shader
		ARBShaderObjects.glCompileShaderARB(fshID);

		// Create the program object
		programID = ARBShaderObjects.glCreateProgramObjectARB();

		// Attach the shader to the program
		ARBShaderObjects.glAttachObjectARB(programID, fshID);

		// Link the program
		ARBShaderObjects.glLinkProgramARB(programID);

		// Validate the program
		ARBShaderObjects.glValidateProgramARB(programID);
	}



	public void render() {
		ARBShaderObjects.glUseProgramObjectARB(programID);

	}

	public void endRender() {
		ARBShaderObjects.glUseProgramObjectARB(0);

	}
	
	public void clean() {
		ARBShaderObjects.glDetachObjectARB(programID, fshID);
		ARBShaderObjects.glDeleteObjectARB(fshID);
		ARBShaderObjects.glDeleteObjectARB(programID);
	}
	
	protected int getUniformLocation(int id, String name) {
		// In LWJGL 3, glGetUniformLocationARB accepts String directly
		final int location = ARBShaderObjects.glGetUniformLocationARB(id, name);

		if ( location == -1 )
			throw new IllegalArgumentException("The uniform \"" + name + "\" does not exist in the Shader Program.");

		return location;
	}


}
