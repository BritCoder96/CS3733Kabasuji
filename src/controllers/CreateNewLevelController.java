package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.KabasujiFrame;
import views.LevelList;
import views.NewLevel;

/**
 * 
 * Controller for moving from the Level List screen
 * to the screen where the editor can input parameters for their
 * New Level.
 * 
 * @author ejcerini
 * 
 */
public class CreateNewLevelController implements ActionListener {
	KabasujiFrame frame;
	LevelList levellist;
	
	/**
	 * The constructor for the CreateNewLevelController
	 * 
	 * @param frame - the frame that is being held static throughout the application
	 * @param llist - the previous screen, (namely the level list)
	 */
	public CreateNewLevelController(KabasujiFrame frame, LevelList llist) {
		this.frame = frame;
		this.levellist = llist;
	}

	/**
	 * What actually happens on the button press.
	 * 
	 * @param e - The performed action, i.e. the button press.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Turn off the view for the level list
		levellist.setVisible(false);
		
		//Pass on the new frame
		NewLevel newPanel = new NewLevel(frame);
		
		//Activate the new Panel
		newPanel.setVisible(true);
		
		//Loading the content of the new window.
		frame.setContentPane(newPanel);
	}

}
