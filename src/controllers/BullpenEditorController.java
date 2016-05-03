package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import models.Piece;
import models.Square;
import views.BullpenView;
import views.GameScreen;
import views.LevelEditor;
import views.PieceView;

/**
 * Controller that allows a piece to be picked up from the bullpen in the editor or dropped into the bullpen.
 * It should be on the bullpen itself, not the pieces inside it.
 * @author bhuchley
 *
 */
public class BullpenEditorController implements MouseListener, MouseMotionListener {
	
	/** The bullpen that this is watching for clicks */
	BullpenView bpView;
	/** The level editor screen that keeps track of dragging widgets */
	LevelEditor editorScreen;
	
	/**
	 * Make a new BullpenEditorController that watches the given bullpen view
	 * and tells the given level editor screen about events.
	 * @param bpv the bullpen view to watch
	 * @param levelEditor the level editor screen to inform about pieces being moved in/out of the bullpen
	 */
	public BullpenEditorController(BullpenView bpv, LevelEditor levelEditor) {
		bpView = bpv;
		editorScreen = levelEditor;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (editorScreen.getDraggingPiece() == null) {
			// If no dragged piece, pick up the piece that was clicked
			PieceView clickedPiece = bpView.getPieceAtY(arg0.getY());
			// If a piece was clicked, then remove it from the bullpen and set it to the dragging piece
			if (clickedPiece != null) {
				bpView.removePieceView(clickedPiece);
				editorScreen.addDraggingPiece(clickedPiece);
			}
		} else {
			// If a piece was being dragged, put it in the bullpen
			Piece draggedPiece = editorScreen.getDraggingPiece().getPiece();
			bpView.addPiece(draggedPiece);
			editorScreen.removeDraggingPiece();
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
		editorScreen.dispatchEvent(arg0);
	}

}
