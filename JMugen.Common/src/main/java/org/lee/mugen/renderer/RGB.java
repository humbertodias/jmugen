package org.lee.mugen.renderer;

import java.io.Serializable;

public class RGB implements Serializable {
	private float a = 255;
	
	private float r = 255;
	private float g = 255;
	private float b = 255;
	
//	private boolean aUpdated;
//	
//	private boolean rUpdated;
//	private boolean gUpdated;
//	private boolean bUpdated;
	
	
	public RGB(float r, float g, float b) {
		this(r, g, b, 255);
	}
	public RGB(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		
//		aUpdated = true;
//		
//		rUpdated = true;
//		gUpdated = true;
//		bUpdated = true;
		
	}
	public RGB() {
	}
	public float getA() {
		return a;
	}
	public void setA(float a) {
		this.a = a;
	}
	public float getB() {
		return b;
	}
	public void setB(float b) {
		this.b = b;
	}
	public float getG() {
		return g;
	}
	public void setG(float g) {
		this.g = g;
	}
	public float getR() {
		return r;
	}
	public void setR(float r) {
		this.r = r;
	}
	public RGB mul(RGB rgb) {
		return new RGB(r * rgb.getR(), g * rgb.getG(), b * rgb.getB(), a * rgb.getA());
	}
	public RGB add(RGB rgb) {
		return new RGB(r + rgb.getR(), g + rgb.getG(), b + rgb.getB(), a + rgb.getA());
	}
	public RGB max(float color) {
		return new RGB(Math.min(r, color), Math.min(g, color), Math.min(b, color), a);
	}
	
	public RGB pow(float pow) {
		return new RGB((float)Math.pow(r, pow), (float)Math.pow(g, pow), (float)Math.pow(b, pow), (float)Math.pow(a, pow));
	}
	
	public RGB mul(float coef) {
		return new RGB(r * coef, g * coef, b * coef, a * coef);
	}
	
	@Override
	public String toString() {
		return "r=" + r + ";b=" + b + ";g=" + g + ";a=" + a;
	}
}
