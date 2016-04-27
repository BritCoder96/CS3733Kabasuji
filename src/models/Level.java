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
	private Bullpen bullpen;
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
	 * Level constructor.
	 * 
	 * @param numberOfBoardRows	The initial number of rows in the board.
	 * @param numberOfBoardCols	The initial number of columns in the board.
	 * @param levelNumber The number of the level.
	 * @param numberOfStars The number of stars the player received for this level.
	 * @param lvlType The type of the level: Lightning, Release, and Puzzle.
	 * @param levelLogic The level logic for the level.
	 * @param levelName The name of the level.
	 */
	public Level (int numberOfBoardRows, int numberOfBoardCols, int levelNumber, int numberOfStars, LevelType lvlType, ExtraLevelLogic levelLogic, String levelName) {
		this.levelNumber = levelNumber;
		this.setNumberOfStars(numberOfStars);
		this.setBoard(new Board(numberOfBoardRows, numberOfBoardCols, lvlType));
		this.setBullpen(new Bullpen());
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

	public Bullpen getBullpen() {
		return bullpen;
	}

	public void setBullpen(Bullpen bullpen) {
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
	
	/**
	 * Make a copy of the level, with entirely new objects, that is unrelated to the original.
	 * @return a level that is identical to, but completely unrelated to, this one
	 */
	public Level deepClone() {
		// Copy the level logic
		ExtraLevelLogic lvlLogicCopy;
		switch(lvlType) {
		case PUZZLE:
			PuzzleLevelLogic ellp = (PuzzleLevelLogic) levelLogic;
			lvlLogicCopy = new PuzzleLevelLogic(ellp.getAllottedPieces(), ellp.getAllottedMoves());
			break;
		case LIGHTNING:
			LightningLevelLogic elll = (LightningLevelLogic) levelLogic;
			lvlLogicCopy = new LightningLevelLogic(elll.getTotalSquares(), elll.getAllottedSeconds());
			break;
		case RELEASE:
			lvlLogicCopy = new ReleaseLevelLogic();
			break;
		default:
			throw new IllegalStateException("level has unknown type " + lvlType);
		}
		Level copy = new Level(board.getRows(), board.getColumns(), levelNumber, numberOfStars, lvlType, lvlLogicCopy, levelName);
		// Copy the board and the bullpen into the copy as well
		for (Piece p : bullpen.getPieces()) {
			copy.getBullpen().addPiece(p);
		}
		// To be totally safe, add each square individually where there's a square in the existing board
		for (int r = 0; r < board.getRows(); r++) {
			for (int c = 0; c < board.getColumns(); c++) {
				if (board.getSquares()[r][c] != null) {
					copy.getBoard().addSquareAt(r, c);
				}
			}
		}
		return copy;
	}
}
