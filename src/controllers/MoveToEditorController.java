package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.LevelType;
import views.KabasujiFrame;
import views.LightningEditor;
import views.NewLevel;
import views.PuzzleEditor;
import views.ReleaseEditor;


/**
 * Controller for drawing the inputs in the New Level screen
 * and using them to open the level editor with the desired
 * parameters.
 * 
 * @author ejcerini
 *
 */
public class MoveToEditorController implements ActionListener{
	
	NewLevel newlevel;
	KabasujiFrame frame;
	
	/**
	 * The constructor for a MoveToEditorController 
	 * 
	 * @param nlevel - the previous screen, in this case the New Level screen
	 * @param frame - The static frame that is being passed along throughout the application
	 */
	public MoveToEditorController(NewLevel nlevel, KabasujiFrame frame){
		this.newlevel = nlevel;
		this.frame = frame;
	}
	
	/**
	 * The function to be called on interaction from the user
	 * 
	 * @param e - The performed action, i.e. the button press.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Receives the type of level the user wishes to create
		LevelType leveltype = newlevel.getLevelType();
		
		//Check what kind it is.
		switch(leveltype){
		
			//If the user wishes to create a puzzle level
			case PUZZLE:
				//Hide this screen
				newlevel.setVisible(false);
				PuzzleEditor puzzlePanel = new PuzzleEditor(frame);
				puzzlePanel.setVisible(true);
				//Load the puzzle editor
				frame.setContentPane(puzzlePanel);
				break;
			
			//If the user wishes to create a lightning level
			case LIGHTNING:
				//Hide this screen
				newlevel.setVisible(false);
				LightningEditor lightningPanel = new LightningEditor(frame);
				lightningPanel.setVisible(true);
				//Load the lightning editor
				frame.setContentPane(lightningPanel);
				break;

			//If the user wishes to create a release level
			case RELEASE:
				//Hide this screen
				newlevel.setVisible(false);
				ReleaseEditor releasePanel = new ReleaseEditor(frame);
				releasePanel.setVisible(true);
				//Load the release editor.
				frame.setContentPane(releasePanel);
				break;
		}
	}
	
}
