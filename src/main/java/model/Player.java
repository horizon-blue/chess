package model;

import com.github.javafaker.Faker;
import controller.Game;
import controller.Session;

public class Player {
    public String name;
    public int score;
    public Session session;

    public Player() {
        this(new Faker().name().firstName());
    }

    public Player(String name) {
        this.name = name;
        score = 0;
    }

    public boolean isWhite() {
        return session != null && session.isWhite(this);
    }


    @Override
    public String toString() {
        return name;
    }
}
