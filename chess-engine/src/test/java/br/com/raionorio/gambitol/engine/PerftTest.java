package br.com.raionorio.gambitol.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PerftTest {

    @Test
    void matchesInitialPositionReferenceCounts() {
        ChessGame game = new ChessGame();

        assertEquals(20, game.perft(1));
        assertEquals(400, game.perft(2));
        assertEquals(8_902, game.perft(3));
        assertEquals(197_281, game.perft(4));
    }

    @Test
    void matchesKiwipeteCastlingReferenceCounts() {
        ChessGame game = new ChessGame(TestPositions.fromFen(
                "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1"
        ));

        assertEquals(48, game.perft(1));
        assertEquals(2_039, game.perft(2));
    }

    @Test
    void matchesEnPassantReferenceCounts() {
        ChessGame game = new ChessGame(TestPositions.fromFen(
                "8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 w - - 0 1"
        ));

        assertEquals(14, game.perft(1));
        assertEquals(191, game.perft(2));
        assertEquals(2_812, game.perft(3));
    }
}
