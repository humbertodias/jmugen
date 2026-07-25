package org.lee.mugen.renderer.libgdx.android;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Copies bundled Mugen {@code data/} from APK assets into app-private storage so {@code java.io.File}
 * paths used by the engine resolve correctly.
 */
public final class AndroidGameData {

    private static final String MARKER = ".installed";

    private AndroidGameData() {
    }

    /**
     * @param context application context
     * @param assetDir asset folder name (e.g. {@code "data"} for files under {@code assets/data/})
     * @param destRoot writable directory whose contents mirror the repo {@code data/} tree
     */
    public static void ensureInstalled(Context context, String assetDir, File destRoot) throws IOException {
        File marker = new File(destRoot, MARKER);
        if (marker.isFile()) {
            return;
        }
        AssetManager am = context.getAssets();
        String[] top = am.list(assetDir);
        if (top == null || top.length == 0) {
            throw new IOException(
                "Bundled assets missing: \"" + assetDir + "/\" is empty or absent in the APK. "
                    + "Build with Gradle so game files are copied into assets (see android/build.gradle "
                    + "syncMugenGameAssets), or copy the repo assets/ tree into src/main/assets/" + assetDir + "/."
            );
        }
        deleteRecursive(destRoot);
        destRoot.mkdirs();
        copyAssetTree(am, assetDir, destRoot);
        if (!marker.createNewFile()) {
            throw new IOException("Could not write install marker: " + marker);
        }
    }

    private static void deleteRecursive(File root) {
        if (!root.exists()) {
            return;
        }
        if (root.isDirectory()) {
            File[] kids = root.listFiles();
            if (kids != null) {
                for (File c : kids) {
                    deleteRecursive(c);
                }
            }
        }
        root.delete();
    }

    /**
     * Recursively copy {@code assetPath} to {@code destDir}. When {@link AssetManager#list} returns no
     * names, the path is either a file (copy bytes) or an empty directory in the bundle (mkdir only) —
     * never call {@link AssetManager#open} on a directory path.
     */
    private static void copyAssetTree(AssetManager am, String assetPath, File destDir) throws IOException {
        String[] children = am.list(assetPath);
        if (children == null) {
            children = new String[0];
        }
        if (children.length > 0) {
            destDir.mkdirs();
            for (String name : children) {
                String sub = assetPath + "/" + name;
                copyAssetTree(am, sub, new File(destDir, name));
            }
            return;
        }
        if (copyAssetFileIfPresent(am, assetPath, destDir)) {
            return;
        }
        if (!destDir.mkdirs() && !destDir.isDirectory()) {
            throw new IOException("Cannot create directory (empty asset folder?): " + destDir);
        }
    }

    /**
     * @return true if {@code assetPath} was opened as a file and copied to {@code destFile}
     */
    private static boolean copyAssetFileIfPresent(AssetManager am, String assetPath, File destFile) throws IOException {
        try (InputStream in = am.open(assetPath)) {
            destFile.getParentFile().mkdirs();
            try (OutputStream out = new FileOutputStream(destFile)) {
                byte[] buf = new byte[8192];
                int n;
                while ((n = in.read(buf)) > 0) {
                    out.write(buf, 0, n);
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
