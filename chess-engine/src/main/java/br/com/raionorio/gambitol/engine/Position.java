package br.com.raionorio.gambitol.engine;

import java.util.Objects;

public final class Position {

    static final int WHITE_KINGSIDE = 1;
    static final int WHITE_QUEENSIDE = 1 << 1;
    static final int BLACK_KINGSIDE = 1 << 2;
    static final int BLACK_QUEENSIDE = 1 << 3;
    static final int ALL_CASTLING_RIGHTS = WHITE_KINGSIDE
            | WHITE_QUEENSIDE
            | BLACK_KINGSIDE
            | BLACK_QUEENSIDE;

    private final Board board;
    private final Side sideToMove;
    private final int castlingRights;
    private final Square enPassantTarget;
    private final int halfmoveClock;
    private final int fullmoveNumber;

    Position(
            Board board,
            Side sideToMove,
            int castlingRights,
            Square enPassantTarget,
            int halfmoveClock,
            int fullmoveNumber
    ) {
        this.board = Objects.requireNonNull(board, "board must not be null");
        this.sideToMove = Objects.requireNonNull(sideToMove, "sideToMove must not be null");

        if ((castlingRights & ~ALL_CASTLING_RIGHTS) != 0) {
            throw new IllegalArgumentException("invalid castling rights");
        }
        if (halfmoveClock < 0) {
            throw new IllegalArgumentException("halfmoveClock must not be negative");
        }
        if (fullmoveNumber < 1) {
            throw new IllegalArgumentException("fullmoveNumber must be at least 1");
        }

        validateKings(board);
        validatePawns(board);

        this.castlingRights = castlingRights;
        this.enPassantTarget = enPassantTarget;
        this.halfmoveClock = halfmoveClock;
        this.fullmoveNumber = fullmoveNumber;
    }

    public static Position initial() {
        return new Position(
                Board.initial(),
                Side.WHITE,
                ALL_CASTLING_RIGHTS,
                null,
                0,
                1
        );
    }

    public Board getBoard() {
        return board;
    }

    public Side getSideToMove() {
        return sideToMove;
    }

    public boolean canCastleKingside(Side side) {
        Objects.requireNonNull(side, "side must not be null");
        int right = side == Side.WHITE ? WHITE_KINGSIDE : BLACK_KINGSIDE;
        return (castlingRights & right) != 0;
    }

    public boolean canCastleQueenside(Side side) {
        Objects.requireNonNull(side, "side must not be null");
        int right = side == Side.WHITE ? WHITE_QUEENSIDE : BLACK_QUEENSIDE;
        return (castlingRights & right) != 0;
    }

    public Square getEnPassantTarget() {
        return enPassantTarget;
    }

    public int getHalfmoveClock() {
        return halfmoveClock;
    }

    public int getFullmoveNumber() {
        return fullmoveNumber;
    }

    int getCastlingRights() {
        return castlingRights;
    }

    Position apply(Move move) {
        Objects.requireNonNull(move, "move must not be null");

        Piece[] squares = board.copySquares();
        int fromIndex = Board.index(move.getFrom().getRow(), move.getFrom().getColumn());
        int toIndex = Board.index(move.getTo().getRow(), move.getTo().getColumn());
        Piece movingPiece = squares[fromIndex];

        if (movingPiece == null) {
            throw new IllegalArgumentException("origin must contain a piece");
        }

        Piece capturedPiece = squares[toIndex];
        boolean enPassant = isEnPassant(move, movingPiece, capturedPiece);
        if (enPassant) {
            int direction = movingPiece.getSide() == Side.WHITE ? 1 : -1;
            int capturedIndex = Board.index(
                    move.getTo().getRow() - direction,
                    move.getTo().getColumn()
            );
            capturedPiece = squares[capturedIndex];
            squares[capturedIndex] = null;
        }

        squares[fromIndex] = null;

        Piece placedPiece = movingPiece;
        boolean reachesPromotionRank = movingPiece.getType() == PieceType.PAWN
                && (move.getTo().getRow() == 0 || move.getTo().getRow() == 7);
        if (reachesPromotionRank) {
            if (!move.isPromotion()) {
                throw new IllegalArgumentException("pawn move to last rank requires promotion");
            }
            placedPiece = new Piece(movingPiece.getSide(), move.getPromotion());
        } else if (move.isPromotion()) {
            throw new IllegalArgumentException("promotion is only valid for a pawn on the last rank");
        }

        squares[toIndex] = placedPiece;

        if (isCastling(move, movingPiece)) {
            moveCastlingRook(squares, movingPiece.getSide(), move.getTo().getColumn());
        }

        int nextCastlingRights = updatedCastlingRights(
                castlingRights,
                movingPiece,
                move.getFrom(),
                capturedPiece,
                move.getTo()
        );

        Square nextEnPassantTarget = null;
        if (movingPiece.getType() == PieceType.PAWN
                && Math.abs(move.getTo().getRow() - move.getFrom().getRow()) == 2) {
            int row = (move.getFrom().getRow() + move.getTo().getRow()) / 2;
            nextEnPassantTarget = new Square(row, move.getFrom().getColumn());
        }

        boolean resetsHalfmove = movingPiece.getType() == PieceType.PAWN || capturedPiece != null;
        int nextHalfmoveClock = resetsHalfmove ? 0 : halfmoveClock + 1;
        int nextFullmoveNumber = sideToMove == Side.BLACK ? fullmoveNumber + 1 : fullmoveNumber;

        return new Position(
                new Board(squares),
                sideToMove.opposite(),
                nextCastlingRights,
                nextEnPassantTarget,
                nextHalfmoveClock,
                nextFullmoveNumber
        );
    }

