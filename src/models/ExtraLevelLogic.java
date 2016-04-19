package models;

/**
 * Additional methods for Levels of different types.
 */
public interface ExtraLevelLogic {
	/** Decrements a counter such as a timer. */ 
	public void decrementCounter();
	
	/**
	 * Resets a counter such as a timer.
	 * Also resets other fields.
	 */
	public void resetAll();
}
