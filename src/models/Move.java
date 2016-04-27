package models;

/**
 * Action related to actual gameplay or building.
 */
public interface Move {
	/**
	 * Execute logic pertaining to the move.
	 * 
	 * @return
	 */
	public boolean execute();
	
	/**
	 * Check whether conditions allow the move to be executed.
	 * 
	 * @return
	 */
	public boolean isValid();
	
	/**
	 * Reverse executed changes.
	 * @return 
	 */
	public boolean undo();
}
