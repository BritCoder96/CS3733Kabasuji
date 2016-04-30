package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.GameScreen;
import views.KabasujiFrame;
import views.LevelSelect;
import views.Title;

/**
 * Controller that allows the player to move from the level select screen
 * to the screen where the game can be played.
 * 
 * @author bhuchley
 */
public class MoveToLevelController implements ActionListener {
	KabasujiFrame frame;
	LevelSelect levelSelect;
	
	/**
	 * Constructor for a MoveToLevelController
	 * 
	 * @param frame - the static frame that is passed along throughout the application
	 * @param levelSelect - the previous screen, in this case, the level select screen.
	 */
	public MoveToLevelController(KabasujiFrame frame, LevelSelect levelSelect) {
		this.frame = frame;
		this.levelSelect = levelSelect;
	}

	/**
	 * The function to be called upon interaction from the user
	 * 
	 * @param e - The event, i.e. the button press.
	 */
	public void actionPerformed(ActionEvent e) {
		//Hide the current screen
		levelSelect.setVisible(false);
		
		//Pass the frame to the next screen
		GameScreen newPanel = new GameScreen(frame);
		
		//Set the new screen to visible
		newPanel.setVisible(true);
		
		//Load the content of the new screen.
		frame.setContentPane(newPanel);
	}

}
