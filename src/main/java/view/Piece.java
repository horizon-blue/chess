package view;

import javax.swing.*;

/**
 * The viewer class for pieces
 */
public class Piece extends ImageLabel {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    private final model.piece.Piece piece;

    public Piece(model.piece.Piece piece) {
        super(new ImageIcon(model.Board.class.getResource("/pieces/"
                + piece.getClass().getSimpleName() + "_"
                + (piece.isWhite() ? "w" : "b")
                + ".png")), WIDTH, HEIGHT);
        this.piece = piece;
        updateLocation();
    }

    /**
     * Calculate corresponding location on screen and move to the location
     */
    private void updateLocation() {
        // these number are used to aligned the pixels of the image
        setLocation(4 + 55 * piece.x + 37 * piece.y, 38 * piece.y - 14 * piece.x - 46);
    }
}
