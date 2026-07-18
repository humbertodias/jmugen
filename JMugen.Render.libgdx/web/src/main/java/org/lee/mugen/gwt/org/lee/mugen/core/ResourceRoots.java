package org.lee.mugen.core;

import org.lee.mugen.ResourceBundleHelper;

/**
 * GWT: no {@link System#getProperty(String)} / {@link java.io.File}.
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
        if (resourcePrefixOverride != null && !resourcePrefixOverride.isEmpty()) {
            return ensureTrailingSlash(resourcePrefixOverride);
        }
        return ResourceBundleHelper.getBundle("data").getString("resource");
    }

    private static String ensureTrailingSlash(String override) {
        char end = override.charAt(override.length() - 1);
        if (end != '/') {
            return override + "/";
        }
        return override;
    }
}
