package controller;

import model.Board;
import model.Player;
import model.Position;
import model.piece.Piece;
import view.GameView;
import view.InitialWindow;
import view.PieceView;
import view.SpaceView;

import java.util.HashSet;
import java.util.Set;

/**
 * The entire displaying game
 */
public class Game implements Runnable {
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
    boolean hasSpecialPieces = true;

    /**
     * run a new game
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
            board.game = this;
            hasSpecialPieces = initForm.hasSpecialPieces();
            initForm.dispose();
            board.init(whitePlayer, blackPlayer, hasSpecialPieces);
            game = new GameView(board, whitePlayer, blackPlayer);
            startGame();
        });

    }

    public Status status;
    Piece selected;
    Set<Position> movements = new HashSet<>();


    private void startGame() {
        status = Status.BEFORE_SELECT;
        // set pieces movement rules
        game.board.onPressPieces(e -> {
            switch (status) {
                case BEFORE_SELECT:
                case AFTER_SELECT:
                    game.statusBar.clearStatus();
                    game.board.removeHighlightPositions();
                    Piece piece = ((PieceView) e.getSource()).piece;
                    if (piece.isWhite() == isWhiteRound) {
                        selected = piece;
                        movements = piece.getAvailablePosition(isWhiteRound);
                        if (movements.isEmpty())
                            game.statusBar.setStatus("No available movement");
                        else {
                            status = Status.AFTER_SELECT;
                            game.board.highlightPositions(movements);
                        }
                        break;
                    } else {
                        Position selectedPos = new Position(piece.x, piece.y);
                        if (status == Status.AFTER_SELECT &&
                                movements.contains(selectedPos)) {
                            moveTo(selectedPos);
                            break;
                        }
                    }
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
                        moveTo(tile.position);
                        break;
                    }
                    game.statusBar.setStatus("Invalid selection");

            }
        });
        // menu items for game control
        game.menu.onPressUndo(e -> {
            board.undo();
        });
        game.menu.onPressRedo(e -> {
            board.redo();
        });
        game.menu.onPressNewGame(e -> {
            restart();
        });

    }

    /**
     * A private helper to move the selected piece to target position
     *
     * @param to the position to move to
     */
    private void moveTo(Position to) {
        game.board.removeHighlightPositions();
        board.movePiece(selected, to);

        if (!isGameEnd()) {
            // go over to next round
            isWhiteRound = !isWhiteRound;
            game.statusBar.switchRound();
            status = Status.BEFORE_SELECT;
        }
    }

    /**
     * A helper function to check whether current game ends
     *
     * @return true if the game ends, false otherwise
     */
    private boolean isGameEnd() {
        Player otherPlayer = isWhiteRound ? blackPlayer : whitePlayer;
        Player currentPlayer = isWhiteRound ? whitePlayer : blackPlayer;

        if (board.isCheckOrStaleMated(otherPlayer)) {
            if (board.isChecked(otherPlayer)) { // is checkmate
                game.statusBar.setStatus(currentPlayer + " checkmate.");
                status = isWhiteRound ? Status.WHITE_WIN : Status.BLACK_WIN;
                ++currentPlayer.score;
            } else {
                status = Status.DRAW;
                ++currentPlayer.score;
                ++otherPlayer.score;
                // update score
                game.statusBar.setStatus("Stalemate.");
            }
            game.statusBar.updateScore();
            return true;
        }
        return false;
    }

    /**
     * Helper function to set the game's round
     *
     * @param isWhiteRound true if current round is white, false otherwise
     */
    public void setRound(boolean isWhiteRound) {
        this.isWhiteRound = isWhiteRound;
        game.statusBar.setRound(isWhiteRound);
        status = Status.BEFORE_SELECT;
    }

    /**
     * Restart a new game, players with black and white pieces are
     * switched
     */
    public void restart() {
        Player prevBlackPlayer = blackPlayer;
        blackPlayer = whitePlayer;
        whitePlayer = prevBlackPlayer;
        blackPlayer.isWhite = false;
        whitePlayer.isWhite = true;

        isWhiteRound = true;

        // re-creates a new board
        board = new Board(board.HEIGHT, board.WIDTH);
        board.init(whitePlayer, blackPlayer, hasSpecialPieces);

        // re-draw GUI
        game.redrawStatusBar(whitePlayer, blackPlayer);
        game.redrawBoard(board);
        startGame();

    }


    public enum Status {
        BEFORE_SELECT,
        AFTER_SELECT,
        BLACK_WIN,
        WHITE_WIN,
        DRAW,
    }

}
