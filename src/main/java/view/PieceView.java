package view;

import model.Position;
import model.piece.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The viewer class for pieces
 */
public class PieceView extends ImageButton {
    private static final int WIDTH = 75;
    private static final int HEIGHT = 75;

    private final model.piece.Piece piece;

    /**
     * Return a viewer for piece
     *
     * @param piece the piece to display
     */
    public PieceView(Piece piece) {
        // select the piece's image based on its classname
        super(getIcon(piece, false), getIcon(piece, true), WIDTH, HEIGHT);
        this.piece = piece;
        updateLocation(piece.x, piece.y, 15, -26);
        addClickListener();
    }

    /**
     * A helper function to get the correct image icon for the piece
     *
     * @param piece      the piece to get the icon
     * @param isRollover whether to get the rollover image
     * @return the image loaded for the piece
     */
    static private Image getIcon(Piece piece, boolean isRollover) {
        String filename = "/pieces/"
                + piece.getClass().getSimpleName() + "_"
                + (piece.isWhite() ? "w" : "b")
                + (isRollover ? "_glow" : "")
                + ".png";
        return new ImageIcon(model.Board.class.getResource(filename)).getImage();
    }


    /**
     * Register mouse listener to the piece
     */
    private void addClickListener() {
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicked " + piece.getClass().getSimpleName()
                        + " at " + new Position(piece.x, piece.y) + ".");
            }
        });
    }


}
