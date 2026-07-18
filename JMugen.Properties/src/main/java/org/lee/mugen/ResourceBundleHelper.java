package org.lee.mugen;

import java.io.File;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public final class ResourceBundleHelper {

    private ResourceBundleHelper() {
    }

    public static ResourceBundle getBundle(String name) {
        try {
            File externalFile = new File(".", name + ".properties");
            if (externalFile.isFile()) {
                return ResourceBundle.getBundle(
                        name, Locale.getDefault(), new ExternalBundleControl(externalFile.getParent()));
            }
            return ResourceBundle.getBundle(name);
        } catch (MissingResourceException e) {
            throw new MissingResourceException(
                    "Missing properties bundle '"
                            + name
                            + "' (expected "
                            + name
                            + ".properties on the classpath, e.g. JMugen.Properties). cwd="
                            + new File(".").getAbsolutePath(),
                    name,
                    "");
        }
    }
}
