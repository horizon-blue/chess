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
     * Create a bishop piece for the owner
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

        // "The bishop can move any number of squares diagonally, but may not leap over other pieces"
        // split into separate for loops so that we can break if the path is blocked
        // check diagonal tiles
        for (int dist = 1; Math.min(x - dist, y - dist) >= 0
                && addValidPos(x - dist, y - dist, availablePositions, isWhiteRound); ++dist)
            ;
        for (int dist = 1; Math.min(board.HEIGHT - dist - x, board.WIDTH - dist - y) > 0
                && addValidPos(x + dist, y + dist, availablePositions, isWhiteRound); ++dist)
            ;
        for (int dist = 1; Math.min(x - dist, board.WIDTH - dist - y - 1) >= 0
                && addValidPos(x - dist, y + dist, availablePositions, isWhiteRound); ++dist)
            ;
        for (int dist = 1; Math.min(board.HEIGHT - dist - x - 1, y - dist) >= 0
                && addValidPos(x + dist, y - dist, availablePositions, isWhiteRound); ++dist)
            ;

        return availablePositions;
    }
}
