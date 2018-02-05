package model.piece;

import model.Player;
import model.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(Player owner) {
        super(owner, "King", "♔", "♚");
        owner.king = this;
    }

    @Override
    public List<Position> getAvailablePosition(boolean isWhiteRound) {
        List<Position> availablePos = new ArrayList<>();
        // no available position if piece isn't in its own round
        if (isWhiteRound != isWhite())
            return null;

        // "The king moves one square in any direction."
        for (int row = x - 1; row <= x + 1; ++row) {
            for (int col = y - 1; col <= y + 1; ++col) {
                if (board.isValidMovement(this, row, col))
                    availablePos.add(new Position(row, col));
            }
        }
        return availablePos;
    }
}
