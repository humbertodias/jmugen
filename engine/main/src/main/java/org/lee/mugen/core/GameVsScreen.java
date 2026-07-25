package org.lee.mugen.core;

import org.lee.mugen.core.gameSelect.GameSelect;
import org.lee.mugen.core.renderer.game.vsScreen.VsScreeenRender;
import org.lee.mugen.core.sound.SoundSystem;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.fight.system.TitleInfo;
import org.lee.mugen.fight.system.VsScreen;
import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.renderer.GraphicsWrapper;

public class GameVsScreen implements Game {

	public GameVsScreen(GameSelect gs) {
		this.gameSelect = gs;
	}
	@Override
	public void free() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Game getNext() throws Exception {
		return next == null?this: next;
	}

	@Override
	public void init(GameWindow container) throws Exception {
		SoundSystem.SoundBackGround.stopMusic();
		SoundSystem.SoundBackGround.playMusic(JMugenConstant.RESOURCE + MugenSystem.getInstance().getMusic().getVs$bgm());

		render = new VsScreeenRender(this);	
		MugenSystem ms = MugenSystem.getInstance();
		VsScreen vsScreen = ms.getVsScreen();
		vsScreen.init();
	}

	@Override
	public void reInit(GameWindow container) throws Exception {
		// TODO Auto-generated method stub
		
	}
	VsScreeenRender render;
	@Override
	public void render() throws Exception {
		render.render();		
	}
	Game next = null;
	GameSelect gameSelect;
	@Override
	public void update(int delta) throws Exception {
		MugenSystem ms = MugenSystem.getInstance();
		VsScreen vsScreen = ms.getVsScreen();
		if (vsScreen.getTime() <= 0) {
			if (vsScreen.getPhase() != TitleInfo.END) {
				vsScreen.setPhase(TitleInfo.LEAVE);
			} else {
				GraphicsWrapper.getInstance().setAlpha(1);
				next = gameSelect.getGameFight();
				return;
			}
		}
		if (vsScreen.getPhase() == TitleInfo.NOTHING) {
			vsScreen.setPhase(TitleInfo.ENTER);
		}
		vsScreen.process();
		ms.getVersusBackground().process();
	}

	public String getName(int i) {
		return gameSelect.getSelectedSpriteName(i);
	}
}
