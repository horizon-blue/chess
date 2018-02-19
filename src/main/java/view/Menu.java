package view;

import javax.swing.*;

/**
 * A menu for game control options
 */
public class Menu extends JMenuBar {
    private JMenu gameMenu = new JMenu("Game");
    private JMenuItem newGame = new JMenuItem("New Game");


    private JMenu progressMenu = new JMenu("Progress");
    private JMenuItem undo = new JMenuItem("Undo");
    private JMenuItem redo = new JMenuItem("Redo");


    /**
     * Creates a new menu for chess game
     */
    Menu() {
        add(gameMenu);
        gameMenu.add(newGame);

        add(progressMenu);
        progressMenu.add(undo);
        progressMenu.add(redo);
    }
}
