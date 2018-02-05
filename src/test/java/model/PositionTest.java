package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    @Test
    @DisplayName("hashCode()")
    void hashCodeTest() {
        Position posA = new Position(8, 20);
        Position posB = new Position(8, 20);
        Position posC = new Position(20, 8);
        assertEquals(posA.hashCode(), posB.hashCode());
        assertNotEquals(posB.hashCode(), posC.hashCode());
    }

    @Test
    void equals() {
        Position posA = new Position(3, 5);
        Position posB = new Position(3, 5);
        Position posC = new Position(8, 10);
        assertEquals(posA, posA);
        assertEquals(posA, posB);
        assertNotEquals(posB, posC);
        assertNotEquals(posB, null);
    }

    @Test
    void compareTo() {
        Position posA = new Position(3, 5);
        Position posB = new Position(4, 5);
        Position posC = new Position(3, 8);
        assertTrue(posA.compareTo(posB) < 0);
        assertTrue(posA.compareTo(posC) < 0);
        assertTrue(posB.compareTo(posC) > 0);
    }

    @Test
    @DisplayName("toString()")
    void toStringTest() {
        Position posA = new Position(3, 5);
        Position posB = new Position(3, 5);
        Position posC = new Position(8, 10);
        assertEquals(posA.toString(), posB.toString());
        assertEquals(posA.toString(), "(3, 5)");
        assertNotEquals(posB.toString(), posC.toString());
    }

}