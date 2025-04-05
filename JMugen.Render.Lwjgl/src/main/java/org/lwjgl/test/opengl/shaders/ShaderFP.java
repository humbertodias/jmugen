//package org.lwjgl.test.opengl.shaders;
//
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//import static org.lwjgl.opengl.GL20.*;
//
//final class ShaderFP extends Shader {
//
//	final int programID;
//	final int vertexShaderID;
//	final int fragmentShaderID;
//
//	ShaderFP(final String vertexPath, final String fragmentPath) {
//		String vertexSource = readFile(vertexPath);
//		String fragmentSource = readFile(fragmentPath);
//
//		vertexShaderID = compileShader(GL_VERTEX_SHADER, vertexSource, vertexPath);
//		fragmentShaderID = compileShader(GL_FRAGMENT_SHADER, fragmentSource, fragmentPath);
//
//		programID = glCreateProgram();
//		glAttachShader(programID, vertexShaderID);
//		glAttachShader(programID, fragmentShaderID);
//		glLinkProgram(programID);
//
//		if (glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE) {
//			throw new RuntimeException("Failed to link shader program:\n" + glGetProgramInfoLog(programID));
//		}
//	}
//
//	void render() {
//		glUseProgram(programID);
//
//		// Example uniform usage — you must declare these in your GLSL
//		int loc = glGetUniformLocation(programID, "someUniformVec4");
//		glUniform4f(loc,
//				ShadersTest.getSin(),
//				ShadersTest.getSpecularity() * 8.0f,
//				-ShadersTest.getDisplayWidth() * 0.5f,
//				-ShadersTest.getDisplayHeight() * 0.5f
//		);
//
//		ShadersTest.renderObject();
//
//		glUseProgram(0);
//	}
//
//	void cleanup() {
//		glDeleteShader(vertexShaderID);
//		glDeleteShader(fragmentShaderID);
//		glDeleteProgram(programID);
//	}
//
//	private int compileShader(int type, String source, String path) {
//		int shader = glCreateShader(type);
//		glShaderSource(shader, source);
//		glCompileShader(shader);
//
//		if (glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE) {
//			throw new RuntimeException("Error compiling shader " + path + ":\n" + glGetShaderInfoLog(shader));
//		}
//
//		return shader;
//	}
//
//	private String readFile(String path) {
//		try {
//			return new String(Files.readAllBytes(Paths.get(path)));
//		} catch (Exception e) {
//			throw new RuntimeException("Failed to read shader file: " + path, e);
//		}
//	}
//}
