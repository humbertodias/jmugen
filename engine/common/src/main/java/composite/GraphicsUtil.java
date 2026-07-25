/*
 * Copyright (c) 2006 Romain Guy <romain.guy@mac.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package composite;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GraphicsUtil {
    private static GraphicsConfiguration configuration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

    private GraphicsUtil() {
    }

    public static BufferedImage createCompatibleImage(int width, int height) {
        return configuration.createCompatibleImage(width, height);
    }

    public static BufferedImage createTranslucentCompatibleImage(int width, int height) {
        return configuration.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
    }

    public static BufferedImage loadCompatibleImage(URL resource) throws IOException {
        BufferedImage image = ImageIO.read(resource);
        return toCompatibleImage(image);
    }

    public static BufferedImage toCompatibleImage(BufferedImage image) {
        BufferedImage compatibleImage = configuration.createCompatibleImage(image.getWidth(),
                image.getHeight(), Transparency.TRANSLUCENT);
        Graphics g = compatibleImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return compatibleImage;
    }

    public static BufferedImage createThumbnail(BufferedImage image, int requestedThumbSize) {
        float ratio = (float) image.getWidth() / (float) image.getHeight();
        int width = image.getWidth();
        BufferedImage thumb = image;

        do {
            width /= 2;
            if (width < requestedThumbSize) {
                width = requestedThumbSize;
            }

            BufferedImage temp = new BufferedImage(width, (int) (width / ratio), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = temp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(thumb, 0, 0, temp.getWidth(), temp.getHeight(), null);
            g2.dispose();

            thumb = temp;
        } while (width != requestedThumbSize);

        return thumb;
    }

    public static BufferedImage createThumbnail(BufferedImage image, int newWidth, int newHeight) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage thumb = image;

        do {
            if (width > newWidth) {
                width /= 2;
                if (width < newWidth) {
                    width = newWidth;
                }
            }

            if (height > newHeight) {
                height /= 2;
                if (height < newHeight) {
                    height = newHeight;
                }
            }

            BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = temp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(thumb, 0, 0, temp.getWidth(), temp.getHeight(), null);
            g2.dispose();

            thumb = temp;
        } while (width != newWidth || height != newHeight);

        return thumb;
    }
}
