package model.piece;

import model.Board;
import model.Player;
import model.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {
    @Test
    @DisplayName("getAvailablePosition() (valid positions)")
    void getAvailablePositionValid() {
        Player player = new Player();
        Board board = new Board(8, 8);
        Piece rook = new Rook(player);
        board.addPiece(rook, 3, 5);

        Set<Position> positions = rook.getAvailablePosition(false);
        // check whether position is the same as expected
        // check rank and tile
        assertTrue(positions.contains(new Position(2, 5)));
        assertTrue(positions.contains(new Position(6, 5)));
        assertTrue(positions.contains(new Position(3, 3)));
        assertTrue(positions.contains(new Position(3, 7)));

        assertEquals(14, positions.size());
    }

    @Test
    @DisplayName("getAvailablePosition() (invalid positions)")
    void getAvailablePositionInvalid() {
        Player player = new Player();
        Board board = new Board(9, 9);
        Piece rook = new Rook(player);
        board.addPiece(rook, 4, 4);

        Set<Position> positions = rook.getAvailablePosition(false);
        // check invalid positions
        assertFalse(positions.contains(new Position(4, 4)));
        assertFalse(positions.contains(new Position(-1, 5)));
        assertFalse(positions.contains(new Position(3, 10)));
        assertFalse(positions.contains(new Position(8, 8)));
        assertEquals(16, positions.size());

    }

    @Test
    @DisplayName("getAvailablePosition() (no board)")
    void getAvailablePositionNoBoard() {
        Piece rook = new Rook(new Player());

        Set<Position> positions = rook.getAvailablePosition(false);
        // check whether position is the same as expected
        // check rank and tile
        assertFalse(positions.contains(new Position(2, 5)));
        assertEquals(0, positions.size());

    }

}