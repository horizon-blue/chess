package model;

import controller.Game;
import model.piece.*;
import utils.printUtils;
import view.BoardView;


import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * BoardView - the model for chess board, which has method for
 * piece movements, end-game status check, etc.
 */
public class Board {
    public final int HEIGHT;
    public final int WIDTH;

    private final Space[][] board;
    // sets of all black/white pieces that have appeared on the board once
    public Set<Piece> blackPieces = new HashSet<>();
    public Set<Piece> whitePieces = new HashSet<>();

    /**
     * related to the view
     */
    public BoardView view;
    public Game game;
    public Stack<History> past = new Stack<>();
    public Stack<History> future = new Stack<>();

    /**
     * generate a board of default size (8 x 8)
     */
    public Board() {
        this(8, 8);
    }

    /**
     * generate a board with given (maxRow x maxCol) size
     *
     * @param maxRow height of the board
     * @param maxCol width of the board
     */
    public Board(int maxRow, int maxCol) {
        HEIGHT = maxRow;
        WIDTH = maxCol;
        board = new Space[HEIGHT][WIDTH];
        // initialize spaces
        for (int row = 0; row < HEIGHT; ++row)
            for (int col = 0; col < WIDTH; ++col)
                board[row][col] = new Space();

    }

    /**
     * Undo previous step using command pattern
     */
    public void undo() {
        if (past.empty())
            return;
        History stepBack = past.pop();
        stepBack.undo();
        future.push(stepBack);
    }

    /**
     * Redo previous undo using command pattern. Rewrite the history
     */
    public void redo() {
        if (future.empty())
            return;
        History stepForward = future.peek();
        movePiece(stepForward.from, stepForward.to);
        if (game != null)
            game.setRound(!getPiece(stepForward.to).isWhite());
    }

    /**
     * remove the piece from board at given (row, col) position
     * have no effect if no piece is presented in (row, col) or
     * (row, col) is invalid for the board
     *
     * @param row row position of the piece to remove
     * @param col col position of the piece to remove
     */
    public void clearPiece(int row, int col) {
        if (isValid(row, col)) {
            board[row][col].clearPiece();
        }
    }

    /**
     * equivalent to clearPiece(pos.row, pos.col)
     *
     * @param pos position of the piece to remove
     */
    public void clearPiece(Position pos) {
        clearPiece(pos.row, pos.col);
    }

