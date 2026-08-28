package br.com.raionorio.gambitol.engine;

import java.util.Objects;

public final class Move {

    private final Square from;
    private final Square to;
    private final PieceType promotion;

    public Move(Square from, Square to) {
        this(from, to, null);
    }

    public Move(Square from, Square to, PieceType promotion) {
        this.from = Objects.requireNonNull(from, "from must not be null");
        this.to = Objects.requireNonNull(to, "to must not be null");

        if (from.equals(to)) {
            throw new IllegalArgumentException("origin and destination must differ");
        }

        if (promotion == PieceType.KING || promotion == PieceType.PAWN) {
            throw new IllegalArgumentException("promotion must be queen, rook, bishop or knight");
        }

        this.promotion = promotion;
    }

    public Square getFrom() {
        return from;
    }

    public Square getTo() {
        return to;
    }

    public PieceType getPromotion() {
        return promotion;
    }

    public boolean isPromotion() {
        return promotion != null;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Move)) {
            return false;
        }

        Move other = (Move) object;
        return from.equals(other.from)
                && to.equals(other.to)
                && promotion == other.promotion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, promotion);
    }

    @Override
    public String toString() {
        String value = from.toAlgebraic() + to.toAlgebraic();
        return promotion == null ? value : value + "=" + promotion.name();
    }
}
