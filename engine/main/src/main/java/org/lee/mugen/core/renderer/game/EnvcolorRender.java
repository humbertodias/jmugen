package org.lee.mugen.core.renderer.game;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.renderer.Renderable;
import org.lee.mugen.stage.Stage;

public class EnvcolorRender implements Renderable {

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
		RGB rgb = GameFight.getInstance().getGlobalEvents().getEnvcolor().getValue();
		Stage stage = GameFight.getInstance().getStage();
		GraphicsWrapper.getInstance().setColor(rgb.getR(), rgb.getG(), rgb.getB());
		GraphicsWrapper.getInstance().fillRect(0, 0, stage.getCamera().getWidth(), stage.getCamera().getHeight());
		
	}

	public void setPriority(int p) {
		
	}

}
