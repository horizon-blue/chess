package model;

import com.github.javafaker.Faker;
import controller.Session;
import model.piece.King;

public class Player {
    public String name;
    public int score;
    public Session session;
    public King king;


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

    public boolean isInRound() {
        return session != null && isWhite() == session.isWhiteRound();
    }


    @Override
    public String toString() {
        return name;
    }
}
