package model.piece;

import model.Player;
import model.Position;

import java.util.List;

public class Knight extends Piece {
    public Knight(Player owner) {
        super(owner, "Knight", "♘", "♞");
    }

    @Override
    public List<Position> getAvailablePosition(boolean isWhiteRound) {
        return null;
    }
}
