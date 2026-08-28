package br.com.raionorio.gambitol.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public final class ChessGame {

    private Position position;
    private final List<Move> moveHistory = new ArrayList<>();
    private final Map<String, Integer> repetitionCounts = new HashMap<>();
    private GameStatus status;
    private Side winner;

    public ChessGame() {
        resetTo(Position.initial());
    }

    ChessGame(Position position) {
        resetTo(position);
    }

    public Position getPosition() {
        return position;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Optional<Side> getWinner() {
        return Optional.ofNullable(winner);
    }

    public boolean isGameOver() {
        return status.isTerminal();
    }

    public boolean isInCheck() {
        return MoveGenerator.isInCheck(position, position.getSideToMove());
    }

    public List<Move> getLegalMoves() {
        if (isGameOver()) {
            return Collections.emptyList();
        }
        return immutableList(MoveGenerator.legalMoves(position));
    }

    public List<Move> getLegalMovesFrom(Square from) {
        Objects.requireNonNull(from, "from must not be null");
        List<Move> moves = new ArrayList<>();
        for (Move move : getLegalMoves()) {
            if (move.getFrom().equals(from)) {
                moves.add(move);
            }
        }
        return immutableList(moves);
    }

    public boolean play(Move move) {
        Objects.requireNonNull(move, "move must not be null");
        if (isGameOver()) {
            return false;
        }

        List<Move> legalMoves = MoveGenerator.legalMoves(position);
        if (!legalMoves.contains(move)) {
            return false;
        }

        position = position.apply(move);
        moveHistory.add(move);
        repetitionCounts.merge(currentRepetitionKey(), 1, Integer::sum);
        evaluateTerminalState();
        return true;
    }

    public Set<DrawClaim> getAvailableDrawClaims() {
        if (isGameOver()) {
            return Collections.emptySet();
        }

        EnumSet<DrawClaim> claims = EnumSet.noneOf(DrawClaim.class);
        if (currentPositionOccurrenceCount() >= 3) {
            claims.add(DrawClaim.THREEFOLD_REPETITION);
        }
        if (position.getHalfmoveClock() >= 100) {
            claims.add(DrawClaim.FIFTY_MOVE);
        }
        return Collections.unmodifiableSet(EnumSet.copyOf(claims));
    }

    public boolean claimDraw(DrawClaim claim) {
        Objects.requireNonNull(claim, "claim must not be null");
        if (!getAvailableDrawClaims().contains(claim)) {
            return false;
        }

        status = claim == DrawClaim.THREEFOLD_REPETITION
                ? GameStatus.DRAW_CLAIMED_THREEFOLD_REPETITION
                : GameStatus.DRAW_CLAIMED_FIFTY_MOVE;
        winner = null;
        return true;
    }

    public Optional<Move> getLastMove() {
        if (moveHistory.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(moveHistory.get(moveHistory.size() - 1));
    }

    public List<Move> getMoveHistory() {
        return immutableList(moveHistory);
    }

    public void restart() {
        resetTo(Position.initial());
    }

    public long perft(int depth) {
        return MoveGenerator.perft(position, depth);
    }

    int currentPositionOccurrenceCount() {
        return repetitionCounts.getOrDefault(currentRepetitionKey(), 0);
    }

    private void resetTo(Position newPosition) {
        position = Objects.requireNonNull(newPosition, "position must not be null");
        moveHistory.clear();
        repetitionCounts.clear();
        repetitionCounts.put(currentRepetitionKey(), 1);
        status = GameStatus.IN_PROGRESS;
        winner = null;
        evaluateTerminalState();
    }

    private void evaluateTerminalState() {
        List<Move> legalMoves = MoveGenerator.legalMoves(position);
        if (legalMoves.isEmpty()) {
            if (MoveGenerator.isInCheck(position, position.getSideToMove())) {
                status = GameStatus.CHECKMATE;
                winner = position.getSideToMove().opposite();
            } else {
                status = GameStatus.STALEMATE;
                winner = null;
            }
            return;
        }

        if (MoveGenerator.isDeadPosition(position)) {
            status = GameStatus.DRAW_DEAD_POSITION;
            winner = null;
            return;
        }

        if (currentPositionOccurrenceCount() >= 5) {
            status = GameStatus.DRAW_FIVEFOLD_REPETITION;
            winner = null;
            return;
        }

        if (position.getHalfmoveClock() >= 150) {
            status = GameStatus.DRAW_SEVENTY_FIVE_MOVE;
            winner = null;
            return;
        }

        status = GameStatus.IN_PROGRESS;
        winner = null;
    }

    private String currentRepetitionKey() {
        Square effectiveEnPassantTarget = MoveGenerator.hasLegalEnPassantCapture(position)
                ? position.getEnPassantTarget()
                : null;
        return position.repetitionKey(effectiveEnPassantTarget);
    }

    private List<Move> immutableList(List<Move> moves) {
        return Collections.unmodifiableList(new ArrayList<>(moves));
    }
}
