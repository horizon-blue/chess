package controller;

import model.Board;
import model.Player;

public class Session {
    private Board board;
    private Player black;
    private Player white;

    public Session() {
        this(new Player(), new Player());
    }

    public Session(Player black, Player white) {
        this.black = black;
        this.white = white;
    }
}
