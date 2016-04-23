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
	int allottedMoves;
	
	/** Number of moves remaining to complete the level. */
	int remainingMoves;
	
	/**
	 * Constructor to initialize PuzzleLevelLogic fields.
	 * 
	 * @param allottedPieces	Number of pieces present in the level.
	 * @param allottedMoves		Number of moves allotted to complete the level.
	 */
	public PuzzleLevelLogic(int allottedPieces, int allottedMoves) {
		this.allottedPieces = allottedPieces;
		this.remainingPieces = allottedPieces;
		this.allottedMoves = allottedMoves;
		this.remainingMoves = allottedMoves;
	}
	
	/**
	 * Decrements the number of pieces that have not been placed on the level board by one.
	 */
	public void decrementRemainingPieces() {
		remainingPieces--;
	}
	
	// TODO: add incrementRemainingPieces()
	
	/**
	 * Decrements the number of moves remaining by one.
	 */
	public void decrementCounter() {
		remainingMoves--;
	}

	/**
	 * Resets all counter variables (remainingPieces and remainingMoves) to their original values.
	 */
	public void resetAll() {
		remainingPieces = allottedPieces;
		remainingMoves = allottedMoves;
	}
}
