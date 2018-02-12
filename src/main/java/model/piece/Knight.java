package model.piece;

import model.Player;
import model.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * Knight - a subclass of Piece
 */
public class Knight extends Piece {
    /**
     * Create a Knight piece for the owner
     *
     * @param owner the player who has the knight piece
     */
    public Knight(Player owner) {
        super(owner, "♘", "♞");
    }

    @Override
    public Set<Position> getAvailablePosition(boolean isWhiteRound) {
        Set<Position> availablePositions = new HashSet<>();
        if (!isOnBoard())
            return availablePositions;

        // "The knight moves to any of the closest squares that are not on the same rank,
        // file, or diagonal, thus the move forms an "L"-shape."
        for (int height = -2; height <= 2; height += 4) {
            for (int width = -1; width <= 1; width += 2) {
                addValidPos(x + height, y + width, availablePositions, isWhiteRound);
                addValidPos(x + width, y + height, availablePositions, isWhiteRound);
            }
        }
        return availablePositions;
    }
}
