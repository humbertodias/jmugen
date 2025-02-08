package org.lee.mugen.sprite.entity;

import java.io.Serializable;

public class Shake implements Cloneable, Serializable {
	private int time;
	private float freq;
	private int ampl;
	private float phase;
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public int getAmpl() {
		return ampl;
	}
	public void setAmpl(int ampl) {
		this.ampl = ampl;
	}
	public float getFreq() {
		return freq;
	}
	public void setFreq(float freq) {
		this.freq = freq;
	}
	public float getPhase() {
		return phase;
	}
	public void setPhase(float phase) {
		this.phase = phase;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public void addTime(int i) {
		time += i;
	}
		
}
