package model.piece;

import model.Player;
import model.Position;

import java.util.Set;

public class Pawn extends Piece {
    public boolean hasMoved = false;

    public Pawn(Player owner) {
        super(owner, "Pawn", "♙", "♟");
    }

    @Override
    public Set<Position> getAvailablePosition(boolean isWhiteRound) {
        return null;
    }
}
