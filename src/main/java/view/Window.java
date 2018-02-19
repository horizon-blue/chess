package view;

import model.Board;
import model.Player;

import javax.swing.*;
import java.awt.*;

/**
 * The viewer component for the main window
 */
public class Window extends JFrame {
    public BoardView board;
    public StatusBar statusBar;
    /**
     * The main frame for display
     */
    public Window(Board board, Player whitePlayer, Player blackPlayer) {
        super("Chess");
        setSize(95 * board.WIDTH, 75 * board.HEIGHT + 30);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        // center the screen
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        statusBar = new StatusBar(whitePlayer, blackPlayer);
        add(this.statusBar, BorderLayout.NORTH);

        this.board = new BoardView(board);
        add(this.board);
    }

}
