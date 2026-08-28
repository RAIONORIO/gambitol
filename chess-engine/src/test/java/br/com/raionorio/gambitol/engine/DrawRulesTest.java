package br.com.raionorio.gambitol.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DrawRulesTest {

    @Test
    void threefoldIsClaimableButNotAutomatic() {
        ChessGame game = new ChessGame();

        repeatKnightCycle(game, 2);

        assertEquals(GameStatus.IN_PROGRESS, game.getStatus());
        assertTrue(
                game.getAvailableDrawClaims().contains(DrawClaim.THREEFOLD_REPETITION)
        );
        assertTrue(game.claimDraw(DrawClaim.THREEFOLD_REPETITION));
        assertEquals(GameStatus.DRAW_CLAIMED_THREEFOLD_REPETITION, game.getStatus());
    }

    @Test
    void fivefoldIsAutomatic() {
        ChessGame game = new ChessGame();

        repeatKnightCycle(game, 4);

        assertEquals(GameStatus.DRAW_FIVEFOLD_REPETITION, game.getStatus());
    }

    @Test
    void fiftyMoveRuleIsClaimableAtOneHundredHalfmoves() {
        ChessGame game = new ChessGame(TestPositions.fromFen(
                "7k/8/8/8/8/8/R7/K7 w - - 99 1"
        ));

        assertTrue(game.play(TestPositions.move("a2b2")));

        assertEquals(GameStatus.IN_PROGRESS, game.getStatus());
        assertEquals(100, game.getPosition().getHalfmoveClock());
        assertTrue(game.getAvailableDrawClaims().contains(DrawClaim.FIFTY_MOVE));
    }

    @Test
    void seventyFiveMoveRuleIsAutomaticAtOneHundredFiftyHalfmoves() {
        ChessGame game = new ChessGame(TestPositions.fromFen(
                "7k/8/8/8/8/8/R7/K7 w - - 149 1"
        ));

        assertTrue(game.play(TestPositions.move("a2b2")));

        assertEquals(GameStatus.DRAW_SEVENTY_FIVE_MOVE, game.getStatus());
    }

    @Test
    void checkmateTakesPrecedenceOverSeventyFiveMoveRule() {
        ChessGame game = new ChessGame(TestPositions.fromFen(
                "7k/8/5KQ1/8/8/8/8/8 w - - 149 1"
        ));

        assertTrue(game.play(TestPositions.move("g6g7")));

        assertEquals(GameStatus.CHECKMATE, game.getStatus());
        assertEquals(Side.WHITE, game.getWinner().orElseThrow());
    }

    @Test
    void pawnMoveAndCaptureResetHalfmoveClock() {
        ChessGame pawnGame = new ChessGame(TestPositions.fromFen(
                "7k/8/8/8/8/8/P7/K7 w - - 42 1"
        ));
        ChessGame captureGame = new ChessGame(TestPositions.fromFen(
                "7k/8/8/8/8/8/R6r/K7 w - - 42 1"
        ));

        assertTrue(pawnGame.play(TestPositions.move("a2a3")));
        assertEquals(0, pawnGame.getPosition().getHalfmoveClock());

        assertTrue(captureGame.play(TestPositions.move("a2h2")));
        assertEquals(0, captureGame.getPosition().getHalfmoveClock());
    }

    @Test
    void unavailableClaimDoesNotEndGame() {
        ChessGame game = new ChessGame();

        assertFalse(game.claimDraw(DrawClaim.FIFTY_MOVE));
        assertEquals(GameStatus.IN_PROGRESS, game.getStatus());
    }

    @Test
    void returningPiecesDoNotRepeatLostCastlingRights() {
        ChessGame game = new ChessGame(TestPositions.fromFen(
                "r3k2r/8/8/8/8/8/8/R3K2R w KQkq - 0 1"
        ));

        game.play(TestPositions.move("h1h2"));
        game.play(TestPositions.move("h8h7"));
        game.play(TestPositions.move("h2h1"));
        game.play(TestPositions.move("h7h8"));

        assertEquals(1, game.currentPositionOccurrenceCount());
        assertFalse(
                game.getAvailableDrawClaims().contains(DrawClaim.THREEFOLD_REPETITION)
        );
    }

    private void repeatKnightCycle(ChessGame game, int cycles) {
        for (int cycle = 0; cycle < cycles; cycle++) {
            assertTrue(game.play(TestPositions.move("g1f3")));
            assertTrue(game.play(TestPositions.move("g8f6")));
            assertTrue(game.play(TestPositions.move("f3g1")));
            assertTrue(game.play(TestPositions.move("f6g8")));
        }
    }
}
