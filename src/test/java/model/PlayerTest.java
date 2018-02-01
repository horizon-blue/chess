package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void defaultName() {
        Player player = new Player(Color.BLACK);
        assertNotNull(player.getName());
    }

    @Test
    void specifiedName() {
        Player player = new Player("foo", Color.BLACK);
        assertEquals("foo", player.getName());
    }

}