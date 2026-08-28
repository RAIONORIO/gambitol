package br.com.raionorio.gambitol;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import br.com.raionorio.gambitol.engine.ChessGame;
import br.com.raionorio.gambitol.engine.DrawClaim;
import br.com.raionorio.gambitol.engine.Move;
import br.com.raionorio.gambitol.engine.Piece;
import br.com.raionorio.gambitol.engine.PieceType;
import br.com.raionorio.gambitol.engine.Side;
import br.com.raionorio.gambitol.engine.Square;

public final class GameViewModel extends ViewModel {

    private final ChessGame game;
    private Square selectedSquare;
    private List<Move> pendingPromotionMoves = Collections.emptyList();

    public GameViewModel() {
        this(new ChessGame());
    }

    GameViewModel(ChessGame game) {
        this.game = game;
    }

    public ChessGame getGame() {
        return game;
    }

    public Square getSelectedSquare() {
        return selectedSquare;
    }

    public List<Move> getLegalMovesForSelection() {
        if (selectedSquare == null || game.isGameOver()) {
            return Collections.emptyList();
        }
        return game.getLegalMovesFrom(selectedSquare);
    }

    public void onSquareTapped(Square square) {
        if (game.isGameOver() || hasPendingPromotion()) {
            return;
        }

        Piece tappedPiece = game.getPosition().getBoard().getPiece(square);
        Side sideToMove = game.getPosition().getSideToMove();

        if (selectedSquare == null) {
            if (tappedPiece != null && tappedPiece.getSide() == sideToMove) {
                selectedSquare = square;
            }
            return;
        }

        if (selectedSquare.equals(square)) {
            selectedSquare = null;
            return;
        }

        List<Move> destinationMoves = new ArrayList<>();
        for (Move move : game.getLegalMovesFrom(selectedSquare)) {
            if (move.getTo().equals(square)) {
                destinationMoves.add(move);
            }
        }

        if (!destinationMoves.isEmpty()) {
            if (destinationMoves.get(0).isPromotion()) {
                pendingPromotionMoves = Collections.unmodifiableList(destinationMoves);
            } else if (game.play(destinationMoves.get(0))) {
                selectedSquare = null;
            }
            return;
        }

        if (tappedPiece != null && tappedPiece.getSide() == sideToMove) {
            selectedSquare = square;
        }
    }

    public boolean isCaptureMove(Move move) {
        Piece destination = game.getPosition().getBoard().getPiece(move.getTo());
        if (destination != null) {
            return true;
        }

        Piece moving = game.getPosition().getBoard().getPiece(move.getFrom());
        return moving != null
                && moving.getType() == PieceType.PAWN
                && move.getFrom().getColumn() != move.getTo().getColumn();
    }

    public boolean hasPendingPromotion() {
        return !pendingPromotionMoves.isEmpty();
    }

    public Side getPendingPromotionSide() {
        if (!hasPendingPromotion()) {
            return null;
        }
        Piece pawn = game.getPosition().getBoard().getPiece(
                pendingPromotionMoves.get(0).getFrom()
        );
        return pawn == null ? null : pawn.getSide();
    }

    public boolean choosePromotion(PieceType type) {
        for (Move move : pendingPromotionMoves) {
            if (move.getPromotion() == type) {
                boolean played = game.play(move);
                if (played) {
                    pendingPromotionMoves = Collections.emptyList();
                    selectedSquare = null;
                }
                return played;
            }
        }
        return false;
    }

    public void cancelPromotion() {
        pendingPromotionMoves = Collections.emptyList();
    }

    public Set<DrawClaim> getAvailableDrawClaims() {
        return game.getAvailableDrawClaims();
    }

    public boolean claimAvailableDraw() {
        Set<DrawClaim> claims = game.getAvailableDrawClaims();
        if (claims.contains(DrawClaim.THREEFOLD_REPETITION)) {
            return game.claimDraw(DrawClaim.THREEFOLD_REPETITION);
        }
        if (claims.contains(DrawClaim.FIFTY_MOVE)) {
            return game.claimDraw(DrawClaim.FIFTY_MOVE);
        }
        return false;
    }

    public void restart() {
        game.restart();
        selectedSquare = null;
        pendingPromotionMoves = Collections.emptyList();
    }
}
