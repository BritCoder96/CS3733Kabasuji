package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.LevelType;
import views.NewLevel;

/**
 * The controller that changes the level type of a new level to
 * Puzzle
 * 
 * @author ejcerini
 */
public class NewLevelToPuzzleController implements ActionListener {

	NewLevel nlevel;
	
	/**
	 * Constructor for a NewLevelToPuzzleController
	 *  
	 * @param nlevel - the New Level screen
	 */
	public NewLevelToPuzzleController(NewLevel nlevel){
		this.nlevel = nlevel;
	}
	
	/**
	 * The function that is called upon user interaction
	 * 
	 * @param e - the event, i.e. the button press.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//Change the level type to Lightning
		nlevel.setLevelType(LevelType.PUZZLE);
		
		//Update the display
		nlevel.updateOptionDisplay();
	}
}
