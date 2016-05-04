package models;

/**
 * Class to be extended by Squares used by Boards.
 * 
 * @author sthuynh
 */
public abstract class ExtraBoardSquareLogic {
	/** Indicates whether the board square is covered by a piece. */
	boolean isCovered;
	/** Indicates whether a hint is associated with the board square. */
	boolean isHint;
	
	/**
	 * Constructor to initialize ExtraBoardSquareLogic fields. 
	 */
	public ExtraBoardSquareLogic () {
		isCovered = false;
		isHint = false;
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
	
	/**
	 * Indicate that the board square is associated with a hint.
	 * 
	 * @param hint The hint to associate.
	 */
	public void setHint(boolean hint) {
		isHint = hint;
	}
	
	/**
	 * Determine that the board square is associated with a hint.
	 * 
	 * @return Whether the board square is associated with a hint.
	 */
	public boolean getIsHint() {
		return isHint;
	}
}
