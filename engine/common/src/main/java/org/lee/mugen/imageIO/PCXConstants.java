package org.lee.mugen.imageIO;

/**
 * PCX layout constants (no AWT) so Android and other runtimes can avoid loading {@link PCXLoader}.
 */
public final class PCXConstants {

	public static final int HEADER_SIZE = 128;

	private PCXConstants() {
	}
}
