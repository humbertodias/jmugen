package org.lee.mugen.fight.system;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.lee.mugen.background.Background;
import org.lee.mugen.background.GwtBackground;
import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.fight.section.Section;
import org.lee.mugen.io.MugenDataStreams;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;

/** GWT: title-screen subset of {@link org.lee.mugen.fight.system.MugenSystem}. */
public class MugenSystem {

    private static MugenSystem instance;

    public static synchronized MugenSystem getInstance() {
        if (instance == null) {
            instance = new MugenSystem(JMugenConstant.RESOURCE + "data/system.def");
            try {
                instance.parse();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return instance;
    }

    private final String filename;
    private Info info;
    private Files files;
    private Music music;
    private TitleInfo titleInfo;
    private Background titleBackground;
    private SelectInfo selectInfo;
    private Background selectBackground;
    private VsScreen vsScreen;
    private Background versusBackground;
    private Background optionBackground;

    private MugenSystem(String filename) {
        this.filename = filename;
    }

    public SpriteSFF getSpriteSff() {
        return getFiles().getSpr();
    }

    public File getCurrentDir() {
        int slash = Math.max(filename.lastIndexOf('/'), filename.lastIndexOf('\\'));
        if (slash < 0) {
            return new File("");
        }
        return new File(filename.substring(0, slash));
    }

    private void parse(Section section, GroupText grp) throws Exception {
        for (String key : grp.getKeysOrdered()) {
            section.parse(this, key, grp.getKeyValues().get(key));
        }
    }

    public Background buildBackground(String prefix) {
        return new GwtBackground(this, getCurrentDir(), prefix);
    }

    private void parse() throws Exception {
        List<GroupText> groups;
        try {
            Reader r = MugenDataStreams.openUtf8Reader(filename);
            groups = Parser.getGroupTextMap(r);
        } catch (Exception e) {
            throw new IOException("Failed to read " + filename, e);
        }

        try {
            titleBackground = buildBackground("title");
            titleBackground.parse(this, filterTitleBackgroundGroups(groups));
        } catch (Exception e) {
            throw new IOException("Failed to parse title background", e);
        }

        try {
            selectBackground = buildBackground("select");
            selectBackground.parse(this, filterBackgroundGroups(groups, "selectbg"));
        } catch (Exception e) {
            throw new IOException("Failed to parse select background", e);
        }

        try {
            versusBackground = buildBackground("versus");
            versusBackground.parse(this, filterBackgroundGroups(groups, "versusbg"));
        } catch (Exception e) {
            throw new IOException("Failed to parse versus background", e);
        }

        for (GroupText grp : groups) {
            try {
                if (grp.getSection().equals("info")) {
                    info = new Info();
                    parse(info, grp);
                } else if (grp.getSection().equals("files")) {
                    files = new Files();
                    parse(files, grp);
                } else if (grp.getSection().equals("music")) {
                    music = new Music();
                    parse(music, grp);
                } else if (grp.getSection().equals("title info")) {
                    titleInfo = new TitleInfo();
                    parse(titleInfo, grp);
                } else if (grp.getSection().equals("select info")) {
                    selectInfo = new SelectInfo();
                    parse(selectInfo, grp);
                } else if (grp.getSection().equals("vs screen")) {
                    vsScreen = new VsScreen();
                    parse(vsScreen, grp);
                }
            } catch (Exception e) {
                throw new IOException("Failed to parse section [" + grp.getSection() + "]", e);
            }
        }
    }

    public Info getInfo() {
        return info;
    }

    public Files getFiles() {
        return files;
    }

    public Music getMusic() {
        return music;
    }

    public TitleInfo getTitleInfo() {
        return titleInfo;
    }

    public Background getTitleBackground() {
        return titleBackground;
    }

    public SelectInfo getSelectInfo() {
        return selectInfo;
    }

    public Background getSelectBackground() {
        return selectBackground;
    }

    public VsScreen getVsScreen() {
        return vsScreen;
    }

    public Background getVersusBackground() {
        return versusBackground;
    }

    public Background getOptionBackground() {
        return optionBackground;
    }

    /** Web: only {@code [TitleBGdef]} / {@code [TitleBG n]} groups. */
    private static List<GroupText> filterTitleBackgroundGroups(List<GroupText> groups) {
        return filterBackgroundGroups(groups, "titlebg");
    }

    private static List<GroupText> filterBackgroundGroups(List<GroupText> groups, String prefix) {
        List<GroupText> out = new ArrayList<GroupText>();
        String defSection = prefix + "def";
        String bgPrefix = prefix + " ";
        for (GroupText grp : groups) {
            String section = grp.getSection();
            if (section == null) {
                continue;
            }
            String s = section.toLowerCase();
            if (defSection.equals(s) || s.startsWith(bgPrefix)) {
                out.add(grp);
            }
        }
        return out;
    }
}
