package org.lee.mugen.renderer.lwjgl;

import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferDouble;
import java.awt.image.DataBufferFloat;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferShort;
import java.awt.image.DataBufferUShort;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import org.lee.mugen.util.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.util.glu.GLU;

/**
 * A utility class to load textures for JOGL. This source is based on a texture
 * that can be found in the Java Gaming (www.javagaming.org) Wiki. It has been
 * simplified slightly for explicit 2D graphics use.
 * 
 * OpenGL uses a particular image format. Since the images that are loaded from
 * disk may not match this format this loader introduces a intermediate image
 * which the source image is copied into. In turn, this image is used as source
 * for the OpenGL texture.
 * 
 * @author Kevin Glass
 * @author Brian Matzon
 */
public class TextureLoader {
	static final TextureLoader instance = new TextureLoader();

	public static TextureLoader getTextureLoader() {
		return instance;
	}

	/**
	 * Create a new texture loader based on the game panel
	 * 
	 * @param gl
	 *            The GL content in which the textures should be loaded
	 */
	private TextureLoader() {
	}

	/**
	 * Create a new texture ID
	 * 
	 * @return A new texture ID
	 */
	private int createTextureID() {
		IntBuffer tmp = createIntBuffer(1);
		GL11.glGenTextures(tmp);
		return tmp.get(0);
	}

	/**
	 * Load a texture
	 * 
	 * @param resourceName
	 *            The location of the resource to load
	 * @return The loaded texture
	 * @throws IOException
	 *             Indicates a failure to access the resource
	 */
	public Texture getTexture(BufferedImage img) throws IOException {
		Buffer buffer = convertImageData(img);
		Texture tex = getTexture(buffer, img.getWidth(), img.getHeight());
		return tex;
	}

	public Texture getTexture(Buffer img, int width, int height)
			throws IOException {
		Texture tex = getTexture(img, width, height, GL12.GL_BGRA,
				GL11.GL_TEXTURE_2D, // target
				GL11.GL_RGBA, // dst pixel format
				GL11.GL_NEAREST, // min filter (unused)
				GL11.GL_NEAREST);
		return tex;
	}

