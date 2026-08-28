package br.com.raionorio.gambitol.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void createsStandardInitialPlacement() {
        Board board = Board.initial();

        assertEquals(32, board.getPieceCount());
        assertEquals(16, board.getPieceCount(Side.WHITE));
        assertEquals(16, board.getPieceCount(Side.BLACK));
        assertEquals(
                new Piece(Side.WHITE, PieceType.QUEEN),
                board.getPiece(Square.fromAlgebraic("d1"))
        );
        assertEquals(
                new Piece(Side.BLACK, PieceType.KING),
                board.getPiece(Square.fromAlgebraic("e8"))
        );
        assertEquals(
                new Piece(Side.WHITE, PieceType.PAWN),
                board.getPiece(Square.fromAlgebraic("a2"))
        );
    }

    @Test
    void exposedPiecesCannotMutateTheBoard() {
        Board board = Board.initial();

        assertThrows(
                UnsupportedOperationException.class,
                () -> board.getPieces().clear()
        );
        assertEquals(32, board.getPieceCount());
    }
}
