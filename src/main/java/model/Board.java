package model;

import model.piece.*;
import utils.printUtils;


import java.util.HashSet;
import java.util.Set;

public class Board {
    public final int HEIGHT;
    public final int WIDTH;

    private final Space[][] board;
    private Set<Piece> blackPieces = new HashSet<>();
    private Set<Piece> whitePieces = new HashSet<>();

    public Board() {
        this(8, 8);
    }

    public Board(int maxRow, int maxCol) {
        HEIGHT = maxRow;
        WIDTH = maxCol;
        board = new Space[HEIGHT][WIDTH];
        // initialize spaces
        for (int row = 0; row < HEIGHT; ++row)
            for (int col = 0; col < WIDTH; ++col)
                board[row][col] = new Space();

    }

    public void setPiece(Piece piece, int row, int col) {
        if (isValid(row, col)) {
            if (piece != null) {
                piece.board = this;
                piece.setPos(row, col);
                if (piece.isWhite())
                    whitePieces.add(piece);
                else
                    blackPieces.add(piece);
            }
            board[row][col].setPiece(piece);
        }
    }

    public void setPiece(Piece piece, Position pos) {
        setPiece(piece, pos.row, pos.col);
    }

    public void clearPiece(int row, int col) {
        if (isValid(row, col)) {
            Piece oldPiece = getPiece(row, col);
            if (oldPiece != null) {
                if (oldPiece.isWhite())
                    whitePieces.remove(oldPiece);
                else
                    blackPieces.remove(oldPiece);
            }
            board[row][col].clearPiece();
        }
    }

    public void clearPiece(Position pos) {
        clearPiece(pos.row, pos.col);
    }

    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        Piece selected = getPiece(fromRow, fromCol);
        clearPiece(fromRow, fromCol);
        clearPiece(toRow, toCol);
        setPiece(selected, toRow, toCol);
    }

    public void movePiece(Position from, Position to) {
        movePiece(from.row, from.col, to.row, to.col);
    }

    public void movePiece(Piece piece, int row, int col) {
        if (piece.board != this)
            return;
        movePiece(piece.x, piece.y, row, col);
    }

    public void movePiece(Piece piece, Position pos) {
        movePiece(piece, pos.row, pos.col);
    }

    public Piece getPiece(int row, int col) {
        if (isValid(row, col))
            return board[row][col].getPiece();
        else
            return null;
    }

    public Piece getPiece(Position pos) {
        return getPiece(pos.row, pos.col);
    }

    public boolean isValid(int row, int col) {
        return row >= 0 && col >= 0 && row < HEIGHT && col < WIDTH;
    }

    public boolean isValid(Position pos) {
        return isValid(pos.row, pos.col);
    }

    public boolean isOccupied(int row, int col) {
        return isValid(row, col) && board[row][col].isOccupied();
    }

    public boolean isOccupied(Position pos) {
        return isOccupied(pos.row, pos.col);
    }


    /**
     * helper function to check whether a piece can be move to a given position
     * this method does NOT check whether the movement obey the piece's rule
     *
     * @param piece the piece that is in action
     * @param row   row position on the board
     * @param col   column position on the board
     * @return true if the movement is valid, false otherwise
     */
    public boolean isValidMovement(Piece piece, int row, int col) {
        // if there is a piece, then it should be in a different color
        return isValid(row, col)
                && (!isOccupied(row, col) || !piece.sameColor(getPiece(row, col)));
    }

    /**
     * Check whether moving the piece to the designated position will result its own king to be
     * in a checked state (i.e. king is in danger)
     * this method does NOT check whether the movement obey the piece's rule
     *
     * @param piece the piece that is in action
     * @param row   the row position to move
     * @param col   the column position to move
     * @return true if moving the piece to the designated position will result in a checked state, false otherwise
     */
    public boolean willBeChecked(Piece piece, int row, int col) {
        if (!isValid(row, col))
            return false;

        // temporarily move piece to new location
        Piece oldPiece = getPiece(row, col);
        int oldRow = piece.x;
        int oldCol = piece.y;
        movePiece(piece, row, col);

        boolean result = isChecked(piece.owner);

        // reset to previous state
        movePiece(piece, oldRow, oldCol);
        setPiece(oldPiece, row, col);

        return result;
    }

    public boolean isChecked(Player owner) {
        if (owner.king == null)
            return false;
        Position kingPos = new Position(owner.king.x, owner.king.y);
        Set<Piece> enemyPieces = owner.isWhite() ? blackPieces : whitePieces;
        for (Piece piece : enemyPieces) {
            if (piece.getAvailablePosition(owner.isWhite()).contains(kingPos))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return toString(null);
    }

    /**
     * Format the chess board into a string with positions highlighted
     *
     * @param positions positions to be highlighted
     * @return a string notation of the chessboard
     */
    public String toString(Set<Position> positions) {
        StringBuilder boardString = new StringBuilder();
        for (int row = HEIGHT; row > 0; --row) {
            // print row number
            boardString.append(row);
            // print each space
            for (int col = 0; col < WIDTH; ++col) {
                if (positions != null && positions.contains(new Position(row - 1, col)))
                    boardString.append(" " +
                            printUtils.ANSI_BLUE_BG +
                            printUtils.ANSI_BOLD +
                            board[row - 1][col] +
                            printUtils.ANSI_RESET
                    );
                else
                    boardString.append(" " + board[row - 1][col]);
            }
            boardString.append('\n');
        }
        // print column number (using alphabet)
        boardString.append(" ");
        for (int col = 0; col < WIDTH; ++col) {
            boardString.append(" " + (char) (col + 'a'));
        }
        return boardString.toString();
    }

    /**
     * A inner class to store pieces
     */
    private class Space {
        private Piece piece;

        public Piece getPiece() {
            return piece;
        }

        public void setPiece(Piece piece) {
            this.piece = piece;
        }

        public boolean isOccupied() {
            return piece != null;
        }

        public void clearPiece() {
            piece = null;
        }

        @Override
        public String toString() {
            return piece == null ? " " : piece.toString();
        }
    }
}
