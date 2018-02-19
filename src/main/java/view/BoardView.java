package view;

import model.Board;
import model.Position;
import model.piece.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

/**
 * The viewer component of the BoardView class
 */
public class BoardView extends JPanel {
    private final Board board;
    private final SpaceView[][] spaces;

    /**
     * Create a BoardView viewer to display the content of the board
     *
     * @param board the board to display
     */
    public BoardView(Board board) {
        // set layout to null to allow absolute positioning
        setLayout(null);

        this.board = board;
        spaces = new SpaceView[board.HEIGHT][board.WIDTH];

        // odd spaces have white color, even spaces have black color
        // reverse drawing oder so that the board can display correctly
        drawBoard();
    }

    /**
     * redraw the entire board
     */
    public void drawBoard() {
        for (int row = 0; row < board.HEIGHT; ++row) {
            for (int col = board.WIDTH - 1; col >= 0; --col) {
                SpaceView space = new SpaceView(new Position(row, col));
                spaces[row][col] = space;
                if (board.isOccupied(row, col)) {
                    PieceView piece = new PieceView(board.getPiece(row, col));
                    board.getPiece(row, col).view = piece;
                    space.piece = piece;
                    add(piece);
                }
                add(space);
            }
        }
    }

    /**
     * A helper function to disable/enable all spaces
     */
    public void toggleSpace(boolean state) {
        for (SpaceView[] spaceRow : spaces)
            for (SpaceView space : spaceRow)
                space.setEnabled(state);
    }

    /**
     * A helper function to disable/enable all pieces
     */
    public void togglePieces(Set<Piece> pieces, boolean state) {
        for (Piece piece : pieces)
            if (piece.view != null)
                piece.view.setEnabled(state);
    }
}
