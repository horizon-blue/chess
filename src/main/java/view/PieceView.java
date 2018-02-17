package view;

import model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The viewer class for pieces
 */
public class PieceView extends ImageLabel {
    private static final int WIDTH = 75;
    private static final int HEIGHT = 75;

    private final model.piece.Piece piece;

    /**
     * Return a viewer for piece
     *
     * @param piece the piece to display
     */
    public PieceView(model.piece.Piece piece) {
        // select the piece's image based on its classname
        super(new ImageIcon(model.Board.class.getResource("/pieces/"
                + piece.getClass().getSimpleName() + "_"
                + (piece.isWhite() ? "w" : "b")
                + ".png")), WIDTH, HEIGHT);
        this.piece = piece;
        updateLocation(piece.x, piece.y, 15, -26);
        // Make pieces appears clickable
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addClickListener();
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
