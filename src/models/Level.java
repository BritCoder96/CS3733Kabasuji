package models;

import java.util.HashSet;

/**
 * 
 * The main Level class represents a level and all of it's attributes. 
 * 
 * @author bjbenson
 * @author jberry
 *
 */
public class Level {
	/** The board for the level*/
	Board board;
	/** The bullpen for the level*/
	HashSet<Piece> bullpen;
	/** The number of the level*/
	int levelNumber;
	/** The number of stars the player received for this level.*/
	int numberOfStars;
	/** The type of the level: Lightning, Release, and Puzzle.*/
	LevelType lvlType;
	/** The level logic for the level. */
	ExtraLevelLogic levelLogic;
	
	/**
	 * 
	 * @param levelNumber The number of the level
	 * @param numberOfStar The number of stars the player received for this level.
	 * @param board The board for the level
	 * @param bullpen The bullpen for the level
	 * @param lvlType The type of the level: Lightning, Release, and Puzzle
	 * @param levelLogic The level logic for the level.
	 */
	public Level (int levelNumber, int numberOfStars, Board board, HashSet<Piece> bullpen, LevelType lvlType, ExtraLevelLogic levelLogic) {
		this.levelNumber = levelNumber;
		this.numberOfStars = numberOfStars;
		this.board = board;
		this.bullpen = bullpen;
		this.lvlType = lvlType;
		this.levelLogic = levelLogic;
	}
	
	/**
	 * Rotates the piece.
	 * 
	 * @param piece The piece to rotate.
	 * @param direction the direction to rotate the piece.
	 */
	public void rotatePiece(Piece piece, Directions direction) {
		if (direction == Directions.SOUTH || direction == Directions.WEST) {
			for (Square square : piece.squares) {
				Square last = square.attachedSquares[square.attachedSquares.length-1];
				System.arraycopy(square.attachedSquares, 0, square.attachedSquares, 1, square.attachedSquares.length-1 );
				piece.squares[0] = last;
			}
		}
	
		else {
			for (Square square : piece.squares) {
				Square start = piece.squares[0];
			    System.arraycopy(square.attachedSquares, 1, square.attachedSquares, 0, square.attachedSquares.length - 1);
			    piece.squares[square.attachedSquares.length - 1] = start;
			}
		}
	}
	
	/**
	 * Flip the piece.
	 * 
	 * @param piece The piece to flip.
	 * @param direction the direction to flip the piece.
	 */
	public void flipPiece(Piece piece, Directions direction) {
		if (direction == Directions.SOUTH || direction == Directions.NORTH ) {
			for (Square square : piece.squares) {
				Square temp = square.attachedSquares[0];
				square.attachedSquares[0] = square.attachedSquares[2];
				square.attachedSquares[2] = temp;
			}
		}
		else {
			for (Square square : piece.squares) {
				Square temp = square.attachedSquares[1];
				square.attachedSquares[1] = square.attachedSquares[3];
				square.attachedSquares[3] = temp;
			}
		}
	}
	
	/**
	 * Gets the number of stars that the player received in the level.
	 * 
	 * @return the number of stars that the player received in the level.
	 */
	public int getNumStars() {
		return numberOfStars;
	}
	
	/**
	 * Gets the type of the level.
	 * 
	 * @return the type of the level
	 */
	public LevelType getLevelType() {
		return lvlType;
	}
}
