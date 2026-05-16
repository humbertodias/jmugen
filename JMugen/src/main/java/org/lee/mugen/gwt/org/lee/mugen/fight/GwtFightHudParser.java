package org.lee.mugen.fight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import org.lee.mugen.io.MugenDataStreams;

/** Parses {@code fight.def} lifebar section for {@link GwtFightHud}. */
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
                if (!"lifebar".equals(section)) {
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
                hud.applyLifebarKey(key, value, hud);
            }
        } finally {
            reader.close();
        }
    }
}
