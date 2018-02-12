package model.piece;

import model.Player;
import model.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * Queen - a subclass of Piece
 */
public class Queen extends Piece {
    /**
     * directions that Queen can go - diagonal tiles or same rank or same file
     * "The queen combines the power of the rook and bishop and can move any number of
     * squares along rank, file, or diagonal, but it may not leap over other pieces"
     */
    private static final int DIRECTIONS[][] =
            {{-1, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

    /**
     * Create a Queen piece for the owner
     *
     * @param owner the player who has the queen piece
     */
    public Queen(Player owner) {
        super(owner, "♕", "♛");
    }

    @Override
    public Set<Position> getAvailablePosition(boolean isWhiteRound) {
        Set<Position> availablePositions = new HashSet<>();
        if (!isOnBoard())
            return availablePositions;

        return addAllInDirections(DIRECTIONS, availablePositions, isWhiteRound);
    }
}
