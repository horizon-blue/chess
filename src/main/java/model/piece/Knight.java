package model.piece;

import model.Player;
import model.Position;

import java.util.Set;

public class Knight extends Piece {
    public Knight(Player owner) {
        super(owner, "Knight", "♘", "♞");
    }

    @Override
    public Set<Position> getAvailablePosition(boolean isWhiteRound) {
        return null;
    }
}
