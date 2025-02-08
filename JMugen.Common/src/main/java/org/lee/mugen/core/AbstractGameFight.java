package org.lee.mugen.core;

import java.util.List;

import org.lee.mugen.renderer.Renderable;

public interface AbstractGameFight extends Game {


	public void addRender(Renderable r);
	public List<Renderable> getRenderables();

	public void renderDebugInfo();
	
	public static enum DebugAction {
		SWICTH_PLAYER_DEBUG_INFO, 
		SHOW_HIDE_CNS, SHOW_HIDE_ATTACK_CNS, 
		INCREASE_FPS, DECREASE_FPS, RESET_FPS,
		DEBUG_PAUSE, PAUSE_PLUS_ONE_FRAME,
		
		INIT_PLAYER, 
		START_OVER, RELOAD_STAGE, RELOAD_PLAYERS, 
		DISPLAY_HELP, EXPLOD_DEBUG_INFO
	}
	public void onDebugAction(DebugAction action);

	
	// TODO : delete this
	public void displayPendingScreeen();
}
