package org.lee.mugen.core;

import java.util.List;

import org.lee.mugen.background.Background;
import org.lee.mugen.core.gameSelect.GameSelect;
import org.lee.mugen.core.renderer.game.system.TitleInfoRender;
import org.lee.mugen.core.sound.SoundSystem;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.fight.system.TitleInfo;
import org.lee.mugen.fight.system.elem.ItemName;
import org.lee.mugen.fight.system.elem.Menu;
import org.lee.mugen.input.CmdProcDispatcher;
import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.renderer.Renderable;

public class GameMenu implements Game {

	static GameMenu instance;

	public static GameMenu getInstance() {
		if (instance == null)
			instance = new GameMenu();
		return instance;
	}

	boolean fire;
	private int incIndexForMove = 0;
	private float lastIndexForMove = 0;
	private int lastStartIndex = 0;
	private Game next;
	
	private TitleInfoRender render;
	private float speedSwitch = 0.3f;
	private int time = 0;
	
	@Override
	public void free() {
		// Not need to free this menu is always used
	}
	public int getaddPixel() {
		return incIndexForMove;
	}
	public int getLastStartIndex() {
		return lastStartIndex;
	}
	
	private Menu getMenu() {
		return MugenSystem.getInstance().getTitleInfo().getMenu();
	}
	@Override
	public Game getNext() throws Exception {
		return next == null?this: next;
	}

	public int getTime() {
		return time;
	}

	private TitleInfo getTitleInfo() {
		return MugenSystem.getInstance().getTitleInfo();
	}

	@Override
	public void init(GameWindow container) throws Exception {
		SoundSystem.SoundBackGround.stopMusic();
		SoundSystem.SoundBackGround.playMusic(JMugenConstant.RESOURCE + MugenSystem.getInstance().getMusic().getTitle$bgm());
		render = new TitleInfoRender(this);
		fire = false;
		next = null;
		time = 0;
		getTitleInfo().init();
		getTitleInfo().setPhase(TitleInfo.ENTER);
		container.clearListener();
		container.addActionListener(new GameWindow.MugenKeyListener() {
			long lastPress = 0;
			@Override
			public void action(int key, boolean isPress) {
				long now = System.currentTimeMillis();
				
				if (!isPress)
					return;
				
				for (String id: CmdProcDispatcher.getSpriteDispatcherMap().keySet()) {
					CmdProcDispatcher cmdOne = CmdProcDispatcher.getSpriteDispatcherMap().get(id);
					if (cmdOne.getDown() == key) {
						lastPress = System.currentTimeMillis();
						lastStartIndex = getMenu().getItemname().startIndex();
						getMenu().getItemname().increaseCurrentIndex();
						
						int grp = MugenSystem.getInstance().getTitleInfo().getMenu().getCursor$move$snd().getSnd_grp();
						int num = MugenSystem.getInstance().getTitleInfo().getMenu().getCursor$move$snd().getSnd_item();
						byte[] snd = MugenSystem.getInstance().getFiles().getSnd().getGroup(grp).getSound(num);
						SoundSystem.Sfx.playSnd(snd);
					} else if (cmdOne.getUp() == key) {
						lastPress = System.currentTimeMillis();
						lastStartIndex = getMenu().getItemname().startIndex();
						getMenu().getItemname().decreaseCurrentIndex();
						int grp = MugenSystem.getInstance().getTitleInfo().getMenu().getCursor$move$snd().getSnd_grp();
						int num = MugenSystem.getInstance().getTitleInfo().getMenu().getCursor$move$snd().getSnd_item();
						byte[] snd = MugenSystem.getInstance().getFiles().getSnd().getGroup(grp).getSound(num);
						SoundSystem.Sfx.playSnd(snd);
					} else if (isButton(cmdOne, key)) {
						fire = true;
						int grp = MugenSystem.getInstance().getTitleInfo().getMenu().getCursor$done$snd().getSnd_grp();
						int num = MugenSystem.getInstance().getTitleInfo().getMenu().getCursor$done$snd().getSnd_item();
						byte[] snd = MugenSystem.getInstance().getFiles().getSnd().getGroup(grp).getSound(num);
						SoundSystem.Sfx.playSnd(snd);
					}
				}
			}
			private boolean isButton(CmdProcDispatcher cmd, int key) {
				boolean result = 
					cmd.getA() == key
					|| cmd.getB() == key
					|| cmd.getB() == key
					|| cmd.getX() == key
					|| cmd.getY() == key
					|| cmd.getZ() == key
					|| cmd.getAbc() == key
					|| cmd.getXyz() == key;
				return result;
			}});
	}
	
	@Override
	public void reInit(GameWindow container) throws Exception {
		fire = false;
		getMenu().getItemname().setCurrentIndex(0);
	}

	@Override
	public void render() throws Exception {
		render.render();
	}

	private void selectNext() {
		if (fire) {
			int index = getMenu().getItemname().getCurrentIndex();
			switch (index) {
			case ItemName.arcade:
				next = GameSelect.getInstance();
				GameSelect.getInstance().setTilte("arcade");
				break;
			case ItemName.versus:
				
				break;
			
			case ItemName.teamarcade:
				
				break;
			case ItemName.teamversus:
				
				break;
			case ItemName.teamcoop:
				
				break;
			case ItemName.survival:
				
				break;
			case ItemName.survivalcoop:
				
				break;
			case ItemName.training:
				
				break;
			case ItemName.watch:
				
				break;
			case ItemName.options:
				
				break;
			case ItemName.exit:
				
				break;
			default:
				break;
			}
			fire = false;
		}
	}

	@Override
	public void update(int delta) throws Exception {
		
		if (fire) {
			if (getTitleInfo().getPhase() != TitleInfo.END) {
				getTitleInfo().setPhase(TitleInfo.LEAVE);
			} else {
				selectNext();
				return;
			}
		}
		if (getTitleInfo().getPhase() == TitleInfo.NOTHING) {
			getTitleInfo();
			getTitleInfo().setPhase(TitleInfo.ENTER);
		}
		
		Background br = MugenSystem.getInstance().getTitleBackground();
		br.process();
		getTitleInfo().process();
		int startIndex = getMenu().getItemname().startIndex();
		if (lastStartIndex + lastIndexForMove < startIndex) {
			lastIndexForMove += speedSwitch;
			incIndexForMove -= speedSwitch * 10;
			if (lastStartIndex + lastIndexForMove > lastStartIndex + 1) {
				lastStartIndex = lastStartIndex + 1;
				lastIndexForMove = 0f;
				incIndexForMove = 0;
			}
		
		} else if (lastStartIndex + lastIndexForMove > startIndex) {
			lastIndexForMove -= speedSwitch;
			incIndexForMove += speedSwitch * 10;
			if (lastStartIndex + lastIndexForMove < lastStartIndex - 1) {
				lastStartIndex = lastStartIndex - 1;
				lastIndexForMove = 0f;
				incIndexForMove = 0;
			}
		} else {
			incIndexForMove = 0;
			lastIndexForMove = 0;
			lastStartIndex = getMenu().getItemname().startIndex();
		}
//		lastIndexForMove = ((int)(lastIndexForMove * 100))/100f;
		time++;
	}

}
