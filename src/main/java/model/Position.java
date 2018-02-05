package model;

public class Position implements Comparable<Position> {
    private static final int MAX_POS = 512;

    public int row;
    public int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Position))
            return false;
        if (obj == this)
            return true;
        Position other = (Position) obj;
        return other.row == row && other.col == col;
    }

    @Override
    public int compareTo(Position other) {
        if (row == other.row)
            return col - other.col;
        else
            return row - other.row;
    }

    @Override
    public int hashCode() {
        return MAX_POS * row + col;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
