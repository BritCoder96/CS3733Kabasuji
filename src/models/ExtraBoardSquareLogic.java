package models;

/**
 * Class to be extended by Squares used by Boards.
 */
public abstract class ExtraBoardSquareLogic {
	/** Indicates whether the board square is covered by a piece. */
	boolean isCovered;
	
	/**
	 * Constructor to initialize ExtraBoardSquareLogic fields. 
	 */
	public ExtraBoardSquareLogic () {
		isCovered = false;
	}
	
	/**
	 * Check if the board square is covered by a piece.
	 * 
	 * @return Whether the board square is covered.
	 */
	public boolean getCovered(boolean covered) {
		return isCovered;
	}
	
	/**
	 * Indicate that the board square is covered by a piece.
	 * 
	 * @param toggle Indicates whether the board square is covered.
	 */
	public void setCovered(boolean toggle) {
		isCovered = toggle;
	}
}
