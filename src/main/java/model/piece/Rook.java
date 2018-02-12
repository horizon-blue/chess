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
     * Create a rook piece for the owner
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

        // "The rook can move any number of squares along any rank or file, but may not leap over other pieces."
        // split into separate for loops so that we can break if the path is blocked
        // check the same file and rank
        for (int row = x - 1; row >= 0 && addValidPos(row, y, availablePositions, isWhiteRound); --row) ;
        for (int row = x + 1; row < board.HEIGHT && addValidPos(row, y, availablePositions, isWhiteRound); ++row) ;
        for (int col = y - 1; col >= 0 && addValidPos(x, col, availablePositions, isWhiteRound); --col) ;
        for (int col = y + 1; col < board.WIDTH && addValidPos(x, col, availablePositions, isWhiteRound); ++col) ;

        return availablePositions;
    }
}
