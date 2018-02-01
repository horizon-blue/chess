package model.piece;

public abstract class Piece {
    private String name;

    public Piece(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
