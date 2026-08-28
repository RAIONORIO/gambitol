package br.com.raionorio.gambitol.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class MoveGenerationTest {

    @Test
    void initialPositionHasTwentyLegalMoves() {
        ChessGame game = new ChessGame();

        assertEquals(20, game.getLegalMoves().size());
        assertEquals(2, game.getLegalMovesFrom(Square.fromAlgebraic("e2")).size());
        assertEquals(2, game.getLegalMovesFrom(Square.fromAlgebraic("g1")).size());
        assertTrue(game.getLegalMovesFrom(Square.fromAlgebraic("a1")).isEmpty());
    }

    @Test
    void slidingPiecesStopAtFirstOccupiedSquare() {
        ChessGame game = new ChessGame(TestPositions.fromFen(
                "4k3/8/8/3p4/3R1P2/8/8/4K3 w - - 0 1"
        ));

        List<Move> rookMoves = game.getLegalMovesFrom(Square.fromAlgebraic("d4"));

        assertTrue(rookMoves.contains(TestPositions.move("d4d5")));
        assertFalse(rookMoves.contains(TestPositions.move("d4d6")));
        assertFalse(rookMoves.contains(TestPositions.move("d4f4")));
        assertTrue(rookMoves.contains(TestPositions.move("d4e4")));
    }

    @Test
    void knightJumpsOverOccupiedSquares() {
        ChessGame game = new ChessGame();

        assertTrue(
                game.getLegalMovesFrom(Square.fromAlgebraic("b1"))
                        .contains(TestPositions.move("b1c3"))
        );
    }

    @Test
    void pinnedPieceCannotExposeItsKing() {
        ChessGame game = new ChessGame(TestPositions.fromFen(
                "k3r3/8/8/8/8/8/4R3/4K3 w - - 0 1"
        ));

        List<Move> rookMoves = game.getLegalMovesFrom(Square.fromAlgebraic("e2"));

        assertFalse(rookMoves.contains(TestPositions.move("e2d2")));
        assertTrue(rookMoves.contains(TestPositions.move("e2e8")));
    }

    @Test
    void attackDetectionDoesNotUseLegalMoveGeneration() {
        Position position = TestPositions.fromFen(
                "4k3/4n3/8/5K2/8/8/4R3/8 w - - 0 1"
        );

        assertTrue(
                MoveGenerator.isSquareAttacked(
                        position,
                        Square.fromAlgebraic("f5"),
                        Side.BLACK
                )
        );
    }

    @Test
    void illegalMoveDoesNotMutateGame() {
        ChessGame game = new ChessGame();
        Position before = game.getPosition();

        assertFalse(game.play(TestPositions.move("e2e5")));
        assertEquals(before, game.getPosition());
        assertEquals(Side.WHITE, game.getPosition().getSideToMove());
    }
}
