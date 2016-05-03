package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import models.Level;

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
public class ReplaceWithLevelController implements ActionListener {
	Level level;
	KabasujiFrame frame;
	JPanel priorFrame;
	
	/**
	 * Constructor for a MoveToLevelController
	 * 
	 * @param frame - the static frame that is passed along throughout the application
	 * @param priorFrame - the previous screen, in this case, the level select screen.
	 */
	public ReplaceWithLevelController(Level level, KabasujiFrame frame, JPanel priorFrame) {
		this.level = level;
		this.frame = frame;
		this.priorFrame = priorFrame;
	}

	/**
	 * The function to be called upon interaction from the user
	 * 
	 * @param e - The event, i.e. the button press.
	 */
	public void actionPerformed(ActionEvent e) {
		//Delete the current screen
		priorFrame.setVisible(false);
		frame.returnToLastContentPane();
		
		//Pass the frame to the next screen
		GameScreen newPanel = new GameScreen(level, frame);
		
		//Set the new screen to visible
		newPanel.setVisible(true);
		
		//Load the content of the new screen.
		frame.setContentPane(newPanel);
	}

}
