package model.piece;

import model.Player;
import model.Position;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
    public boolean hasMoved = false;

    public Pawn(Player owner) {
        super(owner, "Pawn", "♙", "♟");
    }

    @Override
    public Set<Position> getAvailablePosition(boolean isWhiteRound) {
        Set<Position> availablePos = new HashSet<>();
        // no available position if piece isn't in its own round
        if (isWhiteRound != isWhite())
            return availablePos;

        // white pieces move to the larger side, black pieces move to the smaller side
        int direction = isWhite() ? 1 : -1;
        // if 1 tile in front is valid and not occupied (addValidPos does the check)
        if (addValidPos(x + direction, y, availablePos, isWhiteRound) && !hasMoved)
            addValidPos(x + 2 * direction, y, availablePos, isWhiteRound);

        // check diagonal tiles
        for (int colDist = -1; colDist <= 1; colDist += 2) {
            if (board.isOccupied(x + direction, y + colDist))
                addValidPos(x + direction, y + colDist, availablePos, isWhiteRound);
        }
        return availablePos;
    }
}
