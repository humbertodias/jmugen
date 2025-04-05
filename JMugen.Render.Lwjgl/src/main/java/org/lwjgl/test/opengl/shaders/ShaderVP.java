//package org.lwjgl.test.opengl.shaders;
//
//import java.nio.ByteBuffer;
//
//import static org.lwjgl.opengl.GL11.*;
//import static org.lwjgl.opengl.GL20.*;
//
//final class ShaderVP extends Shader {
//
//	final String file;
//	final ByteBuffer source;
//
//	final int vertexShaderID;
//	final int programID;
//
//	ShaderVP(final String shaderFile) {
//		file = shaderFile;
//		source = getShaderText(shaderFile);
//
//		// Compile vertex shader
//		vertexShaderID = glCreateShader(GL_VERTEX_SHADER);
//		glShaderSource(vertexShaderID, toString(source));
//		glCompileShader(vertexShaderID);
//
//		if (glGetShaderi(vertexShaderID, GL_COMPILE_STATUS) == GL_FALSE) {
//			String log = glGetShaderInfoLog(vertexShaderID);
//			ShadersTest.kill("Vertex shader compilation failed (" + shaderFile + "):\n" + log);
//		}
//
//		// Create program and attach shader
//		programID = glCreateProgram();
//		glAttachShader(programID, vertexShaderID);
//		glLinkProgram(programID);
//
//		if (glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE) {
//			String log = glGetProgramInfoLog(programID);
//			ShadersTest.kill("Program linking failed (" + shaderFile + "):\n" + log);
//		}
//	}
//
//	void render() {
//		glUseProgram(programID);
//
//		int loc = glGetUniformLocation(programID, "param0");
//		if (loc != -1) {
//			glUniform4f(loc,
//					ShadersTest.getSin(),
//					ShadersTest.getSpecularity() * 8.0f,
//					0.0f,
//					0.0f
//			);
//		}
//
//		ShadersTest.renderObject();
//
//		glUseProgram(0);
//	}
//
//	void cleanup() {
//		glDeleteShader(vertexShaderID);
//		glDeleteProgram(programID);
//	}
//
//	// Helper to convert ByteBuffer to String
//	private String toString(ByteBuffer buffer) {
//		byte[] bytes = new byte[buffer.remaining()];
//		buffer.get(bytes);
//		buffer.rewind();
//		return new String(bytes);
//	}
//}
