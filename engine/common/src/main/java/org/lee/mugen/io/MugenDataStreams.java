package org.lee.mugen.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * Opens game data as UTF-8 text. Default uses the host filesystem; LibGDX Web installs a Gdx-based
 * provider before {@code MugenSystem} loads so the browser can read preloaded assets.
 */
public final class MugenDataStreams {

    public interface Utf8ReaderOpener {
        Reader openUtf8Reader(String path) throws IOException;
    }

    public interface BinaryOpener {
        InputStream openBinary(String path) throws IOException;
    }

    private static Utf8ReaderOpener utf8ReaderOpener = MugenDataStreams::defaultUtf8Reader;
    private static BinaryOpener binaryOpener = MugenDataStreams::defaultBinary;

    private MugenDataStreams() {
    }

    public static void install(Utf8ReaderOpener utf8, BinaryOpener binary) {
        utf8ReaderOpener = utf8 != null ? utf8 : MugenDataStreams::defaultUtf8Reader;
        binaryOpener = binary != null ? binary : MugenDataStreams::defaultBinary;
    }

    public static void resetDefaults() {
        utf8ReaderOpener = MugenDataStreams::defaultUtf8Reader;
        binaryOpener = MugenDataStreams::defaultBinary;
    }

    public static Reader openUtf8Reader(String path) throws IOException {
        return utf8ReaderOpener.openUtf8Reader(path);
    }

    public static InputStream openBinary(String path) throws IOException {
        return binaryOpener.openBinary(path);
    }

    private static Reader defaultUtf8Reader(String path) throws IOException {
        return new InputStreamReader(defaultBinary(path), StandardCharsets.UTF_8);
    }

    private static InputStream defaultBinary(String path) throws IOException {
        return new java.io.FileInputStream(path);
    }
}
