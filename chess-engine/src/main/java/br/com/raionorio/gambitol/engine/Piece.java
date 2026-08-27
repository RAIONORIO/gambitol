package br.com.raionorio.gambitol.engine;

import java.util.Objects;

public final class Piece {

    private final Side side;
    private final PieceType type;

    public Piece(Side side, PieceType type) {
        this.side = Objects.requireNonNull(side, "side must not be null");
        this.type = Objects.requireNonNull(type, "type must not be null");
    }

    public Side getSide() {
        return side;
    }

    public PieceType getType() {
        return type;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Piece)) {
            return false;
        }

        Piece other = (Piece) object;
        return side == other.side && type == other.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(side, type);
    }
}
