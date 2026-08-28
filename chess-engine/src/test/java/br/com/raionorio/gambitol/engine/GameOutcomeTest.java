package br.com.raionorio.gambitol.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class GameOutcomeTest {

    @Test
    void detectsCheckmateAndRejectsFurtherMoves() {
        ChessGame game = new ChessGame();
        game.play(TestPositions.move("f2f3"));
        game.play(TestPositions.move("e7e5"));
        game.play(TestPositions.move("g2g4"));

        assertTrue(game.play(TestPositions.move("d8h4")));
        assertEquals(GameStatus.CHECKMATE, game.getStatus());
        assertEquals(Side.BLACK, game.getWinner().orElseThrow());
        assertTrue(game.isInCheck());
        assertTrue(game.getLegalMoves().isEmpty());
        assertFalse(game.play(TestPositions.move("e2e4")));
    }

    @Test
    void distinguishesStalemateFromCheckmate() {
        ChessGame stalemate = new ChessGame(TestPositions.fromFen(
                "7k/5Q2/6K1/8/8/8/8/8 b - - 0 1"
        ));
        ChessGame checkmate = new ChessGame(TestPositions.fromFen(
                "7k/6Q1/6K1/8/8/8/8/8 b - - 0 1"
        ));

        assertEquals(GameStatus.STALEMATE, stalemate.getStatus());
        assertFalse(stalemate.isInCheck());
        assertEquals(GameStatus.CHECKMATE, checkmate.getStatus());
        assertTrue(checkmate.isInCheck());
    }

    @Test
    void detectsOnlyConservativeDeadPositions() {
        ChessGame bareKings = new ChessGame(TestPositions.fromFen(
                "7k/8/8/8/8/8/8/K7 w - - 0 1"
        ));
        ChessGame bishop = new ChessGame(TestPositions.fromFen(
                "7k/8/8/8/8/8/2B5/K7 w - - 0 1"
        ));
        ChessGame twoKnights = new ChessGame(TestPositions.fromFen(
                "7k/8/8/8/8/8/1NN5/K7 w - - 0 1"
        ));

        assertEquals(GameStatus.DRAW_DEAD_POSITION, bareKings.getStatus());
        assertEquals(GameStatus.DRAW_DEAD_POSITION, bishop.getStatus());
        assertEquals(GameStatus.IN_PROGRESS, twoKnights.getStatus());
    }

    @Test
    void bishopsOnOneColorAreDeadButOppositeColorsAreNotAssumedDead() {
        ChessGame sameColor = new ChessGame(TestPositions.fromFen(
                "7k/8/8/8/4b3/8/2B5/K7 w - - 0 1"
        ));
        ChessGame oppositeColors = new ChessGame(TestPositions.fromFen(
                "7k/8/8/8/5b2/8/2B5/K7 w - - 0 1"
        ));

        assertEquals(GameStatus.DRAW_DEAD_POSITION, sameColor.getStatus());
        assertEquals(GameStatus.IN_PROGRESS, oppositeColors.getStatus());
    }
}
