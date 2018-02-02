import controller.Session;
import model.Player;

public class ChessGame {
    public static void main(String[] args) {
        Session chessGame = new Session(new Player(), new Player());
        chessGame.start();
    }
}
