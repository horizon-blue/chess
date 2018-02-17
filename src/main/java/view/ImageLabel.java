package view;

import javax.swing.*;
import java.awt.*;

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
    public ImageLabel(Image image, int width, int height) {
        setIcon(new ImageIcon(ViewUtils.getScaledImage(image, width, height)));

        // general setting for ImageLabel display
        setPreferredSize(new Dimension(width, height));
        setSize(getPreferredSize());
        setLayout(null);
        setOpaque(false);
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
