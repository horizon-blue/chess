package view;

import javax.swing.*;
import java.awt.*;

/**
 * The viewer component of the Board class
 */
public class Board extends JPanel {
    private final model.Board board;
    private final Space[][] spaces;

    public Board(model.Board board) {
        setPreferredSize(new Dimension(1000, 500));
        // set layout to null to allow absolute positioning
        setLayout(null);

        this.board = board;
        spaces = new Space[board.WIDTH][board.HEIGHT];

        // odd spaces have white color, even spaces have black color
        for (int row = 0; row < board.HEIGHT; ++row) {
            for (int col = 0; col < board.WIDTH; ++col) {
                Space space = new Space((row + col) % 2 == 1);
                spaces[row][col] = space;
                space.setLocation(50 * row, 50 * col);
                add(space);
            }
        }
    }
}
