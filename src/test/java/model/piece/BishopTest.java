package model.piece;

import model.Board;
import model.Player;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {
    Player player;
    Piece bishop;
    Board board;

    @BeforeEach
    void beforeEach() {
        player = new Player();
        board = new Board();
        bishop = new Bishop(player);
    }

    @Test
    @DisplayName("getAvailablePosition() (valid positions)")
    void getAvailablePositionValid() {
        board.addPiece(bishop, 3, 5);

        Set<Position> positions = bishop.getAvailablePosition(false);
        // check whether position is the same as expected
        // check diagonals
        assertTrue(positions.contains(new Position(2, 4)));
        assertTrue(positions.contains(new Position(4, 6)));
        assertTrue(positions.contains(new Position(1, 7)));
        assertTrue(positions.contains(new Position(7, 1)));
        assertEquals(11, positions.size());
    }

    @Test
    @DisplayName("getAvailablePosition() (invalid positions)")
    void getAvailablePositionInvalid() {
        board.addPiece(bishop, 4, 4);

        Set<Position> positions = bishop.getAvailablePosition(false);
        // check invalid positions
        assertFalse(positions.contains(new Position(4, 4)));
        assertFalse(positions.contains(new Position(-1, 5)));
        assertFalse(positions.contains(new Position(3, 10)));

        // check rank and tile
        assertFalse(positions.contains(new Position(4, 9)));
        assertFalse(positions.contains(new Position(6, 9)));
        assertFalse(positions.contains(new Position(9, 3)));
        assertFalse(positions.contains(new Position(9, 7)));
        assertEquals(13, positions.size());

    }

    @Test
    @DisplayName("getAvailablePosition() (no board)")
    void getAvailablePositionNoBoard() {
        Set<Position> positions = bishop.getAvailablePosition(false);
        // check whether position is the same as expected
        // check rank and tile
        assertFalse(positions.contains(new Position(2, 5)));
        assertEquals(0, positions.size());

    }

}