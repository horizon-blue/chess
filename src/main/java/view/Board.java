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

    public Board(model.Board board) {
        setPreferredSize(new Dimension(500, 500));
        // set layout to null to allow absolute positioning
        setLayout(new BorderLayout());

        this.board = board;
        spaces = new Space[board.WIDTH][board.HEIGHT];


        for (int row = 0; row < board.HEIGHT; ++row) {
            for (int col = 0; col < board.WIDTH; ++col) {
                Space space = new Space(new Position(row, col));
                spaces[row][col] = space;
                add(space);
            }
        }
    }
}
