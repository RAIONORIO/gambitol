package br.com.raionorio.gambitol.engine;

public enum Side {
    WHITE,
    BLACK;

    public Side opposite() {
        return this == WHITE ? BLACK : WHITE;
    }
}
