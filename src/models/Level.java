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
	private Board board;
	/** The bullpen for the level*/
	private HashSet<Piece> bullpen;
	/** The number of the level*/
	int levelNumber;
	/** The number of stars the player received for this level.*/
	private int numberOfStars;
	/** The type of the level: Lightning, Release, and Puzzle.*/
	private LevelType lvlType;
	/** The level logic for the level. */
	private ExtraLevelLogic levelLogic;
	/** The level name for the level. */
	String levelName;
	
	/**
	 * 
	 * @param levelNumber The number of the level
	 * @param numberOfStar The number of stars the player received for this level.
	 * @param board The board for the level
	 * @param bullpen The bullpen for the leve
	 * @param lvlType The type of the level: Lightning, Release, and Puzzle
	 * @param levelLogic The level logic for the level.
	 */
	public Level (int levelNumber, int numberOfStars, Board board, HashSet<Piece> bullpen, LevelType lvlType, ExtraLevelLogic levelLogic, String levelName ) {
		this.levelNumber = levelNumber;
		this.setNumberOfStars(numberOfStars);
		this.setBoard(board);
		this.setBullpen(bullpen);
		this.setLvlType(lvlType);
		this.setLevelLogic(levelLogic);
		this.levelName = levelName;

	}
	
	/**
	 * Gets the number of stars that the player received in the level.
	 * 
	 * @return the number of stars that the player received in the level.
	 */
	public int getNumStars() {
		return getNumberOfStars();
	}
	
	/**
	 * Gets the type of the level.
	 * 
	 * @return the type of the level
	 */
	public LevelType getLevelType() {
		return getLvlType();
	}
	
	/**
	 * Gets the name of the level.
	 * 
	 * @return the name of the level
	 */
	public String getLevelName() {
		return levelName;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public LevelType getLvlType() {
		return lvlType;
	}

	public void setLvlType(LevelType lvlType) {
		this.lvlType = lvlType;
	}

	public HashSet<Piece> getBullpen() {
		return bullpen;
	}

	public void setBullpen(HashSet<Piece> bullpen) {
		this.bullpen = bullpen;
	}

	public ExtraLevelLogic getLevelLogic() {
		return levelLogic;
	}

	public void setLevelLogic(ExtraLevelLogic levelLogic) {
		this.levelLogic = levelLogic;
	}

	public int getNumberOfStars() {
		return numberOfStars;
	}

	public void setNumberOfStars(int numberOfStars) {
		this.numberOfStars = numberOfStars;
	}
}
