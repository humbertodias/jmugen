package org.lee.mugen.imageIO;


public class ImageUtils {
    public static int getRGB(int r, int g, int b) {
        return getARGB(255, r, g, b);
    }
    public static int[] getRGBArray(int argb) {
    	int[] result = new int[4];

    	int a = (argb >> 32) & 0xff;
		int r = (argb >> 16) & 0xff;
		int g = (argb >> 8) & 0xff;
		int b = argb & 0xff;
    	result[0] = a;
    	result[1] = r;
    	result[2] = g;
    	result[3] = b;

    	return result;
    }

    public static int getARGB(int a, int r, int g, int b) {
        return (a << 24) | (r << 16) | (g << 8) | b;
    }
}
