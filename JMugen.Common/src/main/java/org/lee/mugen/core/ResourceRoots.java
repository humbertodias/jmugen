package org.lee.mugen.core;

import java.io.File;
import org.lee.mugen.ResourceBundleHelper;

/**
 * Resolves the Mugen data root (see {@code data.properties} {@code resource} key). On Android the
 * app sets the system property {@code jmugen.resource} to a writable directory before any game code
 * touches {@link JMugenConstant#RESOURCE}.
 */
public final class ResourceRoots {

    private ResourceRoots() {
    }

    public static String resolveDataResourcePrefix() {
        String override = System.getProperty("jmugen.resource");
        if (override != null && !override.isEmpty()) {
            char end = override.charAt(override.length() - 1);
            if (end != '/' && end != File.separatorChar) {
                return override + "/";
            }
            return override;
        }
        return ResourceBundleHelper.getBundle("data").getString("resource");
    }
}
