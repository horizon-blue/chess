package model.piece;

import model.Player;
import model.Position;

import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece {
    public Queen(Player owner) {
        super(owner, "Queen", "♕", "♛");
    }

    @Override
    public Set<Position> getAvailablePosition(boolean isWhiteRound) {
        Set<Position> availablePos = new HashSet<>();
        // no available position if piece isn't in its own round
        if (isWhiteRound != isWhite())
            return availablePos;

        // "The queen combines the power of the rook and bishop and can move any number of
        // squares along rank, file, or diagonal, but it may not leap over other pieces"
        // split into separate for loops so that we can break if the path is blocked
        // check the same file and rank
        for (int row = x - 1; row >= 0 && addValidPos(row, y, availablePos); --row) ;
        for (int row = x + 1; row < board.HEIGHT && addValidPos(row, y, availablePos); ++row) ;
        for (int col = y - 1; col >= 0 && addValidPos(x, col, availablePos); --col) ;
        for (int col = y + 1; col < board.WIDTH && addValidPos(x, col, availablePos); ++col) ;

        // check diagonal tiles
        for (int dist = 1; Math.min(x - dist, y - dist) >= 0
                && addValidPos(x - dist, y - dist, availablePos); ++dist)
            ;
        for (int dist = 1; Math.min(board.HEIGHT - dist - x, board.WIDTH - dist - y) > 0
                && addValidPos(x + dist, y + dist, availablePos); ++dist)
            ;

        return availablePos;
    }
}
