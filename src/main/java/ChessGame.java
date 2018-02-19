import javax.swing.*;

import controller.Game;

public class ChessGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}
