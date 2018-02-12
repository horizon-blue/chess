package model.piece;

import controller.Game;
import model.Board;
import model.Player;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {
    Player player;
    Piece pawn;
    Board board;

    @BeforeEach
    void beforeEach() {
        player = new Player();
        board = new Board();
        pawn = new Pawn(player);
    }

    @Test
    @DisplayName("getAvailablePosition() (valid positions)")
    void getAvailablePositionValid() {
        board.addPiece(pawn, 6, 5);

        Set<Position> positions = pawn.getAvailablePosition(false);
        assertTrue(positions.contains(new Position(5, 5)));
        assertTrue(positions.contains(new Position(4, 5)));
        assertEquals(2, positions.size());
    }

    @Test
    @DisplayName("getAvailablePosition() (second move)")
    void getAvailablePositionSecondMove() {
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
        board.addPiece(pawn, 7, 4);

        Set<Position> positions = pawn.getAvailablePosition(false);
        // check invalid positions
        assertFalse(positions.contains(new Position(7, 4)));
        assertFalse(positions.contains(new Position(0, 0)));
        assertFalse(positions.contains(new Position(6, 3)));
        assertFalse(positions.contains(new Position(6, 5)));
        assertEquals(2, positions.size());

    }

    @Test
    @DisplayName("getAvailablePosition() (no board)")
    void getAvailablePositionNoBoard() {
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