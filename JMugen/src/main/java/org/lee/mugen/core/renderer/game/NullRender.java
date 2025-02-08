package org.lee.mugen.core.renderer.game;

import org.lee.mugen.renderer.Renderable;
import org.lee.mugen.sprite.entity.AfterImageSprite;
import org.lee.mugen.sprite.entity.MakeDustSpriteManager;

public class NullRender implements Renderable, IMakedustRender, IAfterimageRender, IBackgroundRenderer {
	public static final NullRender instance = new NullRender();

	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isProcess() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean remove() {
		// TODO Auto-generated method stub
		return false;
	}

	public void render() {
		// TODO Auto-generated method stub
		
	}

	public void setPriority(int p) {
		// TODO Auto-generated method stub
		
	}

	public MakeDustSpriteManager getMakeDustSpriteManager() {
		// TODO Auto-generated method stub
		return null;
	}

	public AfterImageSprite getAfterImageSprite() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLayerDisplay() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setLayerDisplay(int layerDisplay) {
		// TODO Auto-generated method stub
		
	}
}
