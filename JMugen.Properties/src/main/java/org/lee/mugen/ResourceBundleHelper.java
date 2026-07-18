package org.lee.mugen;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

public final class ResourceBundleHelper {

    private ResourceBundleHelper() {
    }

    public static ResourceBundle getBundle(String name) {
        File externalFile = new File(".",  name + ".properties");
        return (externalFile.exists()) ?
                ResourceBundle.getBundle(name, Locale.getDefault(), new ExternalBundleControl(externalFile.getParent())):
                ResourceBundle.getBundle(name);

    }

}