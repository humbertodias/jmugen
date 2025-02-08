package org.lee.mugen.renderer.jogl.shader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL;

import org.apache.commons.io.IOUtils;


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
		InputStream inputStream = loader.getResourceAsStream("org/lee/mugen/renderer/jogl/shader/" + file);

		String shaderText;
		try {
			shaderText = IOUtils.toString(inputStream);
			return shaderText;
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}


	}
	
	public void compileShader(GL gl) {
		fshID = gl.glCreateShader(GL.GL_FRAGMENT_SHADER_ARB);
		gl.glShaderSource(fshID, 1, new String[] {shaderPrg}, IntBuffer.wrap(new int[] {shaderPrg.length()}));
		gl.glCompileShader(fshID);
		programID = gl.glCreateProgram();
		gl.glAttachShader(programID, fshID);
		gl.glLinkProgram(programID);
		gl.glValidateProgram(programID);
		


	}
	
	
	public void render(GL gl) {
		gl.glUseProgramObjectARB(fshID);
		
	}

	public void endRender(GL gl) {
		gl.glUseProgramObjectARB(0);
		
	}
	public void clean(GL gl) {
		gl.glDetachObjectARB(programID, fshID);
		gl.glDeleteObjectARB(fshID);
		gl.glDeleteObjectARB(programID);

	}
	protected static int getUniformLocation(GL gl, int id, String name) {
		fileBuffer.clear();
		final int location = gl.glGetUniformLocationARB(id, name);

		if ( location == -1 )
			throw new IllegalArgumentException("The uniform \"" + name + "\" does not exist in the Shader Program.");

		return location;
	}


}
