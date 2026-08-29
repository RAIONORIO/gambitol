package br.com.raionorio.gambitol;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.TextViewCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.raionorio.gambitol.engine.ChessGame;
import br.com.raionorio.gambitol.engine.DrawClaim;
import br.com.raionorio.gambitol.engine.GameStatus;
import br.com.raionorio.gambitol.engine.Move;
import br.com.raionorio.gambitol.engine.Piece;
import br.com.raionorio.gambitol.engine.PieceType;
import br.com.raionorio.gambitol.engine.Side;
import br.com.raionorio.gambitol.engine.Square;

public class MainActivity extends AppCompatActivity {

    private final TextView[] squareViews = new TextView[64];

    private GameViewModel viewModel;
    private GridLayout boardGrid;
    private TextView blackPlayer;
    private TextView whitePlayer;
    private TextView statusText;
    private MaterialButton claimDrawButton;
    private AlertDialog promotionDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(GameViewModel.class);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        boardGrid = findViewById(R.id.board_grid);
        blackPlayer = findViewById(R.id.black_player);
        whitePlayer = findViewById(R.id.white_player);
        statusText = findViewById(R.id.status_text);
        claimDrawButton = findViewById(R.id.claim_draw_button);
        MaterialButton restartButton = findViewById(R.id.restart_button);

