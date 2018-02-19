package view;

import javax.swing.*;
import java.awt.*;

/**
 * A helper class to create JButton with Image
 */
public class ImageButton extends JButton {
    /**
     * Creates the JButton with Image
     *
     * @param image  the image file
     * @param width  width of icon
     * @param height height of icon
     */
    public ImageButton(Image image, int width, int height) {
        setIcon(new ImageIcon(ViewUtils.getScaledImage(image, width, height)));

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
}
