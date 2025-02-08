package org.lee.mugen.sprite.entity;

import org.lee.mugen.sprite.character.Sprite;

public class PauseSub {
	private int time;
	private int movetime;
	
	public int getMovetime() {
		return movetime;
	}
	public void setMovetime(int movetime) {
		this.movetime = movetime;
	}

	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public void decreasePause() {
		if (time >= 0)
			time--;
		if (movetime >= 0)
			movetime--;
	}
	public boolean isPauseMoveTime() {
		return movetime > 0;
	}
	private Sprite spriteFrom;

	public Sprite getSpriteFrom() {
		return spriteFrom;
	}
	public void setSpriteFrom(Sprite spriteFrom) {
		this.spriteFrom = spriteFrom;
	}
}