        createBoardViews();
        claimDrawButton.setOnClickListener(view -> claimDraw());
        restartButton.setOnClickListener(view -> confirmRestart());
        render();
    }

    private void createBoardViews() {
        boardGrid.setRowCount(8);
        boardGrid.setColumnCount(8);

        for (int displayRow = 0; displayRow < 8; displayRow++) {
            int logicalRow = 7 - displayRow;
            for (int column = 0; column < 8; column++) {
                Square square = new Square(logicalRow, column);
                AppCompatTextView squareView = new AppCompatTextView(this);
                squareView.setId(View.generateViewId());
                squareView.setGravity(Gravity.CENTER);
                squareView.setIncludeFontPadding(false);
                squareView.setFocusable(true);
                squareView.setClickable(true);
                squareView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                        squareView,
                        20,
                        48,
                        2,
                        TypedValue.COMPLEX_UNIT_SP
                );

                GridLayout.LayoutParams params = new GridLayout.LayoutParams(
                        GridLayout.spec(displayRow, 1, 1f),
                        GridLayout.spec(column, 1, 1f)
                );
                params.width = 0;
                params.height = 0;
                params.setGravity(Gravity.FILL);
                squareView.setLayoutParams(params);
                squareView.setOnClickListener(view -> onSquareTapped(square));

                boardGrid.addView(squareView);
                squareViews[index(square)] = squareView;
            }
        }
    }

    private void onSquareTapped(Square square) {
        viewModel.onSquareTapped(square);
        render();

        if (viewModel.hasPendingPromotion()) {
            showPromotionDialog();
        }
    }

    private void render() {
        ChessGame game = viewModel.getGame();
        List<Move> selectedMoves = viewModel.getLegalMovesForSelection();
        Set<Square> legalDestinations = new HashSet<>();
        Set<Square> captureDestinations = new HashSet<>();
        for (Move move : selectedMoves) {
            legalDestinations.add(move.getTo());
            if (viewModel.isCaptureMove(move)) {
                captureDestinations.add(move.getTo());
            }
        }

        Square selected = viewModel.getSelectedSquare();
        Move lastMove = game.getLastMove().orElse(null);
        Square checkedKing = game.isInCheck()
                ? game.getPosition().getBoard().findKing(game.getPosition().getSideToMove())
                : null;

        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Square square = new Square(row, column);
                renderSquare(
                        square,
                        selected,
                        lastMove,
                        checkedKing,
                        legalDestinations.contains(square),
                        captureDestinations.contains(square)
                );
            }
        }

        Side sideToMove = game.getPosition().getSideToMove();
        blackPlayer.setText(
                sideToMove == Side.BLACK
                        ? R.string.black_player_turn
                        : R.string.black_player_waiting
        );
        whitePlayer.setText(
                sideToMove == Side.WHITE
                        ? R.string.white_player_turn
                        : R.string.white_player_waiting
        );
        blackPlayer.setSelected(sideToMove == Side.BLACK && !game.isGameOver());
        whitePlayer.setSelected(sideToMove == Side.WHITE && !game.isGameOver());

        statusText.setText(statusText(game));
        boolean canClaim = !viewModel.getAvailableDrawClaims().isEmpty();
        claimDrawButton.setVisibility(canClaim ? View.VISIBLE : View.GONE);
        if (canClaim) {
            claimDrawButton.setText(claimButtonText(viewModel.getAvailableDrawClaims()));
        }

        if (viewModel.hasPendingPromotion() && promotionDialog == null) {
            boardGrid.post(this::showPromotionDialog);
        }
    }

    private void renderSquare(
            Square square,
            Square selected,
            Move lastMove,
            Square checkedKing,
            boolean legalDestination,
            boolean captureDestination
    ) {
        TextView view = squareViews[index(square)];
        Piece piece = viewModel.getGame().getPosition().getBoard().getPiece(square);
        boolean isSelected = square.equals(selected);
        boolean isLastMove = lastMove != null
                && (square.equals(lastMove.getFrom()) || square.equals(lastMove.getTo()));
        boolean isCheckedKing = square.equals(checkedKing);

        int baseColor = ContextCompat.getColor(
                this,
                ((square.getRow() + square.getColumn()) & 1) == 0
                        ? R.color.board_dark
                        : R.color.board_light
        );
        int fillColor = isLastMove
                ? ContextCompat.getColor(this, R.color.board_last_move)
                : baseColor;
        int strokeColor = Color.TRANSPARENT;
        int strokeWidth = 0;

        if (captureDestination) {
            strokeColor = ContextCompat.getColor(this, R.color.capture_target);
            strokeWidth = dp(4);
        }
        if (isSelected) {
            fillColor = ContextCompat.getColor(this, R.color.board_selected);
            strokeColor = ContextCompat.getColor(this, R.color.brand_gold);
            strokeWidth = dp(4);
        }
        if (isCheckedKing) {
            fillColor = ContextCompat.getColor(this, R.color.danger_check);
            strokeColor = ContextCompat.getColor(this, R.color.danger_outline);
            strokeWidth = dp(4);
        }

        GradientDrawable background = new GradientDrawable();
        background.setColor(fillColor);
        if (strokeWidth > 0) {
            background.setStroke(strokeWidth, strokeColor);
        }
        view.setBackground(background);

        if (piece != null) {
            view.setText(pieceSymbol(piece));
            view.setTextColor(ContextCompat.getColor(
                    this,
                    piece.getSide() == Side.WHITE ? R.color.piece_white : R.color.piece_black
            ));
            int shadowColor = ContextCompat.getColor(
                    this,
                    piece.getSide() == Side.WHITE ? R.color.piece_black : R.color.piece_white
            );
            float shadowRadius = piece.getSide() == Side.WHITE ? 2f : 1.25f;
            view.setShadowLayer(shadowRadius, 0, 0, shadowColor);
        } else if (legalDestination) {
            view.setText(captureDestination ? R.string.capture_marker : R.string.legal_move_marker);
            int legalMarkerColor = ((square.getRow() + square.getColumn()) & 1) == 0
                    ? R.color.legal_move_on_dark
                    : R.color.legal_move_on_light;
            view.setTextColor(ContextCompat.getColor(
                    this,
                    captureDestination ? R.color.capture_target : legalMarkerColor
            ));
            view.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
        } else {
            view.setText("");
            view.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
        }

        view.setSelected(isSelected);
        view.setActivated(legalDestination);
        view.setClickable(!viewModel.getGame().isGameOver() && !viewModel.hasPendingPromotion());
        view.setContentDescription(squareDescription(
                square,
                piece,
                isSelected,
                legalDestination,
                captureDestination,
                isCheckedKing
        ));
    }

    private String squareDescription(
            Square square,
            Piece piece,
            boolean selected,
            boolean legal,
            boolean capture,
            boolean checkedKing
    ) {
        StringBuilder description = new StringBuilder();
        if (piece == null) {
            description.append(getString(R.string.square_empty, square.toAlgebraic()));
        } else {
            description.append(getString(
                    R.string.square_piece,
                    square.toAlgebraic(),
                    sideName(piece.getSide()),
                    pieceName(piece.getType())
            ));
        }
        if (checkedKing) {
            description.append(", ").append(getString(R.string.accessibility_in_check));
        }
        if (selected) {
            description.append(", ").append(getString(R.string.accessibility_selected));
        }
        if (capture) {
            description.append(", ").append(getString(R.string.accessibility_capture_available));
        } else if (legal) {
            description.append(", ").append(getString(R.string.accessibility_move_available));
        }
        return description.toString();
    }

    private CharSequence statusText(ChessGame game) {
        GameStatus status = game.getStatus();
        switch (status) {
            case CHECKMATE:
                return getString(
                        R.string.status_checkmate,
                        sideName(game.getWinner().orElse(Side.WHITE))
                );
            case STALEMATE:
                return getString(R.string.status_stalemate);
            case DRAW_DEAD_POSITION:
                return getString(R.string.status_dead_position);
            case DRAW_FIVEFOLD_REPETITION:
                return getString(R.string.status_fivefold);
            case DRAW_SEVENTY_FIVE_MOVE:
                return getString(R.string.status_seventy_five_move);
            case DRAW_CLAIMED_THREEFOLD_REPETITION:
                return getString(R.string.status_claimed_threefold);
            case DRAW_CLAIMED_FIFTY_MOVE:
                return getString(R.string.status_claimed_fifty_move);
            case IN_PROGRESS:
            default:
                if (game.isInCheck()) {
                    return getString(
                            R.string.status_check,
                            sideName(game.getPosition().getSideToMove())
                    );
                }
                return getString(
                        R.string.status_turn,
                        sideName(game.getPosition().getSideToMove())
                );
        }
    }

    private int claimButtonText(Set<DrawClaim> claims) {
        if (claims.contains(DrawClaim.THREEFOLD_REPETITION)) {
            return R.string.claim_threefold;
        }
        return R.string.claim_fifty_move;
    }

    private void claimDraw() {
        if (viewModel.claimAvailableDraw()) {
            render();
        }
    }

    private void confirmRestart() {
        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.restart_title)
                .setMessage(R.string.restart_message)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.restart, (dialog, which) -> {
                    viewModel.restart();
                    render();
                })
                .show();
    }

    private void showPromotionDialog() {
        if (!viewModel.hasPendingPromotion()
                || (promotionDialog != null && promotionDialog.isShowing())) {
            return;
        }

        Side side = viewModel.getPendingPromotionSide();
        PieceType[] choices = {
                PieceType.QUEEN,
                PieceType.ROOK,
                PieceType.BISHOP,
                PieceType.KNIGHT
        };
        String[] labels = new String[choices.length];
        for (int index = 0; index < choices.length; index++) {
            labels[index] = pieceSymbol(new Piece(side, choices[index]))
                    + "  "
                    + pieceName(choices[index]);
        }

        promotionDialog = new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.promotion_title)
                .setItems(labels, (dialog, which) -> {
                    viewModel.choosePromotion(choices[which]);
                    render();
                })
                .setNegativeButton(R.string.cancel, (dialog, which) -> {
                    viewModel.cancelPromotion();
                    render();
                })
                .create();
        promotionDialog.setOnCancelListener(dialog -> {
            viewModel.cancelPromotion();
            render();
        });
        promotionDialog.setOnDismissListener(dialog -> promotionDialog = null);
        promotionDialog.show();
    }

    private String pieceSymbol(Piece piece) {
        switch (piece.getType()) {
            case KING:
                return "♚";
            case QUEEN:
                return "♛";
            case ROOK:
                return "♜";
            case BISHOP:
                return "♝";
            case KNIGHT:
                return "♞";
            case PAWN:
                return "♟";
            default:
                throw new IllegalStateException("unknown piece type");
        }
    }

    private String pieceName(PieceType type) {
        switch (type) {
            case KING:
                return getString(R.string.piece_king);
            case QUEEN:
                return getString(R.string.piece_queen);
            case ROOK:
                return getString(R.string.piece_rook);
            case BISHOP:
                return getString(R.string.piece_bishop);
            case KNIGHT:
                return getString(R.string.piece_knight);
            case PAWN:
                return getString(R.string.piece_pawn);
            default:
                throw new IllegalStateException("unknown piece type");
        }
    }

    private String sideName(Side side) {
        return getString(side == Side.WHITE ? R.string.side_white : R.string.side_black);
    }

    private int index(Square square) {
        return square.getRow() * 8 + square.getColumn();
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
