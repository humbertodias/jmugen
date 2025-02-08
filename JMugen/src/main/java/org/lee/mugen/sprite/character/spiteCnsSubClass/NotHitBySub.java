package org.lee.mugen.sprite.character.spiteCnsSubClass;

import java.io.Serializable;

import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.AttrClass;

public class NotHitBySub implements Cloneable, Serializable {

	private AttrClass value;
	private AttrClass value2;
	
	
	private int time;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public AttrClass getValue() {
		return value;
	}
	public void setValue(AttrClass value) {
		this.value = value;
	}
	public AttrClass getValue2() {
		return value2;
	}
	public void setValue2(AttrClass value2) {
		this.value2 = value2;
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
