package org.lee.mugen.core;

import java.io.Reader;
import java.util.List;
import org.lee.mugen.background.Background;
import org.lee.mugen.background.GwtStageRoot;
import org.lee.mugen.background.GwtStageSelectBackground;
import org.lee.mugen.core.renderer.game.system.BackgroundRender;
import org.lee.mugen.fight.GwtCmdLoader;
import org.lee.mugen.fight.GwtFightCmdProcess;
import org.lee.mugen.fight.GwtFightFighterAnim;
import org.lee.mugen.fight.GwtFightHud;
import org.lee.mugen.fight.GwtFightPlayer;
import org.lee.mugen.fight.GwtStageFightInfo;
import org.lee.mugen.fight.select.GwtCharacters;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.fight.system.TitleInfo;
import org.lee.mugen.fight.system.elem.Menu;
import org.lee.mugen.geom.MugenPoint;
import org.lee.mugen.input.CmdProcDispatcher;
import org.lee.mugen.io.MugenDataStreams;
import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;

/**
 * GWT fight: stage + HUD + {@code *.cmd} command recognition (same keys/combos as desktop).
 * Full {@link GameFight} CNS/hitboxes remain excluded from the GWT module.
 */
public class GwtGameFight implements Game {

    private final String stagePath;
    private final String player1Id;
    private final String player2Id;
    private Background stageBackground;
    private BackgroundRender stageRender;
    private GwtStageFightInfo stageInfo;
    private GwtFightHud fightHud;
    private GwtFightPlayer p1;
    private GwtFightPlayer p2;
    private GwtFightCmdProcess cmdProc1;
    private GwtFightCmdProcess cmdProc2;
    private Game next;
    private long gameTime;
    private int roundTimer;
    private int maxLife = 1000;

    public GwtGameFight(String stagePath, String player1Id, String player2Id) {
        this.stagePath = stagePath;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
    }

    @Override
    public void free() {
        stageBackground = null;
        stageRender = null;
        fightHud = null;
        p1 = null;
        p2 = null;
        cmdProc1 = null;
        cmdProc2 = null;
    }

    @Override
    public Game getNext() throws Exception {
        return next == null ? this : next;
    }

    @Override
    public void init(GameWindow container) throws Exception {
        next = null;
        gameTime = 0;
        roundTimer = 0;
        GraphicsWrapper.getInstance().setAlpha(1f);
        loadStage();
        loadFighters();
        try {
            fightHud = GwtFightHud.load();
        } catch (Exception ignored) {
            fightHud = null;
        }
        container.clearListener();
        container.removeSpriteKeysProcessors();
        CmdProcDispatcher d1 = CmdProcDispatcher.getSpriteDispatcherMap().get("1");
        CmdProcDispatcher d2 = CmdProcDispatcher.getSpriteDispatcherMap().get("2");
        if (d1 != null) {
            d1.clear();
        }
        if (d2 != null) {
            d2.clear();
        }
        if (p1 != null && d1 != null) {
            cmdProc1 = new GwtFightCmdProcess(d1, p1);
            container.addSpriteKeyProcessor(cmdProc1);
        }
        if (p2 != null && d2 != null) {
            cmdProc2 = new GwtFightCmdProcess(d2, p2);
            container.addSpriteKeyProcessor(cmdProc2);
        }
        container.addActionListener(
                new GameWindow.MugenKeyListener() {
                    @Override
                    public void action(int key, boolean isPress) {
                        if (!isPress && key == container.getKeyEsc()) {
                            next = GameMenu.getInstance();
                        }
                    }
                });
    }

