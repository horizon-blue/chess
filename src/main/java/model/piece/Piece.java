package model.piece;

import model.Board;
import model.Player;
import model.Position;

import java.util.ArrayList;

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

    public abstract ArrayList<Position> getAvailablePosition();

    /**
     * Check if the piece is in its own round
     *
     * @return true if current round is the piece's round, false otherwise
     */
    public boolean isInRound() {
        return owner.isInRound();
    }

    public boolean sameColor(Piece other) {
        return other.isWhite() == isWhite();
    }

    @Override
    public String toString() {
        return isWhite() ? whiteSymbol : blackSymbol;
    }
}
