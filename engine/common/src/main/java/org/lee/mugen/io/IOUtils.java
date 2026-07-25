package org.lee.mugen.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {
	private static final int DEFAULT_BUFFER_SIZE = 2048;
	/** Guard for GWT/JS {@code new byte[n]} when {@code n} is negative or absurd. */
	public static final int MAX_BINARY_CHUNK = 64 * 1024 * 1024;

	public static int copy(InputStream input, OutputStream output)
			throws IOException {
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int count = 0;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}

	public static byte[] toByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		copy(input, out);
		return out.toByteArray();
	}

	public static void checkChunkSize(int size, int fileLength, String what) throws IOException {
		if (size < 0 || size > fileLength || size > MAX_BINARY_CHUNK) {
			throw new IOException("Invalid " + what + " length " + size + " (file " + fileLength + " bytes)");
		}
	}

	public static void checkRange(int offset, int size, int fileLength, String what) throws IOException {
		if (offset < 0 || size < 0 || offset > fileLength || size > fileLength - offset) {
			throw new IOException("Invalid " + what + " range offset=" + offset + " size=" + size + " file=" + fileLength);
		}
		checkChunkSize(size, fileLength, what);
	}
}
