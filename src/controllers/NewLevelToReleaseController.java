package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.LevelType;
import views.NewLevel;

/**
 * The controller that changes the level type of a new level to
 * Release
 * 
 * @author ejcerini
 */
public class NewLevelToReleaseController implements ActionListener {

	NewLevel nlevel;
	
	/**
	 * Constructor for a NewLevelToReleaseController
	 *  
	 * @param nlevel - the New Level screen
	 */
	public NewLevelToReleaseController(NewLevel nlevel){
		this.nlevel = nlevel;
	}
	
	/**
	 * The function that is called upon user interaction
	 * 
	 * @param e - the event, i.e. the button press.
	 */
	public void actionPerformed(ActionEvent e) {
		//Change the level type to Lightning
		nlevel.setLevelType(LevelType.RELEASE);
		
		//Update the display
		nlevel.updateOptionDisplay();
	}
}
