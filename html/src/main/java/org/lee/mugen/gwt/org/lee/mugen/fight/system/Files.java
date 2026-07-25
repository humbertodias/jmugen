package org.lee.mugen.fight.system;

import java.util.HashMap;
import java.util.Map;
import org.lee.mugen.fight.select.GwtSelect;
import org.lee.mugen.fight.section.Section;
import org.lee.mugen.sff.SffReader;
import org.lee.mugen.snd.Snd;
import org.lee.mugen.snd.SndReader;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;
import org.lee.mugen.sprite.common.resource.FontParser;
import org.lee.mugen.sprite.common.resource.FontProducer;
import org.lee.mugen.sprite.parser.Parser;

/** GWT: title screen only needs spr/snd/font from [Files], not select/intro/fight storyboards. */
public class Files implements Section {

    private MugenSystem root;
    private SpriteSFF spr;
    private Snd snd;
    private GwtSelect gwtSelect;
    private final Map<Integer, FontProducer> font = new HashMap<Integer, FontProducer>();

    private String resolvePath(String value) {
        return Parser.getExistFile(root.getCurrentDir(), value);
    }

    @Override
    public void parse(Object root, String name, String value) throws Exception {
        this.root = (MugenSystem) root;
        if (name.equals("spr")) {
            SffReader sffReader = new SffReader(resolvePath(value), null);
            spr = new SpriteSFF(sffReader, true, false);
        } else if (name.equals("snd")) {
            snd = SndReader.parse(resolvePath(value));
        } else if (name.equals("select")) {
            gwtSelect = new GwtSelect(resolvePath(value));
        } else if (name.startsWith("font")) {
            String sNum = name.indexOf('.') != -1 ? name.substring(4, name.indexOf('.')) : name.substring(4);
            int num = sNum.length() > 0 ? Integer.parseInt(sNum) : 0;
            font.put(num, FontParser.getFontProducer(resolvePath(value)));
        }
    }

    public MugenSystem getRoot() {
        return root;
    }

    public SpriteSFF getSpr() {
        return spr;
    }

    public Snd getSnd() {
        return snd;
    }

    public Map<Integer, FontProducer> getFont() {
        return font;
    }

    public GwtSelect getGwtSelect() {
        return gwtSelect;
    }
}
