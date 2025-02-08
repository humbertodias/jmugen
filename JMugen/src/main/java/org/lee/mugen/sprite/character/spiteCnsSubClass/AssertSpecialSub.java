package org.lee.mugen.sprite.character.spiteCnsSubClass;

import java.io.Serializable;

import org.lee.mugen.sprite.cns.type.function.Assertspecial.Flag;

public class AssertSpecialSub implements Serializable {
	private int time = 1;
	private Flag flag;
	private Flag flag2;
	private Flag flag3;
	
	public boolean isValid() {
		return time >= 0;
	}
	
	public void decrease() {
		if (time >= 0)
			time--;
	}
	
	public Flag getFlag() {
		return flag;
	}
	public void setFlag(Flag flag) {
		this.flag = flag;
	}
	public Flag getFlag2() {
		return flag2;
	}
	public void setFlag2(Flag flag2) {
		this.flag2 = flag2;
	}
	public Flag getFlag3() {
		return flag3;
	}
	public void setFlag3(Flag flag3) {
		this.flag3 = flag3;
	}
}
