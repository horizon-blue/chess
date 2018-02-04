package controller;

import model.Board;
import model.Player;
import model.piece.*;

public class Game {
    private Board board = new Board();
    private boolean isWhiteRound = true;
    private Player whitePlayer;
    private Player blackPlayer;

    public Game(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        board = new Board();
    }

    /**
     * initialize game board
     */
    public void initBoard() {
        // white pieces
        board.setPiece(new Rook(whitePlayer), 0, 0);
        board.setPiece(new Knight(whitePlayer), 0, 1);
        board.setPiece(new Bishop(whitePlayer), 0, 2);
        board.setPiece(new Queen(whitePlayer), 0, 3);
        board.setPiece(new King(whitePlayer), 0, 4);
        board.setPiece(new Bishop(whitePlayer), 0, 5);
        board.setPiece(new Knight(whitePlayer), 0, 6);
        board.setPiece(new Rook(whitePlayer), 0, 7);

        // black pieces
        board.setPiece(new Rook(blackPlayer), board.HEIGHT - 1, 0);
        board.setPiece(new Knight(blackPlayer), board.HEIGHT - 1, 1);
        board.setPiece(new Bishop(blackPlayer), board.HEIGHT - 1, 2);
        board.setPiece(new Queen(blackPlayer), board.HEIGHT - 1, 3);
        board.setPiece(new King(blackPlayer), board.HEIGHT - 1, 4);
        board.setPiece(new Bishop(blackPlayer), board.HEIGHT - 1, 5);
        board.setPiece(new Knight(blackPlayer), board.HEIGHT - 1, 6);
        board.setPiece(new Rook(blackPlayer), board.HEIGHT - 1, 7);

        // pawns
        for (int col = 0; col < 8; ++col) {
            board.setPiece(new Pawn(whitePlayer), 1, col);
            board.setPiece(new Pawn(blackPlayer), board.HEIGHT - 2, col);
        }

        System.out.println(board); // debug
    }

    public void nextRound() {
        isWhiteRound = !isWhiteRound;
        Player currentPlayer = isWhiteRound ? whitePlayer : blackPlayer;
    }
}
