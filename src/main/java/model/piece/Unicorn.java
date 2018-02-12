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
        super(owner, "𝕌", "U");
    }

    @Override
    public Set<Position> getAvailablePosition(boolean isWhiteRound) {
        Set<Position> availablePositions = new HashSet<>();
        if (!isOnBoard())
            return availablePositions;

        // unicorn is "a fairy chess piece that can move any number of
        // steps as a knight in the same direction" -- Wikipedia
        // top-left tiles
        for (int dist = 1; Math.min(x - dist * 1, y - dist * 2) >= 0
                && addValidPos(x - dist * 1, y - dist * 2, availablePositions, isWhiteRound); ++dist)
            ;
        for (int dist = 1; Math.min(x - dist * 2, y - dist * 1) >= 0
                && addValidPos(x - dist * 2, y - dist * 1, availablePositions, isWhiteRound); ++dist)
            ;
        // top-right tiles
        for (int dist = 1; Math.min(x - dist * 2, board.WIDTH - y - dist * 1 - 1) >= 0
                && addValidPos(x - dist * 2, y + dist * 1, availablePositions, isWhiteRound); ++dist)
            ;
        for (int dist = 1; Math.min(x - dist * 1, board.WIDTH - y - dist * 2 - 1) >= 0
                && addValidPos(x - dist * 1, y + dist * 2, availablePositions, isWhiteRound); ++dist)
            ;

        // bottom-left tiles
        for (int dist = 1; Math.min(board.HEIGHT - x - dist * 2 - 1, y - dist * 1) >= 0
                && addValidPos(x + dist * 2, y - dist * 1, availablePositions, isWhiteRound); ++dist)
            ;
        for (int dist = 1; Math.min(board.HEIGHT - x - dist * 1 - 1, y - dist * 2) >= 0
                && addValidPos(x + dist * 1, y - dist * 2, availablePositions, isWhiteRound); ++dist)
            ;

        // bottom-right tiles
        for (int dist = 1; Math.min(board.HEIGHT - x - dist * 2 - 1, board.WIDTH - y - dist * 1 - 1) >= 0
                && addValidPos(x + dist * 2, y + dist * 1, availablePositions, isWhiteRound); ++dist)
            ;
        for (int dist = 1; Math.min(board.HEIGHT - x - dist * 1 - 1, board.WIDTH - y - dist * 2 - 1) >= 0
                && addValidPos(x + dist * 1, y + dist * 2, availablePositions, isWhiteRound); ++dist)
            ;

        return availablePositions;
    }


}
