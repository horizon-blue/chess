package model.piece;

import model.Board;
import model.Player;
import model.Position;

import java.util.ArrayList;
import java.util.List;

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

    public abstract List<Position> getAvailablePosition(boolean isWhiteRound);


    public boolean sameColor(Piece other) {
        return other.isWhite() == isWhite();
    }

    /**
     * A protected helper function that check whether the given position is valid, and if so, add
     * the position to positions ArrayList.
     *
     * @param row       row position to check
     * @param col       column position to check
     * @param positions if (row, col) is valid, it will be added to positions
     * @return true if the given position is empty, false otherwise
     */
    protected boolean addValidPos(int row, int col, List<Position> positions) {
        if (board.isValidMovement(this, row, col))
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
