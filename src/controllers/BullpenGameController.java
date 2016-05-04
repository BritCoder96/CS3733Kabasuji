package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import models.Piece;
import models.Square;
import views.BullpenView;
import views.GameScreen;
import views.PieceView;

/**
 * Controller that allows a piece to be picked up from the bullpen in the game or dropped into the bullpen.
 * It should be on the bullpen itself, not the pieces inside it.
 * @author bhuchley
 *
 */
public class BullpenGameController implements MouseListener, MouseMotionListener {
	
	/** The bullpen that this is watching for clicks */
	BullpenView bpView;
	/** The game screen that keeps track of dragging widgets */
	GameScreen gameScreen;
	
	/**
	 * Make a new BullpenGameController that watches the given bullpen view
	 * and tells the given game screen about events.
	 * @param bpv the bullpen view to watch
	 * @param gs the game screen to inform about pieces being moved in/out of the bullpen
	 */
	public BullpenGameController(BullpenView bpv, GameScreen gs) {
		bpView = bpv;
		gameScreen = gs;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (gameScreen.getActiveDraggingWidget() == null) {
			// If no dragged piece, pick up the piece that was clicked
			PieceView clickedPiece = bpView.getPieceAtY(arg0.getY());
			// If a piece was clicked, then remove it from the bullpen and set it to the dragging piece
			if (clickedPiece != null) {
				gameScreen.getLevel().getBullpen().removePiece(clickedPiece.getPiece());
				bpView.removePieceView(clickedPiece);
				gameScreen.setActiveDraggingPiece(clickedPiece);
			}
		} else {
			// If a piece was being dragged, put it in the bullpen
			Piece draggedPiece = gameScreen.getActiveDraggingWidget().getPiece();
			gameScreen.getLevel().getBullpen().addPiece(draggedPiece);
			bpView.addPiece(draggedPiece);
			gameScreen.removeActiveDraggingWidget();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		int offsetX = bpView.getX();
		int offsetY = bpView.getY() + bpView.getScrollOffset();
		arg0.translatePoint(offsetX, offsetY);
		gameScreen.dispatchEvent(arg0);
	}

}
