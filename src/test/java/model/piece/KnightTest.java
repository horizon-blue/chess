package model.piece;

import model.Board;
import model.Player;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
    Player player;
    Piece knight;
    Board board;

    @BeforeEach
    void beforeEach() {
        player = new Player();
        board = new Board();
        knight = new Knight(player);
    }

    @Test
    @DisplayName("getAvailablePosition() (valid positions)")
    void getAvailablePositionValid() {
        board.addPiece(knight, 4, 5);

        Set<Position> positions = knight.getAvailablePosition(false);
        for (int width = -2; width <= 2; width += 4)
            for (int height = -1; height <= 1; height += 2) {
                assertTrue(positions.contains(new Position(4 + width, 5 + height)));
                assertTrue(positions.contains(new Position(4 + height, 5 + width)));
            }
        assertEquals(8, positions.size());
    }

    @Test
    @DisplayName("getAvailablePosition() (invalid positions)")
    void getAvailablePositionInvalid() {
        board.addPiece(knight, 7, 4);

        Set<Position> positions = knight.getAvailablePosition(false);
        // check invalid positions
        assertFalse(positions.contains(new Position(5, 4)));
        assertFalse(positions.contains(new Position(0, 0)));
        assertEquals(4, positions.size());

    }

    @Test
    @DisplayName("getAvailablePosition() (no board)")
    void getAvailablePositionNoBoard() {
        Set<Position> positions = knight.getAvailablePosition(false);
        // check whether position is the same as expected
        // check rank and tile
        assertFalse(positions.contains(new Position(2, 5)));
        assertEquals(0, positions.size());

    }

}