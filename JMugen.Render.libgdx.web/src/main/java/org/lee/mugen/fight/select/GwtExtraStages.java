package org.lee.mugen.fight.select;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.io.MugenDataStreams;

/** GWT: extra stage list from {@code select.def} without {@code FileInputStream}. */
public class GwtExtraStages {

    private final List<String> stages = new ArrayList<String>();
    private final Map<String, String> displayNames = new HashMap<String, String>();

    public void addStage(String path) {
        if (path != null && path.length() > 0) {
            stages.add(path.replace('\\', '/'));
        }
    }

    public List<String> getStages() {
        return stages;
    }

    public String getRealName(String path) {
        String key = path.replace('\\', '/');
        if (displayNames.containsKey(key)) {
            return displayNames.get(key);
        }
        String name = readStageName(JMugenConstant.RESOURCE + key);
        if (name == null) {
            name = key;
        }
        displayNames.put(key, name);
        return name;
    }

    private static String readStageName(String resourcePath) {
        Reader reader = null;
        try {
            reader = MugenDataStreams.openUtf8Reader(resourcePath);
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
                if (!"info".equals(section)) {
                    continue;
                }
                int eq = line.indexOf('=');
                if (eq < 0) {
                    continue;
                }
                String k = line.substring(0, eq).trim().toLowerCase();
                if ("name".equals(k)) {
                    String value = line.substring(eq + 1).trim();
                    if (value.startsWith("\"") && value.endsWith("\"") && value.length() >= 2) {
                        value = value.substring(1, value.length() - 1);
                    }
                    return value;
                }
            }
        } catch (IOException ignored) {
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }
        return null;
    }
}
