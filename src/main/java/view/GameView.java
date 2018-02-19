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
    private JPanel mainPanel;
    /**
     * The main frame for display
     */
    public GameView(Board board, Player whitePlayer, Player blackPlayer) {
        super("Chess");
        setSize(95 * board.HEIGHT, 75 * board.WIDTH + 50);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        // center the screen
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        menu = new Menu();
        add(menu, BorderLayout.NORTH);

        mainPanel = new JPanel(new BorderLayout());

        statusBar = new StatusBar(whitePlayer, blackPlayer);
        mainPanel.add(this.statusBar, BorderLayout.NORTH);

        this.board = new BoardView(board);
        board.view = this.board;
        mainPanel.add(this.board);

        add(mainPanel);
    }

    /**
     * Redraw a new board
     *
     * @param board the board to draw
     */
    public void redrawBoard(Board board) {
        mainPanel.remove(this.board);
        this.board = new BoardView(board);
        board.view = this.board;
        mainPanel.add(this.board);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    /**
     * Redraw a new status bar
     *
     * @param whitePlayer the white player to be draw
     * @param blackPlayer the black player to be draw
     */
    public void redrawStatusBar(Player whitePlayer, Player blackPlayer) {
        mainPanel.remove(this.statusBar);
        statusBar = new StatusBar(whitePlayer, blackPlayer);
        mainPanel.add(this.statusBar, BorderLayout.NORTH);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

}
