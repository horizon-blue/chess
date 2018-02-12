package view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
     * Load the image resource to the current ImagePanel
     *
     * @param imgPath the path of image to load
     * @throws IOException throws exception if image does not exist
     */
    public ImagePanel(String imgPath) throws IOException {
        img = new ImageIcon(imgPath).getImage();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw the background image
        g.drawImage(img, 0, 0, this);
    }
}
