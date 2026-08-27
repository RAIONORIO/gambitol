package br.com.raionorio.gambitol.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.EnumSet;

import org.junit.jupiter.api.Test;

class PieceTypeTest {

    @Test
    void containsStandardChessPieceTypes() {
        EnumSet<PieceType> expected = EnumSet.of(
                PieceType.KING,
                PieceType.QUEEN,
                PieceType.ROOK,
                PieceType.BISHOP,
                PieceType.KNIGHT,
                PieceType.PAWN
        );

        assertEquals(expected, EnumSet.allOf(PieceType.class));
    }
}