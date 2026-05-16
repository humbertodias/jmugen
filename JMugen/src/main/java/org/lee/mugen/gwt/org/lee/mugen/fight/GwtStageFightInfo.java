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
                if (!"playerinfo".equals(section)) {
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
                }
            }
        } finally {
            reader.close();
        }
        return info;
    }
}
