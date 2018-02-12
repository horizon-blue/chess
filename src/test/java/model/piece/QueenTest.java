package model.piece;

import model.Board;
import model.Player;
import model.Position;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class QueenTest {
    Player player;
    Board board;
    Piece queen;

    @BeforeEach
    void beforeEach() {
        player = new Player();
        board = new Board();
        queen = new Queen(player);
    }

    @Test
    @DisplayName("getAvailablePosition() (valid positions)")
    void getAvailablePositionValid() {
        board.addPiece(queen, 3, 5);

        Set<Position> positions = queen.getAvailablePosition(false);
        // check whether position is the same as expected
        // check rank and tile
        assertTrue(positions.contains(new Position(2, 5)));
        assertTrue(positions.contains(new Position(6, 5)));
        assertTrue(positions.contains(new Position(3, 3)));
        assertTrue(positions.contains(new Position(3, 7)));
        // check diagonals
        assertTrue(positions.contains(new Position(2, 4)));
        assertTrue(positions.contains(new Position(4, 6)));
        assertTrue(positions.contains(new Position(1, 7)));
        assertTrue(positions.contains(new Position(7, 1)));
        assertEquals(25, positions.size());
    }

    @Test
    @DisplayName("getAvailablePosition() (invalid positions)")
    void getAvailablePositionInvalid() {
        board.addPiece(queen, 4, 4);

        Set<Position> positions = queen.getAvailablePosition(false);
        // check invalid positions
        assertFalse(positions.contains(new Position(4, 4)));
        assertFalse(positions.contains(new Position(-1, 5)));
        assertFalse(positions.contains(new Position(3, 10)));
        assertEquals(27, positions.size());

    }

    @Test
    @DisplayName("getAvailablePosition() (no board)")
    void getAvailablePositionNoBoard() {
        Set<Position> positions = queen.getAvailablePosition(false);
        // check whether position is the same as expected
        // check rank and tile
        assertFalse(positions.contains(new Position(2, 5)));
        assertEquals(0, positions.size());

    }

}