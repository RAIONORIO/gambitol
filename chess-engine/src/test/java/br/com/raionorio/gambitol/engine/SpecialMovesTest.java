package br.com.raionorio.gambitol.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class SpecialMovesTest {

    @Test
    void castlingMovesKingAndRookAtomically() {
        ChessGame game = new ChessGame(TestPositions.fromFen(
                "r3k2r/8/8/8/8/8/8/R3K2R w KQkq - 0 1"
        ));

        assertTrue(game.play(TestPositions.move("e1g1")));

        assertEquals(
                new Piece(Side.WHITE, PieceType.KING),
                game.getPosition().getBoard().getPiece(Square.fromAlgebraic("g1"))
        );
        assertEquals(
                new Piece(Side.WHITE, PieceType.ROOK),
                game.getPosition().getBoard().getPiece(Square.fromAlgebraic("f1"))
        );
        assertNull(game.getPosition().getBoard().getPiece(Square.fromAlgebraic("h1")));
        assertFalse(game.getPosition().canCastleKingside(Side.WHITE));
        assertFalse(game.getPosition().canCastleQueenside(Side.WHITE));
    }

    @Test
    void castlingCannotCrossAnAttackedSquare() {
        ChessGame game = new ChessGame(TestPositions.fromFen(
                "r3k2r/8/8/8/2b5/8/8/R3K2R w KQkq - 0 1"
        ));

        List<Move> kingMoves = game.getLegalMovesFrom(Square.fromAlgebraic("e1"));

        assertFalse(kingMoves.contains(TestPositions.move("e1g1")));
        assertTrue(kingMoves.contains(TestPositions.move("e1c1")));
    }

    @Test
    void movedRookNeverRegainsCastlingRight() {
        ChessGame game = new ChessGame(TestPositions.fromFen(
                "r3k2r/8/8/8/8/8/8/R3K2R w KQkq - 0 1"
        ));

        assertTrue(game.play(TestPositions.move("h1h2")));
        assertTrue(game.play(TestPositions.move("h8h7")));
        assertTrue(game.play(TestPositions.move("h2h1")));
        assertTrue(game.play(TestPositions.move("h7h8")));

        assertFalse(game.getPosition().canCastleKingside(Side.WHITE));
        assertFalse(game.getPosition().canCastleKingside(Side.BLACK));
        assertTrue(game.getPosition().canCastleQueenside(Side.WHITE));
        assertTrue(game.getPosition().canCastleQueenside(Side.BLACK));
    }

    @Test
    void enPassantRemovesThePassedPawnAndExpires() {
        ChessGame game = new ChessGame();
        game.play(TestPositions.move("e2e4"));
        game.play(TestPositions.move("a7a6"));
        game.play(TestPositions.move("e4e5"));
        game.play(TestPositions.move("d7d5"));

        assertTrue(
                game.getLegalMovesFrom(Square.fromAlgebraic("e5"))
                        .contains(TestPositions.move("e5d6"))
        );
        assertTrue(game.play(TestPositions.move("e5d6")));
        assertNull(game.getPosition().getBoard().getPiece(Square.fromAlgebraic("d5")));
        assertEquals(
                new Piece(Side.WHITE, PieceType.PAWN),
                game.getPosition().getBoard().getPiece(Square.fromAlgebraic("d6"))
        );
        assertNull(game.getPosition().getEnPassantTarget());
    }

    @Test
    void enPassantCannotExposeOwnKing() {
        ChessGame game = new ChessGame(TestPositions.fromFen(
                "k7/8/8/r4pPK/8/8/8/8 w - f6 0 1"
        ));

        assertFalse(
                game.getLegalMovesFrom(Square.fromAlgebraic("g5"))
                        .contains(TestPositions.move("g5f6"))
        );
    }

    @Test
    void promotionOffersEveryValidPieceAndIsAtomic() {
        ChessGame game = new ChessGame(TestPositions.fromFen(
                "7k/P7/8/8/8/8/8/7K w - - 0 1"
        ));

        List<Move> promotions = game.getLegalMovesFrom(Square.fromAlgebraic("a7"));

        assertEquals(4, promotions.size());
        assertTrue(promotions.contains(TestPositions.move("a7a8q")));
        assertTrue(promotions.contains(TestPositions.move("a7a8r")));
        assertTrue(promotions.contains(TestPositions.move("a7a8b")));
        assertTrue(promotions.contains(TestPositions.move("a7a8n")));

        assertTrue(game.play(TestPositions.move("a7a8n")));
        assertEquals(
                new Piece(Side.WHITE, PieceType.KNIGHT),
                game.getPosition().getBoard().getPiece(Square.fromAlgebraic("a8"))
        );
    }

    @Test
    void promotionCanCapture() {
        ChessGame game = new ChessGame(TestPositions.fromFen(
                "1r5k/P7/8/8/8/8/8/7K w - - 0 1"
        ));

        assertTrue(game.play(TestPositions.move("a7b8q")));
        assertEquals(3, game.getPosition().getBoard().getPieceCount());
        assertEquals(
                new Piece(Side.WHITE, PieceType.QUEEN),
                game.getPosition().getBoard().getPiece(Square.fromAlgebraic("b8"))
        );
    }
}
