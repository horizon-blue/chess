package view;

import model.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatusBar extends JPanel {
    public JLabel status = new JLabel();
    private JLabel round;
    private JLabel blackScore;
    private JLabel whiteScore;

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
        setBorder(new EmptyBorder(10, 20, 0, 20));
        Dimension d = getPreferredSize();
        d.height = HEIGHT;
        setSize(d);

        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;

        add(new JLabel("White: " + whitePlayer));
        add(status);

        add(new JLabel("Black: " + blackPlayer));
        whiteScore = new JLabel("Score: " + whitePlayer.score);
        add(whiteScore);
        round = new JLabel("Round: " + whitePlayer);
        add(round);
        blackScore = new JLabel("Score: " + blackPlayer.score);
        add(blackScore);
    }

    /**
     * Set the status bar to display the specific round
     *
     * @param isWhiteRound whether current round is white pieces'
     */
    public void setRound(boolean isWhiteRound) {
        this.isWhiteRound = isWhiteRound;
        round.setText("Round: " + (isWhiteRound ? whitePlayer : blackPlayer));
    }

    /**
     * Update the score
     */
    public void updateScore() {
        whiteScore.setText("Score: " + whitePlayer.score);
        blackScore.setText("Score: " + blackPlayer.score);
    }

    /**
     * Switch to the next round
     */
    public void switchRound() {
        setRound(!isWhiteRound);
    }

    /**
     * Set the status to given text
     *
     * @param text the status to change
     */
    public void setStatus(String text) {
        status.setText(text);
    }

    /**
     * Clear the current status
     */
    public void clearStatus() {
        setStatus("");
    }

}
