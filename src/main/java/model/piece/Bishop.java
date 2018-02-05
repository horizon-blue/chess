package model.piece;

import model.Player;
import model.Position;

import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {
    public Bishop(Player owner) {
        super(owner, "Bishop", "♗", "♝");
    }

    @Override
    public Set<Position> getAvailablePosition(boolean isWhiteRound) {
        Set<Position> availablePos = new HashSet<>();
        // no available position if piece isn't in its own round
        if (isWhiteRound != isWhite())
            return availablePos;

        // "The bishop can move any number of squares diagonally, but may not leap over other pieces"
        // split into separate for loops so that we can break if the path is blocked
        // check diagonal tiles
        for (int dist = 1; Math.min(x - dist, y - dist) >= 0
                && addValidPos(x - dist, y - dist, availablePos); ++dist)
            ;
        for (int dist = 1; Math.min(board.HEIGHT - dist - x, board.WIDTH - dist - y) > 0
                && addValidPos(x + dist, y + dist, availablePos); ++dist)
            ;
        for (int dist = 1; Math.min(x - dist, board.WIDTH - dist - y - 1) >= 0
                && addValidPos(x - dist, y + dist, availablePos); ++dist)
            ;
        for (int dist = 1; Math.min(board.HEIGHT - dist - x - 1, y - dist) >= 0
                && addValidPos(x + dist, y - dist, availablePos); ++dist)
            ;

        return availablePos;
    }
}
