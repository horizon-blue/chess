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
        setPreferredSize(new Dimension(500, 500));
        // set layout to null to allow absolute positioning
        setLayout(null);

        this.board = board;
        spaces = new Space[board.WIDTH][board.HEIGHT];

        // odd spaces have white color, even spaces have black color
        for (int row = 0; row < board.HEIGHT; ++row) {
            for (int col = 0; col < board.WIDTH; ++col) {
                Space space = new Space((row + col) % 2 == 1);
                add(space);
                space.setLocation(row * 100, col * 100);
                spaces[row][col] = space;
            }
        }
    }
}
