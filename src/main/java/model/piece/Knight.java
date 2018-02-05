package model.piece;

import model.Player;
import model.Position;

import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {
    public Knight(Player owner) {
        super(owner, "Knight", "♘", "♞");
    }

    @Override
    public Set<Position> getAvailablePosition(boolean isWhiteRound) {
        Set<Position> availablePos = new HashSet<>();

        // "The knight moves to any of the closest squares that are not on the same rank,
        // file, or diagonal, thus the move forms an "L"-shape."
        for (int height = -2; height <= 2; height += 4) {
            for (int width = -1; width <= 1; width += 2) {
                addValidPos(x + height, y + width, availablePos, isWhiteRound);
                addValidPos(x + width, y + height, availablePos, isWhiteRound);
            }
        }
        return availablePos;
    }
}
