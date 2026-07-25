package org.lee.mugen.sprite.entity;

import java.io.Serializable;

public class Sparkno implements Serializable {
	private int action;
	private boolean isSpriteUse;
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public boolean isSpriteUse() {
		return isSpriteUse;
	}
	public void setSpriteUse(boolean isSpriteUse) {
		this.isSpriteUse = isSpriteUse;
	}
	
}
