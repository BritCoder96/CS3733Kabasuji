package models;

import java.util.ArrayList;

import views.LevelSelect;

/**
 * Overarching model class for the Kabasuji game application.
 * 
 * @author sthuynh
 * @author bhuchley
 * @author bjbenson
 */
public class SaveFile {
	// eager implementation of the singleton pattern
	
	/** Only instance of the Player class. */
	static final SaveFile inst = new SaveFile();
	
	/** Levels available to be played. */
	ArrayList<Level> levels;
	
	/** Level currently being played. */
	String fileName;
	
	/**
	 * Constructor that accepts a Level array.
	 * 
	 * @param levels	The Level array
	 */
	SaveFile() {
		this.levels = new ArrayList<Level>(15);
		//this.levels = LevelSelect.TEST_LEVELS;
		fileName = null;
	}
	
	/** resets the levels in the savefile*/
	public void clear() {
		this.levels = new ArrayList<Level>(15);
	}
	
	/**
	 * Gets the only instance of the Player class.
	 * 
	 * @return The only instance of the Player class.
	 */
	public static SaveFile instance() {
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
				return i;
			}
		}
		// must get a level that exists
		throw new IllegalArgumentException("Unknown level number: " + levelNumber);
	}
    
    /**
     * Get the number of levels currently stored in the save file.
     *
     * @return The number of levels stored.
     */
    public int getNumberOfLevels() {
        return levels.size();
    }
    
    /**
     * Stores the passed-in level.
     *
     * @param level The level to be stored.
     */
    public void addLevel(Level level) {
        levels.add(level);
    }
    
    /**
     * Destroys a passed-in level.
     *
     * @param level The level to be annihilated.
     */
    public void removeLevel(Level level) {
        levels.remove(level);
    }
    
    /**
     * Checks if a passed-in level exists.
     *
     * @param level The level to be checked.
     */
    public boolean levelExists(Integer level) {
        for(Level i : levels){
        	if(i.getLevelNumber() == level)
        		return true;
        }
        
        return false;
    }
	
	/**
	 * Gets the name of the current save file.
	 */
	public String getFileName() {
		return fileName;
	}
}
