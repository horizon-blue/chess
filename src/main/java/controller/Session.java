package controller;

import model.Player;


public class Session {
    private Player whitePlayer;
    private Player blackPlayer;
    private int gameCount;
    private Game game;


    public Session(Player playerA, Player playerB) {
        gameCount = 0;
        playerA.session = this;
        playerB.session = this;

        // randomly select the player for white piece
        if (Math.random() > 0.5) {
            whitePlayer = playerA;
            blackPlayer = playerB;
        } else {
            whitePlayer = playerB;
            blackPlayer = playerA;
        }
        game = new Game(whitePlayer, blackPlayer);
    }

    public void start() {
        game.initBoard();
    }

    private void swapPlayer() {
        Player prevWhitePlayer = whitePlayer;
        whitePlayer = blackPlayer;
        blackPlayer = prevWhitePlayer;
    }

    public boolean isWhite(Player player) {
        return player == whitePlayer;
    }

    @Override
    public String toString() {
        return whitePlayer.name + blackPlayer.name;
    }
}
