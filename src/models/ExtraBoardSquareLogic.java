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
	 * Indicate that the board square is covered by a piece.
	 */
	public void setCovered(boolean covered) {
		isCovered = covered;
	}
}
