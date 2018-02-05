package model;

/**
 * Position - structure that hold row and column information on the board
 */
public class Position implements Comparable<Position> {
    // assume that neither row nor column can be larger than this number
    private static final int MAX_POS = 512;

    public int row;
    public int col;

    /**
     * generate a new position with coordinate (row, col)
     *
     * @param row row position
     * @param col column position
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * check whether position is equal to the other obj
     *
     * @param obj the object to compare to
     * @return false if obj is not a position, or obj is at
     * a different location (row or column is different), true otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Position))
            return false;
        if (obj == this)
            return true;
        Position other = (Position) obj;
        return other.row == row && other.col == col;
    }

    /**
     * compare position to other position
     *
     * @param other the other position to compare
     * @return < 0 if the position is above the other position (row < other.row),
     * > 0 if the position is below the other position (row > other.row),
     * otherwise, < 0 if the position is on the left of the other position,
     * > 0 if the position is on the right of the other position, = 0 if two positions
     * are equal
     */
    @Override
    public int compareTo(Position other) {
        if (row == other.row)
            return col - other.col;
        else
            return row - other.row;
    }

    /**
     * compute the numeric hashcode for used in the HashSet. Equal positions
     * yield the same hashcode.
     *
     * @return a numeric representation of the position
     */
    @Override
    public int hashCode() {
        return MAX_POS * row + col;
    }

    /**
     * generate a string representation of the position
     *
     * @return "(row, col)" representation of the position
     */
    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
