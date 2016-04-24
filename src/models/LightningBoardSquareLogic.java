package models;

/**
 * Extra square logic exclusive to squares for a lightning level's board.
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
	 */
	public void setMarked() {
		isMarked = true;
	}
}
