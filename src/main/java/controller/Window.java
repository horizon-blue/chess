package controller;

import model.Board;
import model.Player;
import view.InitialWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The entire displaying window
 */
public class Window implements Runnable {
    /**
     * The main frame to display everything
     */
    view.Window window;

    /**
     * Game related information
     */
    public Board board;
    public boolean isWhiteRound = true;
    public Player whitePlayer;
    public Player blackPlayer;

    /**
     * Codes that are used internally for GUI testing
     */
    @Override
    public void run() {
        initGame();
    }

    /**
     * gather important information before game
     */
    private void initGame() {
        InitialWindow initForm = new InitialWindow();
        initForm.onSubmit(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                whitePlayer = new Player(initForm.getWhitePlayerName());
                blackPlayer = new Player(initForm.getBlackPlayerName());
                board = new Board(initForm.getBoardWidth(), initForm.getBoardHeight());
                initForm.dispose();
                window = new view.Window();
            }
        });

    }
}
