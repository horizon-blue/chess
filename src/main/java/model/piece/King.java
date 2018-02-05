package model.piece;

import model.Player;
import model.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * King - a subclass of Piece
 */
public class King extends Piece {
    /**
     * Create a king piece for the owner
     *
     * @param owner the player who has the king piece
     */
    public King(Player owner) {
        super(owner, "King", "♔", "♚");
        owner.king = this;
    }

    @Override
    public Set<Position> getAvailablePosition(boolean isWhiteRound) {
        Set<Position> availablePos = new HashSet<>();
        if (board == null)
            return availablePos;

        // "The king moves one square in any direction."
        for (int row = x - 1; row <= x + 1; ++row) {
            for (int col = y - 1; col <= y + 1; ++col) {
                addValidPos(row, col, availablePos, isWhiteRound);
            }
        }
        return availablePos;
    }
}
