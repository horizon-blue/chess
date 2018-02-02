package model;

import model.piece.Piece;

public class Board {
    public final int HEIGHT;
    public final int WIDTH;

    private final Piece[][] board;

    public Board() {
        this(8, 8);
    }

    public Board(int maxRow, int maxCol) {
        if (maxRow < 4 || maxCol < 8)
            throw new IllegalArgumentException("Board size must be at least 4 x 8.");
        this.HEIGHT = maxRow;
        this.WIDTH = maxCol;
        board = new Piece[this.HEIGHT][this.WIDTH];
    }

    public void setPiece(Piece piece, int row, int col) {
        if (isValid(row, col)) {
            board[row][col] = piece;
            if (piece != null) {
                piece.board = this;
                piece.x = row;
                piece.y = col;
            }
        }
    }

    public void clearPiece(int row, int col) {
        setPiece(null, row, col);
    }

    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        Piece selected = getPiece(fromRow, fromCol);
        clearPiece(fromRow, fromCol);
        setPiece(selected, toRow, toCol);
    }

    public Piece getPiece(int row, int col) {
        if (isValid(row, col))
            return board[row][col];
        else
            return null;
    }

    public boolean isValid(int row, int col) {
        return row >= 0 && col >= 0 && row < HEIGHT && col < WIDTH;
    }

    public boolean isOccupied(int row, int col) {
        return isValid(row, col) && board[row][col] != null;
    }

    public void printBoard() {
        for (Piece[] row : board) {
            for (Piece entry : row) {
                if (entry != null)
                    System.out.printf(entry + " ");
                else
                    System.out.printf("  ");
            }
            System.out.println();
        }
    }
}
