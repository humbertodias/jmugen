package org.lee.mugen.sprite.common.resource;

import org.lee.mugen.imageIO.RawPCXImage;

/** GWT: font file header without {@code BufferedImage}. */
class FntRaw {
    char[] signature = new char[12];
    short verhi;
    short verlo;
    long offsetPcx;
    long pcxLength;
    long offsetText;
    long textLength;
    char[] comment = new char[40];
    RawPCXImage pcx;
}
