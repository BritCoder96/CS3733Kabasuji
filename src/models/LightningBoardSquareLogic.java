package models;

/**
 * Extra square logic exclusive to squares for a lightning level's board.
 * 
 * @author sthuynh
 */
public class LightningBoardSquareLogic extends ExtraBoardSquareLogic {
	/** Indicates whether the lightning board square has been marked after being covered by a piece. */
	boolean isMarked;
	
	/**
	 * Constructor to initialize LightningBoardSquareLogic fields. 
	 */
	public LightningBoardSquareLogic() {
		isMarked = false;
	}
	
	/**
	 * Indicates that the lightning board square has been marked.
	 * @param b 
	 */
	public void setMarked(boolean b) {
		isMarked = b;
	}
	
	/**
	 * Indicates that the lightning board square has been marked.
	 * @return whether or not the square has been marked.
	 */
	public boolean getMarked() {
		return isMarked;
	}
	
}
