package org.lee.mugen;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ExternalBundleControl extends ResourceBundle.Control {
    private final String basePath;

    public ExternalBundleControl(String basePath) {
        this.basePath = basePath;
    }

    @Override
    public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
            throws IOException {
        String fileName = String.format("%s/%s.properties", basePath, baseName);
        File file = new File(fileName);

        if (!file.exists()) {
            return null;
        }

        try (InputStream input = new FileInputStream(file)) {
            return new PropertyResourceBundle(input);
        }
    }

}