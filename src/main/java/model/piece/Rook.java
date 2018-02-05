package model.piece;

import model.Player;
import model.Position;

import java.util.Set;

public class Rook extends Piece {
    public Rook(Player owner) {
        super(owner, "Rook", "♖", "♜");
    }

    @Override
    public Set<Position> getAvailablePosition(boolean isWhiteRound) {
        return null;
    }
}
