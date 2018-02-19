package view;

import javax.swing.*;

/**
 * The viewer component for the main window
 */
public class Window extends JFrame {
    /**
     * The main frame for display
     */
    public Window() {
        super("Chess");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        // center the screen
        setLocationRelativeTo(null);
    }
}
