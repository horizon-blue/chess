package model;

import controller.Game;
import model.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    void addPiece() {
        Board board = new Board();
        Player player = new Player();
        Position pos = new Position(3, 5);
        board.addPiece(new Pawn(player), pos);
        assertNotNull(board.getPiece(pos));
    }

    @Test
    void clearPiece() {
        Board board = new Board();
        Player player = new Player();
        Position pos = new Position(2, 1);
        board.addPiece(new Pawn(player), pos);
        assertNotNull(board.getPiece(pos));
        // given position should be null after clearing the piece
        board.clearPiece(pos);
        assertNull(board.getPiece(pos));
    }

    @Test
    void movePiece() {
        Board board = new Board();
        Player player = new Player();
        Position from = new Position(2, 1);
        Position to = new Position(5, 1);
        Piece queen = new Queen(player);
        board.addPiece(queen, from);
        board.movePiece(from, to);
        // piece should be moved from "from" to "to"
        assertNull(board.getPiece(from));
        assertNotNull(board.getPiece(to));
        assertEquals(queen, board.getPiece(to));
    }

    @Test
    @DisplayName("movePiece() (from other board)")
    void movePieceOtherBoard() {
        Board boardA = new Board();
        Board boardB = new Board();
        Player player = new Player();
        Piece king = new King(player);
        Position from = new Position(2, 1);
        Position to = new Position(5, 1);
        boardA.addPiece(king, from);
        boardB.movePiece(king, to);
        assertNull(boardB.getPiece(to));
        assertEquals(boardA.getPiece(from), king);

    }

    @Test
    void getPiece() {
        Board board = new Board();
        Player player = new Player();
        Position posA = new Position(2, 1);
        Position posB = new Position(-3, 6);
        Piece queen = new Queen(player);
        board.addPiece(queen, posA);

        assertEquals(queen, board.getPiece(posA));
        assertNull(board.getPiece(posB));
    }

    @Test
    void isValid() {
        Board board = new Board(3, 5);
        assertTrue(board.isValid(2, 2));
        assertTrue(board.isValid(0, 4));
        assertFalse(board.isValid(0, 5));
        assertFalse(board.isValid(-1, 3));
        assertFalse(board.isValid(3, 10));
        // test isValid(Position pos)
        assertTrue(board.isValid(new Position(2, 4)));
    }

    @Test
    void isOccupied() {
        Board board = new Board();
        Player player = new Player();
        Position pos = new Position(3, 5);
        board.addPiece(new Pawn(player), pos);
        assertTrue(board.isOccupied(pos));
        assertFalse(board.isOccupied(new Position(-2, 4)));
    }

    @Test
    void isValidMovement() {
        Board board = new Board();
        Player player = new Player();
        Piece queen = new Queen(player);
        board.addPiece(queen, 3, 5);
        board.addPiece(new King(player), 1, 5);
        assertFalse(board.isValidMovement(queen, new Position(1, 5)));
        assertTrue(board.isValidMovement(queen, 2, 5));
    }

    @Test
    void willBeChecked() {
    }

    @Test
    void isChecked() {
    }

    @Test
    void isCheckOrStaleMated() {
    }

    @Test
    @DisplayName("toString()")
    void toStringTest() {
        Game chessGame = new Game(new Player(), new Player());
        chessGame.initBoard();
        String chessBoard = "8 ♜ ♞ ♝ ♛ ♚ ♝ ♞ ♜\n" +
                "7 ♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟\n" +
                "6                \n" +
                "5                \n" +
                "4                \n" +
                "3                \n" +
                "2 ♙ ♙ ♙ ♙ ♙ ♙ ♙ ♙\n" +
                "1 ♖ ♘ ♗ ♕ ♔ ♗ ♘ ♖\n" +
                "  a b c d e f g h";
        assertEquals(chessBoard,
                chessGame.board.toString());
        assertEquals(chessBoard,
                chessGame.board.toString(new HashSet<>()));
    }

}