package model;

import model.piece.Pawn;
import model.piece.Piece;
import model.piece.Queen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HistoryTest {
    Board board;
    History history;
    Piece pawn;
    Position origin;
    Position target;

    @BeforeEach
    void beforeEach() {
        board = new Board();
        origin = new Position(3, 4);
        target = new Position(3, 5);
        pawn = new Pawn(new Player());
        board.addPiece(pawn, origin);
        ++((Pawn) pawn).moveCount;
        history = new History(board, target, origin);
    }

    @Test
    void undo() {
        // before undo, a moved pawn should only be able to advance one step forward
        Set<Position> positions = pawn.getAvailablePosition(false);
        assertTrue(positions.contains(new Position(2, 4)));
        assertFalse(positions.contains(new Position(1, 4)));
        assertNotNull(board.getPiece(origin));
        assertNull(board.getPiece(target));

        history.undo();

        positions = pawn.getAvailablePosition(false);
        assertTrue(positions.contains(new Position(2, 5)));
        assertTrue(positions.contains(new Position(1, 5)));
        assertNull(board.getPiece(origin));
        assertNotNull(board.getPiece(target));

    }

    @Test
    @DisplayName("undo() (capture)")
    void undoCapture() {
        Queen queen = new Queen(new Player("", true));
        history = new History(board, target, origin, queen);
        history.undo();
        assertEquals(board.getPiece(origin), queen);
        assertEquals(board.getPiece(target), pawn);
    }

    @Test
    @DisplayName("equals()")
    void equalsTest() {
        History history2 = new History(board, new Position(3, 5),
                new Position(3, 4));
        assertNotEquals(history, null);
        assertEquals(history2, history);
        assertEquals(history, history);
    }
}