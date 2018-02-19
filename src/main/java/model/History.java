package model;

import controller.Game;
import model.piece.Piece;

/**
 * History - used to record the previous movements made
 * by the players
 */
public class History {
    final Board board;
    final Position from;
    final Position to;
    final Piece target;

    /**
     * Creating a new history for current movement. Equivalent to
     * History(board, from, to, piece, null)
     *
     * @param board the board where the history happened
     * @param from the location where the piece came from
     * @param to   the location where the piece go to
     */
    public History(Board board, Position from, Position to) {
        this(board, from, to, null);
    }

    /**
     * Creating a new history for current movement.
     *
     * @param board   the board where the history happened
     * @param from   the location where the piece came from
     * @param to     the location where the piece go to
     * @param target optional - the piece being captured
     */
    public History(Board board, Position from, Position to, Piece target) {
        this.board = board;
        this.from = from;
        this.to = to;
        this.target = target;
    }

    /**
     * Undo the current history
     */
    public void undo() {
        board.movePieceHelper(to.row, to.col, from.row, from.col);
        if (target != null)
            board.addPiece(target, to);
        // reset current round to the same color as the piece
        if (board.game != null) {
            board.game.isWhiteRound = board.getPiece(from).isWhite();
            board.game.status = Game.Status.BEFORE_SELECT;
        }
    }

}
