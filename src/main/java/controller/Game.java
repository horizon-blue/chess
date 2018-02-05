package controller;

import model.Board;
import model.Player;
import model.Position;
import model.piece.*;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {
    Board board = new Board();
    private boolean isWhiteRound = true;
    private Player whitePlayer;
    private Player blackPlayer;

    public Game(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        board = new Board();
    }

    /**
     * Execute one round of the game.
     *
     * @return game status: one of CONTINUE, BLACK_WIN, WHITE_WIN, or DRAW (no player win).
     * The game ends except for CONTINUE state
     */
    public Status nextRound() {
        Player currentPlayer = isWhiteRound ? whitePlayer : blackPlayer;
        // prompt player to select pieces
        Position selected = getPlayerSelection();


        isWhiteRound = !isWhiteRound;
        return Status.DRAW;
    }

    /**
     * Get user selection via I/O (might upgrade to GUI in later version)
     *
     * @return the row + col position that the user select
     */
    public Position getPlayerSelection() {
        return getPlayerSelection(new HashSet<>());
    }

    /**
     * Same as getPlayerSelection(), but highlight the given positions
     *
     * @param highlightPos positions to be highlighted
     * @return the row + col position that the user select
     */
    public Position getPlayerSelection(Set<Position> highlightPos) {
        Scanner reader = new Scanner(System.in);
        System.out.println(board.toString(highlightPos));
        System.out.printf("Please select a position: ");
        Position selectPos;
        while (true) {
            String input = reader.nextLine()；
            // check if the selection is valid string
            if (input.length() == 2 &&
                    Character.isDigit(input.charAt(0)) &&
                    Character.isLetter(input.charAt(1))) {
                int row = input.charAt(0) - '0';
                int col = Character.toLowerCase(input.charAt(0)) - 'a';
                // check if the selection is valid under the game setting
                if (board.isValid(row, col) &&
                        board.isOccupied(row, col) &&
                        isWhiteRound == board.getPiece(row, col).isWhite()) {
                    selectPos = new Position(row, col);
                    break;
                }
            }
            System.out.printf("Invalid position, please select again: ");
        }
        return selectPos;
    }

    public boolean isWhite(Player player) {
        return player == whitePlayer;
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
        for (int col = 0; col < 8; ++col)
            board.setPiece(new Pawn(whitePlayer), 1, col);

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
        for (int col = 0; col < 8; ++col)
            board.setPiece(new Pawn(blackPlayer), board.HEIGHT - 2, col);
    }

    public static enum Status {
        BLACK_WIN,
        WHITE_WIN,
        DRAW,
        CONTINUE
    }
}
