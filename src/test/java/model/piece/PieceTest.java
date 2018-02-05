package model.piece;

import model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {
    @Test
    void pieceWithoutOwner() {
        assertThrows(IllegalArgumentException.class,
                () -> new Knight(null),
                "Owner cannot be null");
    }

    @Test
    void isWhite() {
        Player player = new Player();
        Piece queen = new Queen(player);
        assertEquals(queen.isWhite(), player.isWhite);
    }

    @Test
    void sameColor() {
        Player player = new Player();
        Piece queen = new Queen(player);
        Piece king = new King(player);
        assertTrue(queen.sameColor(king));
    }

    @Test
    void setPos() {
        Player player = new Player();
        Piece pawn = new Pawn(player);
        pawn.setPos(3, 5);
        assertEquals(3, pawn.x);
        assertEquals(5, pawn.y);
    }

    @Test
    @DisplayName("toString()")
    void toStringTest() {
        Player player = new Player();
        Piece pawn = new Pawn(player);
        assertEquals("♟", pawn.toString());
    }

}