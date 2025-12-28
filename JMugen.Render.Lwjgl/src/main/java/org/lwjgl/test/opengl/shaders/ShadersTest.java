/* 
 * Copyright (c) 2002-2004 LWJGL Project
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are 
 * met:
 * 
 * * Redistributions of source code must retain the above copyright 
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of 
 *   its contributors may be used to endorse or promote products derived 
 *   from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
/*
 * Created by LWJGL.
 * User: spasi
 * Date: 2004-03-30
 * Time: 8:41:42 pm
 * 
 * Converted to LWJGL 3 with GLFW
 */

package org.lwjgl.test.opengl.shaders;

import java.nio.FloatBuffer;

import org.lwjgl.system.MemoryUtil;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLCapabilities;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public final class ShadersTest {

	private static int displayWidth = 1024;
	private static int displayHeight = 768;
	
	private static long window;

	private static boolean run = true;

	private static final FloatBuffer vectorBuffer = MemoryUtil.memAllocFloat(4);

	private static Shader shader;

	private static long lastFrameTime;
	private static float frameTime;

	private static float angle;
	private static float sin;
	private static int specularity = 4;

	private ShadersTest() {
	}

	public static void main(String[] args) {
		initialize(args);

		long frameStart;
		lastFrameTime = System.nanoTime();

		while ( run && !glfwWindowShouldClose(window) ) {
			glfwPollEvents();
			
			// This is the current frame time.
			frameStart = System.nanoTime();

			// How many seconds passed since last frame.
			frameTime = (float)((frameStart - lastFrameTime) / 1000000000.0);

			lastFrameTime = frameStart;

			angle += frameTime * 90.0f;
			sin = (float)Math.sin(Math.toRadians(angle));

			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			if ( shader != null )
				shader.render();
			else
				renderObject();

			// Restore camera position.
			glPopMatrix();
			glPushMatrix();

			glfwSwapBuffers(window);
		}

		cleanup();
		System.exit(0);
	}

	private static void initialize(String[] args) {
		if ( args.length != 1 )
			argsError();

		if (!glfwInit())
			kill("Failed to initialize GLFW");

		// Configure window hints
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

		// Try different display modes
		int[] widths = {1024, 800, 640};
		int[] heights = {768, 600, 480};
		
		for (int i = 0; i < widths.length; i++) {
			displayWidth = widths[i];
			displayHeight = heights[i];
			
			window = glfwCreateWindow(displayWidth, displayHeight, "LWJGL 3 Shaders Test", NULL, NULL);
			if (window != NULL) {
				System.out.println("Setting display mode to: " + displayWidth + "x" + displayHeight);
				break;
			}
		}

		if (window == NULL)
			kill("Failed to create the GLFW window");

		// Setup key callback
		glfwSetKeyCallback(window, (win, key, scancode, action, mods) -> {
			if (action == GLFW_PRESS) {
				if (key == GLFW_KEY_ESCAPE) {
					run = false;
				} else if (key == GLFW_KEY_EQUAL || key == GLFW_KEY_KP_ADD) {
					if (specularity < 8)
						specularity++;
				} else if (key == GLFW_KEY_MINUS || key == GLFW_KEY_KP_SUBTRACT) {
					if (specularity > 1)
						specularity--;
				}
			}
		});

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);

		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GLCapabilities caps = GL.createCapabilities();

		if ( "NONE".equalsIgnoreCase(args[0]) ) {
			shader = null;
		} else if ( "VP".equalsIgnoreCase(args[0]) ) {
			if ( !caps.GL_ARB_vertex_program )
				kill("The ARB_vertex_program extension is not supported.");

			shader = new ShaderVP("shaderVP.vp");
		} else if ( "FP".equalsIgnoreCase(args[0]) ) {
			if ( !caps.GL_ARB_vertex_program )
				kill("The ARB_vertex_program extension is not supported.");
			if ( !caps.GL_ARB_fragment_program )
				kill("The ARB_fragment_program extension is not supported.");

			shader = new ShaderFP("shaderFP.vp", "shaderFP.fp");
		} else if ( "VSH".equalsIgnoreCase(args[0]) ) {
			if ( !caps.GL_ARB_vertex_shader )
				kill("The ARB_vertex_shader extension is not supported.");

			shader = new ShaderVSH("shaderVSH.vsh");
		} else if ( "FSH".equalsIgnoreCase(args[0]) ) {
			if ( !caps.GL_ARB_vertex_shader )
				kill("The ARB_vertex_shader extension is not supported.");
			if ( !caps.GL_ARB_fragment_shader )
				kill("The ARB_fragment_shader extension is not supported.");

			shader = new ShaderFSH("shaderFSH.vsh", "shaderFSH.fsh");
		} else {
			argsError();
		}

		glViewport(0, 0, displayWidth, displayHeight);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		// gluPerspective replacement
		float fovy = 45.0f;
		float aspect = displayWidth / (float)displayHeight;
		float zNear = 1.0f;
		float zFar = 10.0f;
		float fH = (float)Math.tan(fovy / 360.0f * Math.PI) * zNear;
		float fW = fH * aspect;
		glFrustum(-fW, fW, -fH, fH, zNear, zFar);

		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		// Setup camera position.
		glTranslatef(0.0f, 0.0f, -4.0f);
		glRotatef(15.0f, 1.0f, 0.0f, 0.0f);
		glPushMatrix();

		glClearDepth(1.0f);
		glDepthFunc(GL_LEQUAL);

		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);

		glFrontFace(GL_CCW);
		glPolygonMode(GL_FRONT, GL_FILL);

		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);

		glAlphaFunc(GL_NOTEQUAL, 0.0f);
		glEnable(GL_ALPHA_TEST);

		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_BLEND);

		// Setup lighting for when we have fixed function fragment rendering.
		glShadeModel(GL_SMOOTH);

		if ( shader == null ) {
			glEnable(GL_LIGHTING);
			glEnable(GL_LIGHT0);
		}

		vectorBuffer.clear();
		vectorBuffer.put(1.0f).put(1.0f).put(1.0f).put(1.0f);
		vectorBuffer.flip();
		glLightfv(GL_LIGHT0, GL_DIFFUSE, vectorBuffer);

		vectorBuffer.clear();
		vectorBuffer.put(1.0f).put(1.0f).put(1.0f).put(1.0f);
		vectorBuffer.flip();
		glLightfv(GL_LIGHT0, GL_AMBIENT, vectorBuffer);

		vectorBuffer.clear();
		vectorBuffer.put(1.0f).put(1.0f).put(0.5f).put(1.0f);
		vectorBuffer.flip();
		glLightfv(GL_LIGHT0, GL_SPECULAR, vectorBuffer);

		vectorBuffer.clear();
		vectorBuffer.put(-1.0f / 3.0f).put(1.0f / 3.0f).put(1.0f / 3.0f).put(0.0f); // Infinite
		vectorBuffer.flip();
		glLightfv(GL_LIGHT0, GL_POSITION, vectorBuffer);

		vectorBuffer.clear();
		vectorBuffer.put(0.2f).put(0.2f).put(0.2f).put(1.0f);
		vectorBuffer.flip();
		glLightModelfv(GL_LIGHT_MODEL_AMBIENT, vectorBuffer);
	}

	static int getDisplayWidth() {
		return displayWidth;
	}

	static int getDisplayHeight() {
		return displayHeight;
	}

	static float getSin() {
		return sin;
	}

	static int getSpecularity() {
		return specularity;
	}

	static void renderObject() {
		glColor3f(1.0f, 1.0f, 1.0f);
		// Draw a sphere using quad strips (simple sphere replacement)
		drawSphere(1.0f, 32, 32);
	}

	private static void drawSphere(float radius, int slices, int stacks) {
		for (int i = 0; i < stacks; i++) {
			float lat0 = (float) (Math.PI * (-0.5 + (double) (i) / stacks));
			float z0 = (float) (Math.sin(lat0));
			float zr0 = (float) (Math.cos(lat0));

			float lat1 = (float) (Math.PI * (-0.5 + (double) (i + 1) / stacks));
			float z1 = (float) (Math.sin(lat1));
			float zr1 = (float) (Math.cos(lat1));

			glBegin(GL_QUAD_STRIP);
			for (int j = 0; j <= slices; j++) {
				float lng = (float) (2 * Math.PI * (double) (j - 1) / slices);
				float x = (float) (Math.cos(lng));
				float y = (float) (Math.sin(lng));

				glNormal3f(x * zr0, y * zr0, z0);
				glVertex3f(radius * x * zr0, radius * y * zr0, radius * z0);
				glNormal3f(x * zr1, y * zr1, z1);
				glVertex3f(radius * x * zr1, radius * y * zr1, radius * z1);
			}
			glEnd();
		}
	}

	private static void cleanup() {
		// This is not necessary, just showing how to properly delete a program/shader.
		if ( shader != null )
			shader.cleanup();

		MemoryUtil.memFree(vectorBuffer);

		if ( window != NULL ) {
			glfwDestroyWindow(window);
		}
		glfwTerminate();
	}

	private static void argsError() {
		System.out.println("\nInvalid program arguments.");
		System.out.println("\nUsage: ShadersTest <shaderType>, where <shaderType> argument can be one of the following:\n");
		System.out.println("none\t- Use fixed function rendering.");
		System.out.println("vp\t- Use ARB_vertex_program (low-level) only.");
		System.out.println("vsh\t- Use ARB_vertex_shader (GLSL) only.");
		System.out.println("fp\t- Use ARB_vertex_program + ARB_fragment_program (low-level).");
		System.out.println("fsh\t- Use ARB_vertex_shader + ARB_fragment_shader (GLSL).");

		cleanup();
		System.exit(-1);
	}

	static void kill(String reason) {
		System.out.println("The ShaderTest program was terminated because an error occurred.\n");
		System.out.println("Reason: " + (reason == null ? "Unknown" : reason));

		cleanup();
		System.exit(-1);
	}

	static void kill(String reason, Throwable t) {
		System.out.println("The ShaderTest program was terminated because an exception occurred.\n");
		System.out.println("Reason: " + (reason == null ? "Unknown" : reason));

		System.out.println("Exception message: " + t.getMessage());

		cleanup();
		System.exit(-1);
	}

}
