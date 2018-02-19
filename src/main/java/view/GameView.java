package view;

import model.Board;
import model.Player;

import javax.swing.*;
import java.awt.*;

/**
 * The viewer component for the main window
 */
public class GameView extends JFrame {
    public BoardView board;
    public StatusBar statusBar;
    public Menu menu;
    /**
     * The main frame for display
     */
    public GameView(Board board, Player whitePlayer, Player blackPlayer) {
        super("Chess");
        setSize(95 * board.WIDTH, 75 * board.HEIGHT + 50);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        // center the screen
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        menu = new Menu();
        add(menu, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());

        statusBar = new StatusBar(whitePlayer, blackPlayer);
        mainPanel.add(this.statusBar, BorderLayout.NORTH);

        this.board = new BoardView(board);
        board.view = this.board;
        mainPanel.add(this.board);

        add(mainPanel);
    }

}
