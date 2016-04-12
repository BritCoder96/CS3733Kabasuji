package models;

import java.util.HashSet;

public class Level {
	Board board;
	HashSet<Piece> bullpen;
	int levelNumber;
	int numberOfStars;
	ExtraLevelLogic levelLogic;
	
	Level (int levelNumber, int numberOfStars, Board board, HashSet<Piece> bullpen, ExtraLevelLogic levelLogic) {
		this.levelNumber = levelNumber;
		this.numberOfStars = numberOfStars;
		this.board = board;
		this.bullpen = bullpen;
		this.levelLogic = levelLogic;
	}
	
	public void rotatePiece(Piece piece) {
		
	}
	
	public void flipPiece(Piece piece) {
		
	}
}
