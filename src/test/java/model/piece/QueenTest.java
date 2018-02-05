package model.piece;

import model.Board;
import model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {
    @Test
    void getAvailablePosition() {
        Player player = new Player();
        Board board = new Board();
        board.setPiece(new Queen(player), 3, 5);
        
    }

}