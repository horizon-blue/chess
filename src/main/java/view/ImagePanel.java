package view;

import javax.swing.*;
import java.awt.*;

/**
 * A helper class to display images using JPanel
 * Idea taken from: https://stackoverflow.com/questions/1466240/
 * how-to-set-an-image-as-a-background-for-frame-in-swing-gui-of-java
 */
public class ImagePanel extends JPanel {
    /**
     * Background image for current panel
     */
    private Image img;

    /**
     * Set the background image of current panel to the specified image
     *
     * @param image the background image to be set to current panel
     */
    public ImagePanel(Image image, int width, int height) {
        this.img = ViewUtils.getScaledImage(image, width, height);
        setPreferredSize(new Dimension(width, height));
        setSize(getPreferredSize());
        setLayout(null);
        setOpaque(false);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw the background image
        g.drawImage(img, 0, 0, this);
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
