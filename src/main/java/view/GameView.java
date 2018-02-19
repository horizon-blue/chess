package view;

import model.Board;
import model.Player;
import model.piece.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

/**
 * The viewer component for the main window
 */
public class GameView extends JFrame {
    public BoardView board;
    public StatusBar statusBar;
    /**
     * The main frame for display
     */
    public GameView(Board board, Player whitePlayer, Player blackPlayer) {
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
