package view;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface ViewUtils {
    /**
     * Resize an image using a Graphics2D object backed by a BufferedImage.
     * Source: https://stackoverflow.com/questions/11687527/how-to-set-transparent-png-to-jbutton
     *
     * @param srcImg source image to scale
     * @param w      desired width
     * @param h      desired height
     * @return the new resized image
     */
    static BufferedImage getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    /**
     * Check whether a pixel in buffer image is transparent
     * Source:https://stackoverflow.com/questions/8978228/java-bufferedimage-how-to-know-if-a-pixel-is-transparent
     *
     * @param img the image to check
     * @param x   the x index of the pixel
     * @param y   the y index of the pixel
     * @return true if the specific pixel is transparent, false otherwise
     */
    static boolean isTransparent(BufferedImage img, int x, int y) {
        int pixel = img.getRGB(x, y);
        return (pixel >> 24) == 0x00;
    }
}
