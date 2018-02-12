package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A helper class to create JLabel with Image
 */
public class ImageLabel extends JLabel {
    /**
     * Creates the JLabel with Image
     *
     * @param image  the image file
     * @param width  width of icon
     * @param height height of icon
     */
    public ImageLabel(ImageIcon image, int width, int height) {
        setIcon(new ImageIcon(getScaledImage(image.getImage(), width, height)));

        // general setting for ImageLabel display
        setPreferredSize(new Dimension(width, height));
        setSize(getPreferredSize());
        setLayout(null);
        setOpaque(false);
    }

    /**
     * Resize an image using a Graphics2D object backed by a BufferedImage.
     * Source: https://stackoverflow.com/questions/11687527/how-to-set-transparent-png-to-jbutton
     *
     * @param srcImg source image to scale
     * @param w      desired width
     * @param h      desired height
     * @return the new resized image
     */
    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    /**
     * Convert board coordinates to actual pixel position
     *
     * @param row       row index on the board
     * @param col       column index on the board
     * @param offsetRow row pixel offset
     * @param offsetCol column pixel offset
     */
    protected void updateLocation(int row, int col, int offsetRow, int offsetCol) {
        // these number are used to aligned the pixels of the image
        setLocation(offsetRow + 55 * row + 37 * col, 200 + 38 * col - 14 * row + offsetCol);
    }
}
