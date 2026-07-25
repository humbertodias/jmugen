package org.lee.mugen.core;

import java.io.File;
import org.lee.mugen.ResourceBundleHelper;

/**
 * Resolves the Mugen data root (see {@code data.properties} {@code resource} key). On Android the
 * app sets the system property {@code jmugen.resource} to a writable directory before any game code
 * touches {@link JMugenConstant#RESOURCE}.
 */
public final class ResourceRoots {

    private static String resourcePrefixOverride;

    private ResourceRoots() {
    }

    /** LibGDX Web (GWT has no {@link System#setProperty(String, String)}). */
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
        String override = System.getProperty("jmugen.resource");
        if (override != null && !override.isEmpty()) {
            return ensureTrailingSlash(override);
        }
        return ResourceBundleHelper.getBundle("data").getString("resource");
    }

    private static String ensureTrailingSlash(String override) {
        char end = override.charAt(override.length() - 1);
        if (end != '/' && end != File.separatorChar) {
            return override + "/";
        }
        return override;
    }
}
