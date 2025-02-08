package org.lee.mugen.sprite.entity;

import java.io.Serializable;

import org.lee.mugen.sprite.character.Sprite;


public class BindToParentSub implements Cloneable, Serializable {
	private Sprite caller;
	int facing;
	int time = 1;
	PointF pos = new PointF();

	@Override
	public Object clone() throws CloneNotSupportedException {
		BindToParentSub clone = (BindToParentSub) super.clone();
		clone.pos = (PointF) pos.clone();
		return clone;
	}



	public int getFacing() {
		return facing;
	}



	public void setFacing(int facing) {
		this.facing = facing;
	}



	public Boolean isFlip() {
		if (getFacing() == 0) {
			return null;
		} else if (getFacing() == 1) {
			return caller.isFlip();
		} else if (getFacing() == -1) {
			return !caller.isFlip();
		}
		return null;
	}


	public PointF getPos() {
		return pos;
	}

	public void setPos(PointF pos) {
		this.pos = pos;
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

	public Sprite getCaller() {
		return caller;
	}

	public void setCaller(Sprite caller) {
		this.caller = caller;
	}

}