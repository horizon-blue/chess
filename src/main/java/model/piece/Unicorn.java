package model.piece;

import model.Player;
import model.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * Unicorn - a fairy piece (also known as Nightrider)
 */
public class Unicorn extends Piece {
    /**
     * directions that Unicorn can go - "a fairy chess piece that can move
     * any number of steps as a knight in the same direction" -- Wikipedia
     */
    private static final int DIRECTIONS[][] =
            {{-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {-2, 1}, {-2, -1}, {2, 1}, {2, -1}};

    /**
     * Create a unicorn piece for the owner
     *
     * @param owner the player who has the unicorn piece
     */
    public Unicorn(Player owner) {
        super(owner, "𝕌", "U");
    }

    @Override
    public Set<Position> getAvailablePosition(boolean isWhiteRound) {
        Set<Position> availablePositions = new HashSet<>();
        if (!isOnBoard())
            return availablePositions;

        return addAllInDirections(DIRECTIONS, availablePositions, isWhiteRound);
    }


}
