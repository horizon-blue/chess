package model.piece;

import model.Player;
import model.Position;

import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {
    public Rook(Player owner) {
        super(owner, "Rook", "♖", "♜");
    }

    @Override
    public Set<Position> getAvailablePosition(boolean isWhiteRound) {
        Set<Position> availablePos = new HashSet<>();
        if (board == null)
            return availablePos;

        // "The rook can move any number of squares along any rank or file, but may not leap over other pieces."
        // split into separate for loops so that we can break if the path is blocked
        // check the same file and rank
        for (int row = x - 1; row >= 0 && addValidPos(row, y, availablePos, isWhiteRound); --row) ;
        for (int row = x + 1; row < board.HEIGHT && addValidPos(row, y, availablePos, isWhiteRound); ++row) ;
        for (int col = y - 1; col >= 0 && addValidPos(x, col, availablePos, isWhiteRound); --col) ;
        for (int col = y + 1; col < board.WIDTH && addValidPos(x, col, availablePos, isWhiteRound); ++col) ;

        return availablePos;
    }
}
