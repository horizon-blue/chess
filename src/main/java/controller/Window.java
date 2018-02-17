package controller;

import model.*;
import view.BoardView;

/**
 * The entire displaying window
 */
public class Window implements Runnable {
    /**
     * The main frame to display everything
     */
    view.Window window;

    /**
     * Codes that are used internally for GUI testing
     */
    @Override
    public void run() {
        window = new view.Window();
        Player whitePlayer = new Player();
        Player blackPlayer = new Player();
        Game game = new Game(whitePlayer, blackPlayer);
        game.initBoard();
        // check whether the board displayed correctly
        window.add(new BoardView(game.board));
    }
}
