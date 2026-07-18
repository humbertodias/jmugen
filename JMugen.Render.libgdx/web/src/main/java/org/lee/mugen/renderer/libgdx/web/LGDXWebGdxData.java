package org.lee.mugen.renderer.libgdx.web;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import org.lee.mugen.io.MugenDataStreams;

final class LGDXWebGdxData {

    private LGDXWebGdxData() {
    }

    static void install() {
        MugenDataStreams.install(LGDXWebGdxData::openUtf8Reader, LGDXWebGdxData::openBinary);
    }

    private static Reader openUtf8Reader(String path) throws IOException {
        return new InputStreamReader(openBinary(path), "UTF-8");
    }

    private static InputStream openBinary(String path) throws IOException {
        FileHandle fh = Gdx.files.internal(toInternalPath(path));
        if (!fh.exists()) {
            throw new IOException("Asset not found: " + path + " -> " + fh.path());
        }
        try {
            // GWT preloader streams can report wrong lengths via skip(); read fully.
            return new ByteArrayInputStream(fh.readBytes());
        } catch (GdxRuntimeException e) {
            throw new IOException("Failed to read asset: " + path + " -> " + fh.path(), e);
        }
    }

    static String toInternalPath(String path) {
        if (path == null || path.isEmpty()) {
            return "";
        }
        String p = path.replace('\\', '/');
        while (p.startsWith("./")) {
            p = p.substring(2);
        }
        if (p.startsWith("/")) {
            p = p.substring(1);
        }
        return p;
    }
}
