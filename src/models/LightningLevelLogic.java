package models;

/**
 * Extra level logic exclusive to lightning levels.
 * 
 * @author sthuynh
 */
public class LightningLevelLogic implements ExtraLevelLogic{
	/** Number of board squares present in the level. */
	int totalBoardSquares;
	
	/** Number of unmarked board squares present in a level. */
	int unmarkedBoardSquares;
	
	/** Number of seconds allotted to complete the level. */
	private int allottedSeconds;
	
	/** Number of seconds remaining to complete the level. */
	private int remainingSeconds;
	
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
		this.setRemainingSeconds(allottedSeconds);
	}
	
	/**
	 * Gets the total number of squares on the board.
	 * 
	 * @return The total number of squares on the board.
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
	 * 
	 * @return The current number of unmarked squares on the board.
	 */
	public int getUnmarkedSquares() {
		return unmarkedBoardSquares;
	}
	
	/**
	 * Decrements the number of seconds remaining by one.
	 */
	public void decrementRemainingSeconds() {
		setRemainingSeconds(getRemainingSeconds() - 1);
	}
	
	/**
	 * Gets the number of allotted seconds.
	 * 
	 * @return The number of allotted seconds.
	 */
	public int getAllottedSeconds() {
		return allottedSeconds;
	}

	/**
	 * Sets the number of allotted seconds to the specified value.
	 * 
	 * @param allottedSeconds	The value the number of allotted seconds will be set to.
	 */
	public void setAllottedSeconds(int allottedSeconds) {
		this.allottedSeconds = allottedSeconds;
	}

	public int getRemainingSeconds() {
		return remainingSeconds;
	}

	public void setRemainingSeconds(int remainingSeconds) {
		this.remainingSeconds = remainingSeconds;
	}
}
