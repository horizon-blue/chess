package view;

import model.Position;

import javax.swing.*;


/**
 * A position on board that holds a piece
 */
public class SpaceView extends ImageButton {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    public final Position position;
    public PieceView piece;
    /**
     * Associate space with image. Receives a parameter that decides the space's
     * color
     *
     * @param position position of current space
     */
    public SpaceView(Position position) {
        // odd position has white color, even position has black color
        super(new ImageIcon(model.Board.class.getResource("/pieces/Board_"
                + ((position.row + position.col) % 2 == 1 ? "w" : "b")
                + ".png")).getImage(), WIDTH, HEIGHT);
        this.position = position;
        updateLocation(position.row, position.col, 0, 0);
        setSelectedIcon(new ImageIcon(model.Board.class.getResource("/pieces/Board_"
                + ((position.row + position.col) % 2 == 1 ? "w" : "b") + "_selected"
                + ".png")).getImage());
    }

}
