package controller;

import model.Board;
import model.History;
import model.Player;
import model.Position;
import model.piece.Piece;
import view.GameView;
import view.InitialWindow;
import view.PieceView;
import view.SpaceView;

import java.util.ArrayList;
import java.util.List;
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
            startGame();
        });

    }

    private Status status;
    Piece selected;
    Set<Position> movements;
    List<History> history = new ArrayList<>();

    private void startGame() {
        board.init(whitePlayer, blackPlayer);
        game = new GameView(board, whitePlayer, blackPlayer);
        status = Status.BEFORE_SELECT;
        // set pieces movement rules
        game.board.onPressPieces(e -> {
            game.statusBar.clearStatus();
            Piece piece = ((PieceView) e.getSource()).piece;
            switch (status) {
                case BEFORE_SELECT:
                case AFTER_SELECT:
                    if (piece.isWhite() == isWhiteRound) {
                        selected = piece;
                        movements = piece.getAvailablePosition(isWhiteRound);
                        if (movements.isEmpty())
                            game.statusBar.setStatus("No available movement");
                        else
                            status = Status.AFTER_SELECT;
                        break;
                    }
                default:
                    game.statusBar.setStatus("Invalid selection");
            }
        });
        // set tile movement rules
        game.board.onPressTiles(e -> {
            game.statusBar.clearStatus();
            SpaceView tile = (SpaceView) e.getSource();
            switch (status) {
                case AFTER_SELECT:
                    if (movements.contains(tile.position)) {
                        board.movePiece(selected, tile.position);
                        selected.view.updateLocation();
                    }

            }
        });

    }


    public enum Status {
        BEFORE_SELECT,
        AFTER_SELECT,
        CHECKMATE,
        BLACK_WIN,
        WHITE_WIN,
        DRAW,
    }

}
