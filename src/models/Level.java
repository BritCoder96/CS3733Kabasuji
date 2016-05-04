package models;

import java.util.HashSet;

import views.KabasujiFrame;

/**
 * 
 * The main Level class represents a level and all of it's attributes. 
 * 
 * @author bjbenson
 * @author jberry
 *
 */
public class Level {
	/** The board for the level. */
	private Board board;
	/** The bullpen for the level. */
	private Bullpen bullpen;
	/** The number of the level. Is a non-negative number. */
	int levelNumber;
	/** The number of stars the player received for this level. */
	private int numberOfStars;
	/** The type of the level: Lightning, Release, and Puzzle. */
	private LevelType lvlType;
	/** The level logic for the level. */
	private ExtraLevelLogic levelLogic;
	/** The level name for the level. */
	String levelName;
	/** Whether the current player has won that level. */
	private boolean hasWon;
	
	/**
	 * Constructor for a Level. Used to make a new level in the builder.
	 * 
	 * @param numberOfBoardRows	The initial number of rows in the board.
	 * @param numberOfBoardCols	The initial number of columns in the board.
	 * @param levelNumber	The number of the level.
	 * @param lvlType	The type of the level: Lightning, Release, and Puzzle.
	 * @param levelName	The name of the level.
	 */
	public Level (int numberOfBoardRows, int numberOfBoardCols, int levelNumber, LevelType lvlType, String levelName) {
		Board board = new Board(numberOfBoardRows, numberOfBoardCols, lvlType);
		board.fillWithSquares();
		setBoard(board);
		
		setLevelNumber(levelNumber);
		setNumberOfStars(0);
		this.lvlType = lvlType;
		setLevelName(levelName);
		setHasWon(false);
		
		switch (lvlType) {
		case PUZZLE:
			setLevelLogic(new PuzzleLevelLogic(-1, -1));
			break;
		case LIGHTNING:
			setLevelLogic(new LightningLevelLogic(-1, -1));
			break;
		case RELEASE:
			setLevelLogic(new ReleaseLevelLogic());
			break;
		}
		
		bullpen = new Bullpen();
	}
	
	/**
	 * Constructor for a Level. Used to reconstruct a level in the actual game.
	 * 
	 * @param board	The board associated with the level.
	 * @param bullpen	The bullpen associated with the level.
	 * @param levelNumber	The number of the level.
	 * @param numberOfStars	The number of stars the player received for this level.
	 * @param levelLogic	The type of the level: Lightning, Release, and Puzzle.
	 * @param levelName	The name of the level.
	 */
	public Level (Board board, Bullpen bullpen, int levelNumber, int numberOfStars, ExtraLevelLogic levelLogic, String levelName) {
		setBoard(board);
		setBullpen(bullpen);
		setLevelNumber(levelNumber);
		setNumberOfStars(numberOfStars);
		setLevelLogic(levelLogic);
		this.levelName = levelName;
		
		if (levelLogic instanceof PuzzleLevelLogic) {
			this.lvlType = LevelType.PUZZLE;
		}
		else if (levelLogic instanceof LightningLevelLogic) {
			this.lvlType = LevelType.LIGHTNING;
		}
		else if (levelLogic instanceof ReleaseLevelLogic) {
			this.lvlType = LevelType.RELEASE;
		}
        
        hasWon = numberOfStars > 0 ? true : false;
	}
	
	/**
	 * Gets the number associated with the level.
	 * 
	 * @return The number associated with the level.
	 */
	public int getLevelNumber() {
		return levelNumber;
	}
	
	/**
	 * Sets the number associated with the level.
	 * 
	 * @param levelNumber	The number to associate with the level.
	 */
	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}
	
	/**
	 * Gets the number of stars that the player received in the level.
	 * 
	 * @return The number of stars that the player received in the level.
	 */
	public int getNumberOfStars() {
		return numberOfStars;
	}
	
	/**
	 * Sets the number of stars that the player received in the level.
	 * If it's set to a number lower than the number of stars the player
	 * already has, nothing will happen.
	 * 
	 * @param numberOfStars	The number of stars that the player received in the level.
	 */
	public void setNumberOfStars(int numberOfStars) {
		// Number of stars can only go up
		this.numberOfStars = Math.max(this.numberOfStars,numberOfStars);
	}
	
	/**
	 * Gets the type of the level.
	 * 
	 * @return The type of the level.
	 */
	public LevelType getLvlType() {
		return lvlType;
	}

	// don't think we'll switch level type unless making a new level...
	
	/**
	 * Gets the name of the level.
	 * 
	 * @return The name of the level.
	 */
	public String getLevelName() {
		return levelName;
	}
	
	/**
	 * Sets the name of the level.
	 * 
	 * @param levelName	The new name of the level.
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	/**
	 * Gets the board associated with the level.
	 * 
	 * @return The board associated with the level.
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * Sets the board associated with the level.
	 * 
	 * @param board	The board to be associated with the level.
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * Gets the bullpen associated with the level.
	 * 
	 * @return The bullpen associated with the level.
	 */
	public Bullpen getBullpen() {
		return bullpen;
	}
	
	/**
	 * Gets whether or not the player has won the level.
	 * 
	 * @return whether or not the player has won the level.
	 */
	public boolean getHasWon() {
		return hasWon;
	}
	
	/**
	 * Sets the bullpen associated with the level.
	 * 
	 * @param bullpen	The bullpen to be associated with the level.
	 */
	public void setBullpen(Bullpen bullpen) {
		this.bullpen = bullpen;
	}

	/**
	 * Gets the extra logic associated with the level.
	 * 
	 * @return The extra logic associated with the level.
	 */
	public ExtraLevelLogic getLevelLogic() {
		return levelLogic;
	}

	/**
	 * Sets the extra logic associated with the level.
	 * 
	 * @param levelLogic	The extra logic associated with the level.
	 */
	public void setLevelLogic(ExtraLevelLogic levelLogic) {
		this.levelLogic = levelLogic;
	}
	
	// TODO: using the second constructor should be easier
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
			lvlLogicCopy = new ReleaseLevelLogic(); // nothing needed to copy here
			break;
		default:
			throw new IllegalStateException("level has unknown type " + lvlType);
		}
		Level copy = new Level(board.getRows(), board.getColumns(), levelNumber, lvlType, levelName);
		// Copy the number of stars, logic, bullpen, and board into the copy
		copy.setNumberOfStars(getNumberOfStars());
		copy.setLevelLogic(lvlLogicCopy);
		for (Piece p : bullpen.getPieces()) {
			copy.getBullpen().addPiece(p);
		}
		// To be totally safe, add each square individually where there's a square in the existing board
		for (int r = 0; r < board.getRows(); r++) {
			for (int c = 0; c < board.getColumns(); c++) {
				if (board.getSquares()[r][c] != null) {
					copy.getBoard().addSquareAt(r, c);
					// If release level, copy numbers
					if (lvlType == LevelType.RELEASE) {
						ReleaseBoardSquareLogic rbsl = (ReleaseBoardSquareLogic) board.getSquareAt(r, c).getSquareLogic();
						ReleaseBoardSquareLogic rbslCopy = new ReleaseBoardSquareLogic();
						rbslCopy.setColorOfNumber(rbsl.getColorOfNumber());
						rbslCopy.setNumber(rbsl.getNumber());
						copy.getBoard().getSquareAt(r, c).setSquareLogic(rbslCopy);
					}
				}
			}
		}
		return copy;
	}

	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}
}
