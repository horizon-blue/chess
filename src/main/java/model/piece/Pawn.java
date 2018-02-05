package model.piece;

import model.Player;
import model.Position;

import java.util.ArrayList;

public class Pawn extends Piece {
    public boolean hasMoved = false;

    public Pawn(Player owner) {
        super(owner, "Pawn", "♙", "♟");
    }

    @Override
    public ArrayList<Position> getAvailablePosition() {
        return null;
    }
}
