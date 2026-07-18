package org.lee.mugen.fight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.io.MugenDataStreams;

/** GWT: player start positions from stage.def {@code [PlayerInfo]}. */
public class GwtStageFightInfo {

    public int p1startx = -70;
    public int p1starty = 0;
    public int p1facing = 1;
    public int p2startx = 70;
    public int p2starty = 0;
    public int p2facing = -1;
    public int leftbound = -1000;
    public int rightbound = 1000;
    public int boundleft = -150;
    public int boundright = 150;
    /** Stage floor line (fight.def / stage {@code zoffset}); default 192. */
    public int zoffset = 192;
    /** Relative path from stage.def {@code [Music] bgmusic}, or null. */
    public String bgmusic;
    /** Stage {@code [Shadow] intensity} (0–256); 0 disables shadow. */
    public int shadowIntensity = 128;
    /** Stage {@code [Shadow] yscale}; abs used for squash, sign for flip-into-screen. */
    public float shadowYScale = 0.4f;

    public static GwtStageFightInfo load(String stagePath) throws IOException {
        GwtStageFightInfo info = new GwtStageFightInfo();
        if (stagePath == null) {
            return info;
        }
        Reader reader = MugenDataStreams.openUtf8Reader(JMugenConstant.RESOURCE + stagePath);
        try {
            BufferedReader br = new BufferedReader(reader);
            String section = "";
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith(";")) {
                    continue;
                }
                if (line.startsWith("[") && line.endsWith("]")) {
                    section = line.substring(1, line.length() - 1).trim().toLowerCase();
                    continue;
                }
                if (!"playerinfo".equals(section)
                        && !"camera".equals(section)
                        && !"music".equals(section)
                        && !"stageinfo".equals(section)
                        && !"shadow".equals(section)) {
                    continue;
                }
                int eq = line.indexOf('=');
                if (eq < 0) {
                    continue;
                }
                String key = line.substring(0, eq).trim().toLowerCase();
                String value = line.substring(eq + 1).trim();
                int semi = value.indexOf(';');
                if (semi >= 0) {
                    value = value.substring(0, semi).trim();
                }
                if ("music".equals(section)) {
                    if ("bgmusic".equals(key) && value.length() > 0) {
                        info.bgmusic = value.replace('\\', '/');
                    }
                    continue;
                }
                if ("shadow".equals(section)) {
                    if ("intensity".equals(key)) {
                        info.shadowIntensity = Integer.parseInt(value);
                    } else if ("yscale".equals(key)) {
                        info.shadowYScale = Float.parseFloat(value);
                    }
                    continue;
                }
                if ("stageinfo".equals(section)) {
                    if ("zoffset".equals(key)) {
                        info.zoffset = Integer.parseInt(value);
                    }
                    continue;
                }
                if ("playerinfo".equals(section)) {
                    if ("p1startx".equals(key)) {
                        info.p1startx = Integer.parseInt(value);
                    } else if ("p1starty".equals(key)) {
                        info.p1starty = Integer.parseInt(value);
                    } else if ("p1facing".equals(key)) {
                        info.p1facing = Integer.parseInt(value);
                    } else if ("p2startx".equals(key)) {
                        info.p2startx = Integer.parseInt(value);
                    } else if ("p2starty".equals(key)) {
                        info.p2starty = Integer.parseInt(value);
                    } else if ("p2facing".equals(key)) {
                        info.p2facing = Integer.parseInt(value);
                    } else if ("leftbound".equals(key)) {
                        info.leftbound = Integer.parseInt(value);
                    } else if ("rightbound".equals(key)) {
                        info.rightbound = Integer.parseInt(value);
                    }
                } else if ("boundleft".equals(key)) {
                    info.boundleft = Integer.parseInt(value);
                } else if ("boundright".equals(key)) {
                    info.boundright = Integer.parseInt(value);
                }
            }
        } finally {
            reader.close();
        }
        return info;
    }
}
