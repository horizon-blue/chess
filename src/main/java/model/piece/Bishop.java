package model.piece;

import model.Player;
import model.Position;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(Player owner) {
        super(owner, "Bishop", "♗", "♝");
    }

    @Override
    public ArrayList<Position> getAvailablePosition() {
        return null;
    }
}
