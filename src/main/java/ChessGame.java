import controller.Session;
import model.Player;

import java.util.Scanner;

public class ChessGame {
    public static void main(String[] args) {
        Session chessGame = new Session(new Player(), new Player());
        Scanner input = new Scanner(System.in);
        do {
            chessGame.restart();
            System.out.printf("Restart (y/n)? ");
        } while (input.hasNext() && Character.toLowerCase(input.nextLine().charAt(0)) == 'y');
    }
}
