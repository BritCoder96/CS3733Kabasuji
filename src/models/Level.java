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
