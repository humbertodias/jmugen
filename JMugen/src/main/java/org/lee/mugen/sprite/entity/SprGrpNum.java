package org.lee.mugen.sprite.entity;

import java.io.Serializable;

public class SprGrpNum implements Serializable {
	int spritegrp;
	int spriteno;
	public SprGrpNum(int spritegrp, int spriteno) {
		this.spritegrp = spritegrp;
		this.spriteno = spriteno;
	}
	public SprGrpNum() {
	}
	public int getSpritegrp() {
		return spritegrp;
	}
	public void setSpritegrp(int spritegrp) {
		this.spritegrp = spritegrp;
	}
	public int getSpriteno() {
		return spriteno;
	}
	public void setSpriteno(int spriteno) {
		this.spriteno = spriteno;
	}
	
}
