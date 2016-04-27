package views;

/**
 * Interface for level editor screens to implement that allows them to listen for level changes.
 * @author bhuchley
 *
 */
public interface LevelModifiedListener {
	/**
	 * Called whenever a controller that the listener is subscribed to modifies the level.
	 * This should be called before the level is actually modified.
	 * @param r the row of the square that was toggled
	 * @param c the column of the square that was toggled
	 */
	public void onLevelChanged();
}
