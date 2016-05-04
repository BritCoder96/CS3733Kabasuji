package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.LevelType;
import views.NewLevel;

/**
 * The controller that changes the level type of a new level.
 * 
 * @author ejcerini
 */
public class UpdateLevelViewController implements ActionListener {

	NewLevel nlevel;
	LevelType ltype;
	
	/**
	 * Constructor for a NewLevelToReleaseController
	 *  
	 * @param nlevel - the New Level screen
	 */
	public UpdateLevelViewController(NewLevel nlevel, LevelType ltype){
		this.nlevel = nlevel;
		this.ltype = ltype;
	}
	
	/**
	 * The function that is called upon user interaction
	 * 
	 * @param e - the event, i.e. the button press.
	 */
	public void actionPerformed(ActionEvent e) {
		// Change the level type if necessary
		if (ltype != null){
			nlevel.setLevelType(ltype);
		}
		
		// Update the display
		nlevel.updateOptionDisplay();
	}
}
