package br.com.raionorio.gambitol.engine;

import java.util.ArrayList;
import java.util.List;

final class MoveGenerator {

    private static final int[][] ROOK_DIRECTIONS = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };
    private static final int[][] BISHOP_DIRECTIONS = {
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };
    private static final int[][] KING_DIRECTIONS = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };
    private static final int[][] KNIGHT_OFFSETS = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
    };
    private static final PieceType[] PROMOTION_TYPES = {
            PieceType.QUEEN,
            PieceType.ROOK,
            PieceType.BISHOP,
            PieceType.KNIGHT
    };

    private MoveGenerator() {
    }

    static List<Move> legalMoves(Position position) {
        List<Move> legalMoves = new ArrayList<>();
        Side movingSide = position.getSideToMove();

        for (Move move : pseudoLegalMoves(position)) {
            Position next = position.apply(move);
            Square king = next.getBoard().findKing(movingSide);
            if (king != null && !isSquareAttacked(next, king, movingSide.opposite())) {
                legalMoves.add(move);
            }
        }

        return legalMoves;
    }

    static boolean isInCheck(Position position, Side side) {
        Square king = position.getBoard().findKing(side);
        if (king == null) {
            throw new IllegalArgumentException("position must contain a king for " + side);
        }
        return isSquareAttacked(position, king, side.opposite());
    }

    static boolean isSquareAttacked(Position position, Square target, Side bySide) {
        Board board = position.getBoard();

        int pawnSourceRow = target.getRow() - (bySide == Side.WHITE ? 1 : -1);
        for (int columnOffset : new int[]{-1, 1}) {
            Piece pawn = pieceAt(board, pawnSourceRow, target.getColumn() + columnOffset);
            if (isPiece(pawn, bySide, PieceType.PAWN)) {
                return true;
            }
        }

        for (int[] offset : KNIGHT_OFFSETS) {
            Piece knight = pieceAt(
                    board,
                    target.getRow() + offset[0],
                    target.getColumn() + offset[1]
            );
            if (isPiece(knight, bySide, PieceType.KNIGHT)) {
                return true;
            }
        }

        for (int[] direction : KING_DIRECTIONS) {
            Piece king = pieceAt(
                    board,
                    target.getRow() + direction[0],
                    target.getColumn() + direction[1]
            );
            if (isPiece(king, bySide, PieceType.KING)) {
                return true;
            }
        }

        if (attackedOnRay(board, target, bySide, ROOK_DIRECTIONS, PieceType.ROOK)) {
            return true;
        }
        return attackedOnRay(board, target, bySide, BISHOP_DIRECTIONS, PieceType.BISHOP);
    }

    private static boolean attackedOnRay(
            Board board,
            Square target,
            Side bySide,
            int[][] directions,
            PieceType directType
    ) {
        for (int[] direction : directions) {
            int row = target.getRow() + direction[0];
            int column = target.getColumn() + direction[1];

            while (inside(row, column)) {
                Piece piece = pieceAt(board, row, column);
                if (piece != null) {
                    if (piece.getSide() == bySide
                            && (piece.getType() == directType
                            || piece.getType() == PieceType.QUEEN)) {
                        return true;
                    }
                    break;
                }
                row += direction[0];
                column += direction[1];
            }
        }
        return false;
    }

    private static List<Move> pseudoLegalMoves(Position position) {
        List<Move> moves = new ArrayList<>();
        Side side = position.getSideToMove();

        for (java.util.Map.Entry<Square, Piece> entry
                : position.getBoard().getPieces().entrySet()) {
            Square from = entry.getKey();
            Piece piece = entry.getValue();
            if (piece.getSide() != side) {
                continue;
            }

            switch (piece.getType()) {
                case ROOK:
                    addSlidingMoves(position, from, piece, ROOK_DIRECTIONS, moves);
                    break;
                case BISHOP:
                    addSlidingMoves(position, from, piece, BISHOP_DIRECTIONS, moves);
                    break;
                case QUEEN:
                    addSlidingMoves(position, from, piece, ROOK_DIRECTIONS, moves);
                    addSlidingMoves(position, from, piece, BISHOP_DIRECTIONS, moves);
                    break;
                case KNIGHT:
                    addJumpMoves(position, from, piece, KNIGHT_OFFSETS, moves);
                    break;
                case KING:
                    addJumpMoves(position, from, piece, KING_DIRECTIONS, moves);
                    addCastlingMoves(position, from, piece, moves);
                    break;
                case PAWN:
                    addPawnMoves(position, from, piece, moves);
                    break;
                default:
                    throw new IllegalStateException("unknown piece type");
            }
        }

        return moves;
    }

    private static void addSlidingMoves(
            Position position,
            Square from,
            Piece piece,
            int[][] directions,
            List<Move> moves
    ) {
        Board board = position.getBoard();
        for (int[] direction : directions) {
            int row = from.getRow() + direction[0];
            int column = from.getColumn() + direction[1];

            while (inside(row, column)) {
                Square to = new Square(row, column);
                Piece target = board.getPiece(to);
                if (target == null) {
                    moves.add(new Move(from, to));
                } else {
                    if (target.getSide() != piece.getSide()
                            && target.getType() != PieceType.KING) {
                        moves.add(new Move(from, to));
                    }
                    break;
                }
                row += direction[0];
                column += direction[1];
            }
        }
    }

    private static void addJumpMoves(
            Position position,
            Square from,
            Piece piece,
            int[][] offsets,
            List<Move> moves
    ) {
        for (int[] offset : offsets) {
            int row = from.getRow() + offset[0];
            int column = from.getColumn() + offset[1];
            if (!inside(row, column)) {
                continue;
            }

            Square to = new Square(row, column);
            Piece target = position.getBoard().getPiece(to);
            if (target == null
                    || (target.getSide() != piece.getSide()
                    && target.getType() != PieceType.KING)) {
                moves.add(new Move(from, to));
            }
        }
    }

    private static void addPawnMoves(
            Position position,
            Square from,
            Piece pawn,
            List<Move> moves
    ) {
        int direction = pawn.getSide() == Side.WHITE ? 1 : -1;
        int startRow = pawn.getSide() == Side.WHITE ? 1 : 6;
        int promotionRow = pawn.getSide() == Side.WHITE ? 7 : 0;
        int nextRow = from.getRow() + direction;

        if (inside(nextRow, from.getColumn())) {
            Square oneForward = new Square(nextRow, from.getColumn());
            if (position.getBoard().getPiece(oneForward) == null) {
                addPawnMove(from, oneForward, promotionRow, moves);

                int doubleRow = from.getRow() + 2 * direction;
                if (from.getRow() == startRow) {
                    Square twoForward = new Square(doubleRow, from.getColumn());
                    if (position.getBoard().getPiece(twoForward) == null) {
                        moves.add(new Move(from, twoForward));
                    }
                }
            }
        }

        for (int columnOffset : new int[]{-1, 1}) {
            int column = from.getColumn() + columnOffset;
            if (!inside(nextRow, column)) {
                continue;
            }

            Square to = new Square(nextRow, column);
            Piece target = position.getBoard().getPiece(to);
            if (target != null
                    && target.getSide() != pawn.getSide()
                    && target.getType() != PieceType.KING) {
                addPawnMove(from, to, promotionRow, moves);
            } else if (target == null && to.equals(position.getEnPassantTarget())) {
                Piece adjacent = position.getBoard().getPiece(
                        new Square(from.getRow(), column)
                );
                if (isPiece(adjacent, pawn.getSide().opposite(), PieceType.PAWN)) {
                    moves.add(new Move(from, to));
                }
            }
        }
    }

    private static void addPawnMove(
            Square from,
            Square to,
            int promotionRow,
            List<Move> moves
    ) {
        if (to.getRow() == promotionRow) {
            for (PieceType type : PROMOTION_TYPES) {
                moves.add(new Move(from, to, type));
            }
        } else {
            moves.add(new Move(from, to));
        }
    }

    private static void addCastlingMoves(
            Position position,
            Square from,
            Piece king,
            List<Move> moves
    ) {
        int row = king.getSide() == Side.WHITE ? 0 : 7;
        if (!from.equals(new Square(row, 4))) {
            return;
        }

        Side opponent = king.getSide().opposite();
        if (isSquareAttacked(position, from, opponent)) {
            return;
        }

        if (position.canCastleKingside(king.getSide())
                && hasRook(position, king.getSide(), row, 7)
                && isEmpty(position, row, 5)
                && isEmpty(position, row, 6)
                && !isSquareAttacked(position, new Square(row, 5), opponent)
                && !isSquareAttacked(position, new Square(row, 6), opponent)) {
            moves.add(new Move(from, new Square(row, 6)));
        }

        if (position.canCastleQueenside(king.getSide())
                && hasRook(position, king.getSide(), row, 0)
                && isEmpty(position, row, 1)
                && isEmpty(position, row, 2)
                && isEmpty(position, row, 3)
                && !isSquareAttacked(position, new Square(row, 3), opponent)
                && !isSquareAttacked(position, new Square(row, 2), opponent)) {
            moves.add(new Move(from, new Square(row, 2)));
        }
    }

    private static boolean hasRook(Position position, Side side, int row, int column) {
        return isPiece(pieceAt(position.getBoard(), row, column), side, PieceType.ROOK);
    }

    private static boolean isEmpty(Position position, int row, int column) {
        return pieceAt(position.getBoard(), row, column) == null;
    }

    static boolean hasLegalEnPassantCapture(Position position) {
        if (position.getEnPassantTarget() == null) {
            return false;
        }
        for (Move move : legalMoves(position)) {
            if (isEnPassantMove(position, move)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isEnPassantMove(Position position, Move move) {
        Piece movingPiece = position.getBoard().getPiece(move.getFrom());
        return movingPiece != null
                && movingPiece.getType() == PieceType.PAWN
                && move.getTo().equals(position.getEnPassantTarget())
                && position.getBoard().getPiece(move.getTo()) == null
                && move.getFrom().getColumn() != move.getTo().getColumn();
    }

    static boolean isDeadPosition(Position position) {
        List<java.util.Map.Entry<Square, Piece>> nonKings = new ArrayList<>();
        for (java.util.Map.Entry<Square, Piece> entry
                : position.getBoard().getPieces().entrySet()) {
            if (entry.getValue().getType() != PieceType.KING) {
                nonKings.add(entry);
            }
        }

        if (nonKings.isEmpty()) {
            return true;
        }

        if (nonKings.size() == 1) {
            PieceType type = nonKings.get(0).getValue().getType();
            return type == PieceType.BISHOP || type == PieceType.KNIGHT;
        }

        boolean onlyBishops = true;
        Integer bishopSquareColor = null;
        for (java.util.Map.Entry<Square, Piece> entry : nonKings) {
            if (entry.getValue().getType() != PieceType.BISHOP) {
                onlyBishops = false;
                break;
            }
            int squareColor = (entry.getKey().getRow() + entry.getKey().getColumn()) & 1;
            if (bishopSquareColor == null) {
                bishopSquareColor = squareColor;
            } else if (bishopSquareColor != squareColor) {
                return false;
            }
        }
        return onlyBishops;
    }

    static long perft(Position position, int depth) {
        if (depth < 0) {
            throw new IllegalArgumentException("depth must not be negative");
        }
        if (depth == 0) {
            return 1;
        }

        long nodes = 0;
        for (Move move : legalMoves(position)) {
            nodes += perft(position.apply(move), depth - 1);
        }
        return nodes;
    }

    private static Piece pieceAt(Board board, int row, int column) {
        return inside(row, column) ? board.getPiece(new Square(row, column)) : null;
    }

    private static boolean isPiece(Piece piece, Side side, PieceType type) {
        return piece != null && piece.getSide() == side && piece.getType() == type;
    }

    private static boolean inside(int row, int column) {
        return row >= 0 && row < Board.SIZE && column >= 0 && column < Board.SIZE;
    }
}
