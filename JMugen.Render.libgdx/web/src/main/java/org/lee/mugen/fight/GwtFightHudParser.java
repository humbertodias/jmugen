package org.lee.mugen.fight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import org.lee.mugen.io.MugenDataStreams;

/** Parses {@code fight.def} sections needed by {@link GwtFightHud}. */
final class GwtFightHudParser {

    private GwtFightHudParser() {
    }

    static void parse(String path, GwtFightHud hud) throws IOException {
        Reader reader = MugenDataStreams.openUtf8Reader(path);
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
                if (!"files".equals(section)
                        && !"lifebar".equals(section)
                        && !"powerbar".equals(section)
                        && !"time".equals(section)
                        && !"face".equals(section)
                        && !"name".equals(section)) {
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
                if ("files".equals(section)) {
                    hud.applyFilesKey(key, value);
                } else if ("lifebar".equals(section)) {
                    hud.applyLifebarKey(key, value, hud);
                } else if ("powerbar".equals(section)) {
                    hud.applyPowerbarKey(key, value);
                } else if ("time".equals(section)) {
                    hud.applyTimeKey(key, value);
                } else if ("face".equals(section)) {
                    hud.applyFaceKey(key, value);
                } else {
                    hud.applyNameKey(key, value);
                }
            }
        } finally {
            reader.close();
        }
    }
}
