package controller;

import model.Board;
import model.Player;
import model.Position;
import model.piece.*;

import java.util.Scanner;
import java.util.Set;


public class Game {
    public Board board = new Board();
    private boolean isWhiteRound = true;
    public Player whitePlayer;
    public Player blackPlayer;

    public Game(Player whitePlayer, Player blackPlayer) {
        whitePlayer.isWhite = true;
        blackPlayer.isWhite = false;
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
        boolean madeMovement = false;
        do {
            // prompt player to select pieces
            Position sourcePos = getPlayerSelection();
            if (sourcePos == null) {    // player resign
                System.out.println(currentPlayer + " resigned.");
                return isWhiteRound ? Status.BLACK_WIN : Status.WHITE_WIN;
            }
            Piece selectPiece = board.getPiece(sourcePos);
            Set<Position> validMovement = selectPiece.getAvailablePosition(isWhiteRound);
            if (validMovement == null || validMovement.size() == 0)
                continue;
            Position targetPos = getPlayerSelection(validMovement);
            if (targetPos == null) // cancel selection
                continue;
            board.movePiece(sourcePos, targetPos);
            madeMovement = true;
        } while (!madeMovement);

        // checking checkmate and stalemate status
        Player otherPlayer = isWhiteRound ? blackPlayer : whitePlayer;
        if (board.isCheckOrStaleMated(otherPlayer)) {
            System.out.println(board);
            if (board.isChecked(otherPlayer)) { // is checkmate
                System.out.println(currentPlayer + " checkmate.");
                return isWhiteRound ? Status.WHITE_WIN : Status.BLACK_WIN;
            }
            System.out.println("Stalemate.");
            return Status.DRAW;
        }
        isWhiteRound = !isWhiteRound;
        return Status.CONTINUE;
    }

    /**
     * Get user selection via I/O (might upgrade to GUI in later version)
     * The returning position is guaranteed to be valid (or null, if user
     * cancel the input)
     *
     * @return the position that the user select or null
     */
    public Position getPlayerSelection() {
        return getPlayerSelection(null);
    }

    /**
     * Same as getPlayerSelection(), but highlight the given positions
     * If highlighted position is not null, then the returned position must
     * be one of them
     *
     * @param highlightPos positions to be highlighted
     * @return the position that the user select or null
     */
    public Position getPlayerSelection(Set<Position> highlightPos) {
        Player currentPlayer = isWhiteRound ? whitePlayer : blackPlayer;
        Scanner reader = new Scanner(System.in);
        System.out.println("Black pieces: " + blackPlayer);
        System.out.println(board.toString(highlightPos));
        System.out.println("White pieces: " + whitePlayer);
        System.out.println("Current round: " + currentPlayer);
        System.out.printf("Please select a position (press q to cancel): ");

        // keep asking for user input until one valid input is received
        while (true) {
            String input = reader.nextLine();
            // check if the selection is valid string
            if (input.length() == 2 &&
                    Character.isDigit(input.charAt(0)) &&
                    Character.isLetter(input.charAt(1))) {
                Position selected = new Position(input.charAt(0) - '1',
                        Character.toLowerCase(input.charAt(1)) - 'a');

                // check if selected position is one of the highlighted
                if (highlightPos != null && highlightPos.contains(selected)) {
                    System.out.println(); // for formatting
                    return selected;
                }
                // check if the selection is valid under the game setting
                else if (board.isValid(selected) &&
                        board.isOccupied(selected) &&
                        board.getPiece(selected).owner == currentPlayer) {
                    System.out.println(); // for formatting
                    return selected;
                }
            } else if (input.length() == 1 && Character.toLowerCase(input.charAt(0)) == 'q')
                return null;
            System.out.printf("Invalid position, please select again: ");
        }
    }

    public boolean isWhite(Player player) {
        return player == whitePlayer;
    }

    /**
     * initialize game board
     */
    public void initBoard() {
        // white pieces
        board.addPiece(new Rook(whitePlayer), 0, 0);
        board.addPiece(new Knight(whitePlayer), 0, 1);
        board.addPiece(new Bishop(whitePlayer), 0, 2);
        board.addPiece(new Queen(whitePlayer), 0, 3);
        board.addPiece(new King(whitePlayer), 0, 4);
        board.addPiece(new Bishop(whitePlayer), 0, 5);
        board.addPiece(new Knight(whitePlayer), 0, 6);
        board.addPiece(new Rook(whitePlayer), 0, 7);
        // pawns
        for (int col = 0; col < 8; ++col)
            board.addPiece(new Pawn(whitePlayer), 1, col);
        // fairy piece
        board.addPiece(new Unicorn(whitePlayer), 2, 0);
        board.addPiece(new Artillery(whitePlayer), 2, 7);

        // black pieces
        board.addPiece(new Rook(blackPlayer), board.HEIGHT - 1, 0);
        board.addPiece(new Knight(blackPlayer), board.HEIGHT - 1, 1);
        board.addPiece(new Bishop(blackPlayer), board.HEIGHT - 1, 2);
        board.addPiece(new Queen(blackPlayer), board.HEIGHT - 1, 3);
        board.addPiece(new King(blackPlayer), board.HEIGHT - 1, 4);
        board.addPiece(new Bishop(blackPlayer), board.HEIGHT - 1, 5);
        board.addPiece(new Knight(blackPlayer), board.HEIGHT - 1, 6);
        board.addPiece(new Rook(blackPlayer), board.HEIGHT - 1, 7);
        // pawns
        for (int col = 0; col < 8; ++col)
            board.addPiece(new Pawn(blackPlayer), board.HEIGHT - 2, col);
        board.addPiece(new Unicorn(blackPlayer), board.HEIGHT - 3, 7);
        board.addPiece(new Artillery(blackPlayer), board.HEIGHT - 3, 0);
    }

    public static enum Status {
        BLACK_WIN,
        WHITE_WIN,
        DRAW,
        CONTINUE
    }
}
