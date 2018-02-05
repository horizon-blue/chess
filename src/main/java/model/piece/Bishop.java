package model.piece;

import model.Player;
import model.Position;

import java.util.List;

public class Bishop extends Piece {
    public Bishop(Player owner) {
        super(owner, "Bishop", "♗", "♝");
    }

    @Override
    public List<Position> getAvailablePosition(boolean isWhiteRound) {
        return null;
    }
}
