package org.lee.mugen.test;

import org.lee.mugen.fight.intro.GameScene;
import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.renderer.GraphicsWrapper;

public class TestScene {
	public static void main(String[] args) throws Exception {
		GameScene gameScene = new GameScene();
		
		GraphicsWrapper.init();
		final GameWindow gameWindow = GraphicsWrapper.getInstance().getInstanceOfGameWindow();
		gameWindow.setGameWindowCallback(gameScene);
		gameWindow.start();
	}
}
