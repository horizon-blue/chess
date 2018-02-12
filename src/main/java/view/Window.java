package view;

import javax.swing.*;
import java.awt.*;

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
    }
}
