package org.lee.mugen.renderer.lwjgl.shader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.apache.commons.io.IOUtils;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;


public class Shader {
	// Use instance buffers instead of static to allow proper cleanup
	protected IntBuffer programBuffer;
	protected ByteBuffer fileBuffer;

	protected int fshID;
	protected int programID;
	protected String name;
	protected String shaderPrg;

	public Shader(String name) {
		shaderPrg = getShaderText(name);
		this.name = name;
		// Allocate buffers per instance
		programBuffer = MemoryUtil.memAllocInt(1);
		fileBuffer = MemoryUtil.memAlloc(1024 * 10);
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
		ByteBuffer b = ByteBuffer.wrap(shaderPrg.getBytes());
		b.rewind();
		ARBShaderObjects.glShaderSourceARB(fshID, b);
		ARBShaderObjects.glCompileShaderARB(fshID);
		programID = ARBShaderObjects.glCreateProgramObjectARB();
		ARBShaderObjects.glAttachObjectARB(programID, fshID);
		ARBShaderObjects.glLinkProgramARB(programID);
		ARBShaderObjects.glValidateProgramARB(programID);
	}

	public void compileShader() {
		// Create the shader object (fragment shader)
		fshID = ARBShaderObjects.glCreateShaderObjectARB(ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);

		// Create a direct ByteBuffer to hold the shader program
		ByteBuffer b = MemoryUtil.memAlloc(shaderPrg.length());  // Ensure the buffer is large enough
		try {
			b.put(shaderPrg.getBytes());  // Fill the buffer with the shader program byte data
			b.flip();  // Prepare the buffer for reading by OpenGL

			// Pass the direct ByteBuffer to the shader
			ARBShaderObjects.glShaderSourceARB(fshID, b);

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
		} finally {
			MemoryUtil.memFree(b);
		}
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
		
		// Free allocated buffers
		if (programBuffer != null) {
			MemoryUtil.memFree(programBuffer);
			programBuffer = null;
		}
		if (fileBuffer != null) {
			MemoryUtil.memFree(fileBuffer);
			fileBuffer = null;
		}
	}
	
	protected int getUniformLocation(int id, String name) {
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
