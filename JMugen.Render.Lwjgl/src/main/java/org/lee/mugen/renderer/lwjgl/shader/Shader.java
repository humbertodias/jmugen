package org.lee.mugen.renderer.lwjgl.shader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.apache.commons.io.IOUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;


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
		ByteBuffer b = ByteBuffer.allocateDirect(shaderPrg.length());  // Ensure the buffer is large enough
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
