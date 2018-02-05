package model.piece;

import model.Board;
import model.Player;
import model.Position;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class QueenTest {
    @Test
    void getAvailablePosition() {
        Player player = new Player();
        Board board = new Board(8, 8);
        Piece queen = new Queen(player);
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
        // check invalid positions
        assertFalse(positions.contains(new Position(3, 5)));
        assertFalse(positions.contains(new Position(-1, 5)));
        assertFalse(positions.contains(new Position(3, 10)));
        assertEquals(positions.size(), 25);
    }

    @Test
    @DisplayName("getAvailablePosition() (no board)")
    void getAvailablePositionWithoutBoard() {
        Piece queen = new Queen(new Player());

        Set<Position> positions = queen.getAvailablePosition(false);
        // check whether position is the same as expected
        // check rank and tile
        assertFalse(positions.contains(new Position(2, 5)));
        assertEquals(positions.size(), 0);

    }

}