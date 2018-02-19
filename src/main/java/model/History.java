package model;

import controller.Game;
import model.piece.Piece;

/**
 * History - used to record the previous movements made
 * by the players
 */
public class History {
    final Game game;
    final Position from;
    final Position to;
    final Piece target;

    /**
     * Creating a new history for current movement. Equivalent to
     * History(board, from, to, piece, null)
     *
     * @param game the game where the history happened
     * @param from the location where the piece came from
     * @param to   the location where the piece go to
     */
    public History(Game game, Position from, Position to) {
        this(game, from, to, null);
    }

    /**
     * Creating a new history for current movement.
     *
     * @param game   the game where the history happened
     * @param from   the location where the piece came from
     * @param to     the location where the piece go to
     * @param target optional - the piece being captured
     */
    public History(Game game, Position from, Position to, Piece target) {
        this.game = game;
        this.from = from;
        this.to = to;
        this.target = target;
    }

    /**
     * Undo the current history
     */
    public void undo() {
        game.board.movePiece(to, from);
        if (target != null)
            game.board.addPiece(target, to);
        // reset current round to the same color as the piece
        game.isWhiteRound = game.board.getPiece(from).isWhite();
    }

}
