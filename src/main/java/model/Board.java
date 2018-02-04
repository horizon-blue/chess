package model;

import model.piece.Piece;

public class Board {
    public final int HEIGHT;
    public final int WIDTH;

    private final Space[][] board;

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
                piece.x = row;
                piece.y = col;
            }
            board[row][col].setPiece(piece);
        }
    }

    public void setPiece(Piece piece, Position pos) {
        setPiece(piece, pos.row, pos.col);
    }

    public void clearPiece(int row, int col) {
        if (isValid(row, col))
            board[row][col].clearPiece();
    }

    public void clearPiece(Position pos) {
        clearPiece(pos.row, pos.col);
    }

    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        Piece selected = getPiece(fromRow, fromCol);
        clearPiece(fromRow, fromCol);
        setPiece(selected, toRow, toCol);
    }

    public void movePiece(Position from, Position to) {
        movePiece(from.row, from.col, to.row, to.col);
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

    public boolean isOccupied(int row, int col) {
        return isValid(row, col) && board[row][col].isOccupied();
    }

    public boolean isOccupied(Position pos) {
        return isOccupied(pos.row, pos.col)
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        for (int row = HEIGHT; row > 0; --row) {
            // print row number
            boardString.append(row);
            // print each space
            for (Space space : board[row - 1]) {
                boardString.append(' ');
                boardString.append(space);
            }
            boardString.append('\n');
        }
        // print column number (using alphabet)
        boardString.append(' ');
        for (int col = 0; col < WIDTH; ++col) {
            boardString.append(' ');
            boardString.append((char) (col + 'a'));
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
