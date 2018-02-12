package view;

import javax.swing.*;

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
        window.setSize(650, 400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        
    }
}
