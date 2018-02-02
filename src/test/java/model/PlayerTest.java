package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void defaultName() {
        Player player = new Player();
        assertNotNull(player.name);
    }

    @Test
    void specifiedName() {
        Player player = new Player("foo");
        assertEquals("foo", player.name);
    }

}