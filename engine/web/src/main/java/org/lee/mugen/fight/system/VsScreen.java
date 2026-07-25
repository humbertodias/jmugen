package org.lee.mugen.fight.system;

import org.lee.mugen.fight.intro.entity.Fade;
import org.lee.mugen.fight.section.Section;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.fight.system.elem.GwtPlayerVsScreen;

/** GWT: versus screen timing and player layout from system.def. */
public class VsScreen implements Section {

    public static final int NOTHING = -1;
    public static final int ENTER = 0;
    public static final int CURRENT = 1;
    public static final int LEAVE = 2;
    public static final int END = 3;

    private int phase = NOTHING;
    private int lastPhase = NOTHING;
    private int time;
    private int originalTime = 180;
    private Fade fadein;
    private Fade fadeout;
    private GwtPlayerVsScreen p1;
    private GwtPlayerVsScreen p2;

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
        } else if ("time".equalsIgnoreCase(name)) {
            originalTime = Integer.parseInt(value.trim());
            time = originalTime;
        } else if (name.startsWith("p1.")) {
            if (p1 == null) {
                p1 = new GwtPlayerVsScreen();
            }
            p1.parse(root, Type.getNext(name), value);
        } else if (name.startsWith("p2.")) {
            if (p2 == null) {
                p2 = new GwtPlayerVsScreen();
            }
            p2.parse(root, Type.getNext(name), value);
        }
    }

    public GwtPlayerVsScreen getP1() {
        return p1;
    }

    public GwtPlayerVsScreen getP2() {
        return p2;
    }

    /** GWT-safe name (avoid {@code init} in JS). */
    public void resetState() {
        time = originalTime > 0 ? originalTime : 180;
        if (fadein != null) {
            fadein.init();
        }
        if (fadeout != null) {
            fadeout.init();
        }
        phase = NOTHING;
        lastPhase = NOTHING;
    }

    public int getTime() {
        return time;
    }

    public void decreaseTime() {
        time--;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.lastPhase = this.phase;
        this.phase = phase;
    }

    public Fade getFadein() {
        return fadein;
    }

    public Fade getFadeout() {
        return fadeout;
    }

    public void process() {
        if (phase == NOTHING && (lastPhase == END || lastPhase == NOTHING)) {
            resetState();
        }
        if (phase == ENTER) {
            if (fadein != null) {
                fadein.decrease();
                if (fadein.getTime() <= 0) {
                    setPhase(CURRENT);
                }
            } else {
                setPhase(CURRENT);
            }
        }
        if (phase == CURRENT) {
            decreaseTime();
        }
        if (phase == LEAVE) {
            if (fadeout != null) {
                fadeout.decrease();
                if (fadeout.getTime() <= 0) {
                    setPhase(END);
                }
            } else {
                setPhase(END);
            }
        }
    }
}
