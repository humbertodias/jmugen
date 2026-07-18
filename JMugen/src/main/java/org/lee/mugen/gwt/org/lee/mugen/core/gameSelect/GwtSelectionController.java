package org.lee.mugen.core.gameSelect;

import org.lee.mugen.core.sound.SoundSystem;
import org.lee.mugen.fight.section.elem.SndType;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.fight.select.GwtExtraStages;
import org.lee.mugen.fight.select.GwtSelect;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.fight.system.SelectInfo;
import org.lee.mugen.fight.system.elem.PlayerSelectInfo;
import org.lee.mugen.geom.MugenPoint;
import org.lee.mugen.input.CmdProcDispatcher;
import org.lee.mugen.renderer.GameWindow.MugenKeyListener;
import org.lee.mugen.renderer.GraphicsWrapper;

/**
 * GWT character-select input for one player (no BeanTools {@code p1.*} paths).
 */
public class GwtSelectionController implements MugenKeyListener {

    private static final int P1_SELECTED = 1;
    private static final int P2_SELECTED = 2;
    private static final int STAGE_SELECTED = 16;

    private static int shareState;
    private static int shareIndexOfStage;
    private static final Object MONITOR = new Object();

    private final String id;
    private final MugenPoint position = new MugenPoint();
    private final GwtGameSelect gameSelect;
    private long lastPress;

    public GwtSelectionController(GwtGameSelect gameSelect, String id, MugenPoint startcell) {
        this.gameSelect = gameSelect;
        this.id = id;
        position.x = startcell.x;
        position.y = startcell.y;
    }

    public static void initShareVariable() {
        synchronized (MONITOR) {
            shareState = 0;
            shareIndexOfStage = 0;
        }
    }

    public static int getShareIndexOfStage() {
        synchronized (MONITOR) {
            return shareIndexOfStage;
        }
    }

