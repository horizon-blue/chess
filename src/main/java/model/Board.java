package model;

import model.piece.*;

import java.util.HashSet;

public class Board {
    public final int HEIGHT;
    public final int WIDTH;

    private final Space[][] board;
    private HashSet<Piece> pieces;

    public Board() {
        this(8, 8);
    }

    public Board(int maxRow, int maxCol) {
        HEIGHT = maxRow;
        WIDTH = maxCol;
        board = new Space[HEIGHT][WIDTH];
        // initialize spaces
        for (int row = 0; row < HEIGHT; ++row)
            for (int col = 0; col < WIDTH; ++col)
                board[row][col] = new Space();

    }

    public void setPiece(Piece piece, int row, int col) {
        if (isValid(row, col)) {
            if (piece != null) {
                piece.board = this;
                piece.x = row;
                piece.y = col;
                pieces.add(piece);
            }
            board[row][col].setPiece(piece);
        }
    }

    public void setPiece(Piece piece, Position pos) {
        setPiece(piece, pos.row, pos.col);
    }

    public void clearPiece(int row, int col) {
        if (isValid(row, col))
            board[row][col].clearPiece();
    }

    public void clearPiece(Position pos) {
        clearPiece(pos.row, pos.col);
    }

    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        Piece selected = getPiece(fromRow, fromCol);
        clearPiece(fromRow, fromCol);
        setPiece(selected, toRow, toCol);
    }

    public void movePiece(Position from, Position to) {
        movePiece(from.row, from.col, to.row, to.col);
    }

    public void movePiece(Piece piece, int row, int col) {
        if (piece.board != this)
            return;
        movePiece(piece.x, piece.y, row, col);
    }

    public void movePiece(Piece piece, Position pos) {
        movePiece(piece, pos.row, pos.col);
    }

    public Piece getPiece(int row, int col) {
        if (isValid(row, col))
            return board[row][col].getPiece();
        else
            return null;
    }

    public Piece getPiece(Position pos) {
        return getPiece(pos.row, pos.col);
    }

    public boolean isValid(int row, int col) {
        return row >= 0 && col >= 0 && row < HEIGHT && col < WIDTH;
    }

    public boolean isOccupied(int row, int col) {
        return isValid(row, col) && board[row][col].isOccupied();
    }

    public boolean isOccupied(Position pos) {
        return isOccupied(pos.row, pos.col);
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
                && (!isOccupied(row, col) || !piece.sameColor(getPiece(row, col)))
                && !willbeChecked(piece, row, col);
    }

    /**
     * Check whether moving the piece to the designated position will result its own king to be
     * in a checked state (i.e. king is in danger)
     * this method does NOT check whether the movement obey the piece's rule
     *
     * @param piece the piece that is in action
     * @param row   the row position to move
     * @param col   the column position to move
     * @return true if moving the piece to the designated position will result in a checked state, false otherwise
     */
    public boolean willbeChecked(Piece piece, int row, int col) {
        if (!isValid(row, col))
            return false;

        // temporarily move piece to new location
        Piece oldPiece = getPiece(row, col);
        int oldRow = piece.x;
        int oldCol = piece.y;
        movePiece(piece, row, col);

        boolean result = isChecked(piece.owner);

        // reset to previous state
        movePiece(piece, oldRow, oldCol);
        setPiece(oldPiece, row, col);

        return result;
    }

    public boolean isChecked(Player owner) {
        Piece king = owner.king;

        // check for pawn
        int direction = king.isWhite() ? 1 : -1; // direction of enemy's pawn
        for (int col = king.y - 1; col <= king.y + 1; ++col) {
            if (isOccupied(king.x - direction, col)
                    && getPiece(king.x - direction, col) instanceof Pawn
                    && !king.sameColor(getPiece(king.x - direction, col)))
                return true;
        }
        // on first move, pawn can goes 2 tiles in front
        if (!isOccupied(king.x - direction, king.y)
                && isOccupied(king.x - 2 * direction, king.y)
                && getPiece(king.x - 2 * direction, king.y) instanceof Pawn
                && !king.sameColor(getPiece(king.x - 2 * direction, king.y)))
            return true;

        // check for knight
        for (int row = king.x - 2; row <= king.x + 2; row += 4) {
            for (int col = king.y - 1; col <= king.y + 1; col += 2) {
                if ((isOccupied(row, col)
                        && getPiece(row, col) instanceof Knight
                        && !king.sameColor(getPiece(row, col)))
                        || (isOccupied(col, row)
                        && getPiece(col, row) instanceof Knight
                        && !king.sameColor(getPiece(col, row)))
                        )
                    return true;
            }
        }

        // check file
        for (int row = king.x - 1; row >= 0; --row) {
            if (isOccupied(row, king.y)) {
                Piece targetPiece = getPiece(row, king.y);
                if (!king.sameColor(targetPiece)
                        && (targetPiece instanceof Rook
                        || targetPiece instanceof Queen
                ))
                    return true;
                else
                    break;
            }
        }
        for (int row = king.x + 1; row < HEIGHT; ++row) {
            if (isOccupied(row, king.y)) {
                Piece targetPiece = getPiece(row, king.y);
                if (!king.sameColor(targetPiece)
                        && (targetPiece instanceof Rook
                        || targetPiece instanceof Queen
                ))
                    return true;
                else
                    break;
            }
        }


        return false;
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        for (int row = HEIGHT; row > 0; --row) {
            // print row number
            boardString.append(row);
            // print each space
            for (Space space : board[row - 1]) {
                boardString.append(' ');
                boardString.append(space);
            }
            boardString.append('\n');
        }
        // print column number (using alphabet)
        boardString.append(' ');
        for (int col = 0; col < WIDTH; ++col) {
            boardString.append(' ');
            boardString.append((char) (col + 'a'));
        }
        return boardString.toString();
    }

    /**
     * A inner class to store pieces
     */
    private class Space {
        private Piece piece;

        public Piece getPiece() {
            return piece;
        }

        public void setPiece(Piece piece) {
            this.piece = piece;
        }

        public boolean isOccupied() {
            return piece != null;
        }

        public void clearPiece() {
            piece = null;
        }

        @Override
        public String toString() {
            return piece == null ? " " : piece.toString();
        }
    }
}
