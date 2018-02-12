package model.piece;

import model.Board;
import model.Player;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {
    Player player;
    Piece king;
    Board board;

    @BeforeEach
    void beforeEach() {
        player = new Player();
        board = new Board();
        king = new King(player);
    }

    @Test
    @DisplayName("getAvailablePosition() (valid positions)")
    void getAvailablePositionValid() {
        board.addPiece(king, 6, 5);

        Set<Position> positions = king.getAvailablePosition(false);
        for (int row = 5; row <= 7; ++row)
            for (int col = 4; col <= 6; ++col)
                if (row != 6 && col != 5)
                    assertTrue(positions.contains(new Position(row, col)));
        assertEquals(8, positions.size());
    }

    @Test
    @DisplayName("getAvailablePosition() (invalid positions)")
    void getAvailablePositionInvalid() {
        board.addPiece(king, 7, 4);

        Set<Position> positions = king.getAvailablePosition(false);
        // check invalid positions
        assertFalse(positions.contains(new Position(8, 4)));
        assertFalse(positions.contains(new Position(0, 0)));
        assertEquals(5, positions.size());

    }

    @Test
    @DisplayName("getAvailablePosition() (no board)")
    void getAvailablePositionNoBoard() {
        Set<Position> positions = king.getAvailablePosition(false);
        // check whether position is the same as expected
        // check rank and tile
        assertFalse(positions.contains(new Position(2, 5)));
        assertEquals(0, positions.size());

    }

}