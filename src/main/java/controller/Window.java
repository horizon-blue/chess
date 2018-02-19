package controller;

import model.Board;
import model.Player;
import model.piece.Piece;
import view.GameView;
import view.InitialWindow;

import java.util.Set;

/**
 * The entire displaying game
 */
public class Window implements Runnable {
    /**
     * The main frame to display everything
     */
    GameView game;

    /**
     * Game related information
     */
    public Board board;
    public boolean isWhiteRound = true;
    public Player whitePlayer;
    public Player blackPlayer;

    /**
     * Codes that are used internally for GUI testing
     */
    @Override
    public void run() {
        initGame();
    }

    /**
     * gather important information before game
     */
    private void initGame() {
        InitialWindow initForm = new InitialWindow();
        initForm.onSubmit(e -> {
            whitePlayer = new Player(initForm.getWhitePlayerName(), true);
            blackPlayer = new Player(initForm.getBlackPlayerName(), false);
            board = new Board(initForm.getBoardWidth(), initForm.getBoardHeight());
            initForm.dispose();
            board.init(whitePlayer, blackPlayer);
            game = new GameView(board, whitePlayer, blackPlayer);
            startGame();
        });

    }

    private void startGame() {

        getPieceSelection();
    }

    /**
     * Get a piece selection of current round
     *
     * @return a piece of color for current round
     */
    public Piece getPieceSelection() {
        Set<Piece> potentialPieces = isWhiteRound ? board.whitePieces : board.blackPieces;
        Set<Piece> otherPieces = isWhiteRound ? board.blackPieces : board.whitePieces;
        game.board.toggleSpace(false);
        game.board.togglePieces(otherPieces, false);

        Piece selected;
        for (Piece piece : potentialPieces) {
        }
        return null;
    }


}
