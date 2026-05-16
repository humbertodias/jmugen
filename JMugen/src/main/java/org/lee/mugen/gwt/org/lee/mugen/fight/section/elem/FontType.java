package org.lee.mugen.fight.section.elem;

import java.util.Map;
import org.lee.mugen.fight.system.Files;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.sprite.common.resource.FontProducer;
import org.lee.mugen.util.BeanTools;

/** GWT: title fonts without {@code Class#getMethod()}. */
public class FontType extends CommonType {

    public enum ALIGNMT {
        left(-1), center(0), right(1);

        final int code;

        ALIGNMT(int code) {
            this.code = code;
        }

        public static ALIGNMT getValue(int alignmt) {
            switch (alignmt) {
                case -1:
                    return left;
                case 0:
                    return center;
                case 1:
                    return right;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public Integer getCode() {
            return code;
        }
    }

    int fontno;
    int fontbank;
    ALIGNMT alignmt = ALIGNMT.left;
    String text;
    Object root;
    private Map<Integer, FontProducer> font;

    public FontType(Object root, int fontno, int fontbank, int alignmt) {
        this(root, fontno, fontbank, ALIGNMT.getValue(alignmt));
    }

    public FontType(Object root, int fontno, int fontbank, ALIGNMT alignmt) {
        this.fontno = fontno;
        this.fontbank = fontbank;
        this.alignmt = alignmt;
        this.root = root;
    }

    public FontType(Object root) {
        this.root = root;
    }

    public Map<Integer, FontProducer> getFont() {
        if (font == null) {
            Files files = resolveFiles();
            if (files != null) {
                font = files.getFont();
            }
        }
        return font;
    }

    private Files resolveFiles() {
        if (root instanceof MugenSystem) {
            return ((MugenSystem) root).getFiles();
        }
        if (root instanceof Files) {
            return (Files) root;
        }
        return null;
    }

    @Override
    public void parse(String name, String value) {
        if (name.equals("font")) {
            int[] res = (int[]) BeanTools.getConvertersMap().get(int[].class).convert(value);
            fontno = res[0];
            fontbank = res[1];
        } else if (name.equals("text")) {
            text = value;
        } else if (name.equals("alignmt")) {
            alignmt = ALIGNMT.getValue(Integer.parseInt(value));
        }
    }

    public int getFontno() {
        return fontno;
    }

    public int getFontbank() {
        return fontbank;
    }

    public ALIGNMT getAlignmt() {
        return alignmt;
    }

    public void setAlignmt(ALIGNMT alignmt) {
        this.alignmt = alignmt;
    }

    public void setFontno(int fontno) {
        this.fontno = fontno;
    }

    public void setFontbank(int fontbank) {
        this.fontbank = fontbank;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