    private void loadFighters() {
        p1 = null;
        p2 = null;
        cmdProc1 = null;
        cmdProc2 = null;
        GwtCharacters chars = MugenSystem.getInstance().getFiles().getGwtSelect().getCharacters();
        if (chars == null) {
            return;
        }
        try {
            String[] f1 = chars.getFightFiles(player1Id);
            if (f1 != null) {
                GwtCmdLoader.FightCmdData cmd1 = GwtCmdLoader.load(player1Id);
                GwtFightFighterAnim a1 = new GwtFightFighterAnim(player1Id, f1[0], f1[1]);
                maxLife = chars.getLife(player1Id);
                p1 =
                        new GwtFightPlayer(
                                "1",
                                stageInfo.p1facing,
                                stageInfo.p1startx,
                                stageInfo.p1starty,
                                a1,
                                chars.getLife(player1Id),
                                cmd1);
            }
        } catch (Exception ignored) {
        }
        try {
            String[] f2 = chars.getFightFiles(player2Id);
            if (f2 != null) {
                GwtCmdLoader.FightCmdData cmd2 = GwtCmdLoader.load(player2Id);
                GwtFightFighterAnim a2 = new GwtFightFighterAnim(player2Id, f2[0], f2[1]);
                int life2 = chars.getLife(player2Id);
                maxLife = Math.max(maxLife, life2);
                p2 =
                        new GwtFightPlayer(
                                "2",
                                stageInfo.p2facing,
                                stageInfo.p2startx,
                                stageInfo.p2starty,
                                a2,
                                life2,
                                cmd2);
            }
        } catch (Exception ignored) {
        }
    }

    private void loadStage() {
        stageBackground = null;
        stageRender = null;
        stageInfo = new GwtStageFightInfo();
        if (stagePath == null) {
            return;
        }
        try {
            String resourcePath = JMugenConstant.RESOURCE + stagePath;
            stageInfo = GwtStageFightInfo.load(stagePath);
            GwtStageRoot root = new GwtStageRoot(resourcePath);
            Reader reader = MugenDataStreams.openUtf8Reader(resourcePath);
            List<GroupText> groups;
            try {
                groups = Parser.getGroupTextMap(reader);
            } finally {
                reader.close();
            }
            GwtStageSelectBackground stageBg = new GwtStageSelectBackground(root);
            stageBg.parse(root, groups);
            for (org.lee.mugen.background.BG bg : stageBg.getBgs()) {
                if (!bg.isInit()) {
                    bg.init();
                    bg.setInit(true);
                }
            }
            stageBackground = stageBg;
            stageRender = new BackgroundRender(stageBackground);
        } catch (Exception ignored) {
            stageBackground = null;
            stageRender = null;
        }
    }

    @Override
    public void reInit(GameWindow container) throws Exception {
    }

    @Override
    public void render() throws Exception {
        MugenDrawer md = GraphicsWrapper.getInstance();
        md.setAlpha(1f);
        md.setColor(0f, 0f, 0f, 1f);
        md.fillRect(0, 0, 320, 240);

        renderStage(md);

        if (p1 != null) {
            p1.draw(md);
        }
        if (p2 != null) {
            p2.draw(md);
        }

        if (fightHud != null && p1 != null && p2 != null) {
            fightHud.render(md, p1.life, p2.life, maxLife);
        }
    }

    private void renderStage(MugenDrawer md) {
        if (stageRender == null) {
            return;
        }
        MugenPoint menuPos = null;
        int savedMenuX = 0;
        TitleInfo titleInfo = MugenSystem.getInstance().getTitleInfo();
        if (titleInfo != null) {
            Menu menu = titleInfo.getMenu();
            if (menu != null && menu.getPos() != null) {
                menuPos = menu.getPos();
                savedMenuX = menuPos.x;
                menuPos.x = 0;
            }
        }
        try {
            stageRender.render();
        } finally {
            if (menuPos != null) {
                menuPos.x = savedMenuX;
            }
        }
    }

    @Override
    public void update(int delta) throws Exception {
        if (stageBackground != null) {
            stageBackground.process();
        }
        if (p1 == null || p2 == null) {
            return;
        }
        gameTime += delta > 0 ? delta : 16;
        if (cmdProc1 != null) {
            cmdProc1.setGameTime(gameTime);
            cmdProc1.process();
        }
        if (cmdProc2 != null) {
            cmdProc2.setGameTime(gameTime);
            cmdProc2.process();
        }
        p1.update(p2);
        p2.update(p1);

        roundTimer += delta;
        if (p1.life <= 0 || p2.life <= 0) {
            if (roundTimer > 2000) {
                next = GameMenu.getInstance();
            }
        }
    }
}
