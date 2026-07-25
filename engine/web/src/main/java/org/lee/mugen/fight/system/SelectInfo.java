package org.lee.mugen.fight.system;

import org.lee.mugen.fight.intro.entity.Fade;
import org.lee.mugen.fight.section.Section;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.fight.system.elem.Cell;
import org.lee.mugen.fight.system.elem.PlayerSelectInfo;
import org.lee.mugen.fight.system.elem.Stage;
import org.lee.mugen.fight.system.elem.StageDisplay;
import org.lee.mugen.geom.MugenPoint;
import org.lee.mugen.util.BeanTools;

/**
 * GWT: same as desktop {@link org.lee.mugen.fight.system.SelectInfo} but fields
 * {@code player1}/{@code player2} instead of {@code p1}/{@code p2} — GWT/JS breaks
 * {@code getP1()} when a field is named {@code p1}.
 */
public class SelectInfo implements Section {

    private Fade fadein;
    private Fade fadeout;
    int rows;
    int columns;
    int wrapping;
    MugenPoint pos;
    boolean showemptyboxes;
    boolean moveoveremptyboxes;
    Cell cell;
    PlayerSelectInfo player1;
    PlayerSelectInfo player2;
    boolean random$move$snd$cancel;
    Stage stage;
    StageDisplay stagedisplay;
    Type cancel;
    Type portrait;
    Type title;
    int teammenu$move$wrapping;

    @Override
    public void parse(Object root, String name, String value) throws Exception {
        if (name.startsWith("fadein.")) {
            if (fadein == null) {
                fadein = new Fade();
            }
            fadein.parse(Type.getNext(name), value);
        } else if (name.startsWith("fadeout.")) {
            if (fadeout == null) {
                fadeout = new Fade();
            }
            fadeout.parse(Type.getNext(name), value);
        } else if (name.equals("rows")) {
            rows = Integer.parseInt(value);
        } else if (name.equals("columns")) {
            columns = Integer.parseInt(value);
        } else if (name.equals("wrapping")) {
            wrapping = Integer.parseInt(value);
        } else if (name.equals("pos")) {
            pos = (MugenPoint) BeanTools.getConvertersMap().get(MugenPoint.class).convert(value);
        } else if (name.equals("showemptyboxes")) {
            showemptyboxes = Integer.parseInt(value) != 0;
        } else if (name.equals("moveoveremptyboxes")) {
            moveoveremptyboxes = Integer.parseInt(value) != 0;
        } else if (name.startsWith("cell.")) {
            if (cell == null) {
                cell = new Cell();
            }
            cell.parse(root, Type.getNext(name), value);
        } else if (name.startsWith("p1.")) {
            if (player1 == null) {
                player1 = new PlayerSelectInfo();
            }
            player1.parse(root, Type.getNext(name), value);
        } else if (name.startsWith("p2.")) {
            if (player2 == null) {
                player2 = new PlayerSelectInfo();
            }
            player2.parse(root, Type.getNext(name), value);
        } else if (name.equals("random.move.snd.cancel")) {
            random$move$snd$cancel = Integer.parseInt(value) != 0;
        } else if (name.startsWith("stage.")) {
            if (stage == null) {
                stage = new Stage();
            }
            stage.parse(root, Type.getNext(name), value);
        } else if (name.startsWith("stagedisplay.")) {
            if (stagedisplay == null) {
                stagedisplay = new StageDisplay();
            }
            stagedisplay.parse(root, Type.getNext(name), value);
        } else if (name.startsWith("cancel")) {
            if (cancel == null) {
                cancel = new Type();
            }
            cancel.setType(Type.getNext(name), cancel, value, root);
            cancel.parse(Type.getNext(name), value);
        } else if (name.startsWith("portrait")) {
            if (cancel == null) {
                cancel = new Type();
            }
            cancel.setType(Type.getNext(name), cancel, value, root);
            cancel.parse(Type.getNext(name), value);
        } else if (name.startsWith("title")) {
            if (title == null) {
                title = new Type();
            }
            title.setType(Type.getNext(name), title, value, root);
            title.parse(Type.getNext(name), value);
        } else if (name.equals("teammenu.move.wrapping")) {
            teammenu$move$wrapping = Integer.parseInt(value);
        }
    }

    public StageDisplay getStagedisplay() {
        return stagedisplay;
    }

    public Fade getFadein() {
        return fadein;
    }

    public Fade getFadeout() {
        return fadeout;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public MugenPoint getPos() {
        return pos;
    }

    public Cell getCell() {
        return cell;
    }

    /** GWT-safe accessor (use instead of {@code getP1()}). */
    public PlayerSelectInfo getPlayer1() {
        return player1;
    }

    /** GWT-safe accessor (use instead of {@code getP2()}). */
    public PlayerSelectInfo getPlayer2() {
        return player2;
    }

    public Stage getStage() {
        return stage;
    }

    public Type getCancel() {
        return cancel;
    }
}
