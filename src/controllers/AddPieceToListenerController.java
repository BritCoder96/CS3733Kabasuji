package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import models.Piece;
import views.AddPieceListener;
import views.AddPieceOverlay;
import views.BuilderTitle;
import views.BullpenView;
import views.KabasujiFrame;
import views.LevelList;
/**
 * Controller that opens the add piece overlay.
 * 
 * @author bhuchley
 */
public class AddPieceToListenerController implements MouseListener {
	KabasujiFrame frame;
	
	/** The listener that the selected piece should be added to */
	private AddPieceListener thingToAddTo;
	/** The piece to add to that bullpen */
	private Piece pieceToAdd;
	
	/**
	 * Make a new add piece controller with the given frame and prior screen
	 * 
	 * @param frame - the static frame that is being passed along throughout the application
	 * @param btitle - the previous screen, in this case, the Builder Title.
	 */
	public AddPieceToListenerController(KabasujiFrame frame, AddPieceListener thingToAddTo, Piece piece) {
		this.frame = frame;
		this.thingToAddTo = thingToAddTo;
		this.pieceToAdd = piece;
	}

	/**
	 * Returns to the last frame (as this is assumed to be called from the add piece overlay)
	 * and adds the piece to the bullpen that was specified on construction.
	 */
	public void mouseClicked(MouseEvent e) {
		frame.returnToLastContentPane();
		thingToAddTo.onLevelChanged();
		thingToAddTo.addPiece(pieceToAdd);
	}

	public void mouseEntered(MouseEvent e) {
		// Do nothing
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Do nothing
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Do nothing, we want it to trigger on a click
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Do nothing, we want it to trigger on a click
	}

}
