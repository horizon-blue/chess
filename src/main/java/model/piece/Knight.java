package model.piece;

import model.Player;
import model.Position;

import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(Player owner) {
        super(owner, "Knight", "♘", "♞");
    }

    @Override
    public ArrayList<Position> getAvailablePosition() {
        return null;
    }
}
