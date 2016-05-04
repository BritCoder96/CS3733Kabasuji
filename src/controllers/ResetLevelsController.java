package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import views.LevelSelect;
import models.Level;

/**
 * Controller that resets the stars in all the levels.
 * 
 * @author bjbenson
 *
 */
public class ResetLevelsController implements ActionListener {
	LevelSelect levelSelect;
	
	/**
	 * The Constructor for a PreviousLevelController
	 * 
	 * @param ls - The level select screen being modified
	 * @param levels - The list of levels in the current file
	 */
	public ResetLevelsController(LevelSelect ls) {
		levelSelect = ls;
	}

	/**
	 * The function that is called when the button is pressed
	 * 
	 * @param e - the actual event that calls the function, i.e. the button press.
	 */
	public void actionPerformed(ActionEvent e) {
		for(Level level : levelSelect.getLevels()) {
			level.clearStars();
			level.setHasWon(false);
			SaveLevelController sc = new SaveLevelController(level);
			sc.saveLevel();
		}
		levelSelect.clearLevels();
		levelSelect.loadLevels();
		levelSelect.revalidate();
		levelSelect.repaint();
	}
}
