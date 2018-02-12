package model.piece;

import model.Player;
import model.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * Bishop - a subclass of Piece
 */
public class Bishop extends Piece {
    /**
     * directions that Bishop can go - either same file or same rank
     * "The bishop can move any number of squares diagonally, but may not
     * leap over other pieces"
     */
    private static final int DIRECTIONS[][] = {{-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

    /**
     * Create a Bishop piece for the owner
     *
     * @param owner the player who has the bishop piece
     */
    public Bishop(Player owner) {
        super(owner, "♗", "♝");
    }

    @Override
    public Set<Position> getAvailablePosition(boolean isWhiteRound) {
        Set<Position> availablePositions = new HashSet<>();
        if (!isOnBoard())
            return availablePositions;

        return addAllInDirections(DIRECTIONS, availablePositions, isWhiteRound);
    }
}
