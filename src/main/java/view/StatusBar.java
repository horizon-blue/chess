package view;

import model.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatusBar extends JPanel {
    public JLabel status = new JLabel();
    private JLabel round;

    private Player whitePlayer;
    private Player blackPlayer;
    boolean isWhiteRound = true;


    /**
     * Set the players to be displayed at the status bar
     *
     * @param whitePlayer
     * @param blackPlayer
     */
    StatusBar(Player whitePlayer, Player blackPlayer) {
        setLayout(new GridLayout(2, 3));
        setBorder(new EmptyBorder(0, 10, 0, 10));
        Dimension d = getPreferredSize();
        d.height = HEIGHT;
        setSize(d);

        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;

        add(new JLabel("White: " + whitePlayer.name));
        add(status);
        add(new JLabel("Black: " + blackPlayer.name));
        add(new JLabel("Score: " + whitePlayer.score));
        round = new JLabel("Round: " + whitePlayer.name);
        add(round);
        add(new JLabel("Score: " + blackPlayer.score));
    }

    /**
     * Set the status bar to display the specific round
     *
     * @param isWhiteRound whether current round is white pieces'
     */
    public void setRound(boolean isWhiteRound) {
        this.isWhiteRound = isWhiteRound;
        round.setText("Round: " + (isWhiteRound ? whitePlayer.name : blackPlayer.name));
    }

    /**
     * Switch to the next round
     */
    public void switchRound() {
        setRound(!isWhiteRound);
    }

}
