package models;

public class PuzzleLevelLogic implements ExtraLevelLogic{
	int allottedPieces;
	int remainingPieces;
	int allottedMoves;
	int remainingMoves;
	
	public PuzzleLevelLogic(int allottedPieces, int allottedSeconds) {
		this.allottedPieces = allottedPieces;
		this.remainingPieces = allottedPieces;
		this.allottedMoves = allottedMoves;
		this.remainingMoves = allottedMoves;
	}
	
	public void decrementNumberOfPieces() {
		remainingPieces--;
	}
	
	public void decrementCounter() {
		remainingMoves--;
	}

	public void resetAll() {
		remainingPieces = allottedPieces;
		remainingMoves = allottedMoves;
	}
}
