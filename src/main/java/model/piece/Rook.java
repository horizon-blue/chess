package model.piece;

import model.Player;
import model.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * Rook - a subclass of Piece
 */
public class Rook extends Piece {
    /**
     * directions that Rook can go - either same file or same rank
     * "The rook can move any number of squares along any rank or file,
     * but may not leap over other pieces."
     */
    private static final int DIRECTIONS[][] = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    /**
     * Create a Rook piece for the owner
     *
     * @param owner the player who has the rook piece
     */
    public Rook(Player owner) {
        super(owner, "♖", "♜");
    }

    @Override
    public Set<Position> getAvailablePosition(boolean isWhiteRound) {
        Set<Position> availablePositions = new HashSet<>();
        if (!isOnBoard())
            return availablePositions;

        return addAllInDirections(DIRECTIONS, availablePositions, isWhiteRound);
    }
}
