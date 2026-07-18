package org.lee.mugen;

import java.util.HashMap;
import java.util.Map;

/**
 * GWT: classpath-only property lookup (no {@link java.util.ResourceBundle} / {@link java.io.File}).
 * Super-source override for {@code org.lee.mugen.ResourceBundleHelper}.
 */
public final class ResourceBundleHelper {

    public static final class MugenBundle {
        private final Map<String, String> values;

        MugenBundle(Map<String, String> values) {
            this.values = values;
        }

        public String getString(String key) {
            String v = values.get(key);
            if (v == null) {
                throw new IllegalArgumentException(
                        "Unknown key: " + key + " in GWT ResourceBundleHelper");
            }
            return v;
        }
    }

    private static final MugenBundle DATA = bundle("resource", "data/");
    private static final MugenBundle RENDER =
            bundle("renderClass", "org.lee.mugen.renderer.libgdx.web.LGDXMugenDrawer");
    private static final MugenBundle KEYS =
            bundle(
                    "P1.UP", "w",
                    "P1.DOWN", "s",
                    "P1.LEFT", "a",
                    "P1.RIGHT", "d",
                    "P1.X", "u",
                    "P1.Y", "i",
                    "P1.Z", "o",
                    "P1.A", "j",
                    "P1.B", "k",
                    "P1.C", "l",
                    "P1.XYZ", "p",
                    "P1.ABC", "SEMICOLON",
                    "P2.UP", "up",
                    "P2.DOWN", "down",
                    "P2.LEFT", "left",
                    "P2.RIGHT", "right",
                    "P2.X", "NUMPAD4",
                    "P2.Y", "NUMPAD5",
                    "P2.Z", "NUMPAD6",
                    "P2.A", "0",
                    "P2.B", "NUMPAD2",
                    "P2.C", "NUMPAD3",
                    "P2.XYZ", "NUMPAD7",
                    "P2.ABC", "NUMPAD8",
                    "P3.UP", "9",
                    "P3.DOWN", "9",
                    "P3.LEFT", "9",
                    "P3.RIGHT", "9",
                    "P3.X", "9",
                    "P3.Y", "9",
                    "P3.Z", "9",
                    "P3.A", "9",
                    "P3.B", "9",
                    "P3.C", "9",
                    "P3.XYZ", "9",
                    "P3.ABC", "9",
                    "P4.UP", "9",
                    "P4.DOWN", "9",
                    "P4.LEFT", "9",
                    "P4.RIGHT", "9",
                    "P4.X", "9",
                    "P4.Y", "9",
                    "P4.Z", "9",
                    "P4.A", "9",
                    "P4.B", "9",
                    "P4.C", "9",
                    "P4.XYZ", "9",
                    "P4.ABC", "9");

    private ResourceBundleHelper() {
    }

    public static MugenBundle getBundle(String name) {
        if ("data".equals(name)) {
            return DATA;
        }
        if ("render".equals(name)) {
            return RENDER;
        }
        if ("keys".equals(name)) {
            return KEYS;
        }
        throw new IllegalArgumentException("Unknown bundle: " + name);
    }

    private static MugenBundle bundle(String... kv) {
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i + 1 < kv.length; i += 2) {
            map.put(kv[i], kv[i + 1]);
        }
        return new MugenBundle(map);
    }
}
