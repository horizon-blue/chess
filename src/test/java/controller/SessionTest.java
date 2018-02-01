package controller;

import model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {
    @Test
    void noPlayer() {
        assertThrows(IllegalArgumentException.class, () -> new Session());
    }

}