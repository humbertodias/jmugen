package org.lee.mugen.fight.select;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.lee.mugen.io.MugenDataStreams;

/** GWT: load {@code select.def} character list without {@code FileInputStream}. */
public class GwtSelect {

    private final List<String> charactersOrder = new ArrayList<String>();
    private final GwtCharacters characters;
    private final GwtExtraStages extraStages = new GwtExtraStages();

    public GwtSelect(String path) throws IOException {
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
                int comment = line.indexOf(';');
                if (comment >= 0) {
                    line = line.substring(0, comment).trim();
                }
                if (line.isEmpty()) {
                    continue;
                }
                if ("characters".equals(section)) {
                    String name = line.split(",")[0].trim();
                    if (name.length() > 0 && !"random".equalsIgnoreCase(name)) {
                        charactersOrder.add(name);
                    }
                } else if ("extrastages".equals(section)) {
                    extraStages.addStage(line);
                }
            }
        } finally {
            reader.close();
        }
        characters = new GwtCharacters(charactersOrder);
    }

    public GwtExtraStages getExtraStages() {
        return extraStages;
    }

    public GwtCharacters getCharacters() {
        return characters;
    }

    public List<String> getCharactersOrder() {
        return Collections.unmodifiableList(charactersOrder);
    }

    public String getCharacterAt(int row, int col, int rows, int columns) {
        int index = row * columns + col;
        if (index < 0 || index >= charactersOrder.size()) {
            return null;
        }
        return charactersOrder.get(index);
    }
}
