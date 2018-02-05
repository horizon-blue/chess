package model.piece;

import model.Player;
import model.Position;

import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(Player owner) {
        super(owner, "Rook", "♖", "♜");
    }

    @Override
    public ArrayList<Position> getAvailablePosition(boolean isWhiteRound) {
        return null;
    }
}
