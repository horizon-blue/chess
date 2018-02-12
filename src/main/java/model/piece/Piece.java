package model.piece;

import model.Board;
import model.Player;
import model.Position;

import java.util.Set;

/**
 * Piece - abstract superclass for a pieces
 */
public abstract class Piece {
    // attributes
    private final String whiteSymbol;
    private final String blackSymbol;

    public final Player owner;

    // relationship to the board
    public Board board;
    public int x;
    public int y;

    /**
     * generate a new piece for the owner
     * @param owner       the player who has this piece
     * @param whiteSymbol short notation for the piece when it is white
     * @param blackSymbol short notation for the piece when it is black
     */
    Piece(Player owner, String whiteSymbol, String blackSymbol) {
        if (owner == null)
            throw new IllegalArgumentException("Owner cannot be null");
        this.owner = owner;
        this.whiteSymbol = whiteSymbol;
        this.blackSymbol = blackSymbol;
    }

    /**
     * check whether the piece is white or not
     *
     * @return true if the piece is white piece, false otherwise
     */
    public boolean isWhite() {
        return owner.isWhite;
    }

    /**
     * for the given piece, return all positions that it can possibly move while
     * obeying the game rule
     *
     * @param isWhiteRound whether current round is the white round. The piece will
     *                     still check for possible movements even if current round is
     *                     different from the piece's color, but it will not check
     *                     whether the movement will caused the king to be in danger
     * @return a set of all positions that the current piece can move to
     */
    public abstract Set<Position> getAvailablePosition(boolean isWhiteRound);

    /**
     * check whether current piece has the same color as the other one
     *
     * @param other the other piece
     * @return true if this piece and other piece have same color,false otherwise
     */
    public boolean sameColor(Piece other) {
        return other.isWhite() == isWhite();
    }

    /**
     * update the piece's position to (row, col). This method does NOT check whether
     * the supplied position is valid
     *
     * @param row row position
     * @param col column position
     */
    public void setPos(int row, int col) {
        x = row;
        y = col;
    }

    /**
     * A helper function to check whether the piece is currently on board
     *
     * @return true if the piece belongs to a board and it is currently on board,
     * false otherwise
     */
    public boolean isOnBoard() {
        return board != null && board.isValid(x, y);
    }

    /**
     * A protected helper function that check whether the given position is valid, and if so, add
     * the position to positions.
     * This method WILL modify the positions parameter
     *
     * @param row       row position to check
     * @param col       column position to check
     * @param positions if (row, col) is valid, it will be added to positions
     * @param isWhiteRound whether current round is white piece - used to decide whether to verify king's
     *                     checked status
     * @return true if the given position is empty, false otherwise
     */
    protected boolean addValidPos(int row, int col, Set<Position> positions, boolean isWhiteRound) {
        boolean isSelfRound = isWhiteRound == this.isWhite();

        if (board.isValidMovement(this, row, col))
            if (!isSelfRound || !board.willBeChecked(this, row, col))
                positions.add(new Position(row, col));

        if (board.isOccupied(row, col))
            return false;
        else
            return true;
    }

    /**
     * Given a array of directions, add all valid positions to the set of positions
     *
     * @param directions   array of directions (direction[0] corresponds to row, and direction[1]
     *                     correspond to column)
     * @param positions    the data structure to hold returning positions
     * @param isWhiteRound whether current round is white piece - used to decide whether to verify king's
     *                     checked status
     * @return the set of all valid positions in the direction
     */
    protected Set<Position> getAllInDirections(int directions[][], Set<Position> positions, boolean isWhiteRound) {
        for (int direction[] : directions)
            for (int row = x + direction[0], col = y + direction[1];
                 board.isValid(row, col) && addValidPos(row, col, positions, isWhiteRound);
                 row += direction[0], col += direction[1])
                ;
        return positions;
    }

    /**
     * convert the piece to String (symbol) based on its color
     *
     * @return white symbol if piece is white, black otherwise
     */
    @Override
    public String toString() {
        return isWhite() ? whiteSymbol : blackSymbol;
    }
}
