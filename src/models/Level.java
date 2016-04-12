package models;

import java.util.HashSet;

public class Level {
	Board board;
	HashSet<Piece> bullpen;
	int levelNumber;
	int numberOfStars;
	LevelType lvlType;
	ExtraLevelLogic levelLogic;
	
	public Level (int levelNumber, int numberOfStars, Board board, HashSet<Piece> bullpen, LevelType lvlType, ExtraLevelLogic levelLogic) {
		this.levelNumber = levelNumber;
		this.numberOfStars = numberOfStars;
		this.board = board;
		this.bullpen = bullpen;
		this.lvlType = lvlType;
		this.levelLogic = levelLogic;
	}
	
	public void rotatePiece(Piece piece) {
		
	}
	
	public void flipPiece(Piece piece) {
		
	}
	
	public int getNumStars() {
		return numberOfStars;
	}
	
	public LevelType getLevelType() {
		return lvlType;
	}
}