	/**
	 * Load a texture into OpenGL from a image reference on disk.
	 * 
	 * @param resourceName
	 *            The location of the resource to load
	 * @param target
	 *            The GL target to load the texture against
	 * @param dstPixelFormat
	 *            The pixel format of the screen
	 * @param minFilter
	 *            The minimising filter
	 * @param magFilter
	 *            The magnification filter
	 * @return The loaded texture
	 * @throws IOException
	 *             Indicates a failure to access the resource
	 */
	public Texture getTexture(Buffer data, int width, int height,
			int srcPixelFormat, int target, int dstPixelFormat, int minFilter,
			int magFilter) throws IOException {
		// create the texture ID for this texture
		int textureID = createTextureID();
		Texture texture = new Texture(target, textureID);

		// bind this texture
		GL11.glBindTexture(target, textureID);

		int texWidth = get2Fold(width);
		int texHeight = get2Fold(height);
		texture.setTextureWidth(width);
		texture.setTexHeight(height);

		data.rewind();

		texture.setWidth(width);
		texture.setHeight(height);

		// Logger.log("End Transform to ByteBuffer");
		if (target == GL11.GL_TEXTURE_2D) {
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_MIN_FILTER, minFilter);
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_MAG_FILTER, magFilter);
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_WRAP_S,
					GL12.GL_CLAMP_TO_EDGE);
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_WRAP_T,
					GL12.GL_CLAMP_TO_EDGE);
		}
		
		GL11.glTexImage2D(target, 0, dstPixelFormat, width, height, 0,
				srcPixelFormat, GL11.GL_UNSIGNED_BYTE, (IntBuffer)data);
		// Logger.log("end glTexImage2D to ByteBuffer");
		return texture;
	}

	/**
	 * Get the closest greater power of 2 to the fold number
	 * 
	 * @param fold
	 *            The target number
	 * @return The power of 2
	 */
	private int get2Fold(int fold) {
		int ret = 2;
		while (ret < fold) {
			ret *= 2;
		}
		return ret;
	}

	private static Buffer wrapImageDataBuffer(DataBuffer data) {
		if (data instanceof DataBufferByte) {
			return ByteBuffer.wrap(((DataBufferByte) data).getData());
		} else if (data instanceof DataBufferDouble) {
			throw new RuntimeException(
					"DataBufferDouble rasters not supported by OpenGL");
		} else if (data instanceof DataBufferFloat) {
			return FloatBuffer.wrap(((DataBufferFloat) data).getData());
		} else if (data instanceof DataBufferInt) {
			return IntBuffer.wrap(((DataBufferInt) data).getData());
		} else if (data instanceof DataBufferShort) {
			return ShortBuffer.wrap(((DataBufferShort) data).getData());
		} else if (data instanceof DataBufferUShort) {
			return ShortBuffer.wrap(((DataBufferUShort) data).getData());
		} else {
			throw new RuntimeException("Unexpected DataBuffer type?");
		}
	}

	/**
	 * Convert the buffered image to a texture
	 * 
	 * @param bufferedImage
	 *            The image to convert to a texture
	 * @param texture
	 *            The texture to store the data into
	 * @return A buffer containing the data
	 */
	public Buffer convertImageData(BufferedImage bufferedImage) {
		BufferedImage texImage;

		if (bufferedImage.getType() == BufferedImage.TYPE_INT_ARGB) {
			return wrapImageDataBuffer(bufferedImage.getRaster()
					.getDataBuffer());
		} else {
			texImage = new BufferedImage(bufferedImage.getWidth(),
					bufferedImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics g = texImage.getGraphics();
			g.drawImage(bufferedImage, 0, 0, null);
			return wrapImageDataBuffer(texImage.getRaster().getDataBuffer());
		}
	}

	/**
	 * Creates an integer buffer to hold specified ints - strictly a utility
	 * method
	 * 
	 * @param size
	 *            how many int to contain
	 * @return created IntBuffer
	 */
	protected IntBuffer createIntBuffer(int size) {
		ByteBuffer temp = ByteBuffer.allocateDirect(4 * size);
		temp.order(ByteOrder.nativeOrder());

		return temp.asIntBuffer();
	}

	public Texture createTexture(final int width, final int height)
			throws IOException {
		int textWidth = get2Fold(width);
		int texHeight = get2Fold(height);
		ByteBuffer buffer = BufferUtils.createByteBuffer(textWidth * texHeight
				* 4);
		return getTexture(32, width, height, textWidth, texHeight, buffer,
				GL11.GL_NEAREST, GL11.GL_RGBA);
	}

	public Texture getTexture(int detpth, int width, int height, int texWidth,
			int texHeight, ByteBuffer byteBuffer, int filter, int dstPixelFormat)
			throws IOException {
		int target = GL11.GL_TEXTURE_2D;

		// create the texture ID for this texture
		int textureID = createTextureID();
		Texture texture = new Texture(target, textureID);

		int minFilter = filter;
		int magFilter = filter;
		boolean flipped = false;

		// bind this texture
		GL11.glBindTexture(target, textureID);

		ByteBuffer textureBuffer;

		boolean hasAlpha;
		textureBuffer = byteBuffer;

		hasAlpha = detpth == 32;

		texture.setTextureWidth(texWidth);
		texture.setTextureHeight(texHeight);

		int srcPixelFormat = hasAlpha ? GL11.GL_RGBA : GL11.GL_RGB;
		int componentCount = hasAlpha ? 4 : 3;

		texture.setWidth(width);
		texture.setHeight(height);
		// texture.setAlpha(hasAlpha);

		IntBuffer temp = BufferUtils.createIntBuffer(16);
		GL11.glGetInteger(GL11.GL_MAX_TEXTURE_SIZE, temp);
		int max = temp.get(0);
		if ((texWidth > max) || (texHeight > max)) {
			throw new IOException(
					"Attempt to allocate a texture to big for the current hardware");
		}

		GL11.glTexParameteri(target, GL11.GL_TEXTURE_MIN_FILTER, minFilter);
		GL11.glTexParameteri(target, GL11.GL_TEXTURE_MAG_FILTER, magFilter);

		if (minFilter == GL11.GL_LINEAR_MIPMAP_NEAREST) {
			// generate a mip map textur
			GLU.gluBuild2DMipmaps(GL11.GL_TEXTURE_2D, componentCount, texWidth,
					texHeight, srcPixelFormat, GL11.GL_UNSIGNED_BYTE,
					textureBuffer);
		} else {
			// produce a texture from the byte buffer
			GL11.glTexImage2D(target, 0, dstPixelFormat, get2Fold(width),
					get2Fold(height), 0, srcPixelFormat, GL11.GL_UNSIGNED_BYTE,
					textureBuffer);
		}

		return texture;
	}

	public void free(Texture texture) {
		Logger.log("Free Texture");
		IntBuffer scratch = BufferUtils.createIntBuffer(1);
		scratch.put(0, texture.getTextureID());
		GL11.glDeleteTextures(scratch);
	}
}
