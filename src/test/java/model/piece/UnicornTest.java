package model.piece;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UnicornTest {
    Player player;
    Piece unicorn;
    Board board;

    @BeforeEach
    void beforeEach() {
        player = new Player();
        board = new Board();
        unicorn = new Unicorn(player);
    }

    @Test
    @DisplayName("getAvailablePosition() (valid positions)")
    void getAvailablePosition() {
        board.addPiece(unicorn, 3, 6);
        Set<Position> positions = unicorn.getAvailablePosition(true);
        // check valid positions
        assertTrue(positions.contains(new Position(1, 5)));
        assertTrue(positions.contains(new Position(5, 7)));
        assertTrue(positions.contains(new Position(1, 2)));
        assertTrue(positions.contains(new Position(5, 5)));
        assertEquals(11, positions.size());
    }

    @Test
    @DisplayName("getAvailablePosition() (invalid positions)")
    void getAvailablePositionInvalid() {
        board.addPiece(unicorn, 4, 4);

        Set<Position> positions = unicorn.getAvailablePosition(false);
        // check invalid positions
        assertFalse(positions.contains(new Position(4, 4)));
        assertFalse(positions.contains(new Position(-1, 5)));
        assertFalse(positions.contains(new Position(3, 10)));
        assertFalse(positions.contains(new Position(6, 8)));
        assertEquals(12, positions.size());

    }

    @Test
    @DisplayName("getAvailablePosition() (no board)")
    void getAvailablePositionNoBoard() {
        Set<Position> positions = unicorn.getAvailablePosition(false);
        // check whether position is the same as expected
        // check rank and tile
        assertFalse(positions.contains(new Position(2, 5)));
        assertEquals(0, positions.size());

    }


}