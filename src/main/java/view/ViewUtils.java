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
    static Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }
}
