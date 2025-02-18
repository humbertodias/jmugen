///*
// * Copyright (c) 2002-2004 LWJGL Project
// * All rights reserved.
// *
// * Redistribution and use in source and binary forms, with or without
// * modification, are permitted provided that the following conditions are
// * met:
// *
// * * Redistributions of source code must retain the above copyright
// *   notice, this list of conditions and the following disclaimer.
// *
// * * Redistributions in binary form must reproduce the above copyright
// *   notice, this list of conditions and the following disclaimer in the
// *   documentation and/or other materials provided with the distribution.
// *
// * * Neither the name of 'LWJGL' nor the names of
// *   its contributors may be used to endorse or promote products derived
// *   from this software without specific prior written permission.
// *
// * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
// * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
// * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
// * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
// * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
// * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
// * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
// * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
// * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
// */
///*
// * Created by LWJGL.
// * User: spasi
// * Date: 2004-03-30
// * Time: 9:55:38 pm
// */
//
//package org.lwjgl.test.opengl.shaders;
//
//import java.nio.ByteBuffer;
//import org.lwjgl.opengl.GL33;
//
//final class ShaderFP extends Shader {
//
//	final String vpFile;
//	final ByteBuffer vpSource;
//
//	final int vpID;
//
//	final String fpFile;
//	final ByteBuffer fpSource;
//
//	final int fpID;
//
//	ShaderFP(final String vpShaderFile, final String fpShaderFile) {
//		// Initialize the vertex shader.
//		vpFile = vpShaderFile;
//		vpSource = getShaderText(vpShaderFile);
//
//		vpID = compileShader(GL33.GL_VERTEX_SHADER, vpSource);
//
//		// Initialize the fragment shader.
//		fpFile = fpShaderFile;
//		fpSource = getShaderText(fpShaderFile);
//
//		fpID = compileShader(GL33.GL_FRAGMENT_SHADER, fpSource);
//	}
//
//	private int compileShader(int type, ByteBuffer shaderSource) {
//		int shaderID = GL33.glCreateShader(type);
//		GL33.glShaderSource(shaderID, shaderSource);
//		GL33.glCompileShader(shaderID);
//
//		// Check for compilation errors
//		if (GL33.glGetShaderi(shaderID, GL33.GL_COMPILE_STATUS) == GL33.GL_FALSE) {
//			String infoLog = GL33.glGetShaderInfoLog(shaderID);
//			System.err.println("Error compiling shader:\n" + infoLog);
//			throw new RuntimeException("Shader compilation failed");
//		}
//
//		return shaderID;
//	}
//
//	void render() {
//		// Create the program and attach shaders
//		int programID = GL33.glCreateProgram();
//		GL33.glAttachShader(programID, vpID);
//		GL33.glAttachShader(programID, fpID);
//		GL33.glLinkProgram(programID);
//
//		// Check for linking errors
//		if (GL33.glGetProgrami(programID, GL33.GL_LINK_STATUS) == GL33.GL_FALSE) {
//			String infoLog = GL33.glGetProgramInfoLog(programID);
//			System.err.println("Error linking program:\n" + infoLog);
//			throw new RuntimeException("Program linking failed");
//		}
//
//		// Use the program
//		GL33.glUseProgram(programID);
//
//		// Set local parameters
//		int vertexParamLocation = GL33.glGetUniformLocation(programID, "vertexParam");
//		GL33.glUniform4f(vertexParamLocation, ShadersTest.getSin(), ShadersTest.getSpecularity() * 8.0f, 0.0f, 0.0f);
//
//		int fragmentParamLocation = GL33.glGetUniformLocation(programID, "fragmentParam");
//		GL33.glUniform4f(fragmentParamLocation, ShadersTest.getSin(), ShadersTest.getSpecularity() * 8.0f,
//				-ShadersTest.getDisplayWidth() * 0.5f, -ShadersTest.getDisplayHeight() * 0.5f);
//
//		ShadersTest.renderObject();
//
//		GL33.glUseProgram(0); // Unbind the program
//	}
//
//	void cleanup() {
//		// Delete the shaders and program
//		GL33.glDeleteShader(vpID);
//		GL33.glDeleteShader(fpID);
//	}
//}
