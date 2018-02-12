package view;

import javax.swing.*;
import java.awt.*;

/**
 * The entire displaying window
 */
public class Window implements Runnable {
    /**
     * The main frame to display everything
     */
    JFrame window;

    @Override
    public void run() {
        window = new JFrame("Chess");
        window.setSize(1024, 512);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.getContentPane().add(new Board(new model.Board()));
    }
}
