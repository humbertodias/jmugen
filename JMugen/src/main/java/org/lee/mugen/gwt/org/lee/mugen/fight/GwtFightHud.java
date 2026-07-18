package org.lee.mugen.fight;

import java.util.HashMap;
import java.util.Map;
import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.geom.MugenPoint;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;
import org.lee.mugen.sprite.common.resource.FontParser;
import org.lee.mugen.sprite.common.resource.FontProducer;
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
    private MugenPoint p1powerPos = new MugenPoint(140, 22);
    private MugenPoint p2powerPos = new MugenPoint(178, 22);
    private int p1powerRangeMax = 107;
    private int p2powerRangeMax = 107;
    private MugenPoint timePos = new MugenPoint(160, 23);
    private int timeFontNo = 2;
    private int timeFontBank = 0;
    private int timeAlign = 0;
    private int framesPerCount = 60;
    private MugenPoint p1FacePos = new MugenPoint(2, 12);
    private MugenPoint p2FacePos = new MugenPoint(316, 12);
    private MugenPoint p1FaceOffset = new MugenPoint(0, 10);
    private MugenPoint p2FaceOffset = new MugenPoint(0, 10);
    private int p1FaceFacing = 1;
    private int p2FaceFacing = -1;
    private int faceBgGrp = 50;
    private int faceBgImg = 0;
    private ImageContainer p1Face;
    private ImageContainer p2Face;
    private int p1FaceXAxis;
    private int p1FaceYAxis;
    private int p2FaceXAxis;
    private int p2FaceYAxis;
    private MugenPoint p1NamePos = new MugenPoint(14, 10);
    private MugenPoint p2NamePos = new MugenPoint(305, 10);
    private int p1NameFontNo = 3;
    private int p1NameFontBank = 0;
    private int p1NameAlign = 1;
    private int p2NameFontNo = 3;
    private int p2NameFontBank = 0;
    private int p2NameAlign = -1;
    private String p1Name = "";
    private String p2Name = "";
    /** Fonts from {@code fight.def} [Files] — not the same as {@code system.def} fonts. */
    private final Map<Integer, FontProducer> fightFonts = new HashMap<Integer, FontProducer>();

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

    void applyFilesKey(String key, String value) {
        if (!key.startsWith("font")) {
            return;
        }
        String sNum = key.length() > 4 ? key.substring(4) : "";
        if (sNum.isEmpty()) {
            return;
        }
        try {
            int num = Integer.parseInt(sNum);
            String resolved = resolveFightFontPath(value.replace('\\', '/'));
            if (resolved == null) {
                return;
            }
            fightFonts.put(num, FontParser.getFontProducer(resolved));
        } catch (Exception ignored) {
            // optional font
        }
    }

    private static String resolveFightFontPath(String relative) {
        String[] candidates =
                new String[] {
                    JMugenConstant.RESOURCE + "data/" + relative,
                    JMugenConstant.RESOURCE + relative,
                    relative
                };
        for (String c : candidates) {
            try {
                return Parser.getExistFile(c);
            } catch (Exception ignored) {
            }
        }
        return null;
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

    void applyPowerbarKey(String key, String value) {
        if (key.startsWith("p1.pos")) {
            p1powerPos = parsePoint(value);
        } else if (key.startsWith("p2.pos")) {
            p2powerPos = parsePoint(value);
        } else if (key.startsWith("p1.range.x")) {
            p1powerRangeMax = parseRangeMax(value);
        } else if (key.startsWith("p2.range.x")) {
            p2powerRangeMax = parseRangeMax(value);
        }
    }

    void applyTimeKey(String key, String value) {
        if ("pos".equals(key)) {
            timePos = parsePoint(value);
        } else if ("counter.font".equals(key)) {
            String[] p = value.replaceAll(" ", "").split(",");
            if (p.length >= 1) {
                timeFontNo = Integer.parseInt(p[0]);
            }
            if (p.length >= 2) {
                timeFontBank = Integer.parseInt(p[1]);
            }
            if (p.length >= 3) {
                timeAlign = Integer.parseInt(p[2]);
            }
        } else if ("framespercount".equals(key)) {
            framesPerCount = Math.max(1, Integer.parseInt(value.trim()));
        }
    }

    void applyFaceKey(String key, String value) {
        if ("p1.pos".equals(key)) {
            p1FacePos = parsePoint(value);
        } else if ("p2.pos".equals(key)) {
            p2FacePos = parsePoint(value);
        } else if ("p1.face.offset".equals(key)) {
            p1FaceOffset = parsePoint(value);
        } else if ("p2.face.offset".equals(key)) {
            p2FaceOffset = parsePoint(value);
        } else if ("p1.face.facing".equals(key)) {
            p1FaceFacing = Integer.parseInt(value.trim());
        } else if ("p2.face.facing".equals(key)) {
            p2FaceFacing = Integer.parseInt(value.trim());
        } else if ("p1.bg.spr".equals(key) || "p2.bg.spr".equals(key)) {
            String[] p = value.replaceAll(" ", "").split(",");
            if (p.length >= 2) {
                faceBgGrp = Integer.parseInt(p[0]);
                faceBgImg = Integer.parseInt(p[1]);
            }
        }
    }

    /** Character portraits (typically SFF 9000,0) for the fight HUD. */
    public void setFaces(ImageContainer p1, ImageContainer p2) {
        setFaces(p1, 0, 0, p2, 0, 0);
    }

    /** Prefer this: axes from the character SFF match desktop {@code FaceRender}. */
    public void setFaces(ImageSpriteSFF p1, ImageSpriteSFF p2) {
        setFaces(
                p1 != null ? p1.getImage() : null,
                p1 != null ? p1.getXAxis() : 0,
                p1 != null ? p1.getYAxis() : 0,
                p2 != null ? p2.getImage() : null,
                p2 != null ? p2.getXAxis() : 0,
                p2 != null ? p2.getYAxis() : 0);
    }

    public void setFaces(
            ImageContainer p1,
            int p1XAxis,
            int p1YAxis,
            ImageContainer p2,
            int p2XAxis,
            int p2YAxis) {
        this.p1Face = p1;
        this.p1FaceXAxis = p1XAxis;
        this.p1FaceYAxis = p1YAxis;
        this.p2Face = p2;
        this.p2FaceXAxis = p2XAxis;
        this.p2FaceYAxis = p2YAxis;
    }

    /** Display names under the face / near the lifebar ({@code fight.def} [Name]). */
    public void setNames(String p1, String p2) {
        this.p1Name = p1 != null ? p1 : "";
        this.p2Name = p2 != null ? p2 : "";
    }

    void applyNameKey(String key, String value) {
        if ("p1.pos".equals(key)) {
            p1NamePos = parsePoint(value);
        } else if ("p2.pos".equals(key)) {
            p2NamePos = parsePoint(value);
        } else if ("p1.name.font".equals(key)) {
            int[] f = parseFont(value);
            p1NameFontNo = f[0];
            p1NameFontBank = f[1];
            p1NameAlign = f[2];
        } else if ("p2.name.font".equals(key)) {
            int[] f = parseFont(value);
            p2NameFontNo = f[0];
            p2NameFontBank = f[1];
            p2NameAlign = f[2];
        }
    }

    private static int[] parseFont(String value) {
        String[] p = value.replaceAll(" ", "").split(",");
        int no = p.length >= 1 ? Integer.parseInt(p[0]) : 3;
        int bank = p.length >= 2 ? Integer.parseInt(p[1]) : 0;
        int align = p.length >= 3 ? Integer.parseInt(p[2]) : 0;
        return new int[] {no, bank, align};
    }

    public int getFramesPerCount() {
        return framesPerCount;
    }

    private static MugenPoint parsePoint(String value) {
        String[] p = value.replaceAll(" ", "").split(",");
        return new MugenPoint(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
    }

    private static int parseRangeMax(String value) {
        String[] p = value.replaceAll(" ", "").split(",");
        return Math.abs(Integer.parseInt(p[1]));
    }

    public void render(MugenDrawer md, int p1Life, int p2Life, int maxLife, int p1Power, int p2Power) {
        if (fightSff == null) {
            return;
        }
        // Desktop FightdefRender order: lifebar/power → face+name (so names aren't covered).
        drawLifeBar(md, p1pos, p1Life, maxLife, p1rangeMax, false);
        drawLifeBar(md, p2pos, p2Life, maxLife, p2rangeMax, true);
        drawPowerBar(md, p1powerPos, p1Power, p1powerRangeMax, false);
        drawPowerBar(md, p2powerPos, p2Power, p2powerRangeMax, true);
        drawFace(md, p1FacePos, p1FaceOffset, p1FaceFacing, p1Face, p1FaceXAxis, p1FaceYAxis, false);
        drawFace(md, p2FacePos, p2FaceOffset, p2FaceFacing, p2Face, p2FaceXAxis, p2FaceYAxis, true);
        drawName(md, p1Name, p1NamePos, p1NameFontNo, p1NameFontBank, p1NameAlign);
        drawName(md, p2Name, p2NamePos, p2NameFontNo, p2NameFontBank, p2NameAlign);
    }

    /**
     * Face bg (fight.sff) + character portrait — same math as desktop {@code FaceRender} /
     * {@code BaseRender} (not lifebar flip).
     */
    private void drawFace(
            MugenDrawer md,
            MugenPoint pos,
            MugenPoint offset,
            int facing,
            ImageContainer portrait,
            int xAxis,
            int yAxis,
            boolean p2) {
        drawFaceSpr(md, pos, faceBgGrp, faceBgImg, p2);
        if (portrait == null) {
            return;
        }
        boolean flipH = facing < 0;
        float x;
        float y = pos.y - yAxis + offset.y;
        if (flipH) {
            x = pos.x - portrait.getWidth() + xAxis + offset.x;
        } else {
            x = pos.x - xAxis + offset.x;
        }
        md.draw(new DrawProperties(x, y, flipH, false, portrait));
    }

    /** HUD face/bg sprites: facing -1 uses {@code pos - width + xAxis} (BaseRender). */
    private void drawFaceSpr(MugenDrawer md, MugenPoint pos, int grp, int img, boolean flipH) {
        ImageSpriteSFF sff = getSpr(grp, img);
        if (sff == null) {
            return;
        }
        float x = flipH ? pos.x - sff.getWidth() + sff.getXAxis() : pos.x - sff.getXAxis();
        float y = pos.y - sff.getYAxis();
        md.draw(new DrawProperties(x, y, flipH, false, sff.getImage()));
    }

    private void drawName(
            MugenDrawer md, String name, MugenPoint pos, int fontNo, int fontBank, int align) {
        if (name == null || name.isEmpty()) {
            return;
        }
        FontProducer font = resolveFont(fontNo);
        if (font == null) {
            font = resolveFont(2);
        }
        if (font == null) {
            return;
        }
        font.draw(fontBank, pos.x, pos.y, md, name, align, 1f);
    }

    /**
     * Draw round timer using {@code fight.def} [Time] font (same as desktop {@code TimeRender}).
     *
     * @param ticksLeft remaining round ticks ({@link GwtFightRules#roundTimer})
     */
    public void renderTimer(MugenDrawer md, int ticksLeft) {
        if (ticksLeft < 0) {
            return;
        }
        int seconds = ticksLeft / framesPerCount;
        String text = Integer.toString(seconds);
        FontProducer font = resolveFont(timeFontNo);
        if (font != null) {
            font.draw(timeFontBank, timePos.x, timePos.y, md, text, timeAlign, 1f);
            return;
        }
        // Fallback if system fonts are unavailable
        md.setColor(1f, 1f, 1f, 1f);
        int x = timePos.x - text.length() * 3;
        md.fillRect(x - 2, timePos.y - 10, text.length() * 7 + 4, 12);
        md.setColor(0.1f, 0.1f, 0.1f, 1f);
        for (int i = 0; i < text.length(); i++) {
            drawDigitFallback(md, x + i * 7, timePos.y - 8, text.charAt(i));
        }
    }

    /**
     * Round announcement text ({@code fight.def} round.default.font = 1 → jg.fnt).
     */
    public void renderRoundBanner(MugenDrawer md, int roundNo) {
        renderBannerText(md, "Round " + roundNo, 160, 100);
    }

    /** FIGHT! call from fight.sff group 80 (same as desktop {@code fight.anim = 80}). */
    public void renderFightCall(MugenDrawer md) {
        drawBannerSpr(md, 160, 110, 80, 0);
    }

    /** K.O. from fight.sff group 200. */
    public void renderKoCall(MugenDrawer md) {
        drawBannerSpr(md, 160, 70, 200, 0);
    }

    public void renderBanner(MugenDrawer md, String text) {
        renderBannerText(md, text, 160, 112);
    }

    private void renderBannerText(MugenDrawer md, String text, int x, int y) {
        if (text == null || text.isEmpty()) {
            return;
        }
        // fight.def font1 = jg.fnt (letters). font2 is num1 (digits only) — do not use for text.
        FontProducer font = resolveFont(1);
        if (font == null) {
            font = resolveSystemFont(3);
        }
        if (font == null) {
            font = resolveSystemFont(2);
        }
        if (font != null) {
            font.draw(0, x, y, md, text, 0, 1f);
            return;
        }
        md.setColor(0f, 0f, 0f, 0.5f);
        md.fillRect(x - 80, y - 14, 160, 28);
        md.setColor(1f, 1f, 1f, 1f);
        int tx = x - text.length() * 4;
        for (int i = 0; i < text.length(); i++) {
            md.fillRect(tx + i * 8, y - 8, 6, 10);
        }
    }

    private void drawBannerSpr(MugenDrawer md, int posX, int posY, int grp, int img) {
        if (fightSff == null) {
            return;
        }
        ImageSpriteSFF sff = getSpr(grp, img);
        if (sff == null) {
            // Fallback text if sprite missing
            if (grp == 80) {
                renderBannerText(md, "FIGHT!", posX, posY);
            } else if (grp == 200) {
                renderBannerText(md, "K.O.", posX, posY);
            }
            return;
        }
        float x = posX - sff.getXAxis();
        float y = posY - sff.getYAxis();
        md.draw(new DrawProperties(x, y, false, false, sff.getImage()));
    }

    private FontProducer resolveFont(int fontNo) {
        if (fontNo < 0) {
            return null;
        }
        FontProducer fight = fightFonts.get(fontNo);
        if (fight != null) {
            return fight;
        }
        return resolveSystemFont(fontNo);
    }

    private static FontProducer resolveSystemFont(int fontNo) {
        if (fontNo < 0) {
            return null;
        }
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

    /** Very small 3x5 digit marks so countdown is readable without fonts. */
    private static void drawDigitFallback(MugenDrawer md, int x, int y, char c) {
        if (c < '0' || c > '9') {
            return;
        }
        // Solid block per digit — enough to see the count change width/position.
        int w = 5;
        int h = 8;
        md.fillRect(x, y, w, h);
    }

    /** Lifebar: bg 11, mid 12, front 13 ({@code fight.def} [Lifebar]). */
    private void drawLifeBar(
            MugenDrawer md, MugenPoint pos, int life, int maxLife, int rangeMax, boolean flip) {
        drawSpr(md, pos, 11, 0, flip);
        drawSpr(md, pos, 12, 0, flip);
        drawSprMeter(md, pos, 13, 0, life, maxLife, rangeMax, flip);
    }

    /** Powerbar: bg 41, front 43 ({@code fight.def} [Powerbar]) — not life sprites. */
    private void drawPowerBar(
            MugenDrawer md, MugenPoint pos, int power, int rangeMax, boolean flip) {
        drawSpr(md, pos, 41, 0, flip);
        drawSprMeter(md, pos, 43, 0, power, GwtFightRules.MAX_POWER, rangeMax, flip);
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

    private void drawSprMeter(
            MugenDrawer md,
            MugenPoint pos,
            int grp,
            int img,
            int value,
            int maxValue,
            int rangeMax,
            boolean flipH) {
        ImageSpriteSFF sff = getSpr(grp, img);
        if (sff == null) {
            return;
        }
        int fillPx = maxValue > 0 ? (value * rangeMax) / maxValue : 0;
        DrawProperties dp;
        if (flipH) {
            dp =
                    new DrawProperties(
                            pos.x + sff.getWidth() - sff.getXAxis(),
                            pos.y - sff.getYAxis(),
                            true,
                            false,
                            sff.getImage());
            int delta = Math.min(fillPx, sff.getWidth());
            dp.setXRightDst(dp.getXLeftDst() + sff.getWidth());
            dp.setXLeftDst(dp.getXRightDst() - delta);
            dp.setXLeftSrc(dp.getXRightSrc() - delta);
        } else {
            dp =
                    new DrawProperties(
                            pos.x - sff.getXAxis(), pos.y - sff.getYAxis(), false, false, sff.getImage());
            int delta = Math.min(fillPx, sff.getWidth());
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
