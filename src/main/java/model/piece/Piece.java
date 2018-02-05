package model.piece;

import model.Board;
import model.Player;
import model.Position;

import java.util.Set;

public abstract class Piece {
    // attributes
    public final String name;
    private final String whiteSymbol;
    private final String blackSymbol;

    public final Player owner;

    // relationship to the board
    public Board board;
    public int x;
    public int y;


    Piece(Player owner, String name, String whiteSymbol, String blackSymbol) {
        if (owner == null)
            throw new IllegalArgumentException("Owner cannot be null");
        this.owner = owner;
        this.name = name;
        this.whiteSymbol = whiteSymbol;
        this.blackSymbol = blackSymbol;
    }

    public boolean isWhite() {
        return owner.isWhite();
    }

    public abstract Set<Position> getAvailablePosition(boolean isWhiteRound);


    public boolean sameColor(Piece other) {
        return other.isWhite() == isWhite();
    }

    public void setPos(int row, int col) {
        x = row;
        y = col;
    }

    /**
     * A protected helper function that check whether the given position is valid, and if so, add
     * the position to positions.
     * This method WILL modify the positions parameter
     *
     * @param row       row position to check
     * @param col       column position to check
     * @param positions if (row, col) is valid, it will be added to positions
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

    @Override
    public String toString() {
        return isWhite() ? whiteSymbol : blackSymbol;
    }
}
