package view;

import model.Position;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * A position on board that holds a piece
 */
public class Space extends JLabel {
    Position position;
    /**
     * Associate space with image. Receives a parameter that decides the space's
     * color
     *
     * @param position position of the space
     */
    public Space(Position position) {
        // odd spaces have white color, even spaces have black color
        boolean isWhite = (position.row + position.col) % 2 == 1;
        this.position = position;
        URL backgroundImage = model.Board.class.getResource("/pieces/Board_"
                + (isWhite ? "w" : "b") + ".png");
        if (backgroundImage != null)
            setIcon(new ImageIcon(backgroundImage));

        setLocation(position.row * 100, position.col * 100);
        SwingUtilities.updateComponentTreeUI(this);
    }
}
