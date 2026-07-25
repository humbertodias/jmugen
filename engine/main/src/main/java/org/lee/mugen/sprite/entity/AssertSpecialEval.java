package org.lee.mugen.sprite.entity;

import org.lee.mugen.sprite.character.spiteCnsSubClass.AssertSpecialSub;

public class AssertSpecialEval {
	private AssertSpecialSub assertspecial;
	private String spriteId;
	public AssertSpecialEval(AssertSpecialSub assertspecial, String spriteId) {
		this.assertspecial = assertspecial;
		this.spriteId = spriteId;
	}
	
	public AssertSpecialSub getAssertspecial() {
		return assertspecial;
	}

	public String getSpriteId() {
		return spriteId;
	}

}
