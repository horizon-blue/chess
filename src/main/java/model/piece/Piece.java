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

    private final Player owner;

    // relationship to the board
    public Board board;
    public int x;
    public int y;


    Piece(Player owner, String name, String whiteSymbol, String blackSymbol) {
        this.owner = owner;
        this.name = name;
        this.whiteSymbol = whiteSymbol;
        this.blackSymbol = blackSymbol;
    }

    public boolean isWhite() {
        return owner != null && owner.isWhite();
    }

//    public abstract ArrayList<Position> getAvailablePosition();

    @Override
    public String toString() {
        return isWhite() ? whiteSymbol : blackSymbol;
    }
}
