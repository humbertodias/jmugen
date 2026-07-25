package org.lee.mugen.fight.intro.entity;

import org.lee.mugen.renderer.RGB;
import org.lee.mugen.util.BeanTools;

public class Fade {
	private RGB col;
	private int time;
	private int originalTime;
	
	public RGB getCol() {
		return col;
	}

	public void setCol(RGB col) {
		this.col = col;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void parse(String name, String value) {
		if (name.equals("col")) {
			col = (RGB) BeanTools.getConvertersMap().get(RGB.class).convert(value);
		} else if (name.equals("time")) {
			time = Integer.parseInt(value);
			originalTime = time;
		}
		
	}

	public int getOriginalTime() {
		return originalTime;
	}

	public void init() {
		time = originalTime;
	}
	public void decrease() {
		if (time > 0)
			time--;
	}
}
