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
     * @param img the background image to be set to current panel
     */
    public ImagePanel(Image img) {
        this.img = img;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw the background image
        g.drawImage(img, 0, 0, this);
    }
}
