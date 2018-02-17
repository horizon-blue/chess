package view;

import model.Position;

import javax.swing.*;
import java.awt.*;

/**
 * The viewer component of the BoardView class
 */
public class BoardView extends JPanel {
    private final model.Board board;
    private final SpaceView[][] spaces;

    /**
     * Create a BoardView viewer to display the content of the board
     *
     * @param board the board to display
     */
    public BoardView(model.Board board) {
        setPreferredSize(new Dimension(500, 500));
        // set layout to null to allow absolute positioning
        setLayout(null);

        this.board = board;
        spaces = new SpaceView[board.HEIGHT][board.WIDTH];

        // odd spaces have white color, even spaces have black color
        // reverse drawing oder so that the board can display correctly
        for (int row = 0; row < board.HEIGHT; ++row) {
            for (int col = board.WIDTH - 1; col >= 0; --col) {
                SpaceView space = new SpaceView(new Position(row, col));
                spaces[row][col] = space;
                if (board.isOccupied(row, col)) {
                    PieceView piece = new PieceView(board.getPiece(row, col));
                    space.piece = piece;
                    add(piece);
                }
                add(space);
            }
        }
    }
}
