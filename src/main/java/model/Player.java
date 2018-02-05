package model;

import com.github.javafaker.Faker;
import controller.Session;
import model.piece.King;

public class Player {
    public String name;
    public int score;
    public King king;
    public boolean isWhite = false;


    public Player() {
        this(new Faker().name().firstName());
    }

    public Player(String name) {
        this.name = name;
        score = 0;
    }


    @Override
    public String toString() {
        return name;
    }
}
