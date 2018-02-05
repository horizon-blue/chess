package model.piece;

import model.Player;
import model.Position;

import java.util.Set;

public class Bishop extends Piece {
    public Bishop(Player owner) {
        super(owner, "Bishop", "♗", "♝");
    }

    @Override
    public Set<Position> getAvailablePosition(boolean isWhiteRound) {
        return null;
    }
}
