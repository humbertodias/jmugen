package org.lee.mugen.core;

import java.util.List;

import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.renderer.Renderable;

/**
 * I take this from slick. Why not using slick because i don't need all slick implementation
 * And later i want the possibilité of plug in renderer. So SlickRender
 * 
 * The main game interface that should be implemented by any game being developed
 * using the container system. There will be some utility type sub-classes as development
 * continues.
 * 
 * @see org.newdawn.slick.BasicGame
 *
 * @author kevin
 */
public interface Game {
	/**
	 * Initialise the game. This can be used to load static resources. It's called
	 * before the game loop starts
	 * 
	 * @param container The container holding the game
	 * @throws Exception Throw to indicate an internal error
	 */
	public void init(GameWindow container) throws Exception;
	public void reInit(GameWindow container) throws Exception;
	
	/**
	 * Update the game logic here. No rendering should take place in this method
	 * though it won't do any harm. 
	 * 
	 * @param container The container holing this game
	 * @param delta The amount of time thats passed since last update in milliseconds
	 * @throws Exception Throw to indicate an internal error
	 */
	public void update(int delta) throws Exception;
	
	/**
	 * Render the game's screen here. 
	 * 
	 * @param container The container holing this game
	 * @param g The graphics context that can be used to render. However, normal rendering
	 * routines can also be used.
 	 * @throws Exception Throw to indicate a internal error
	 */
	public void render() throws Exception;
	
	public Game getNext() throws Exception;
	
	public void free();

}
