package model.piece;

import model.Player;
import model.Position;

import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(Player owner) {
        super(owner, "Queen", "♕", "♛");
    }

    @Override
    public ArrayList<Position> getAvailablePosition() {
        return null;
    }
}
