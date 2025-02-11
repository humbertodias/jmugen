package org.lee.mugen.renderer.jogl.shader;

import javax.media.opengl.GL;

import org.lee.mugen.renderer.RGB;

public class AfterImageShader extends Shader {



	private int palbrightUniPos;
	private int palcontrastUniPos;
	private int palpostbrightUniPos;
	
	private int mulUniPos;
	private int addUniPos;
	
	private int typeUniPos;
	
	private int alphaUniPos;
	
	
	public AfterImageShader() {
		super("afterimage");
	}

	public void compileShader(GL gl) {
		super.compileShader(gl);
		
		gl.glGetObjectParameterivARB(programID,
				GL.GL_OBJECT_LINK_STATUS_ARB, programBuffer);
		if (programBuffer.get(0) == GL.GL_FALSE)
			System.err.println("A linking error occured in a shader program.");
		palbrightUniPos = getUniformLocation(gl, programID, "palbright");
		palcontrastUniPos = getUniformLocation(gl, programID, "palcontrast");
		palpostbrightUniPos = getUniformLocation(gl, programID, "palpostbright");
		
		mulUniPos = getUniformLocation(gl, programID, "mul");
		addUniPos = getUniformLocation(gl, programID, "add");
		typeUniPos = getUniformLocation(gl, programID, "type");
		alphaUniPos = getUniformLocation(gl, programID, "alpha");
	}

	public void render(GL gl, RGB palbright, RGB palcontrast, RGB palpostbright, RGB add, RGB mul, float type, float alpha) {
		gl.glUseProgramObjectARB(programID);
		
		gl.glUniform4fARB(palbrightUniPos, palbright.getR(), palbright.getG(), palbright.getB(), palbright.getA());
		gl.glUniform4fARB(palcontrastUniPos, palcontrast.getR(), palcontrast.getG(), palcontrast.getB(), palcontrast.getA());
		gl.glUniform4fARB(palpostbrightUniPos, palpostbright.getR(), palpostbright.getG(), palpostbright.getB(), palpostbright.getA());

		gl.glUniform4fARB(addUniPos, add.getR(), add.getG(), add.getB(), add.getA());
		gl.glUniform4fARB(mulUniPos, mul.getR(), mul.getG(), mul.getB(), mul.getA());
		
		gl.glUniform1fARB(typeUniPos, type);

		gl.glUniform1fARB(alphaUniPos, alpha);
	}


}
