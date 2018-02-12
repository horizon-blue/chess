package model.piece;

import controller.Game;
import model.Board;
import model.Player;
import model.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {
    @Test
    @DisplayName("getAvailablePosition() (valid positions)")
    void getAvailablePositionValid() {
        Player player = new Player();
        Board board = new Board(8, 8);
        Piece pawn = new Pawn(player);
        board.addPiece(pawn, 6, 5);

        Set<Position> positions = pawn.getAvailablePosition(false);
        assertTrue(positions.contains(new Position(5, 5)));
        assertTrue(positions.contains(new Position(4, 5)));
        assertEquals(2, positions.size());
    }

    @Test
    @DisplayName("getAvailablePosition() (second move)")
    void getAvailablePositionSecondMove() {
        Player player = new Player();
        Board board = new Board(8, 8);
        Piece pawn = new Pawn(player);
        board.addPiece(pawn, 2, 3);
        board.movePiece(pawn, 3, 3);

        Set<Position> positions = pawn.getAvailablePosition(false);
        assertTrue(positions.contains(new Position(2, 3)));
        assertFalse(positions.contains(new Position(1, 3)));

        assertEquals(1, positions.size());
    }

    @Test
    @DisplayName("getAvailablePosition() (invalid positions)")
    void getAvailablePositionInvalid() {
        Player player = new Player();
        Board board = new Board(9, 9);
        Piece pawn = new Pawn(player);
        board.addPiece(pawn, 8, 4);

        Set<Position> positions = pawn.getAvailablePosition(false);
        // check invalid positions
        assertFalse(positions.contains(new Position(8, 4)));
        assertFalse(positions.contains(new Position(0, 0)));
        assertFalse(positions.contains(new Position(7, 3)));
        assertFalse(positions.contains(new Position(7, 5)));
        assertEquals(2, positions.size());

    }

    @Test
    @DisplayName("getAvailablePosition() (no board)")
    void getAvailablePositionNoBoard() {
        Piece pawn = new Pawn(new Player());

        Set<Position> positions = pawn.getAvailablePosition(false);
        // check whether position is the same as expected
        // check rank and tile
        assertFalse(positions.contains(new Position(2, 5)));
        assertEquals(0, positions.size());

    }

    @Test
    @DisplayName("getAvailablePosition() (enemy occupied)")
    void getAvailablePositionWithEnemy() {
        Player blackPlayer = new Player();
        Player whitePlayer = new Player();
        Game game = new Game(whitePlayer, blackPlayer);
        Piece pawn = new Pawn(whitePlayer);
        Piece rook = new Rook(blackPlayer);
        // pawn shouldn't be able to move diagonally without enemy piece
        game.board.addPiece(pawn, 6, 4);
        Set<Position> positions = pawn.getAvailablePosition(true);
        assertFalse(positions.contains(new Position(7, 3)));
        game.board.addPiece(rook, 7, 3);
        // pawn should be able to move diagonally now
        positions = pawn.getAvailablePosition(true);
        assertTrue(positions.contains(new Position(7, 3)));
        assertFalse(positions.contains(new Position(7, 5)));
    }

}