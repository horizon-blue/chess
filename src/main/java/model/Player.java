package model;

public class Player {
    private String name;

    public Player() {
        this.name = "foo";
    }

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
