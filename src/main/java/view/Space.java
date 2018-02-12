package view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * A position on board that holds a piece
 */
public class Space extends ImageLabel {
    /**
     * Associate space with image. Receives a parameter that decides the space's
     * color
     *
     * @param isWhite whether the space is white
     */
    public Space(boolean isWhite) {
        super(new ImageIcon(model.Board.class.getResource("/pieces/Board_"
                + (isWhite ? "w" : "b") + ".png")), 100, 100);
    }
}
