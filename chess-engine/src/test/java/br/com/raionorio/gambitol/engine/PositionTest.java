package br.com.raionorio.gambitol.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    void initialPositionContainsCompleteGameState() {
        Position position = Position.initial();

        assertEquals(Side.WHITE, position.getSideToMove());
        assertTrue(position.canCastleKingside(Side.WHITE));
        assertTrue(position.canCastleQueenside(Side.WHITE));
        assertTrue(position.canCastleKingside(Side.BLACK));
        assertTrue(position.canCastleQueenside(Side.BLACK));
        assertNull(position.getEnPassantTarget());
        assertEquals(0, position.getHalfmoveClock());
        assertEquals(1, position.getFullmoveNumber());
    }

    @Test
    void pawnDoubleStepCreatesTemporaryEnPassantTarget() {
        ChessGame game = new ChessGame();

        assertTrue(game.play(TestPositions.move("e2e4")));

        Position position = game.getPosition();
        assertEquals(Side.BLACK, position.getSideToMove());
        assertEquals(Square.fromAlgebraic("e3"), position.getEnPassantTarget());
        assertEquals(0, position.getHalfmoveClock());
        assertEquals(1, position.getFullmoveNumber());

        assertTrue(game.play(TestPositions.move("g8f6")));
        assertNull(game.getPosition().getEnPassantTarget());
        assertEquals(1, game.getPosition().getHalfmoveClock());
        assertEquals(2, game.getPosition().getFullmoveNumber());
    }

    @Test
    void restartRestoresEveryInitialField() {
        ChessGame game = new ChessGame();
        game.play(TestPositions.move("e2e4"));
        game.play(TestPositions.move("e7e5"));

        game.restart();

        assertEquals(Position.initial(), game.getPosition());
        assertEquals(GameStatus.IN_PROGRESS, game.getStatus());
        assertTrue(game.getMoveHistory().isEmpty());
        assertFalse(game.getLastMove().isPresent());
    }
}
