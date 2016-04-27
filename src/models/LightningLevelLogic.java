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
	private int allottedSeconds;
	
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
		this.setAllottedSeconds(allottedSeconds);
		this.remainingSeconds = allottedSeconds;
	}
	
	/**
	 * Gets the total number of squares on the board
	 * @return the total number of squares on the board
	 */
	public int getTotalSquares() {
		return totalBoardSquares;
	}
	
	/**
	 * Decrements the number of unmarked board squares by one.
	 */
	public void decrementUnmarkedSquares() {
		unmarkedBoardSquares--;
	}
	
	/**
	 * Gets the current number of unmarked squares on the board.
	 * @return the current number of unmarked squares on the board
	 */
	public int getUnmarkedSquares() {
		return unmarkedBoardSquares;
	}
	
	/**
	 * Decrements the number of seconds remaining by one.
	 */
	public void decrementRemainingSeconds() {
		remainingSeconds--;
	}

	public int getAllottedSeconds() {
		return allottedSeconds;
	}

	public void setAllottedSeconds(int allottedSeconds) {
		this.allottedSeconds = allottedSeconds;
	}
}
