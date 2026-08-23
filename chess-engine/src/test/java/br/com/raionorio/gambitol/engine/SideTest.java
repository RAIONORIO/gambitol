package br.com.raionorio.gambitol.engine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SideTest {

    @Test
    void oppositeOfWhiteIsBlack() {
        // Arrange
        Side side = Side.WHITE;

        // Act
        Side opposite = side.opposite();

        // Assert
        assertEquals(Side.BLACK, opposite);
    }

    @Test
    void oppositeOfBlackIsWhite() {
        // Arrange
        Side side = Side.BLACK;

        // Act
        Side opposite = side.opposite();

        // Assert
        assertEquals(Side.WHITE, opposite);
    }
}
