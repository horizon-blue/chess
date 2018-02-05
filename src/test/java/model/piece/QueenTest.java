package model.piece;

import model.Board;
import model.Player;
import model.Position;

import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class QueenTest {
    @Test
    void getAvailablePosition() {
        Player player = new Player();
        Board board = new Board(8, 8);
        Piece queen = new Queen(player);
        board.setPiece(queen, 3, 5);
        Set<Position> positions = queen.getAvailablePosition(false);
        // check whether position is the same as expected
        for (int row = 0; row < 8; ++row) {
            if (row != 3)
                assertTrue(positions.contains(new Position(row, 5)));
            else
                assertFalse(positions.contains(new Position(row, 5)));
        }

    }

}