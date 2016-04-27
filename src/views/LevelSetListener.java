package views;

import models.Level;

/**
 * Interface that allows a class to declare that it allows its current level to be set.
 * It's used by editor views so that the undo and redo controllers can set the level there. 
 * @author bhuchley
 *
 */
public interface LevelSetListener {
	/**
	 * Set the current level used by this object.
	 * @param level the new level to use
	 */
	public void setLevel(Level level);
}
