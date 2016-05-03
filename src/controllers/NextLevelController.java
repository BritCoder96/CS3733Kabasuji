package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import views.LevelSelect;
import models.Level;

/**
 * Controller that brings the Player to an overview of the Level directly after
 * the currently displayed Level on the Level Select screen
 * 
 * @author bhuchley
 */
public class NextLevelController implements ActionListener {
	LevelSelect levelSelect;
	int numberOfLevels;
	
	/**
	 * The Constructor for a NextLevelController
	 * 
	 * @param ls - The level select screen being modified
	 * @param levels - The list of levels in the current file
	 */
	public NextLevelController(LevelSelect ls, int numberOfLevels) {
		levelSelect = ls;
		this.numberOfLevels = numberOfLevels;
	}

	/**
	 * The function that is called when the button is pressed
	 * 
	 * @param e - the actual event that calls the function, i.e. the button press.
	 */
	public void actionPerformed(ActionEvent e) {
		if (levelSelect.getCurrentLevelIndex() < numberOfLevels - 1) {
			levelSelect.moveToNextLevel();
		}
	}

}
