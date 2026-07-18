package org.lee.mugen.core;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.lee.mugen.background.Background;
import org.lee.mugen.background.GwtStageRoot;
import org.lee.mugen.background.GwtStageSelectBackground;
import org.lee.mugen.core.renderer.game.GwtStageBackgroundRender;
import org.lee.mugen.core.sound.SoundSystem;
import org.lee.mugen.fight.GwtCmdLoader;
import org.lee.mugen.fight.GwtCnsData;
import org.lee.mugen.fight.GwtCnsLoader;
import org.lee.mugen.fight.GwtFightAudio;
import org.lee.mugen.fight.GwtFightCmdProcess;
import org.lee.mugen.fight.GwtFightFighterAnim;
import org.lee.mugen.fight.GwtFightHud;
import org.lee.mugen.fight.GwtFightPlayer;
import org.lee.mugen.fight.GwtFightPush;
import org.lee.mugen.fight.GwtFightRules;
import org.lee.mugen.fight.GwtStageFightInfo;
import org.lee.mugen.fight.select.GwtCharacters;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.input.CmdProcDispatcher;
import org.lee.mugen.io.MugenDataStreams;
import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.renderer.MugenTimer;
import org.lee.mugen.renderer.Renderable;
import org.lee.mugen.sprite.common.resource.FontProducer;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;

/**
 * GWT fight engine: {@code *.cmd} + AIR hitboxes, rounds, timer, power and camera (Android-like UX).
 */
public class GwtGameFight implements AbstractGameFight {

    private static final int MAX_KEYS = 512;

    private final String stagePath;
    private final String player1Id;
    private final String player2Id;
    private Background stageBackground;
    private GwtStageFightInfo stageInfo;
    private GwtFightHud fightHud;
    private GwtFightAudio fightAudio;
    private GwtFightPlayer p1;
    private GwtFightPlayer p2;
    private GwtFightCmdProcess cmdProc1;
    private GwtFightCmdProcess cmdProc2;
    private final GwtFightRules rules = new GwtFightRules();
    private final boolean[] keysDown = new boolean[MAX_KEYS];
    private final List<Renderable> renderables = new ArrayList<>();
    private Game next;
    private GameWindow window;
    private long gameTime;
    private int maxLife = 1000;
    private int matchEndTimer;
    private int lastRoundStarted;
    private float cameraX;

    private boolean showCns;
    private boolean showAttackCns;
    private boolean displayHelp;
    private boolean debugPaused;
    private boolean forceOneFrame;
    private int debugPlayerIndex; // 0=off, 1=p1, 2=p2

    public GwtGameFight(String stagePath, String player1Id, String player2Id) {
        this.stagePath = stagePath;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
    }

    @Override
    public void free() {
        stageBackground = null;
        fightHud = null;
        p1 = null;
        p2 = null;
        cmdProc1 = null;
        cmdProc2 = null;
        window = null;
        renderables.clear();
    }

    @Override
    public Game getNext() throws Exception {
        return next == null ? this : next;
    }

