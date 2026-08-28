package br.com.raionorio.gambitol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.raionorio.gambitol.engine.Move;
import br.com.raionorio.gambitol.engine.PieceType;
import br.com.raionorio.gambitol.engine.Side;
import br.com.raionorio.gambitol.engine.Square;

public class GameViewModelTest {

    @Test
    public void selectionAndMoveComeFromEngineState() {
        GameViewModel viewModel = new GameViewModel();
        Square e2 = Square.fromAlgebraic("e2");
        Square e4 = Square.fromAlgebraic("e4");

        viewModel.onSquareTapped(e2);

        assertEquals(e2, viewModel.getSelectedSquare());
        assertTrue(viewModel.getLegalMovesForSelection().contains(new Move(e2, e4)));

        viewModel.onSquareTapped(e4);

        assertNull(viewModel.getSelectedSquare());
        assertEquals(Side.BLACK, viewModel.getGame().getPosition().getSideToMove());
        assertEquals(1, viewModel.getGame().getMoveHistory().size());
    }

    @Test
    public void tappingOpponentWithoutSelectionDoesNothing() {
        GameViewModel viewModel = new GameViewModel();

        viewModel.onSquareTapped(Square.fromAlgebraic("e7"));

        assertNull(viewModel.getSelectedSquare());
        assertTrue(viewModel.getGame().getMoveHistory().isEmpty());
    }

    @Test
    public void tappingSelectedSquareCancelsSelection() {
        GameViewModel viewModel = new GameViewModel();
        Square b1 = Square.fromAlgebraic("b1");

        viewModel.onSquareTapped(b1);
        viewModel.onSquareTapped(b1);

        assertNull(viewModel.getSelectedSquare());
    }

    @Test
    public void tappingAnotherFriendlyPieceChangesSelection() {
        GameViewModel viewModel = new GameViewModel();

        viewModel.onSquareTapped(Square.fromAlgebraic("b1"));
        viewModel.onSquareTapped(Square.fromAlgebraic("g1"));

        assertEquals(Square.fromAlgebraic("g1"), viewModel.getSelectedSquare());
    }

    @Test
    public void restartClearsGameAndPresentationState() {
        GameViewModel viewModel = new GameViewModel();
        viewModel.onSquareTapped(Square.fromAlgebraic("e2"));
        viewModel.onSquareTapped(Square.fromAlgebraic("e4"));
        viewModel.onSquareTapped(Square.fromAlgebraic("e7"));

        viewModel.restart();

        assertNull(viewModel.getSelectedSquare());
        assertFalse(viewModel.hasPendingPromotion());
        assertTrue(viewModel.getGame().getMoveHistory().isEmpty());
        assertEquals(Side.WHITE, viewModel.getGame().getPosition().getSideToMove());
    }

    @Test
    public void promotionWaitsForAnExplicitChoice() {
        GameViewModel viewModel = new GameViewModel();
        play(viewModel, "a2", "a4");
        play(viewModel, "h7", "h5");
        play(viewModel, "a4", "a5");
        play(viewModel, "h5", "h4");
        play(viewModel, "a5", "a6");
        play(viewModel, "h4", "h3");
        play(viewModel, "a6", "b7");
        play(viewModel, "h3", "g2");

        viewModel.onSquareTapped(Square.fromAlgebraic("b7"));
        viewModel.onSquareTapped(Square.fromAlgebraic("a8"));

        assertTrue(viewModel.hasPendingPromotion());
        assertEquals(8, viewModel.getGame().getMoveHistory().size());
        assertTrue(viewModel.choosePromotion(PieceType.KNIGHT));
        assertFalse(viewModel.hasPendingPromotion());
        assertEquals(
                PieceType.KNIGHT,
                viewModel.getGame().getPosition().getBoard()
                        .getPiece(Square.fromAlgebraic("a8"))
                        .getType()
        );
    }

    private void play(GameViewModel viewModel, String from, String to) {
        viewModel.onSquareTapped(Square.fromAlgebraic(from));
        viewModel.onSquareTapped(Square.fromAlgebraic(to));
    }
}
