package br.com.raionorio.gambitol.engine;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class Board {

    public static final int SIZE = 8;
    private static final int SQUARE_COUNT = SIZE * SIZE;

    private final Piece[] squares;

    Board(Piece[] squares) {
        Objects.requireNonNull(squares, "squares must not be null");
        if (squares.length != SQUARE_COUNT) {
            throw new IllegalArgumentException("board must contain exactly 64 squares");
        }
        this.squares = squares.clone();
    }

    public static Board initial() {
        Piece[] squares = new Piece[SQUARE_COUNT];
        addBackRank(squares, Side.WHITE, 0);
        addPawns(squares, Side.WHITE, 1);
        addPawns(squares, Side.BLACK, 6);
        addBackRank(squares, Side.BLACK, 7);
        return new Board(squares);
    }

    private static void addBackRank(Piece[] squares, Side side, int row) {
        PieceType[] types = {
                PieceType.ROOK,
                PieceType.KNIGHT,
                PieceType.BISHOP,
                PieceType.QUEEN,
                PieceType.KING,
                PieceType.BISHOP,
                PieceType.KNIGHT,
                PieceType.ROOK
        };

        for (int column = 0; column < SIZE; column++) {
            squares[index(row, column)] = new Piece(side, types[column]);
        }
    }

    private static void addPawns(Piece[] squares, Side side, int row) {
        for (int column = 0; column < SIZE; column++) {
            squares[index(row, column)] = new Piece(side, PieceType.PAWN);
        }
    }

    public Piece getPiece(Square square) {
        Objects.requireNonNull(square, "square must not be null");
        return squares[index(square.getRow(), square.getColumn())];
    }

    public int getPieceCount() {
        int count = 0;
        for (Piece piece : squares) {
            if (piece != null) {
                count++;
            }
        }
        return count;
    }

    public int getPieceCount(Side side) {
        Objects.requireNonNull(side, "side must not be null");
        int count = 0;
        for (Piece piece : squares) {
            if (piece != null && piece.getSide() == side) {
                count++;
            }
        }
        return count;
    }

    public Map<Square, Piece> getPieces() {
        Map<Square, Piece> pieces = new LinkedHashMap<>();
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                Piece piece = squares[index(row, column)];
                if (piece != null) {
                    pieces.put(new Square(row, column), piece);
                }
            }
        }
        return Collections.unmodifiableMap(pieces);
    }

    Piece[] copySquares() {
        return squares.clone();
    }

    public Square findKing(Side side) {
        Objects.requireNonNull(side, "side must not be null");
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                Piece piece = squares[index(row, column)];
                if (piece != null
                        && piece.getSide() == side
                        && piece.getType() == PieceType.KING) {
                    return new Square(row, column);
                }
            }
        }
        return null;
    }

    String placementKey() {
        StringBuilder key = new StringBuilder(SQUARE_COUNT);
        for (Piece piece : squares) {
            key.append(piece == null ? '.' : pieceCode(piece));
        }
        return key.toString();
    }

    private char pieceCode(Piece piece) {
        char code;
        switch (piece.getType()) {
            case KING:
                code = 'k';
                break;
            case QUEEN:
                code = 'q';
                break;
            case ROOK:
                code = 'r';
                break;
            case BISHOP:
                code = 'b';
                break;
            case KNIGHT:
                code = 'n';
                break;
            case PAWN:
                code = 'p';
                break;
            default:
                throw new IllegalStateException("unknown piece type");
        }
        return piece.getSide() == Side.WHITE ? Character.toUpperCase(code) : code;
    }

    static int index(int row, int column) {
        return row * SIZE + column;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Board)) {
            return false;
        }
        Board other = (Board) object;
        return Arrays.equals(squares, other.squares);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(squares);
    }
}
