package model;

import controller.Game;
import model.piece.Pawn;
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
        board.movePiece(to.row, to.col, from.row, from.col, false);
        Piece piece = board.getPiece(from);
        // reset pawn moving status
        if (piece instanceof Pawn) {
            --((Pawn) piece).moveCount;
        }
        if (target != null)
            board.addPiece(target, to);
        // reset current round to the same color as the piece
        if (board.game != null) {
            board.game.setRound(piece.isWhite());
        }
    }

    /**
     * Check whether two histories are equals.
     *
     * @param obj the other history to compare
     * @return true if they have same source and target
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof History))
            return false;
        if (obj == this)
            return true;
        History other = (History) obj;
        return other.from.equals(from) && other.to.equals(to);
    }

}
