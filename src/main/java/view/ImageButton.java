package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A helper class to create JButton with Image
 */
public class ImageButton extends JButton {
    private final BufferedImage ICON;
    private final int WIDTH;
    private final int HEIGHT;

    /**
     * Creates the JButton with Image
     *
     * @param image  the image file
     * @param width  width of icon
     * @param height height of icon
     */
    public ImageButton(Image image, int width, int height) {
        // initialize final variables
        ICON = ViewUtils.getScaledImage(image, width, height);
        WIDTH = width;
        HEIGHT = height;

        setIcon(new ImageIcon(ICON));

        // general setting for ImageButton display
        setPreferredSize(new Dimension(width, height));
        setSize(getPreferredSize());
        setLayout(null);
        // remove the default JButton style
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    public ImageButton(Image image, Image rolloverImage, int width, int height) {
        this(image, width, height);
        setRolloverIcon(new ImageIcon(ViewUtils.getScaledImage(rolloverImage, width, height)));
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

    /**
     * Only set the hover status when the mouse is over non-transparent pixels
     *
     * @param x the x-index of the current mouse location
     * @param y the y-index of the current mouse location
     * @return true if the button contains mouse, false otherwise
     */
    @Override
    public boolean contains(int x, int y) {
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT)
            return false;
        return !ViewUtils.isTransparent(ICON, x, y);
    }
}