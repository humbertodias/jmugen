package org.lwjgl.test.opengl.shaders;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

abstract class Shader {

	protected static ByteBuffer fileBuffer = BufferUtils.createByteBuffer(1024 * 10);

	protected Shader() {}

	abstract void render();
	abstract void cleanup();

	protected static ByteBuffer getShaderText(String file) {
		try {
			ClassLoader loader = Shader.class.getClassLoader();
			InputStream inputStream = loader.getResourceAsStream("org/lwjgl/test/opengl/shaders/" + file);

			if (inputStream == null)
				kill("A shader source file could not be found: " + file);

			BufferedInputStream stream = new BufferedInputStream(inputStream);
			byte character;
			while ((character = (byte) stream.read()) != -1)
				fileBuffer.put(character);

			stream.close();

			fileBuffer.flip();
			ByteBuffer shader = BufferUtils.createByteBuffer(fileBuffer.limit());
			shader.put(fileBuffer);
			shader.flip();

			fileBuffer.clear();
			return shader;
		} catch (IOException e) {
//			ShadersTest.kill("Failed to read the shader source file: " + file, e);
			return null;
		}
	}

	protected static void checkLinkStatus(int programID) {
		int status = org.lwjgl.opengl.GL20.glGetProgrami(programID, org.lwjgl.opengl.GL20.GL_LINK_STATUS);
		if (status == org.lwjgl.opengl.GL11.GL_FALSE) {
			String log = org.lwjgl.opengl.GL20.glGetProgramInfoLog(programID);
//			ShadersTest.kill("Shader program linking failed:\n" + log);
		}
	}

	static void kill(String reason) {
		System.out.println("The ShaderTest program was terminated because an error occured.\n");
		System.out.println("Reason: " + (reason == null ? "Unknown" : reason));

//		cleanup();
		System.exit(-1);
	}
}
