package models;

/**
 * Extra level logic exclusive to lightning levels.
 */
public class LightningLevelLogic implements ExtraLevelLogic{
	/** Number of board squares present in the level. */
	int totalBoardSquares;
	
	/** Number of unmarked board squares present in a level. */
	int unmarkedBoardSquares;
	
	/** Number of seconds allotted to complete the level. */
	int allottedSeconds;
	
	/** Number of seconds remaining to complete the level. */
	int remainingSeconds;
	
	/**
	 * Constructor to initialize LightningLevelLogic fields.
	 * 
	 * @param numberOfBoardSquares	Number of board squares present in the level.
	 * @param allottedSeconds		Number of seconds allotted to complete the level. 
	 */
	public LightningLevelLogic(int numberOfBoardSquares, int allottedSeconds) {
		this.totalBoardSquares = numberOfBoardSquares;
		this.unmarkedBoardSquares = numberOfBoardSquares;
		this.allottedSeconds = allottedSeconds;
		this.remainingSeconds = allottedSeconds;
	}
	
	/**
	 * Decrements the number of unmarked board squares by one.
	 */
	public void decrementUnmarkedSquares() {
		unmarkedBoardSquares--;
	}
	
	/**
	 * Decrements the number of seconds remaining by one.
	 */
	public void decrementRemainingSeconds() {
		remainingSeconds--;
	}
}
