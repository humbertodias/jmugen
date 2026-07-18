package org.lee.mugen;

/**
 * GWT: classpath-only property lookup (no {@link java.util.ResourceBundle} / {@link java.io.File}).
 */
public final class ResourceBundleHelper {

    public static final class MugenBundle {
        private final String resourcePrefix;

        MugenBundle(String resourcePrefix) {
            this.resourcePrefix = resourcePrefix;
        }

        public String getString(String key) {
            if ("resource".equals(key)) {
                return resourcePrefix;
            }
            throw new IllegalArgumentException("Unknown key: " + key);
        }
    }

    private ResourceBundleHelper() {
    }

    public static MugenBundle getBundle(String name) {
        if ("data".equals(name)) {
            return new MugenBundle("data/");
        }
        throw new IllegalArgumentException("Unknown bundle: " + name);
    }
}
