package model.piece;

import model.Player;
import model.Position;

import java.util.List;

public class Rook extends Piece {
    public Rook(Player owner) {
        super(owner, "Rook", "♖", "♜");
    }

    @Override
    public List<Position> getAvailablePosition(boolean isWhiteRound) {
        return null;
    }
}
