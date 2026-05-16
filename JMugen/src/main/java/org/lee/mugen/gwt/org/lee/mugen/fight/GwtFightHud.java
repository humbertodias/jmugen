package org.lee.mugen.fight;

import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.geom.MugenPoint;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;
import org.lee.mugen.sprite.parser.Parser;

/**
 * GWT lifebar HUD from {@code data/fight.sff} under the inner {@code data/} tree (sprites 11/12/13).
 */
public class GwtFightHud {

    /** Relative to {@link org.lee.mugen.core.JMugenConstant#RESOURCE} (web: {@code data/data/…}). */
    private static final String FIGHT_DEF = "data/fight.def";
    private static final String FIGHT_SFF = "data/fight.sff";

    private SpriteSFF fightSff;
    private MugenPoint p1pos = new MugenPoint(140, 12);
    private MugenPoint p2pos = new MugenPoint(178, 12);
    private int p1rangeMax = 127;
    private int p2rangeMax = 127;

    public static GwtFightHud load() throws Exception {
        GwtFightHud hud = new GwtFightHud();
        hud.parse();
        return hud;
    }

    private void parse() throws Exception {
        String sffPath = Parser.getExistFile(JMugenConstant.RESOURCE + FIGHT_SFF);
        fightSff = new SpriteSFF(new org.lee.mugen.sff.SffReader(sffPath, null), true, false);
        try {
            String defPath = Parser.getExistFile(JMugenConstant.RESOURCE + FIGHT_DEF);
            GwtFightHudParser.parse(defPath, this);
        } catch (Exception ignored) {
            applyDefaults();
        }
        if (p1pos == null) {
            p1pos = new MugenPoint(140, 12);
        }
        if (p2pos == null) {
            p2pos = new MugenPoint(178, 12);
        }
    }

    private void applyDefaults() {
        p1pos = new MugenPoint(140, 12);
        p2pos = new MugenPoint(178, 12);
        p1rangeMax = 127;
        p2rangeMax = 127;
    }

    void applyLifebarKey(String key, String value, Object root) {
        if (key.startsWith("p1.pos")) {
            p1pos = parsePoint(value);
        } else if (key.startsWith("p2.pos")) {
            p2pos = parsePoint(value);
        } else if (key.startsWith("p1.range.x")) {
            p1rangeMax = parseRangeMax(value);
        } else if (key.startsWith("p2.range.x")) {
            p2rangeMax = parseRangeMax(value);
        }
    }

    private static MugenPoint parsePoint(String value) {
        String[] p = value.replaceAll(" ", "").split(",");
        return new MugenPoint(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
    }

    private static int parseRangeMax(String value) {
        String[] p = value.replaceAll(" ", "").split(",");
        return Math.abs(Integer.parseInt(p[1]));
    }

    public void render(MugenDrawer md, int p1Life, int p2Life, int maxLife) {
        if (fightSff == null) {
            return;
        }
        drawPlayerBar(md, p1pos, p1Life, maxLife, p1rangeMax, false);
        drawPlayerBar(md, p2pos, p2Life, maxLife, p2rangeMax, true);
    }

    private void drawPlayerBar(
            MugenDrawer md,
            MugenPoint pos,
            int life,
            int maxLife,
            int rangeMax,
            boolean flip) {
        drawSpr(md, pos, 11, 0, flip);
        drawSpr(md, pos, 12, 0, flip);
        drawSprLife(md, pos, 13, 0, life, maxLife, rangeMax, flip);
    }

    private void drawSpr(MugenDrawer md, MugenPoint pos, int grp, int img, boolean flipH) {
        ImageSpriteSFF sff = getSpr(grp, img);
        if (sff == null) {
            return;
        }
        float x = flipH ? pos.x + sff.getWidth() - sff.getXAxis() : pos.x - sff.getXAxis();
        float y = pos.y - sff.getYAxis();
        md.draw(new DrawProperties(x, y, flipH, false, sff.getImage()));
    }

    private void drawSprLife(
            MugenDrawer md,
            MugenPoint pos,
            int grp,
            int img,
            int life,
            int maxLife,
            int rangeMax,
            boolean flipH) {
        ImageSpriteSFF sff = getSpr(grp, img);
        if (sff == null) {
            return;
        }
        int lifePx = maxLife > 0 ? (life * rangeMax) / maxLife : 0;
        DrawProperties dp;
        if (flipH) {
            dp =
                    new DrawProperties(
                            pos.x + sff.getWidth() - sff.getXAxis(),
                            pos.y - sff.getYAxis(),
                            true,
                            false,
                            sff.getImage());
            int delta = Math.min(lifePx, sff.getWidth());
            dp.setXRightDst(dp.getXLeftDst() + sff.getWidth());
            dp.setXLeftDst(dp.getXRightDst() - delta);
            dp.setXLeftSrc(dp.getXRightSrc() - delta);
        } else {
            dp =
                    new DrawProperties(
                            pos.x - sff.getXAxis(), pos.y - sff.getYAxis(), false, false, sff.getImage());
            int delta = Math.min(lifePx, sff.getWidth());
            dp.setXRightDst(dp.getXLeftDst() + delta);
            dp.setXRightSrc(dp.getXLeftSrc() + delta);
        }
        md.draw(dp);
    }

    private ImageSpriteSFF getSpr(int grp, int img) {
        if (fightSff.getGroupSpr(grp) == null) {
            return null;
        }
        return fightSff.getGroupSpr(grp).getImgSpr(img);
    }
}
