package model;

import com.github.javafaker.Faker;
import model.piece.King;

/**
 * Player - a model for game player
 */
public class Player {
    public String name;
    public int score;
    public King king;
    public boolean isWhite = false;

    /**
     * generate a player with random name
     */
    public Player() {
        this(new Faker().name().firstName());
    }

    /**
     * generate a player with given name
     *
     * @param name name of the player
     */
    public Player(String name) {
        this.name = name;
        score = 0;
    }

    /**
     * convert player to string. this method returns the player's
     * name
     *
     * @return player's name
     */
    @Override
    public String toString() {
        return name;
    }
}
