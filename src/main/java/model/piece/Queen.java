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
     * Create a queen piece for the owner
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

        // "The queen combines the power of the rook and bishop and can move any number of
        // squares along rank, file, or diagonal, but it may not leap over other pieces"
        // split into separate for loops so that we can break if the path is blocked
        // check the same file and rank
        for (int row = x - 1; row >= 0 && addValidPos(row, y, availablePositions, isWhiteRound); --row) ;
        for (int row = x + 1; row < board.HEIGHT && addValidPos(row, y, availablePositions, isWhiteRound); ++row) ;
        for (int col = y - 1; col >= 0 && addValidPos(x, col, availablePositions, isWhiteRound); --col) ;
        for (int col = y + 1; col < board.WIDTH && addValidPos(x, col, availablePositions, isWhiteRound); ++col) ;

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
