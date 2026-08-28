package br.com.raionorio.gambitol.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MoveTest {

    @Test
    void storesOriginDestinationAndPromotion() {
        Move move = new Move(
                Square.fromAlgebraic("a7"),
                Square.fromAlgebraic("a8"),
                PieceType.QUEEN
        );

        assertEquals(Square.fromAlgebraic("a7"), move.getFrom());
        assertEquals(Square.fromAlgebraic("a8"), move.getTo());
        assertEquals(PieceType.QUEEN, move.getPromotion());
        assertEquals("a7a8=QUEEN", move.toString());
    }

    @Test
    void rejectsNullAndSameSquare() {
        Square e4 = Square.fromAlgebraic("e4");

        assertThrows(NullPointerException.class, () -> new Move(null, e4));
        assertThrows(NullPointerException.class, () -> new Move(e4, null));
        assertThrows(IllegalArgumentException.class, () -> new Move(e4, e4));
    }

    @Test
    void rejectsKingAndPawnPromotion() {
        Square a7 = Square.fromAlgebraic("a7");
        Square a8 = Square.fromAlgebraic("a8");

        assertThrows(
                IllegalArgumentException.class,
                () -> new Move(a7, a8, PieceType.KING)
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> new Move(a7, a8, PieceType.PAWN)
        );
    }

    @Test
    void equalityIncludesPromotionChoice() {
        Move queen = TestPositions.move("a7a8q");
        Move anotherQueen = TestPositions.move("a7a8q");
        Move knight = TestPositions.move("a7a8n");

        assertEquals(queen, anotherQueen);
        assertEquals(queen.hashCode(), anotherQueen.hashCode());
        assertNotEquals(queen, knight);
    }
}
