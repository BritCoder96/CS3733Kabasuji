package views;

import models.EditorMode;

/**
 * 
 * Interface for Level Editors to implement that holds basic functions that
 * need to be implemented for them.
 * 
 * @author ejcerini
 */
public interface LevelEditor {

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
	
}
