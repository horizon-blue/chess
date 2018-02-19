package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    @DisplayName("toString()")
    void toStringTest() {
        Player player = new Player("foo");
        assertEquals(player.toString(), "foo");
    }

    @Test
    @DisplayName("construct with color")
    void constructWiithCOlor() {
        Player player = new Player("foo", false);
        assertFalse(player.isWhite);
    }

}