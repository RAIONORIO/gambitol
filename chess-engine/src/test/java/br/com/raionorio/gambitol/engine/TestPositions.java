package br.com.raionorio.gambitol.engine;

final class TestPositions {

    private TestPositions() {
    }

    static Position fromFen(String fen) {
        String[] fields = fen.split(" ");
        if (fields.length != 6) {
            throw new IllegalArgumentException("FEN must contain six fields");
        }

        Piece[] squares = new Piece[64];
        String[] ranks = fields[0].split("/");
        if (ranks.length != 8) {
            throw new IllegalArgumentException("FEN board must contain eight ranks");
        }

        for (int fenRank = 0; fenRank < ranks.length; fenRank++) {
            int row = 7 - fenRank;
            int column = 0;
            for (char symbol : ranks[fenRank].toCharArray()) {
                if (Character.isDigit(symbol)) {
                    column += symbol - '0';
                } else {
                    squares[Board.index(row, column)] = piece(symbol);
                    column++;
                }
            }
            if (column != 8) {
                throw new IllegalArgumentException("each FEN rank must contain eight squares");
            }
        }

        Side sideToMove = fields[1].equals("w") ? Side.WHITE : Side.BLACK;
        int castlingRights = 0;
        if (fields[2].contains("K")) {
            castlingRights |= Position.WHITE_KINGSIDE;
        }
        if (fields[2].contains("Q")) {
            castlingRights |= Position.WHITE_QUEENSIDE;
        }
        if (fields[2].contains("k")) {
            castlingRights |= Position.BLACK_KINGSIDE;
        }
        if (fields[2].contains("q")) {
            castlingRights |= Position.BLACK_QUEENSIDE;
        }

        Square enPassantTarget = fields[3].equals("-")
                ? null
                : Square.fromAlgebraic(fields[3]);

        return new Position(
                new Board(squares),
                sideToMove,
                castlingRights,
                enPassantTarget,
                Integer.parseInt(fields[4]),
                Integer.parseInt(fields[5])
        );
    }

    static Move move(String coordinateMove) {
        if (coordinateMove.length() != 4 && coordinateMove.length() != 5) {
            throw new IllegalArgumentException("move must use coordinate notation");
        }

        Square from = Square.fromAlgebraic(coordinateMove.substring(0, 2));
        Square to = Square.fromAlgebraic(coordinateMove.substring(2, 4));
        if (coordinateMove.length() == 4) {
            return new Move(from, to);
        }

        char promotion = Character.toLowerCase(coordinateMove.charAt(4));
        PieceType promotionType;
        switch (promotion) {
            case 'q':
                promotionType = PieceType.QUEEN;
                break;
            case 'r':
                promotionType = PieceType.ROOK;
                break;
            case 'b':
                promotionType = PieceType.BISHOP;
                break;
            case 'n':
                promotionType = PieceType.KNIGHT;
                break;
            default:
                throw new IllegalArgumentException("invalid promotion symbol");
        }
        return new Move(from, to, promotionType);
    }

    private static Piece piece(char symbol) {
        Side side = Character.isUpperCase(symbol) ? Side.WHITE : Side.BLACK;
        PieceType type;
        switch (Character.toLowerCase(symbol)) {
            case 'k':
                type = PieceType.KING;
                break;
            case 'q':
                type = PieceType.QUEEN;
                break;
            case 'r':
                type = PieceType.ROOK;
                break;
            case 'b':
                type = PieceType.BISHOP;
                break;
            case 'n':
                type = PieceType.KNIGHT;
                break;
            case 'p':
                type = PieceType.PAWN;
                break;
            default:
                throw new IllegalArgumentException("invalid FEN piece");
        }
        return new Piece(side, type);
    }
}
