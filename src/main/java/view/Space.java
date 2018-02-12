package view;

import javax.swing.*;
import java.awt.*;

/**
 * A position on board that holds a piece
 */
public class Space extends ImagePanel {
    /**
     * Associate space with image. Receives a parameter that decides the space's
     * color
     *
     * @param isWhite whether the space is white
     */
    public Space(boolean isWhite) {
        super(new ImageIcon(model.Board.class.getResource("/pieces/Board_"
                + (isWhite ? "w" : "b") + ".png")).getImage());
        setLayout(null);
    }
}