    /**
     * Move the piece from (fromRow, fromCol) to (toRow, toCol), creating history
     * equivalent to movePiece(fromRow, fromCol, toRow, toCol, true)
     *
     * @param fromRow original row position of the piece
     * @param fromCol original column position of the piece
     * @param toRow   new row position of the piece
     * @param toCol   new column position of the piece
     */
    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        movePiece(fromRow, fromCol, toRow, toCol, true);
    }

    /**
     * Move the piece from (fromRow, fromCol) to (toRow, toCol).
     *
     * @param fromRow
     * @param fromCol
     * @param toRow
     * @param toCol
     * @param isRecorded
     */
    public void movePiece(int fromRow, int fromCol, int toRow, int toCol, boolean isRecorded) {
        Piece piece = getPiece(fromRow, fromCol);
        Piece target = getPiece(toRow, toCol);
        if (isRecorded) {
            // setting the special property for pawn
            if (piece instanceof Pawn)
                ++((Pawn) piece).moveCount;
            // add new history
            History current = new History(this, new Position(fromRow, fromCol),
                    new Position(toRow, toCol), target);
            // if current movement is same as the latest future movement,
            // then the path to future have not changed
            if (!future.empty() && current.equals(future.peek()))
                future.pop();
            else
                future.clear();
            past.push(current);

        }
        // remove future events, if any
        movePieceHelper(fromRow, fromCol, toRow, toCol);
        if (piece.view != null)
            piece.view.updateLocation();
        if (target != null && target.view != null)
            target.view.removeSelf();
    }

    /**
     * equivalent to movePiece(from.row, from.col, to.row, to.col)
     *
     * @param from original position of the piece
     * @param to   new position of the piece
     */
    public void movePiece(Position from, Position to) {
        movePiece(from.row, from.col, to.row, to.col);
    }

    /**
     * equivalent to movePiece(piece.x, piece.y, row, col). Have no effect if piece does
     * not belong to current board
     *
     * @param piece piece to move
     * @param row   target row position
     * @param col   target column position
     */
    public void movePiece(Piece piece, int row, int col) {
        if (piece.board != this)
            return;
        movePiece(piece.x, piece.y, row, col);
    }

    /**
     * equivalent to movePiece(piece, pos.row, pos.col)
     *
     * @param piece piece to move
     * @param pos   target position
     */
    public void movePiece(Piece piece, Position pos) {
        movePiece(piece, pos.row, pos.col);
    }

    /**
     * A helper that does the actual action of moving a piece
     * (without record history or set the status of Pawn to moved)
     * Move the piece from (fromRow, fromCol) to (toRow, toCol).
     *
     * @param fromRow original row position of the piece
     * @param fromCol original column position of the piece
     * @param toRow   new row position of the piece
     * @param toCol   new column position of the piece
     */
    public void movePieceHelper(int fromRow, int fromCol, int toRow, int toCol) {
        Piece selected = getPiece(fromRow, fromCol);
        clearPiece(fromRow, fromCol);
        clearPiece(toRow, toCol);
        setPiece(selected, toRow, toCol);
    }

    /**
     * equivalent to movePieceHelper(piece.x, piece.y, row, col).
     *
     * @param piece piece to move
     * @param row   target row position
     * @param col   target column position
     */
    private void movePieceHelper(Piece piece, int row, int col) {
        movePieceHelper(piece.x, piece.y, row, col);
    }

    /**
     * get the piece (if any) at the given (row, col) position
     *
     * @param row row position of the piece
     * @param col column position of the piece
     * @return the piece at given position, if any; null otherwise. Also return null
     * if the position is invalid
     */
    public Piece getPiece(int row, int col) {
        if (isValid(row, col))
            return board[row][col].getPiece();
        else
            return null;
    }

    /**
     * equivalent to getPiece(pos.row, pos.col)
     *
     * @param pos position of the piece
     * @return the piece at given position, if any; null otherwise. Also return null
     * if the position is invalid
     */
    public Piece getPiece(Position pos) {
        return getPiece(pos.row, pos.col);
    }

    /**
     * check if given (row, col) is valid on the board (within board boundary)
     *
     * @param row row position to check
     * @param col column position to check
     * @return true if (row, col) is valid, false otherwise
     */
    public boolean isValid(int row, int col) {
        return row >= 0 && col >= 0 && row < HEIGHT && col < WIDTH;
    }

    /**
     * equivalent to isValid(pos.row, pos.col)
     *
     * @param pos position to check
     * @return true if pos is valid, false otherwise
     */
    public boolean isValid(Position pos) {
        return isValid(pos.row, pos.col);
    }

    /**
     * check if any piece (either color) occupies the given (row, col) position
     *
     * @param row row position to check
     * @param col column position to check
     * @return true if there is one piece of any color that occupies (row, col),
     * false otherwise
     */
    public boolean isOccupied(int row, int col) {
        return isValid(row, col) && board[row][col].isOccupied();
    }

    /**
     * equivalent to isOccupied(pos.row, pos.col)
     *
     * @param pos position to check
     * @return true if there is one piece of any color that occupies (row, col),
     * false otherwise
     */
    public boolean isOccupied(Position pos) {
        return isOccupied(pos.row, pos.col);
    }


    /**
     * Add piece to the board.
     *
     * @param piece piece to add
     * @param row   row position on the board
     * @param col   column position on the board
     */
    public void addPiece(Piece piece, int row, int col) {
        if (piece != null) {
            piece.board = this;
            if (piece.isWhite())
                whitePieces.add(piece);
            else
                blackPieces.add(piece);
        }
        setPiece(piece, row, col);
        if (piece.view != null)
            piece.view.addSelf();
    }

    /**
     * equivalent to addPiece(piece, pos.row, pos.col)
     *
     * @param piece piece to add
     * @param pos   position for the new piece
     */
    public void addPiece(Piece piece, Position pos) {
        addPiece(piece, pos.row, pos.col);
    }


    /**
     * Helper function to update piece's information and board information
     * co-currently
     *
     * @param piece piece to be update
     * @param row   row position on the board
     * @param col   column position on the board
     */
    private void setPiece(Piece piece, int row, int col) {
        if (isValid(row, col)) {
            if (piece != null)
                piece.setPos(row, col);
            board[row][col].setPiece(piece);
        }
    }


    /**
     * helper function to check whether a piece can be move to a given position
     * this method does NOT check whether the movement obey the piece's rule
     *
     * @param piece the piece that is in action
     * @param row   row position on the board
     * @param col   column position on the board
     * @return true if the movement is valid, false otherwise
     */
    public boolean isValidMovement(Piece piece, int row, int col) {
        // if there is a piece, then it should be in a different color
        return isValid(row, col)
                && (!isOccupied(row, col) || !piece.sameColor(getPiece(row, col)));
    }

    /**
     * Equivalent to isValidMovement(piece, pos.row, pos.col)
     *
     * @param piece the piece that is in action
     * @param pos   position on the board
     * @return true if the movement is valid, false otherwise
     */
    public boolean isValidMovement(Piece piece, Position pos) {
        return isValidMovement(piece, pos.row, pos.col);
    }

    /**
     * Check whether moving the piece to the designated position will result its
     * own king to be
     * in a checked state (i.e. king is in danger)
     * this method does NOT check whether the movement obey the piece's rule
     *
     * @param piece the piece that is in action
     * @param row   the row position to move
     * @param col   the column position to move
     * @return true if moving the piece to the designated position will result
     * in a checked state, false otherwise
     */
    public boolean willBeChecked(Piece piece, int row, int col) {
        if (!isValid(row, col))
            return false;

        // temporarily move piece to new location
        Piece oldPiece = getPiece(row, col);
        int oldRow = piece.x;
        int oldCol = piece.y;
        movePieceHelper(piece, row, col);

        boolean result = isChecked(piece.owner);

        // reset to previous state
        movePieceHelper(piece, oldRow, oldCol);
        setPiece(oldPiece, row, col);

        return result;
    }

    /**
     * equivalent to willBeChecked(piece, pos.row, pos.col)
     *
     * @param piece the piece that is in action
     * @param pos   position to move
     * @return true if moving the piece to the designated position will result
     * in a checked state, false otherwise
     */
    public boolean willBeChecked(Piece piece, Position pos) {
        return willBeChecked(piece, pos.row, pos.col);
    }

    /**
     * Decide whether the player is in checked status (king is in danger)
     *
     * @param owner the player to check
     * @return true if the player is in checked status, false otherwise
     */
    public boolean isChecked(Player owner) {
        if (owner.king == null)
            return false;
        Position kingPos = new Position(owner.king.x, owner.king.y);
        Set<Piece> enemyPieces = owner.isWhite ? blackPieces : whitePieces;
        for (Piece piece : enemyPieces) {
            if (piece.getAvailablePosition(owner.isWhite).contains(kingPos))
                return true;
        }
        return false;
    }

    /**
     * Decide whether the player is in checkmated or is in stalemated status
     *
     * @param owner the player to check
     * @return true if the player is in checkmated or is in stalemated status,
     * false otherwise
     */
    public boolean isCheckOrStaleMated(Player owner) {
        Set<Piece> ownerPieces = owner.isWhite ? whitePieces : blackPieces;
        for (Piece piece : ownerPieces) {
            Set<Position> possibleMovements = piece.getAvailablePosition(owner.isWhite);
            if (!possibleMovements.isEmpty())
                return false;
        }
        return true;
    }

    /**
     * equivalent to toString(null)
     *
     * @return the formatted string without any highlight
     */
    @Override
    public String toString() {
        return toString(null);
    }

    /**
     * Format the chess board into a string with positions highlighted
     *
     * @param positions positions to be highlighted
     * @return a string notation of the chessboard
     */
    public String toString(Set<Position> positions) {
        StringBuilder boardString = new StringBuilder();
        for (int row = HEIGHT; row > 0; --row) {
            // print row number
            boardString.append(row);
            // print each space
            for (int col = 0; col < WIDTH; ++col) {
                if (positions != null && positions.contains(new Position(row - 1, col)))
                    boardString.append(" " +
                            printUtils.ANSI_BLUE_BG +
                            printUtils.ANSI_BOLD +
                            board[row - 1][col] +
                            printUtils.ANSI_RESET
                    );
                else
                    boardString.append(" " + board[row - 1][col]);
            }
            boardString.append('\n');
        }
        // print column number (using alphabet)
        boardString.append(" ");
        for (int col = 0; col < WIDTH; ++col) {
            boardString.append(" " + (char) (col + 'a'));
        }
        return boardString.toString();
    }

    /**
     * Initialize the game board for the given players
     *
     * @param whitePlayer the player to hold white pieces
     * @param blackPlayer the player to hold black pieces
     * @param hasSpecialPieces whether to include special pieces or not
     */
    public void init(Player whitePlayer, Player blackPlayer, boolean hasSpecialPieces) {
        // white pieces
        addPiece(new Rook(whitePlayer), 0, 0);
        addPiece(new Knight(whitePlayer), 0, 1);
        addPiece(new Bishop(whitePlayer), 0, 2);
        addPiece(new Queen(whitePlayer), 0, 3);
        addPiece(new King(whitePlayer), 0, 4);
        addPiece(new Bishop(whitePlayer), 0, 5);
        addPiece(new Knight(whitePlayer), 0, 6);
        addPiece(new Rook(whitePlayer), 0, 7);
        // pawns
        for (int col = 0; col < 8; ++col)
            addPiece(new Pawn(whitePlayer), 1, col);
        if (hasSpecialPieces) {
            // fairy piece
            addPiece(new Unicorn(whitePlayer), 2, 0);
            addPiece(new Artillery(whitePlayer), 2, 7);
        }


        // black pieces
        addPiece(new Rook(blackPlayer), HEIGHT - 1, 0);
        addPiece(new Knight(blackPlayer), HEIGHT - 1, 1);
        addPiece(new Bishop(blackPlayer), HEIGHT - 1, 2);
        addPiece(new Queen(blackPlayer), HEIGHT - 1, 3);
        addPiece(new King(blackPlayer), HEIGHT - 1, 4);
        addPiece(new Bishop(blackPlayer), HEIGHT - 1, 5);
        addPiece(new Knight(blackPlayer), HEIGHT - 1, 6);
        addPiece(new Rook(blackPlayer), HEIGHT - 1, 7);
        // pawns
        for (int col = 0; col < 8; ++col)
            addPiece(new Pawn(blackPlayer), HEIGHT - 2, col);
        if (hasSpecialPieces) {
            addPiece(new Unicorn(blackPlayer), HEIGHT - 3, 7);
            addPiece(new Artillery(blackPlayer), HEIGHT - 3, 0);
        }
    }

    /**
     * Initialize the game board for the given players. equivalent to
     * init(whitePlayer, blackPlayer, true)
     *
     * @param whitePlayer the player to hold white pieces
     * @param blackPlayer the player to hold black pieces
     */
    public void init(Player whitePlayer, Player blackPlayer) {
        init(whitePlayer, blackPlayer, true);
    }

    /**
     * Space - A inner class to store pieces
     */
    private class Space {
        // the piece (or null) that occupies the space
        private Piece piece;

        /**
         * return the piece that the space hold
         *
         * @return the piece (or null) that the space hold
         */
        public Piece getPiece() {
            return piece;
        }

        /**
         * let the space hold the given piece
         *
         * @param piece the piece to put in the space
         */
        public void setPiece(Piece piece) {
            this.piece = piece;
        }

        /**
         * check whether the space contains any piece
         *
         * @return true if the space has piece, false otherwise
         */
        public boolean isOccupied() {
            return piece != null;
        }

        /**
         * remove the piece that the space hold
         */
        public void clearPiece() {
            piece = null;
        }

        /**
         * convert the space to String. If the space contains any piece,
         * the string representation will be that of the piece; otherwise,
         * it will be a space (" ")
         *
         * @return the string representation of the space
         */
        @Override
        public String toString() {
            return piece == null ? " " : piece.toString();
        }
    }
}
