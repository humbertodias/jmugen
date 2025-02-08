package org.lee.mugen.core.renderer.game;

import org.lee.mugen.renderer.Renderable;
import org.lee.mugen.sprite.entity.MakeDustSpriteManager;

public interface IMakedustRender extends Renderable {
	public static final int ACTION_NUMBER = 120;
	MakeDustSpriteManager getMakeDustSpriteManager();
	
}
