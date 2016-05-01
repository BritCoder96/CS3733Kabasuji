package models;

/**
 * Extra level logic exclusive to puzzle levels.
 */
public class PuzzleLevelLogic implements ExtraLevelLogic{
	/** Number of pieces present in the level. */
	int allottedPieces;
	
	/** Number of pieces that have not been placed on the level board. */
	int remainingPieces;
	
	/** Number of moves allotted to complete the level. */
	private int allottedMoves;
	
	/** Number of moves remaining to complete the level. */
	private int remainingMoves;
	
	/**
	 * Constructor to initialize PuzzleLevelLogic fields.
	 * 
	 * @param allottedPieces	Number of pieces present in the level.
	 * @param allottedMoves		Number of moves allotted to complete the level.
	 */
	public PuzzleLevelLogic(int allottedPieces, int allottedMoves) {
		this.allottedPieces = allottedPieces;
		this.remainingPieces = allottedPieces;
		this.setAllottedMoves(allottedMoves);
		this.setRemainingMoves(allottedMoves);
	}
	
	/**
	 * Decrements the number of pieces that have not been placed on the level board by one.
	 */
	public void decrementRemainingPieces() {
		remainingPieces--;
	}
	
	/**
	 * Increments the number of pieces that have not been placed on the level board by one.
	 */
	public void incrementRemainingPieces() {
		remainingPieces++;
	}
	
	/**
	 * Gets the number of pieces currently in the level.
	 * @return the number of pieces in the level
	 */
	public int getAllottedPieces() {
		return allottedPieces;
	}
	
	/**
	 * Decrements the number of moves remaining by one.
	 */
	public void decrementRemainingMoves() {
		setRemainingMoves(getRemainingMoves() - 1);
	}

	public int getAllottedMoves() {
		return allottedMoves;
	}

	public void setAllottedMoves(int allottedMoves) {
		this.allottedMoves = allottedMoves;
	}

	public void incrementRemainingMoves() {
		setRemainingMoves(getRemainingMoves() + 1);
	}

	public int getRemainingMoves() {
		return remainingMoves;
	}

	public void setRemainingMoves(int remainingMoves) {
		this.remainingMoves = remainingMoves;
	}
}
