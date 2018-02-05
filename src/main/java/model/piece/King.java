package model.piece;

import model.Player;
import model.Position;

import java.util.HashSet;
import java.util.Set;

public class King extends Piece {
    public King(Player owner) {
        super(owner, "King", "♔", "♚");
        owner.king = this;
    }

    @Override
    public Set<Position> getAvailablePosition(boolean isWhiteRound) {
        Set<Position> availablePos = new HashSet<>();
        // no available position if piece isn't in its own round
        if (isWhiteRound != isWhite())
            return availablePos;

        // "The king moves one square in any direction."
        for (int row = x - 1; row <= x + 1; ++row) {
            for (int col = y - 1; col <= y + 1; ++col) {
                addValidPos(row, col, availablePos);
            }
        }
        return availablePos;
    }
}
