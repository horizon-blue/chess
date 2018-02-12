package view;

import model.Position;

import javax.swing.*;
import java.awt.*;

/**
 * The viewer component of the Board class
 */
public class Board extends JPanel {
    private final model.Board board;
    private final Space[][] spaces;

    /**
     * Create a Board viewer to display the content of the board
     *
     * @param board the board to display
     */
    public Board(model.Board board) {
        setPreferredSize(new Dimension(500, 500));
        // set layout to null to allow absolute positioning
        setLayout(null);

        this.board = board;
        spaces = new Space[board.HEIGHT][board.WIDTH];

        // odd spaces have white color, even spaces have black color
        // reverse drawing oder so that the board can display correctly
        for (int row = 0; row < board.HEIGHT; ++row) {
            for (int col = board.WIDTH - 1; col >= 0; --col) {
                Space space = new Space(new Position(row, col));
                spaces[row][col] = space;
                if (board.isOccupied(row, col)) {
                    Piece piece = new Piece(board.getPiece(row, col));
                    space.piece = piece;
                    add(piece);
                }
                add(space);
            }
        }
    }
}
