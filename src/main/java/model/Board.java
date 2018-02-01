package model;

import model.piece.Piece;

public class Board {
    private final int HEIGHT;
    private final int WIDTH;

    private final Piece[][] board;

    public Board() {
        this(8, 8);
    }

    public Board(int height, int width) {
        this.HEIGHT = height;
        this.WIDTH = width;
        board = new Piece[this.HEIGHT][this.WIDTH];
    }

    public boolean isValid(int row, int col) {
        return row >= 0 && col >= 0 && row < HEIGHT && col < WIDTH;
    }

    public boolean isOccupied(int row, int col) {
        return isValid(row, col) && board[row][col] != null;
    }


}
