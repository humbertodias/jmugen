package org.lee.mugen.sprite.entity;

import org.lee.mugen.sprite.character.Sprite;


public abstract class BindToSub implements Cloneable {
	private Sprite caller;
	int facing;
	int time = 1;
	PointF pos = new PointF();

	@Override
	public Object clone() throws CloneNotSupportedException {
		BindToSub clone = (BindToSub) super.clone();
		clone.pos = (PointF) pos.clone();
		return clone;
	}

	public boolean isBindOriginal() {
		return false;
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


	public abstract PointF getPos();
	
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