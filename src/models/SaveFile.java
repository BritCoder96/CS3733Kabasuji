package models;

import java.util.ArrayList;

import views.LevelSelect;

/**
 * Overarching model class for the Kabasuji game application.
 * 
 * @author sthuynh
 * @author bhuchley
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
	
	/**
	 * Gets the only instance of the Player class.
	 * 
	 * @return The only instance of the Player class.
	 */
	public static SaveFile instance() {
		return inst;
	}
	
	/**
	 * Gets the list of levels.
	 * @return the list of levels
	 */
	public ArrayList<Level> getLevels() {
		return levels;
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
		// must play a level that exists
		throw new IllegalArgumentException("Unknown level number " + levelNumber);
	}
	
	/**
	 * Gets the name of the current save file.
	 */
	public String getFileName() {
		return fileName;
	}
	
	public void setLevels(ArrayList<Level> levels) {
		this.levels = levels;
	}
	
	public void loadFile(String fileName) {
		// Do a thing
	}
}
