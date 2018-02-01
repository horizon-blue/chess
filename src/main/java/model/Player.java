package model;

import com.github.javafaker.Faker;

public class Player {
    private String name;
    private Color color;

    public Player(Color color) {
        this(new Faker().name().firstName(), color);
    }

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
