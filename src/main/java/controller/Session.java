package controller;

import model.Board;
import model.Player;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class Session {
    private final int PLAYER_COUNT = 2;

    private Board board;
    private final Player[] players;

    public Session(Player... players) {
        if (players.length == PLAYER_COUNT) {
            this.players = players;
        } else {
            throw new IllegalArgumentException("Expected " + PLAYER_COUNT
                    + " players, got " + players.length + ".");
        }
    }

    public void 

    @Override
    public String toString() {
        return StringUtils.join(players, ", ");
    }
}
