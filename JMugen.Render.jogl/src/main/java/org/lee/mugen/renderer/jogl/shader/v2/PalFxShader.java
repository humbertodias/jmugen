package org.lee.mugen.renderer.jogl.shader;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import org.lee.mugen.renderer.RGB;

public class PalFxShader extends Shader {

	private int addUniPos;
	private int mulUniPos;
	private int amplUniPos;
	private int alphaUniPos;

	public PalFxShader() {
		super("palfx");
	}

	@Override
	public void compileShader(GL2 gl) {
		super.compileShader(gl);

		// Use modern method to check the shader program link status
		gl.getGL2().glGetProgramiv(programID, GL2.GL_LINK_STATUS, programBuffer);
		if (programBuffer.get(0) == GL.GL_FALSE) {
			System.err.println("A linking error occurred in the shader program.");

			// Optional: Retrieve and display the program info log for debugging
			int[] infoLogLength = new int[1];
			gl.getGL2().glGetProgramiv(programID, GL2.GL_INFO_LOG_LENGTH, infoLogLength, 0);
			if (infoLogLength[0] > 0) {
				byte[] infoLog = new byte[infoLogLength[0]];
				gl.getGL2().glGetProgramInfoLog(programID, infoLogLength[0], null, 0, infoLog, 0);
				System.err.println(new String(infoLog));
			}
		}

		// Retrieve uniform locations
		addUniPos = getUniformLocation(gl, programID, "add");
		mulUniPos = getUniformLocation(gl, programID, "mul");
		amplUniPos = getUniformLocation(gl, programID, "ampl");
		alphaUniPos = getUniformLocation(gl, programID, "alpha");
	}

	public void render(GL2 gl, RGB add, RGB mul, RGB ampl, float alpha) {
		// Use modern method to activate the shader program
		gl.glUseProgram(programID);

		// Pass values to the uniforms
		gl.glUniform4f(addUniPos, add.getR(), add.getG(), add.getB(), add.getA());
		gl.glUniform4f(mulUniPos, mul.getR(), mul.getG(), mul.getB(), mul.getA());
		gl.glUniform4f(amplUniPos, ampl.getR(), ampl.getG(), ampl.getB(), ampl.getA());
		gl.glUniform1f(alphaUniPos, alpha);

		// Optional: Deactivate the shader after use
		gl.glUseProgram(0);
	}
}
