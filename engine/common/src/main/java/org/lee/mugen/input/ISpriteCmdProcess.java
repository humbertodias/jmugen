package org.lee.mugen.input;

public interface ISpriteCmdProcess {

	public abstract void addSprite(String spriteId);

	public abstract void remove(String spriteId);

	public abstract void process();
	public abstract void process(String SpriteId);

	public abstract void keyPressed(int keycode);

	public abstract void keyReleased(int keycode);
	
	public abstract int[] getKeys();
	
	

}