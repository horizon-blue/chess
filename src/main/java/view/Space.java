package view;

import model.Position;

import javax.swing.*;

/**
 * A position on board that holds a piece
 */
public class Space extends ImageLabel {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    private final Position position;
    public Piece piece;
    /**
     * Associate space with image. Receives a parameter that decides the space's
     * color
     *
     * @param position position of current space
     */
    public Space(Position position) {
        // odd position has white color, even position has black color
        super(new ImageIcon(model.Board.class.getResource("/pieces/Board_"
                + ((position.row + position.col) % 2 == 1 ? "w" : "b")
                + ".png")), WIDTH, HEIGHT);
        this.position = position;
        updateLocation(position.row, position.col, 0, 0);
    }
}
