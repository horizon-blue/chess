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
     * Create a unicorn piece for the owner
     *
     * @param owner the player who has the unicorn piece
     */
    public Unicorn(Player owner) {
        super(owner, "𝕦", "u");
    }

    @Override
    public Set<Position> getAvailablePosition(boolean isWhiteRound) {
        Set<Position> availablePositions = new HashSet<>();
        if (board == null)
            return availablePositions;

        // unicorn is "a fairy chess piece that can move any number of
        // steps as a knight in the same direction" -- Wikipedia

        return availablePositions;
    }


}
