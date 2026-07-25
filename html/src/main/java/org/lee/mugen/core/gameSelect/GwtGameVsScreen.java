package org.lee.mugen.core.gameSelect;

import org.lee.mugen.background.Background;
import org.lee.mugen.core.Game;
import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.core.renderer.game.vsScreen.GwtVsScreenRender;
import org.lee.mugen.core.sound.SoundSystem;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.fight.system.VsScreen;
import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.renderer.GraphicsWrapper;

/** GWT versus screen: portraits, names, then {@link org.lee.mugen.core.GwtGameFight}. */
public class GwtGameVsScreen implements Game {

    private final GwtGameSelect gameSelect;
    private GwtVsScreenRender vsRender;
    private Game next;
    private boolean fightLaunched;

    public GwtGameVsScreen(GwtGameSelect gameSelect) {
        this.gameSelect = gameSelect;
    }

    @Override
    public void free() {
        vsRender = null;
    }

    @Override
    public Game getNext() throws Exception {
        return next == null ? this : next;
    }

    @Override
    public void init(GameWindow container) throws Exception {
        next = null;
        fightLaunched = false;
        MugenSystem ms = MugenSystem.getInstance();
        SoundSystem.SoundBackGround.stopMusic();
        String bgm = ms.getMusic().getVs$bgm();
        if (bgm != null && bgm.length() > 0) {
            SoundSystem.SoundBackGround.playMusic(JMugenConstant.RESOURCE + bgm);
        }
        VsScreen vsScreen = ms.getVsScreen();
        if (vsScreen != null) {
            vsScreen.resetState();
        }
        Background versus = ms.getVersusBackground();
        if (versus != null) {
            for (org.lee.mugen.background.BG bg : versus.getBgs()) {
                if (!bg.isInit()) {
                    bg.init();
                    bg.setInit(true);
                }
            }
        }
        vsRender = new GwtVsScreenRender(this);
    }

    @Override
    public void reInit(GameWindow container) throws Exception {
    }

    @Override
    public void render() throws Exception {
        if (vsRender != null) {
            vsRender.render();
        }
    }

    @Override
    public void update(int delta) throws Exception {
        MugenSystem ms = MugenSystem.getInstance();
        VsScreen vsScreen = ms.getVsScreen();
        Background versus = ms.getVersusBackground();
        if (versus != null) {
            versus.process();
        }
        if (vsScreen == null) {
            launchFight();
            return;
        }
        if (vsScreen.getTime() <= 0) {
            if (vsScreen.getPhase() != VsScreen.END) {
                vsScreen.setPhase(VsScreen.LEAVE);
            } else {
                launchFight();
                return;
            }
        }
        if (vsScreen.getPhase() == VsScreen.NOTHING) {
            vsScreen.setPhase(VsScreen.ENTER);
        }
        vsScreen.process();
    }

    public String getPlayerName(int index) {
        return gameSelect.getSelectedCharacterName(index);
    }

    private void launchFight() throws Exception {
        if (fightLaunched) {
            return;
        }
        fightLaunched = true;
        GraphicsWrapper.getInstance().setAlpha(1f);
        next = gameSelect.getGameFight();
    }
}
