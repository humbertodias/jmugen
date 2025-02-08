package org.lee.mugen;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public final class ResourceBundleHelper {

    private ResourceBundleHelper() {
    }

    /**
     * Retrieves the ResourceBundle based on the provided name.
     * If a system property with the specified name exists, it uses a custom properties file.
     *
     * @param name the name of the ResourceBundle
     * @return the corresponding ResourceBundle
     */
    public static ResourceBundle getBundle(String name) {
        // Check if the system property exists to decide whether to use a custom properties file
        String propertiesPath = System.getProperty(name);
        if (propertiesPath != null && !propertiesPath.trim().isEmpty()) {
            return loadBundleFromFile(propertiesPath);
        }
        return ResourceBundle.getBundle(name);
    }

    /**
     * Loads a ResourceBundle from a properties file located at the specified path.
     * If the file is not found or an error occurs while loading it, returns null.
     *
     * @param propertiesPath the full path to the properties file
     * @return the loaded ResourceBundle, or null if not found
     */
    private static ResourceBundle loadBundleFromFile(String propertiesPath) {
        File file = new File(propertiesPath);

        // Check if the file exists before attempting to load it
        if (!file.exists() || !file.isFile()) {
            return null;
        }

        // Attempt to load the properties file
        try (FileInputStream fis = new FileInputStream(file)) {
            return new PropertyResourceBundle(fis);
        } catch (IOException e) {
            // IOException caught and re-wrapped in a runtime exception with more context
            throw new RuntimeException("Error loading properties from file: " + propertiesPath, e);
        }
    }
}