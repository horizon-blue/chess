package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The Initial Window used to create users and board size
 */
public class InitialWindow extends JFrame {
    /**
     * The form to ask for user input
     */
    private JPanel form;
    private JTextField whitePlayerName;
    private JTextField blackPlayerName;
    private JSpinner boardWidth;
    private JSpinner boardHeight;
    private JButton submit;

    /**
     * Creates the new initial window
     */
    public InitialWindow() {
        super("New Session");
        setSize(400, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setVisible(true);
        // center the screen
        setLocationRelativeTo(null);

        initForm();
        add(form, BorderLayout.NORTH);

        submit = new JButton("Launch");
        add(submit, BorderLayout.SOUTH);
    }

    /**
     * Initialize the form to get user input
     */
    private void initForm() {
        form = new JPanel();
        form.setBorder(new EmptyBorder(10, 10, 10, 10));
        form.setLayout(new GridLayout(4, 2));
        whitePlayerName = new JTextField();
        blackPlayerName = new JTextField();
        boardWidth = new JSpinner();
        boardWidth.setValue(8);
        boardHeight = new JSpinner();
        boardHeight.setValue(8);

        form.add(new JLabel("White Player: "));
        form.add(whitePlayerName);
        form.add(new JLabel("Black Player: "));
        form.add(blackPlayerName);
        form.add(new JLabel("Border Width: "));
        form.add(boardWidth);
        form.add(new JLabel("Border Height: "));
        form.add(boardHeight);
    }

    /**
     * Method to allow controller set onSubmit trigger
     *
     * @param e the listener to be added
     */
    public void onSubmit(ActionListener e) {
        submit.addActionListener(e);
    }

    /**
     * Get the name of white player in the form
     *
     * @return the name of white player
     */
    public String getWhitePlayerName() {
        return whitePlayerName.getText();
    }

    /**
     * Get the name of black player in the form
     *
     * @return the name of black player
     */
    public String getBlackPlayerName() {
        return blackPlayerName.getText();
    }

    /**
     * Get the width of the board in the form
     *
     * @return the width of the board
     */
    public int getBoardWidth() {
        return (int) boardWidth.getValue();
    }

    /**
     * Get the height of the board in the form
     *
     * @return the height of the board
     */
    public int getBoardHeight() {
        return (int) boardHeight.getValue();
    }

}
