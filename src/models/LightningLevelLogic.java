package models;

public class LightningLevelLogic implements ExtraLevelLogic{
	int numberOfBoardSquares;
	int numberOfUnmarkedBoardSquares;
	int allottedSeconds;
	int remainingSeconds;
	
	public LightningLevelLogic(int numberOfBoardSquares, int allottedSeconds) {
		this.numberOfBoardSquares = numberOfBoardSquares;
		this.numberOfUnmarkedBoardSquares = numberOfBoardSquares;
		this.allottedSeconds = allottedSeconds;
		this.remainingSeconds = allottedSeconds;
	}
	
	public void decrementUnmarkedSquares() {
		numberOfUnmarkedBoardSquares--;
	}
	
	@Override
	public void decrementCounter() {
		remainingSeconds--;
	}

	@Override
	public void resetAll() {
		numberOfUnmarkedBoardSquares = numberOfBoardSquares;
		remainingSeconds = allottedSeconds;
	}
}
