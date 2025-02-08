package org.lee.mugen.core.renderer.game.fight;

import org.lee.mugen.renderer.Renderable;

public class FightdefRender implements Renderable {
	FaceRender faceRender = new FaceRender();
	LifeBarRender lifeBarRender = new LifeBarRender();
	PowerBarRender powerBarRender = new PowerBarRender();
	TimeRender timeRender = new TimeRender();
	
	
	@Override
	public int getPriority() {
		return 10;
	}

	@Override
	public boolean isProcess() {
		return true;
	}

	@Override
	public boolean remove() {
		return false;
	}
	@Override
	public void setPriority(int p) {
		
	}

	
	@Override
	public void render() {
		for (int layer = 0; layer < 5; layer++) {
			faceRender.setLayer(layer);
			lifeBarRender.setLayer(layer);
			powerBarRender.setLayer(layer);
			timeRender.setLayer(layer);
			
			
			lifeBarRender.render();
			powerBarRender.render();
			timeRender.render();
			faceRender.render();
		}
		
	}


}
