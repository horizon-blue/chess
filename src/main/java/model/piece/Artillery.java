package model.piece;

import model.Player;
import model.Position;

import java.util.HashSet;
import java.util.Set;

public class Artillery extends Piece {
    /**
     * Create a king piece for the owner
     *
     * @param owner the player who has the king piece
     */
    public Artillery(Player owner) {
        super(owner, "𝔸", "A");
    }

    @Override
    public Set<Position> getAvailablePosition(boolean isWhiteRound) {
        Set<Position> availablePositions = new HashSet<>();
        if (!isOnBoard())
            return availablePositions;
        // it can goes vertically or horizontally
        int directions[][] = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        for (int direction[] : directions) {
            int row = x, col = y;
            // artillery cannot directly capture other piece
            for (row = x + direction[0], col = y + direction[1];
                 board.isValid(row, col) && !board.isOccupied(row, col)
                         && addValidPos(row, col, availablePositions, isWhiteRound);
                 row += direction[0], col += direction[1])
                ;
            // however, it can capture a piece by "jumping over" another piece
            for (row += direction[0], col += direction[1];
                 board.isValid(row, col); row += direction[0],
                         col += direction[1]) {
                if (board.isOccupied(row, col))
                    addValidPos(row, col, availablePositions, isWhiteRound);
            }

        }
        return availablePositions;
    }
}
