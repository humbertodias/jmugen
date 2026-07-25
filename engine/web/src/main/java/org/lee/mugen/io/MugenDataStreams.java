package org.lee.mugen.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * GWT: no {@link java.io.FileInputStream}; host must {@link #install} openers before use.
 */
public final class MugenDataStreams {

    public interface Utf8ReaderOpener {
        Reader openUtf8Reader(String path) throws IOException;
    }

    public interface BinaryOpener {
        InputStream openBinary(String path) throws IOException;
    }

    private static Utf8ReaderOpener utf8ReaderOpener;
    private static BinaryOpener binaryOpener;

    private MugenDataStreams() {
    }

    public static void install(Utf8ReaderOpener utf8, BinaryOpener binary) {
        utf8ReaderOpener = utf8;
        binaryOpener = binary;
    }

    public static void resetDefaults() {
        utf8ReaderOpener = null;
        binaryOpener = null;
    }

    public static Reader openUtf8Reader(String path) throws IOException {
        if (utf8ReaderOpener == null) {
            throw new IOException("MugenDataStreams not installed (web/desktop must call install first)");
        }
        return utf8ReaderOpener.openUtf8Reader(path);
    }

    public static InputStream openBinary(String path) throws IOException {
        if (binaryOpener == null) {
            throw new IOException("MugenDataStreams not installed (web/desktop must call install first)");
        }
        return binaryOpener.openBinary(path);
    }
}
