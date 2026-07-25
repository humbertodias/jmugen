package org.lee.mugen.core.renderer.game;

import org.lee.mugen.sprite.base.AbstractSprite;

public class SuperpauseRender extends SparkhitRender {

	public SuperpauseRender(AbstractSprite sprite) {
		super(sprite);
	}


	@Override
	public int getPriority() {
		return 5000;
	}

}
