package org.lee.mugen.renderer;

import java.awt.event.ActionListener;

import org.lee.mugen.core.Game;
import org.lee.mugen.input.ISpriteCmdProcess;
import org.lee.mugen.input.Key;

/**
 * The window in which the game will be displayed. This interface exposes just
 * enough to allow the game logic to interact with, while still maintaining an
 * abstraction away from any physical implementation of windowing (i.e. AWT, LWJGL)
 *
 * @author Kevin Glass
 */
public interface GameWindow {
	public MugenTimer getTimer();
	/**
	 * Set the title of the game window
	 * 
	 * @param title The new title for the game window
	 */
	public void setTitle(String title);
	
	/**
	 * Set the game display resolution
	 * 
	 * @param x The new x resolution of the display
	 * @param y The new y resolution of the display
	 */
	public void setResolution(int x,int y);
	
	/**
	 * Start the game window rendering the display
	 * @throws Exception 
	 */
	public void start() throws Exception;
	
	/**
	 * Set the callback that should be notified of the window
	 * events.
	 * 
	 * @param callback The callback that should be notified of game
	 * window events.
	 */
	public void setGameWindowCallback(Game callback);
	
	public void addSpriteKeyProcessor(ISpriteCmdProcess scp);
	
	public MouseCtrl getMouseStatus();
	
	public static class MouseCtrl {
		int x;
		int y;
		public void setX(int x) {
			this.x = x;
		}
		public void setY(int y) {
			this.y = y;
		}
		public void setLeftPress(boolean isLeftPress) {
			this.isLeftPress = isLeftPress;
		}

		public boolean isRightPress() {
			return isRightPress;
		}
		public void setRightPress(boolean isRightPress) {
			this.isRightPress = isRightPress;
		}
		public boolean isRightRelease() {
			return isRightRelease;
		}
		public void setRightRelease(boolean isRightRelease) {
			this.isRightRelease = isRightRelease;
		}
		public boolean isLeftPress() {
			return isLeftPress;
		}

		boolean isLeftPress;
		boolean isLeftRelease;
		public boolean isLeftRelease() {
			return isLeftRelease;
		}
		public void setLeftRelease(boolean isLeftRelease) {
			this.isLeftRelease = isLeftRelease;
		}
		boolean isRightPress;
		boolean isRightRelease;
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public boolean isLeftClick() {
			return isLeftPress;
		}

		
		
	}
	public int getKeyEsc();
	public int getKeyF1();
	public int getKeyF2();
	public int getKeyF3();

	
	public static interface MugenKeyListener {
		public void action(int key, boolean isPress);
	}

	public void addActionListener(MugenKeyListener al);
	public void clearListener();
	public void setRender(boolean v);
	public boolean isRender();
	public void render();
	void removeSpriteKeysProcessors();
	
	// TODO : add getTimer and add Timer interface
}