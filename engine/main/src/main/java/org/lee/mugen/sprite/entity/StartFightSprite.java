package org.lee.mugen.sprite.entity;

import org.lee.mugen.sprite.base.AbstractSprite;

public class StartFightSprite extends AbstractSprite {
	enum STATE {
		PRE_INTRO, INTRO, DISPLAY_ROUND, DISPLAY_FIGHT, DISPLAY_END_ROUND, DISPLAY_END_MATCH
	};
	
	private STATE state = STATE.PRE_INTRO;
	
	@Override
	public float getRealXPos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getRealYPos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isFlip() {
		return false;
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}

}
