package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    @DisplayName("isValid (valid values)")
    void isValidValid() {
        Board board = new Board(8, 8);
        assertTrue(board.isValid(3, 5));
        assertTrue(board.isValid(0, 7));
    }

    @Test
    @DisplayName("isValid (invalid values)")
    void isValidInvalid() {
        Board board = new Board(8, 8);
        assertFalse(board.isValid(3, -1));
        assertFalse(board.isValid(-1, 5));
        assertFalse(board.isValid(5, 8));
        assertFalse(board.isValid(8, 8));
        assertFalse(board.isValid(-1, -1));
    }


}