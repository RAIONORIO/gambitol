package br.com.raionorio.gambitol.engine;

public final class Square {

    private static final int BOARD_SIZE = 8;

    private final int row;
    private final int column;

    public Square(int row, int column) {
        if (row < 0 || row >= BOARD_SIZE) {
            throw new IllegalArgumentException("row must be between 0 and 7");
        }

        if (column < 0 || column >= BOARD_SIZE) {
            throw new IllegalArgumentException("column must be between 0 and 7");
        }

        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public static Square fromAlgebraic(String notation) {
        if (notation == null || notation.length() != 2) {
            throw new IllegalArgumentException("notation must be between a1 and h8");
        }

        char file = notation.charAt(0);
        char rank = notation.charAt(1);

        if (file < 'a' || file > 'h' || rank < '1' || rank > '8') {
            throw new IllegalArgumentException("notation must be between a1 and h8");
        }

        int column = file - 'a';
        int row = rank - '1';
        return new Square(row, column);
    }

    public String toAlgebraic() {
        char file = (char) ('a' + column);
        int rank = row + 1;
        return "" + file + rank;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Square)) {
            return false;
        }

        Square other = (Square) object;

        return row == other.row && column == other.column;
    }


    @Override
    public int hashCode() {
        int result = Integer.hashCode(row);
        result = 31 * result + Integer.hashCode(column);
        return result;
    }
}
