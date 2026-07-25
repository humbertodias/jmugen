package org.lee.mugen.core;

/**
 * GWT/TeaVM: no {@link System#getProperty(String)} / {@link java.io.File}.
 */
public final class ResourceRoots {

    private static String resourcePrefixOverride;

    private ResourceRoots() {
    }

    public static void setResourcePrefixOverride(String prefix) {
        resourcePrefixOverride = prefix;
    }

    public static void clearResourcePrefixOverride() {
        resourcePrefixOverride = null;
    }

    public static String resolveDataResourcePrefix() {
        if (resourcePrefixOverride != null) {
            if (resourcePrefixOverride.isEmpty()) {
                return "";
            }
            return ensureTrailingSlash(resourcePrefixOverride);
        }
        // gdx-teavm serves repo assets/ at internal root (data/system.def, chars/…).
        return "";
    }

    private static String ensureTrailingSlash(String override) {
        char end = override.charAt(override.length() - 1);
        if (end != '/') {
            return override + "/";
        }
        return override;
    }
}
