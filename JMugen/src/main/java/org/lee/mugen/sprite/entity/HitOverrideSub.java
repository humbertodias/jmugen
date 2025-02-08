package org.lee.mugen.sprite.entity;

import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.AttrClass;

public class HitOverrideSub {

	private AttrClass attr;
	private int slot = 0;
	private int stateno = -1;
	private int time = 1;
	private int forceair = 0;
	public AttrClass getAttr() {
		return attr;
	}
	public void setAttr(AttrClass attr) {
		this.attr = attr;
	}
	public int getSlot() {
		return slot;
	}
	public void setSlot(int slot) {
		this.slot = slot;
	}
	public int getStateno() {
		return stateno;
	}
	public void setStateno(int stateno) {
		this.stateno = stateno;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getForceair() {
		return forceair;
	}
	public void setForceair(int forceair) {
		this.forceair = forceair;
	}
	public void decreaseTime() {
		time--;
	}
	public boolean isValid() {
		return time == -1 || time > 0;
	}
}