    @Override
    public void init(GameWindow container) throws Exception {
        window = container;
        next = null;
        gameTime = 0;
        matchEndTimer = 0;
        showCns = false;
        showAttackCns = false;
        displayHelp = false;
        debugPaused = false;
        forceOneFrame = false;
        debugPlayerIndex = 0;
        fightAudio = GwtFightAudio.load();
        rules.setAudio(fightAudio);
        rules.startMatch();
        lastRoundStarted = 0;
        GraphicsWrapper.getInstance().setAlpha(1f);
        loadStage();
        startStageMusic();
        loadFighters();
        if (p1 != null) {
            p1.setAudio(fightAudio);
            p1.setZOffset(stageInfo != null ? stageInfo.zoffset : 192);
            if (stageInfo != null) {
                p1.setShadow(stageInfo.shadowIntensity, stageInfo.shadowYScale);
            }
        }
        if (p2 != null) {
            p2.setAudio(fightAudio);
            p2.setZOffset(stageInfo != null ? stageInfo.zoffset : 192);
            if (stageInfo != null) {
                p2.setShadow(stageInfo.shadowIntensity, stageInfo.shadowYScale);
            }
        }
        try {
            fightHud = GwtFightHud.load();
            GwtCharacters chars = MugenSystem.getInstance().getFiles().getGwtSelect().getCharacters();
            if (chars != null) {
                fightHud.setFaces(
                        chars.getPortraitSprite(player1Id), chars.getPortraitSprite(player2Id));
                fightHud.setNames(chars.getDisplayName(player1Id), chars.getDisplayName(player2Id));
            }
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
                        if (key >= 0 && key < MAX_KEYS) {
                            keysDown[key] = isPress;
                        }
                        if (!isPress && key == container.getKeyEsc()) {
                            next = GameMenu.getInstance();
                        }
                    }
                });
    }

    private void loadFighters() {
        p1 = null;
        p2 = null;
        GwtCharacters chars = MugenSystem.getInstance().getFiles().getGwtSelect().getCharacters();
        if (chars == null) {
            return;
        }
        int boundL = stageInfo != null ? stageInfo.leftbound : -120;
        int boundR = stageInfo != null ? stageInfo.rightbound : 120;
        try {
            String[] f1 = chars.getFightFiles(player1Id);
            if (f1 != null) {
                GwtCmdLoader.FightCmdData cmd1 = GwtCmdLoader.load(player1Id);
                GwtCnsData cns1 = null;
                try {
                    cns1 = GwtCnsLoader.load(player1Id);
                } catch (Exception ignored) {
                }
                GwtFightFighterAnim a1 = new GwtFightFighterAnim(player1Id, f1[0], f1[1]);
                int life1 = cns1 != null ? cns1.life : chars.getLife(player1Id);
                maxLife = life1;
                p1 =
                        new GwtFightPlayer(
                                "1",
                                stageInfo.p1facing,
                                stageInfo.p1startx,
                                stageInfo.p1starty,
                                a1,
                                life1,
                                cmd1,
                                cns1,
                                boundL,
                                boundR);
            }
        } catch (Exception ignored) {
        }
        try {
            String[] f2 = chars.getFightFiles(player2Id);
            if (f2 != null) {
                GwtCmdLoader.FightCmdData cmd2 = GwtCmdLoader.load(player2Id);
                GwtCnsData cns2 = null;
                try {
                    cns2 = GwtCnsLoader.load(player2Id);
                } catch (Exception ignored) {
                }
                GwtFightFighterAnim a2 = new GwtFightFighterAnim(player2Id, f2[0], f2[1]);
                int life2 = cns2 != null ? cns2.life : chars.getLife(player2Id);
                maxLife = Math.max(maxLife, life2);
                p2 =
                        new GwtFightPlayer(
                                "2",
                                stageInfo.p2facing,
                                stageInfo.p2startx,
                                stageInfo.p2starty,
                                a2,
                                life2,
                                cmd2,
                                cns2,
                                boundL,
                                boundR);
            }
        } catch (Exception ignored) {
        }
    }

    private void loadStage() {
        stageBackground = null;
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
        } catch (Exception ignored) {
            stageBackground = null;
        }
    }

    private void startStageMusic() {
        SoundSystem.SoundBackGround.stopMusic();
        String path = stageInfo != null ? stageInfo.bgmusic : null;
        if (path == null || path.length() == 0) {
            path = "sound/intro.mp3";
        }
        if (!path.startsWith("sound/") && !path.startsWith("data/")) {
            int slash = stagePath != null ? stagePath.lastIndexOf('/') : -1;
            if (slash >= 0) {
                path = stagePath.substring(0, slash + 1) + path;
            }
        }
        try {
            SoundSystem.SoundBackGround.playMusic(JMugenConstant.RESOURCE + path);
        } catch (Exception ignored) {
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

        updateCamera();
        renderStage(md);

        if (p1 != null) {
            p1.drawShadow(md, cameraX);
        }
        if (p2 != null) {
            p2.drawShadow(md, cameraX);
        }
        if (p1 != null) {
            p1.draw(md, cameraX);
        }
        if (p2 != null) {
            p2.draw(md, cameraX);
        }
        if (showCns) {
            if (p1 != null) {
                p1.drawDebugCns(md, cameraX);
            }
            if (p2 != null) {
                p2.drawDebugCns(md, cameraX);
            }
        }
        if (showAttackCns) {
            if (p1 != null) {
                p1.drawDebugAttackCns(md, cameraX);
            }
            if (p2 != null) {
                p2.drawDebugAttackCns(md, cameraX);
            }
        }

        if (fightHud != null && p1 != null && p2 != null) {
            fightHud.render(md, p1.life, p2.life, maxLife, p1.power, p2.power);
            // Timer only during combat (desktop TimeRender requires Roundstate.COMBAT).
            if (rules.phase == GwtFightRules.Phase.FIGHTING) {
                fightHud.renderTimer(md, rules.roundTimer);
            }
            if (rules.phase == GwtFightRules.Phase.ROUND_SHOW) {
                fightHud.renderRoundBanner(md, rules.round);
            } else if (rules.phase == GwtFightRules.Phase.CTRL_DELAY) {
                fightHud.renderFightCall(md);
            } else if (rules.statusBanner != null) {
                if ("K.O.".equals(rules.statusBanner) || "D.K.O.".equals(rules.statusBanner)) {
                    fightHud.renderKoCall(md);
                } else {
                    fightHud.renderBanner(md, rules.statusBanner);
                }
            }
        }
        renderDebugInfo();
    }

    private void updateCamera() {
        if (p1 == null || p2 == null) {
            cameraX = 0;
            return;
        }
        // Follow midpoint (same idea as desktop Stage camera + SpriteRender).
        float mid = (p1.getWorldX() + p2.getWorldX()) * 0.5f;
        cameraX = -mid;
        if (stageInfo != null) {
            if (cameraX < stageInfo.boundleft) {
                cameraX = stageInfo.boundleft;
            }
            if (cameraX > stageInfo.boundright) {
                cameraX = stageInfo.boundright;
            }
        }
    }

    /** Keep fighters inside the 320px view like desktop screenbound. */
    private void clampPlayersToScreen() {
        updateCamera();
        if (p1 != null) {
            p1.clampToScreen(cameraX);
        }
        if (p2 != null) {
            p2.clampToScreen(cameraX);
        }
        updateCamera();
        if (p1 != null) {
            p1.clampToScreen(cameraX);
        }
        if (p2 != null) {
            p2.clampToScreen(cameraX);
        }
    }

    private void renderStage(MugenDrawer md) {
        if (stageBackground == null) {
            return;
        }
        md.setColor(1f, 1f, 1f, 1f);
        // cameraX + 160 (screen center); parallax floor tiles horizontally.
        new GwtStageBackgroundRender(stageBackground, cameraX, 0f, 160).render();
        new GwtStageBackgroundRender(stageBackground, cameraX, 0f, 160, 1f, 1f, 1).render();
    }

    @Override
    public void update(int delta) throws Exception {
        if (stageBackground != null && (!debugPaused || forceOneFrame)) {
            stageBackground.process();
        }
        if (p1 == null || p2 == null) {
            return;
        }
        if (rules.isMatchOver()) {
            matchEndTimer += delta;
            if (matchEndTimer > 3000) {
                next = GameMenu.getInstance();
            }
            return;
        }

        if (debugPaused && !forceOneFrame) {
            return;
        }
        forceOneFrame = false;

        // Same as desktop/Android GameFight: one Mugen tick per rendered frame.
        // Catching up with wall-clock delta after fight init skips Round/FIGHT banners.
        int ticks = 1;
        rules.tick();
        if (rules.round != lastRoundStarted) {
            lastRoundStarted = rules.round;
            resetRoundFighters();
        }

        boolean ctrl = rules.ctrlActive;
        p1.setCtrlEnabled(ctrl);
        p2.setCtrlEnabled(ctrl);

        // Mugen game-time is tick-based (60/s), not milliseconds.
        gameTime += ticks;
        if (cmdProc1 != null) {
            cmdProc1.setGameTime(gameTime);
        }
        if (cmdProc2 != null) {
            cmdProc2.setGameTime(gameTime);
        }
        if (ctrl) {
            if (cmdProc1 != null) {
                cmdProc1.process();
            }
            if (cmdProc2 != null) {
                cmdProc2.process();
            }
        }
        CmdProcDispatcher d1 = CmdProcDispatcher.getSpriteDispatcherMap().get("1");
        CmdProcDispatcher d2 = CmdProcDispatcher.getSpriteDispatcherMap().get("2");
        // Always advance anim/physics; movement/attacks only when ctrl is active.
        p1.update(ctrl ? d1 : null, keysDown, p2);
        p2.update(ctrl ? d2 : null, keysDown, p1);
        // Body collision first (desktop: PhysicsEngime then FightEngine).
        GwtFightPush.resolve(p1, p2);
        clampPlayersToScreen();
        GwtFightPush.resolve(p1, p2);
        clampPlayersToScreen();
        p1.tryHitOpponent(p2);
        p2.tryHitOpponent(p1);

        if (rules.phase == GwtFightRules.Phase.FIGHTING) {
            if (p1.life <= 0 && p2.life <= 0) {
                rules.onKo(0);
            } else if (p1.life <= 0) {
                rules.onKo(2);
            } else if (p2.life <= 0) {
                rules.onKo(1);
            } else if (rules.roundTimer <= 0) {
                if (p1.life > p2.life) {
                    rules.onTimeOver(1);
                } else if (p2.life > p1.life) {
                    rules.onTimeOver(2);
                } else {
                    rules.onTimeOver(0);
                }
            }
        }
    }

    private void resetRoundFighters() {
        if (p1 != null) {
            p1.resetForRound(stageInfo.p1startx, stageInfo.p1starty, maxLife);
        }
        if (p2 != null) {
            p2.resetForRound(stageInfo.p2startx, stageInfo.p2starty, maxLife);
        }
    }

    @Override
    public void addRender(Renderable r) {
        if (r != null) {
            renderables.add(r);
        }
    }

    @Override
    public List<Renderable> getRenderables() {
        return renderables;
    }

    @Override
    public void displayPendingScreeen() {
    }

    @Override
    public void onDebugAction(DebugAction action) {
        switch (action) {
            case SWITCH_PLAYER_DEBUG_INFO:
                debugPlayerIndex = (debugPlayerIndex + 1) % 3;
                break;
            case INIT_PLAYER:
                rules.beginRound();
                lastRoundStarted = rules.round;
                resetRoundFighters();
                break;
            case SHOW_HIDE_CNS:
                showCns = !showCns;
                break;
            case SHOW_HIDE_ATTACK_CNS:
                showAttackCns = !showAttackCns;
                break;
            case INCREASE_FPS:
                if (window != null) {
                    long fps = window.getTimer().getFramerate();
                    window.getTimer().setFramerate(fps + 1);
                }
                break;
            case DECREASE_FPS:
                if (window != null) {
                    long fps = window.getTimer().getFramerate();
                    window.getTimer().setFramerate(fps - 1 < 0 ? 0 : fps - 1);
                }
                break;
            case RESET_FPS:
                if (window != null) {
                    window.getTimer().setFramerate(MugenTimer.DEFAULT_FPS);
                }
                break;
            case DISPLAY_HELP:
                displayHelp = !displayHelp;
                break;
            case DEBUG_PAUSE:
                debugPaused = !debugPaused;
                break;
            case PAUSE_PLUS_ONE_FRAME:
                if (debugPaused) {
                    forceOneFrame = true;
                }
                break;
            case EXPLOD_DEBUG_INFO:
            default:
                break;
        }
    }

    @Override
    public void renderDebugInfo() {
        MugenDrawer md = GraphicsWrapper.getInstance();
        FontProducer font = resolveFont(2);
        if (font == null) {
            font = resolveFont(3);
        }
        if (font == null) {
            return;
        }
        int x = 8;
        int y = 40;
        if (displayHelp) {
            String[] lines = {
                "F1       : Help",
                "CTRL +/- : FPS (or =/-)",
                "CTRL *   : Reset FPS",
                "Space    : Reset round",
                "CTRL-D   : Player debug info",
                "CTRL-C   : Clsn2 (hurt) boxes",
                "CTRL-X   : Clsn1 (attack) boxes",
                "CTRL-P   : Pause",
                "CTRL-A   : Step frame (paused)"
            };
            for (String line : lines) {
                y += 10;
                font.draw(0, x, y, md, line, 1, 1f);
            }
        }
        if (debugPlayerIndex == 1 && p1 != null) {
            font.draw(0, 8, 220, md, p1.debugSummary(), 1, 1f);
        } else if (debugPlayerIndex == 2 && p2 != null) {
            font.draw(0, 8, 220, md, p2.debugSummary(), 1, 1f);
        }
        if (debugPaused) {
            font.draw(0, 240, 230, md, "PAUSE", 1, 1f);
        }
    }

    private static FontProducer resolveFont(int fontNo) {
        MugenSystem sys = MugenSystem.getInstance();
        if (sys == null || sys.getFiles() == null) {
            return null;
        }
        Map<Integer, FontProducer> fonts = sys.getFiles().getFont();
        if (fonts == null) {
            return null;
        }
        return fonts.get(fontNo);
    }
}
