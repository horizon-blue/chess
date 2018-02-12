package model.piece;

import model.Player;
import model.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * Pawn - a subclass of Piece
 */
public class Pawn extends Piece {
    /**
     * Record whether Pawn has been moved. A Pawn can move two steps forward
     * in its first movement
     */
    public boolean hasMoved = false;

    /**
     * Create a Pawn piece for the owner
     *
     * @param owner the player who has the pawn piece
     */
    public Pawn(Player owner) {
        super(owner, "♙", "♟");
    }

    @Override
    public Set<Position> getAvailablePosition(boolean isWhiteRound) {
        Set<Position> availablePositions = new HashSet<>();
        if (!isOnBoard())
            return availablePositions;

        // white pieces move to the larger side, black pieces move to the smaller side
        int direction = isWhite() ? 1 : -1;
        // if 1 tile in front is valid and not occupied (addValidPos does the check)
        if (addValidPos(x + direction, y, availablePositions, isWhiteRound) && !hasMoved)
            addValidPos(x + 2 * direction, y, availablePositions, isWhiteRound);

        // check diagonal tiles
        for (int colDist = -1; colDist <= 1; colDist += 2) {
            if (board.isOccupied(x + direction, y + colDist))
                addValidPos(x + direction, y + colDist, availablePositions, isWhiteRound);
        }
        return availablePositions;
    }
}