    private boolean isEnPassant(Move move, Piece movingPiece, Piece capturedPiece) {
        return movingPiece.getType() == PieceType.PAWN
                && capturedPiece == null
                && move.getFrom().getColumn() != move.getTo().getColumn()
                && move.getTo().equals(enPassantTarget);
    }

    private boolean isCastling(Move move, Piece movingPiece) {
        return movingPiece.getType() == PieceType.KING
                && Math.abs(move.getTo().getColumn() - move.getFrom().getColumn()) == 2;
    }

    private void moveCastlingRook(Piece[] squares, Side side, int kingDestinationColumn) {
        int row = side == Side.WHITE ? 0 : 7;
        int rookFromColumn = kingDestinationColumn == 6 ? 7 : 0;
        int rookToColumn = kingDestinationColumn == 6 ? 5 : 3;
        int rookFromIndex = Board.index(row, rookFromColumn);
        int rookToIndex = Board.index(row, rookToColumn);
        Piece rook = squares[rookFromIndex];

        if (rook == null || rook.getSide() != side || rook.getType() != PieceType.ROOK) {
            throw new IllegalArgumentException("castling requires the corresponding rook");
        }

        squares[rookFromIndex] = null;
        squares[rookToIndex] = rook;
    }

    private int updatedCastlingRights(
            int rights,
            Piece movingPiece,
            Square from,
            Piece capturedPiece,
            Square to
    ) {
        int updated = rights;

        if (movingPiece.getType() == PieceType.KING) {
            updated &= movingPiece.getSide() == Side.WHITE
                    ? ~(WHITE_KINGSIDE | WHITE_QUEENSIDE)
                    : ~(BLACK_KINGSIDE | BLACK_QUEENSIDE);
        }

        if (movingPiece.getType() == PieceType.ROOK) {
            updated = removeRookRight(updated, movingPiece.getSide(), from);
        }

        if (capturedPiece != null && capturedPiece.getType() == PieceType.ROOK) {
            updated = removeRookRight(updated, capturedPiece.getSide(), to);
        }

        return updated;
    }

    private int removeRookRight(int rights, Side side, Square rookSquare) {
        if (side == Side.WHITE && rookSquare.equals(Square.fromAlgebraic("h1"))) {
            return rights & ~WHITE_KINGSIDE;
        }
        if (side == Side.WHITE && rookSquare.equals(Square.fromAlgebraic("a1"))) {
            return rights & ~WHITE_QUEENSIDE;
        }
        if (side == Side.BLACK && rookSquare.equals(Square.fromAlgebraic("h8"))) {
            return rights & ~BLACK_KINGSIDE;
        }
        if (side == Side.BLACK && rookSquare.equals(Square.fromAlgebraic("a8"))) {
            return rights & ~BLACK_QUEENSIDE;
        }
        return rights;
    }

    String repetitionKey(Square effectiveEnPassantTarget) {
        StringBuilder key = new StringBuilder();
        key.append(board.placementKey());
        key.append('|').append(sideToMove == Side.WHITE ? 'w' : 'b');
        key.append('|').append(castlingRights);
        key.append('|').append(
                effectiveEnPassantTarget == null
                        ? "-"
                        : effectiveEnPassantTarget.toAlgebraic()
        );
        return key.toString();
    }

    private static void validateKings(Board board) {
        int whiteKings = 0;
        int blackKings = 0;
        for (Piece piece : board.getPieces().values()) {
            if (piece.getType() == PieceType.KING) {
                if (piece.getSide() == Side.WHITE) {
                    whiteKings++;
                } else {
                    blackKings++;
                }
            }
        }
        if (whiteKings != 1 || blackKings != 1) {
            throw new IllegalArgumentException("position must contain exactly one king per side");
        }
    }

    private static void validatePawns(Board board) {
        for (java.util.Map.Entry<Square, Piece> entry : board.getPieces().entrySet()) {
            if (entry.getValue().getType() == PieceType.PAWN
                    && (entry.getKey().getRow() == 0 || entry.getKey().getRow() == 7)) {
                throw new IllegalArgumentException("pawns cannot remain on the first or last rank");
            }
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Position)) {
            return false;
        }
        Position other = (Position) object;
        return castlingRights == other.castlingRights
                && halfmoveClock == other.halfmoveClock
                && fullmoveNumber == other.fullmoveNumber
                && board.equals(other.board)
                && sideToMove == other.sideToMove
                && Objects.equals(enPassantTarget, other.enPassantTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                board,
                sideToMove,
                castlingRights,
                enPassantTarget,
                halfmoveClock,
                fullmoveNumber
        );
    }
}
