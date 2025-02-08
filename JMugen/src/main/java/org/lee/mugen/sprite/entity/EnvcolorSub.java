package org.lee.mugen.sprite.entity;

import org.lee.mugen.renderer.RGB;
import org.lee.mugen.sprite.parser.Parser;

public class EnvcolorSub {
	private RGB add = new RGB();
	private int time;
	private boolean under;
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

	public boolean isUnder() {
		return under;
	}
	public void setUnder(boolean under) {
		this.under = under;
	}
	public RGB getValue() {
		return add;
	}
	public boolean isUse() {
		return time > 0;
	}
	public void addTime(int i) {
		time += i;
	}	
	public int decreaseTime() {
		if (time == 0)
			return 0;
		if (time == -1)
			return -1;
		return time--;
	}
	public void setValue(Object...params) {
		int count = params.length;
		int pos = 0;
		if (count > 0) {
			Object p = params[pos++];
			add.setR(p instanceof Number? Parser.getIntValue(p): new Integer(p.toString()));
		}
		if (count > pos) {
			Object p = params[pos++];
			add.setG(p instanceof Number? Parser.getIntValue(p): new Integer(p.toString()));
		}
		if (count > pos) {
			Object p = params[pos++];
			add.setB(p instanceof Number? Parser.getIntValue(p): new Integer(p.toString()));
		}
	}
	public void init() {
		under = false;
		setValue(255,255,255);
		time = 0;
		
	}
}