    public static boolean isAllSelectionDone(int playerCount) {
        synchronized (MONITOR) {
            if (!isStageSelected()) {
                return false;
            }
            for (int i = 0; i < playerCount; i++) {
                if (!isCharSelected(i + 1)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean isBothCharsSelected() {
        return isCharSelected(1) && isCharSelected(2);
    }

    public static boolean isStageSelected() {
        synchronized (MONITOR) {
            return (shareState & STAGE_SELECTED) == STAGE_SELECTED;
        }
    }

    public boolean isCharSelected() {
        synchronized (MONITOR) {
            return (shareState & getCharacterBit()) == getCharacterBit();
        }
    }

    private static boolean isCharSelected(int playerId) {
        synchronized (MONITOR) {
            return (shareState & getCharacterBit(playerId)) == getCharacterBit(playerId);
        }
    }

    public String getId() {
        return id;
    }

    public MugenPoint getPosition() {
        return position;
    }

    public PlayerSelectInfo getPlayerInfo() {
        SelectInfo si = MugenSystem.getInstance().getSelectInfo();
        if (si == null) {
            return null;
        }
        return "1".equals(id) ? si.getPlayer1() : si.getPlayer2();
    }

    @Override
    public void action(int key, boolean isPress) {
        long now = System.currentTimeMillis();
        if (!isPress || now - lastPress < 100) {
            return;
        }
        if (GraphicsWrapper.getInstance().getInstanceOfGameWindow().getKeyEsc() == key) {
            gameSelect.setBackToMenu();
            return;
        }
        lastPress = now;

        CmdProcDispatcher cmd = CmdProcDispatcher.getSpriteDispatcherMap().get(id);
        if (cmd == null) {
            return;
        }

        if (!isCharSelected()) {
            if (cmd.getDown() == key) {
                addToPosition(1, 0);
            } else if (cmd.getUp() == key) {
                addToPosition(-1, 0);
            } else if (cmd.getBack() == key) {
                addToPosition(0, -1);
            } else if (cmd.getForward() == key) {
                addToPosition(0, 1);
            } else if (isButtonConfirm(cmd, key) && isPlayerSelectable()) {
                setSelected();
            }
        } else if (!isStageSelected()) {
            if (cmd.getBack() == key) {
                if (decIndexOfStage()) {
                    gameSelect.setStageChanged();
                    playStageMoveSnd();
                }
            } else if (cmd.getForward() == key) {
                if (incIndexOfStage()) {
                    gameSelect.setStageChanged();
                    playStageMoveSnd();
                }
            } else if (isButtonConfirm(cmd, key)) {
                setStageSelected();
            } else if (isButtonCancel(cmd, key)) {
                setUnSelected();
            }
        } else if (isButtonCancel(cmd, key)) {
            setStageUnSelected();
        }
    }

    private static boolean incIndexOfStage() {
        synchronized (MONITOR) {
            GwtExtraStages stages = getExtraStages();
            if (stages == null || stages.getStages().isEmpty()) {
                return false;
            }
            int max = stages.getStages().size() - 1;
            int prev = shareIndexOfStage;
            shareIndexOfStage++;
            if (shareIndexOfStage > max) {
                shareIndexOfStage = 0;
            }
            return prev != shareIndexOfStage;
        }
    }

    private static boolean decIndexOfStage() {
        synchronized (MONITOR) {
            GwtExtraStages stages = getExtraStages();
            if (stages == null || stages.getStages().isEmpty()) {
                return false;
            }
            int max = stages.getStages().size() - 1;
            int prev = shareIndexOfStage;
            shareIndexOfStage--;
            if (shareIndexOfStage < 0) {
                shareIndexOfStage = max;
            }
            return prev != shareIndexOfStage;
        }
    }

    private static GwtExtraStages getExtraStages() {
        GwtSelect select = MugenSystem.getInstance().getFiles().getGwtSelect();
        return select != null ? select.getExtraStages() : null;
    }

    private void addToPosition(int dx, int dy) {
        SelectInfo si = MugenSystem.getInstance().getSelectInfo();
        int rows = si.getRows();
        int cols = si.getColumns();
        MugenPoint original = position.copy();
        position.x += dx;
        position.y += dy;
        if (position.x < 0) {
            position.x = rows - 1;
        }
        if (position.y < 0) {
            position.y = cols - 1;
        }
        if (position.x > rows - 1) {
            position.x = 0;
        }
        if (position.y > cols - 1) {
            position.y = 0;
        }
        if (original.x != position.x || original.y != position.y) {
            playCursorMoveSnd();
        }
    }

    private void setSelected() {
        synchronized (MONITOR) {
            shareState |= getCharacterBit();
        }
        playSndFromCursorType(true);
    }

    private void setUnSelected() {
        synchronized (MONITOR) {
            shareState &= ~getCharacterBit();
        }
        playCancelSnd();
    }

    private static void setStageSelected() {
        synchronized (MONITOR) {
            shareState |= STAGE_SELECTED;
        }
        SelectInfo si = MugenSystem.getInstance().getSelectInfo();
        if (si != null && si.getStage() != null && si.getStage().getDone() != null) {
            playSnd(si.getStage().getDone());
        }
    }

    private static void setStageUnSelected() {
        synchronized (MONITOR) {
            shareState &= ~STAGE_SELECTED;
        }
        playCancelSndStatic();
    }

    private static void playStageMoveSnd() {
        SelectInfo si = MugenSystem.getInstance().getSelectInfo();
        if (si != null && si.getStage() != null && si.getStage().getMove() != null) {
            playSnd(si.getStage().getMove());
        }
    }

    private void playCursorMoveSnd() {
        PlayerSelectInfo pi = getPlayerInfo();
        if (pi == null || pi.getCursor() == null || pi.getCursor().getMove() == null) {
            return;
        }
        playSnd(pi.getCursor().getMove());
    }

    private void playSndFromCursorType(boolean done) {
        PlayerSelectInfo pi = getPlayerInfo();
        if (pi == null || pi.getCursor() == null) {
            return;
        }
        Type type = done ? pi.getCursor().getDone() : pi.getCursor().getActive();
        if (type != null) {
            playSnd(type);
        }
    }

    private static void playCancelSndStatic() {
        SelectInfo si = MugenSystem.getInstance().getSelectInfo();
        if (si != null && si.getCancel() != null) {
            playSnd(si.getCancel());
        }
    }

    private void playCancelSnd() {
        playCancelSndStatic();
    }

    private static void playSnd(Type type) {
        if (type == null || type.getSnd() == null) {
            return;
        }
        try {
            SndType snd = type.getSnd();
            byte[] data =
                    MugenSystem.getInstance()
                            .getFiles()
                            .getSnd()
                            .getGroup(snd.getGrp())
                            .getSound(snd.getNum());
            SoundSystem.Sfx.playSnd(data);
        } catch (Exception ignored) {
        }
    }

    private int getCharacterBit() {
        return getCharacterBit(Integer.parseInt(id));
    }

    private static int getCharacterBit(int playerId) {
        switch (playerId) {
            case 1:
                return P1_SELECTED;
            case 2:
                return P2_SELECTED;
            default:
                throw new IllegalStateException();
        }
    }

    private static boolean isButtonCancel(CmdProcDispatcher cmd, int key) {
        return cmd.getX() == key
                || cmd.getY() == key
                || cmd.getZ() == key
                || cmd.getXyz() == key;
    }

    private static boolean isButtonConfirm(CmdProcDispatcher cmd, int key) {
        return cmd.getA() == key || cmd.getB() == key || cmd.getC() == key || cmd.getAbc() == key;
    }

    public boolean isPlayerSelectable() {
        return gameSelect.getSelectedSprite(position) != null;
    }
}
