package model;

import model.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board board;
    Player player;

    @BeforeEach
    void beforeEach() {
        board = new Board();
        player = new Player();
    }

    @Test
    void addPiece() {
        Position pos = new Position(3, 5);
        board.addPiece(new Pawn(player), pos);
        assertNotNull(board.getPiece(pos));
    }

    @Test
    void clearPiece() {
        Position pos = new Position(2, 1);
        board.addPiece(new Pawn(player), pos);
        assertNotNull(board.getPiece(pos));
        // given position should be null after clearing the piece
        board.clearPiece(pos);
        assertNull(board.getPiece(pos));
    }

    @Test
    void movePiece() {
        Position from = new Position(2, 1);
        Position to = new Position(5, 1);
        Piece queen = new Queen(player);
        board.addPiece(queen, from);
        board.movePiece(from, to);
        // piece should be moved from "from" to "to"
        assertNull(board.getPiece(from));
        assertNotNull(board.getPiece(to));
        assertEquals(queen, board.getPiece(to));
        // move piece again
        board.movePiece(queen, 3, 5);
        assertEquals(queen, board.getPiece(3, 5));
    }

    @Test
    @DisplayName("movePiece() (from other board)")
    void movePieceOtherBoard() {
        Board boardA = new Board();
        Board boardB = new Board();
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
        Position pos = new Position(3, 5);
        board.addPiece(new Pawn(player), pos);
        assertTrue(board.isOccupied(pos));
        assertFalse(board.isOccupied(new Position(-2, 4)));
    }

    @Test
    void isValidMovement() {
        Piece queen = new Queen(player);
        board.addPiece(queen, 3, 5);
        board.addPiece(new King(player), 1, 5);
        assertFalse(board.isValidMovement(queen, new Position(1, 5)));
        assertTrue(board.isValidMovement(queen, 2, 5));
    }

    @Test
    void willBeChecked() {
        Player whitePlayer = new Player();
        Player blackPlayer = new Player();
        whitePlayer.isWhite = true;
        board.addPiece(new King(whitePlayer), 4, 5);
        board.addPiece(new King(blackPlayer), 4, 7);
        board.addPiece(new Rook(whitePlayer), 1, 7);
        Piece rook = new Rook(blackPlayer);
        board.addPiece(rook, 3, 7);
        assertFalse(board.willBeChecked(blackPlayer.king, 5, 7));
        assertTrue(board.willBeChecked(rook, 3, 5));
    }

    @Test
    @DisplayName("willBeChecked() (invalid position)")
    void willBeCheckedInvalid() {
        Player whitePlayer = new Player();
        Player blackPlayer = new Player();
        whitePlayer.isWhite = true;
        board.addPiece(new King(whitePlayer), 4, 5);
        board.addPiece(new King(blackPlayer), 4, 7);
        assertFalse(board.willBeChecked(whitePlayer.king, new Position(8, 8)));
    }

    @Test
    void isChecked() {
        Player whitePlayer = new Player();
        Player blackPlayer = new Player();
        whitePlayer.isWhite = true;
        board.addPiece(new King(whitePlayer), 4, 5);
        board.addPiece(new King(blackPlayer), 4, 7);
        board.addPiece(new Rook(whitePlayer), 1, 7);
        assertTrue(board.isChecked(blackPlayer));
    }

    @Test
    @DisplayName("isCheckOrStaleMated() (checkmate)")
    void isCheckmated() {
        Player whitePlayer = new Player();
        Player blackPlayer = new Player();
        whitePlayer.isWhite = true;
        board.addPiece(new King(whitePlayer), 4, 5);
        board.addPiece(new King(blackPlayer), 4, 7);
        board.addPiece(new Rook(whitePlayer), 1, 7);
        assertTrue(board.isCheckOrStaleMated(blackPlayer));
        assertTrue(board.isChecked(blackPlayer));
    }

    @Test
    @DisplayName("isCheckOrStaleMated() (stalemate)")
    void isStalemated() {
        Player whitePlayer = new Player();
        Player blackPlayer = new Player();
        whitePlayer.isWhite = true;
        board.addPiece(new King(whitePlayer), 6, 5);
        board.addPiece(new King(blackPlayer), 7, 7);
        board.addPiece(new Queen(whitePlayer), 5, 6);

        assertTrue(board.isCheckOrStaleMated(blackPlayer));
        assertFalse(board.isChecked(blackPlayer));
    }

    @Test
    @DisplayName("isCheckOrStaleMated() (neither)")
    void isNeitherCheckOrStaleMated() {
        Player whitePlayer = new Player();
        Player blackPlayer = new Player();
        whitePlayer.isWhite = true;
        board.addPiece(new King(whitePlayer), 6, 5);
        board.addPiece(new King(blackPlayer), 7, 7);

        assertFalse(board.isCheckOrStaleMated(blackPlayer));
        assertFalse(board.isChecked(blackPlayer));
    }


    @Test
    @DisplayName("toString()")
    void toStringTest() {
        Piece queen = new Queen(player);
        // highlighting won't affect block content
        board.addPiece(queen, 3, 5);
        String chessBoard = "8                \n" +
                "7                \n" +
                "6                \n" +
                "5                \n" +
                "4           ♛    \n" +
                "3                \n" +
                "2                \n" +
                "1                \n" +
                "  a b c d e f g h";
        assertEquals(chessBoard,
                board.toString());

    }

    @Test
    @DisplayName("toString() (colored)")
    void toStringTestColored() {
        Piece queen = new Queen(player);
        // highlighting won't affect block content
        board.addPiece(queen, 3, 5);
        Set<Position> positions = queen.getAvailablePosition(false);
        // the ANSI codes are for coloring
        String chessBoard = "8   \u001B[44m\u001B[1m \u001B[0m       \u001B[44m\u001B[1m \u001B[0m    \n" +
                "7     \u001B[44m\u001B[1m \u001B[0m     \u001B[44m\u001B[1m \u001B[0m    \n" +
                "6       \u001B[44m\u001B[1m \u001B[0m   \u001B[44m\u001B[1m \u001B[0m   \u001B[44m\u001B[1m \u001B[0m\n" +
                "5         \u001B[44m\u001B[1m \u001B[0m \u001B[44m\u001B[1m \u001B[0m \u001B[44m\u001B[1m \u001B[0m  \n" +
                "4 \u001B[44m\u001B[1m \u001B[0m \u001B[44m\u001B[1m \u001B[0m \u001B[44m\u001B[1m \u001B[0m \u001B[44m\u001B[1m \u001B[0m \u001B[44m\u001B[1m \u001B[0m ♛ \u001B[44m\u001B[1m \u001B[0m \u001B[44m\u001B[1m \u001B[0m\n" +
                "3         \u001B[44m\u001B[1m \u001B[0m \u001B[44m\u001B[1m \u001B[0m \u001B[44m\u001B[1m \u001B[0m  \n" +
                "2       \u001B[44m\u001B[1m \u001B[0m   \u001B[44m\u001B[1m \u001B[0m   \u001B[44m\u001B[1m \u001B[0m\n" +
                "1     \u001B[44m\u001B[1m \u001B[0m     \u001B[44m\u001B[1m \u001B[0m    \n" +
                "  a b c d e f g h";
        assertEquals(chessBoard,
                board.toString(positions));

    }

    @Test
    @DisplayName("init() (with special pieces)")
    void init() {
        Player wPlayer = new Player("", true);
        Player bPlayer = player;
        board.init(wPlayer, bPlayer);

        assertTrue(board.getPiece(0, 0) instanceof Rook);
        assertTrue(board.getPiece(board.HEIGHT - 1, 0) instanceof Rook);
        assertTrue(board.getPiece(board.HEIGHT - 3, 0) instanceof Artillery);
        assertEquals(board.getPiece(1, 1).owner, wPlayer);
        assertNull(board.getPiece(board.HEIGHT - 3, 1));
        assertNull(board.getPiece(2, 1));
    }

    @Test
    @DisplayName("init() (no special pieces)")
    void initSpecial() {
        Player wPlayer = new Player("", true);
        Player bPlayer = player;
        board.init(wPlayer, bPlayer, false);

        assertTrue(board.getPiece(0, 1) instanceof Knight);
        assertTrue(board.getPiece(board.HEIGHT - 1, 0) instanceof Rook);
        assertNull(board.getPiece(board.HEIGHT - 3, 0));
        assertEquals(board.getPiece(1, 1).owner, wPlayer);
        assertNull(board.getPiece(board.HEIGHT - 3, 1));
        assertNull(board.getPiece(2, 1));
    }

    @Test
    void undo() {
        Queen queen = new Queen(player);
        Pawn pawn = new Pawn(player);
        board.addPiece(pawn, 1, 1);
        board.addPiece(queen, 0, 0);
        board.movePiece(queen, 1, 1);
        assertEquals(board.getPiece(1, 1), queen);

        board.undo();

        assertEquals(board.getPiece(1, 1), pawn);
        assertEquals(board.getPiece(0, 0), queen);
    }

    @Test
    void redo() {
        Queen queen = new Queen(player);
        Pawn pawn = new Pawn(player);
        board.addPiece(pawn, 1, 1);
        board.addPiece(queen, 0, 0);
        board.movePiece(queen, 1, 1);
        assertEquals(board.getPiece(1, 1), queen);

        board.undo();
        board.redo();

        assertEquals(board.getPiece(1, 1), queen);
    }

    @Test
    void undoRedoEmpty() {
        Queen queen = new Queen(player);
        board.addPiece(queen, 1, 1);
        assertEquals(board.getPiece(1, 1), queen);
        board.undo();
        assertEquals(board.getPiece(1, 1), queen);
        board.redo();
        assertEquals(board.getPiece(1, 1), queen);
    }

}