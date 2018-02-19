package model.piece;

import controller.GameLegacy;
import model.Board;
import model.Player;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArtilleryTest {
    Player player;
    Piece artillery;
    Board board;

    @BeforeEach
    void beforeEach() {
        player = new Player();
        board = new Board();
        artillery = new Artillery(player);
    }

    @Test
    @DisplayName("getAvailablePosition() (valid positions)")
    void getAvailablePosition() {
        board.addPiece(artillery, 3, 5);

        Set<Position> positions = artillery.getAvailablePosition(false);
        // check whether position is the same as expected
        // check rank and tile
        assertTrue(positions.contains(new Position(4, 5)));
        assertTrue(positions.contains(new Position(1, 5)));
        assertTrue(positions.contains(new Position(3, 1)));
        assertTrue(positions.contains(new Position(3, 7)));

        assertEquals(14, positions.size());
    }

    @Test
    @DisplayName("getAvailablePosition() (invalid positions)")
    void getAvailablePositionInvalid() {
        board.addPiece(artillery, 2, 3);

        Set<Position> positions = artillery.getAvailablePosition(false);
        // check whether position is the same as expected
        // check rank and tile
        assertFalse(positions.contains(new Position(4, 5)));
        assertFalse(positions.contains(new Position(1, 5)));
        assertFalse(positions.contains(new Position(0, -1)));
        assertFalse(positions.contains(new Position(7, 7)));

        assertEquals(14, positions.size());
    }

    @Test
    @DisplayName("getAvailablePosition() (no board)")
    void getAvailablePositionNoBoard() {
        Set<Position> positions = artillery.getAvailablePosition(false);
        // check whether position is the same as expected
        // check rank and tile
        assertFalse(positions.contains(new Position(4, 5)));
        assertEquals(0, positions.size());

    }

    @Test
    @DisplayName("getAvailablePosition() (jump over piece)")
    void getAvailablePositionJumpOver() {
        Player blackPlayer = new Player();
        GameLegacy game = new GameLegacy(player, blackPlayer);

        board.addPiece(artillery, 2, 2);
        board.addPiece(new Pawn(blackPlayer), 2, 4);
        board.addPiece(new Queen(blackPlayer), 2, 6);
        board.addPiece(new Bishop(blackPlayer), 2, 7);

        Set<Position> positions = artillery.getAvailablePosition(false);
        // artillery can jump over to other piece
        assertTrue(positions.contains(new Position(2, 6)));
        assertTrue(positions.contains(new Position(2, 3)));
        assertFalse(positions.contains(new Position(2, 4)));
        assertFalse(positions.contains(new Position(2, 5)));
        // it should only be able to capture first piece beyond the
        // one it jumps over
        assertFalse(positions.contains(new Position(2, 7)));
        assertEquals(11, positions.size());

    }

}