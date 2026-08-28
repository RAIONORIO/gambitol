package br.com.raionorio.gambitol.engine;

public enum GameStatus {
    IN_PROGRESS,
    CHECKMATE,
    STALEMATE,
    DRAW_DEAD_POSITION,
    DRAW_FIVEFOLD_REPETITION,
    DRAW_SEVENTY_FIVE_MOVE,
    DRAW_CLAIMED_THREEFOLD_REPETITION,
    DRAW_CLAIMED_FIFTY_MOVE;

    public boolean isTerminal() {
        return this != IN_PROGRESS;
    }

    public boolean isDraw() {
        return this != IN_PROGRESS && this != CHECKMATE;
    }
}
