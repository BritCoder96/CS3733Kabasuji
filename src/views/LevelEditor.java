package views;

import java.awt.AWTEvent;
import java.awt.event.MouseEvent;

import models.EditorMode;
import models.Level;
import models.Piece;

/**
 * 
 * Interface for Level Editors to implement that holds basic functions that
 * need to be implemented for them.
 * 
 * @author ejcerini
 */
public interface LevelEditor extends LevelModifiedListener {

	/**
	 * Allows the gameboard to be updated when the mode that the editor is in is shifted
	 * 
	 * @param ebv - the new board view
	 */
	public void setGameBoard(EditorBoardView ebv);
	
	/**
	 * Tells the editor to shift the mode to em.
	 * 
	 * @param em - The mode to shift the editor into.
	 */
	public void setEditorMode(EditorMode em);
	
	/**
	 * Tells the editor to update the options display.
	 */
	public void updateOptionsDisplay();
	
	
	/**
	 * Get the level as it is currently under construction.
	 * @return the level under construction
	 */
	public Level getLevel();
	
	/**
	 * Sets the piece currently being dragged to the given piece view.
	 * @param pv the piece to start dragging
	 */
	public void setDraggingPiece(PieceView pv);
	
	/**
	 * Sets the piece currently being dragged to the given piece.
	 * @param p the piece to start dragging
	 */
	public void setDraggingPiece(Piece p);
	
	/**
	 * Adds the given piece view to the frame and sets it to the dragging piece.
	 * @param pv the piece to add and start dragging
	 */
	public void addDraggingPiece(PieceView pv);
	
	/**
	 * Remove the piece currently being dragged.
	 */
	public void removeDraggingPiece();
	
	/**
	 * Gets the piece currently being dragged.
	 * @return the piece currently being dragged
	 */
	public PieceView getDraggingPiece();

	/**
	 * Dispatch a mouse event to the screen. Used to drag pieces around.
	 * This is implemented by default in JPanels but we need to replicate it here
	 * so that it can be accessed via this interface.
	 * @param arg0 the event to pass to the screen
	 */
	public void dispatchEvent(AWTEvent arg0);

	/**
	 * Moves the dragging widget, if any, to the given point.
	 * @param x the x coordinate of the point to move it to
	 * @param y the y coordinate of the point to move it to
	 */
	public void moveDraggingWidgetTo(int x, int y);
}
