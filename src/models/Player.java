package models;

/**
 * Overarching model class for the Kabasuji game application.
 */
public class Player {
	// eager implementation of the singleton pattern
	
	/** Only instance of the Player class. */
	static final Player inst = new Player(new Level[15]);	// TODO: use some other Level array for the parameter
	
	/** Levels available to be played. */
	Level[] levels;
	
	/** Level currently being played. */
	Level currentLevel;
	
	/**
	 * Constructor that accepts a Level array.
	 * 
	 * @param levels	The Level array
	 */
	Player(Level[] levels) {
		this.levels = levels;
	}
	
	/**
	 * Gets the only instance of the Player class.
	 * 
	 * @return The only instance of the Player class.
	 */
	public static Player instance() {
		return inst;
	}
	
	/**
	 * Gets a Level that is determined from its associated number.
	 * 
	 * @param levelNumber A number that is associated with a Level from levels.
	 */
	public Level getLevel(int levelNumber) {
		for(Level i : levels) {
			if(i.levelNumber == levelNumber){
				currentLevel = i;
				return currentLevel;
			}
		}
		// must play a level that exists
		throw new IllegalArgumentException();
	}
	
	/**
	 * Gets the Level that is currently being played.
	 */
	public Level getCurrentLevel() {
		return currentLevel;
	}
}
