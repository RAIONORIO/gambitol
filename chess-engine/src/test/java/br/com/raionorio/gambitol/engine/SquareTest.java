package br.com.raionorio.gambitol.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SquareTest {

    @Test
    void storesRowAndColumn() {
        Square square = new Square(3, 4);

        assertEquals(3, square.getRow());
        assertEquals(4, square.getColumn());
    }

    @Test
    void rejectsRowOutsideBoard() {
        assertThrows(IllegalArgumentException.class, () -> new Square(-1, 4));
        assertThrows(IllegalArgumentException.class, () -> new Square(8, 4));
    }

    @Test
    void rejectsColumnOutsideBoard() {
        assertThrows(IllegalArgumentException.class, () -> new Square(3, -1));
        assertThrows(IllegalArgumentException.class, () -> new Square(3, 8));
    }

    @Test
    void squaresWithSameCoordinatesAreEqual() {
        Square first = new Square(3, 4);
        Square second = new Square(3, 4);

        assertEquals(first, second);
    }

    @Test
    void squaresWithDifferentCoordinatesAreNotEqual() {
        Square first = new Square(3, 4);
        Square second = new Square(4, 3);

        assertNotEquals(first, second);
    }

    @Test
    void convertsCoordinatesToAlgebraicNotation() {
        assertEquals("a1", new Square(0, 0).toAlgebraic());
        assertEquals("e4", new Square(3, 4).toAlgebraic());
        assertEquals("h8", new Square(7, 7).toAlgebraic());
    }

    @Test
    void createsSquareFromAlgebraicNotation() {
        assertEquals(new Square(0, 0), Square.fromAlgebraic("a1"));
        assertEquals(new Square(3, 4), Square.fromAlgebraic("e4"));
        assertEquals(new Square(7, 7), Square.fromAlgebraic("h8"));
    }

    @Test
    void rejectsNullAlgebraicNotation() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Square.fromAlgebraic(null)
        );
    }

    @Test
    void rejectsAlgebraicNotationWithWrongLength() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Square.fromAlgebraic("a")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> Square.fromAlgebraic("a10")
        );
    }

    @Test
    void rejectsAlgebraicNotationOutsideBoard() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Square.fromAlgebraic("i1")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> Square.fromAlgebraic("a9")
        );
    }

    @Test
    void equalSquaresHaveSameHashCode() {
        Square first = new Square(3, 4);
        Square second = new Square(3, 4);

        assertEquals(first.hashCode(), second.hashCode());
    }
}
