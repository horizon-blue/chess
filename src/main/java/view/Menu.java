package view;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * A menu for game control options
 */
public class Menu extends JMenuBar {
    private JMenu gameMenu = new JMenu("Game");
    private JMenuItem newGame = new JMenuItem("New Game");


    private JMenu progressMenu = new JMenu("Progress");
    private JMenuItem undo = new JMenuItem("Undo");
    private JMenuItem redo = new JMenuItem("Redo");

    private JMenuItem forfeit = new JMenuItem("Forfeit");


    /**
     * Creates a new menu for chess game
     */
    Menu() {
        add(gameMenu);
        gameMenu.add(newGame);

        add(progressMenu);
        progressMenu.add(undo);
        progressMenu.add(redo);
        progressMenu.addSeparator();
        progressMenu.add(forfeit);

    }

    /**
     * Listener to trigger when pressing undo button
     *
     * @param e the listener to trigger
     */
    public void onPressUndo(ActionListener e) {
        undo.addActionListener(e);
    }

    /**
     * Listener to trigger when pressing redo button
     *
     * @param e the listener to trigger
     */
    public void onPressRedo(ActionListener e) {
        redo.addActionListener(e);
    }

    /**
     * Listener to trigger when pressing new game button
     *
     * @param e the listener to trigger
     */
    public void onPressNewGame(ActionListener e) {
        newGame.addActionListener(e);
    }

    /**
     * Listener to trigger when pressing forfeit button
     *
     * @param e the listener to trigger
     */
    public void onPressForfeit(ActionListener e) {
        forfeit.addActionListener(e);
    }
}
