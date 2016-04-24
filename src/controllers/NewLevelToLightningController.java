package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.LevelType;
import views.NewLevel;

/**
 * The controller that changes the level type of a new level to
 * Lightning
 * 
 * @author ejcerini
 */
public class NewLevelToLightningController implements ActionListener {

	NewLevel nlevel;
	
	/**
	 * Constructor for a NewLevelToLightningController
	 *  
	 * @param nlevel - the New Level screen
	 */
	public NewLevelToLightningController(NewLevel nlevel){
		this.nlevel = nlevel;
	}
	
	/**
	 * The function that is called upon user interaction
	 * 
	 * @param e - the event, i.e. the button press.
	 */
	public void actionPerformed(ActionEvent e) {
		//Change the level type to Lightning
		nlevel.setLevelType(LevelType.LIGHTNING);
		
		//Update the display
		nlevel.updateOptionDisplay();
	}
}
