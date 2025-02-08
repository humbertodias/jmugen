package org.lee.mugen.test;

import org.lee.mugen.core.GameMenu;
import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.renderer.GraphicsWrapper;

public class TestMenu {
	public static void main(String[] args) throws Exception {
		GameMenu gameMenu = GameMenu.getInstance();
		
		GraphicsWrapper.init();
		final GameWindow gameWindow = GraphicsWrapper.getInstance().getInstanceOfGameWindow();
		gameWindow.setGameWindowCallback(gameMenu);
		gameWindow.start();

		
		
	}
}
