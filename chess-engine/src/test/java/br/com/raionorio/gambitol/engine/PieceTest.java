package br.com.raionorio.gambitol.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    void storesSideAndType() {
        Piece piece = new Piece(Side.WHITE, PieceType.KNIGHT);

        assertEquals(Side.WHITE, piece.getSide());
        assertEquals(PieceType.KNIGHT, piece.getType());
    }

    @Test
    void rejectsNullSide() {
        assertThrows(
                NullPointerException.class,
                () -> new Piece(null, PieceType.KING)
        );
    }

    @Test
    void rejectsNullType() {
        assertThrows(
                NullPointerException.class,
                () -> new Piece(Side.BLACK, null)
        );
    }

    @Test
    void piecesWithSameSideAndTypeAreEqual() {
        Piece first = new Piece(Side.WHITE, PieceType.QUEEN);
        Piece second = new Piece(Side.WHITE, PieceType.QUEEN);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void piecesWithDifferentSidesAreNotEqual() {
        Piece whitePawn = new Piece(Side.WHITE, PieceType.PAWN);
        Piece blackPawn = new Piece(Side.BLACK, PieceType.PAWN);

        assertNotEquals(whitePawn, blackPawn);
    }

    @Test
    void piecesWithDifferentTypesAreNotEqual() {
        Piece rook = new Piece(Side.BLACK, PieceType.ROOK);
        Piece bishop = new Piece(Side.BLACK, PieceType.BISHOP);

        assertNotEquals(rook, bishop);
    }
}
