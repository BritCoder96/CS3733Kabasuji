package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import models.Level;
import models.LevelType;
import models.LightningLevelLogic;
import models.PuzzleLevelLogic;
import views.KabasujiFrame;
import views.LightningEditor;
import views.NewLevel;
import views.PuzzleEditor;
import views.ReleaseEditor;


/**
 * Controller to open the level editor with the desired parameters.
 * 
 * @author ejcerini
 * @author sthuynh
 */
public class MoveToEditorController implements ActionListener{
	
	Level level;
	JPanel previousScreen;
	KabasujiFrame frame;
	
	/**
	 * The constructor for a MoveToEditorController 
	 * 
	 * @param level
	 * @param previousScreen - the previous screen, in this case the New Level screen
	 * @param frame - The static frame that is being passed along throughout the application
	 */
	public MoveToEditorController(Level level, JPanel previousScreen, KabasujiFrame frame){
		this.level = level;
		this.previousScreen = previousScreen;
		this.frame = frame;
	}
	
	/**
	 * The function to be called on interaction from the user
	 * 
	 * @param e - The performed action, i.e. the button press.
	 */
	public void actionPerformed(ActionEvent e) {
		
		//Receives the type of level the user wishes to create/edit
		LevelType leveltype = level.getLvlType();
		
		//Check what kind it is.
		switch(leveltype){
		
			//If the user wishes to create/edit a puzzle level
			case PUZZLE:
				//Hide this screen
				PuzzleEditor puzzlePanel;
				previousScreen.setVisible(false);
				if(previousScreen instanceof NewLevel)
					puzzlePanel = new PuzzleEditor(frame, level, ((NewLevel) previousScreen).getMoveLimit());
				else{
					puzzlePanel = new PuzzleEditor(frame, level, ((PuzzleLevelLogic) level.getLevelLogic()).getAllottedMoves());
				}
				puzzlePanel.setVisible(true);
				//Load the puzzle editor
				frame.setContentPane(puzzlePanel);
				break;
				
			//If the user wishes to create a lightning level
			case LIGHTNING:
				//Hide this screen
				previousScreen.setVisible(false);
				LightningEditor lightningPanel;

				if(previousScreen instanceof NewLevel)
					lightningPanel = new LightningEditor(frame, level, ((NewLevel) previousScreen).getMoveLimit());
				else{
					lightningPanel = new LightningEditor(frame, level, ((LightningLevelLogic) level.getLevelLogic()).getAllottedSeconds());
				}
				lightningPanel.setVisible(true);
				//Load the lightning editor
				frame.setContentPane(lightningPanel);
				break;
				
			//If the user wishes to create a release level
			case RELEASE:
				//Hide this screen
				previousScreen.setVisible(false);
				ReleaseEditor releasePanel = new ReleaseEditor(frame, level);
				releasePanel.setVisible(true);
				//Load the release editor.
				frame.setContentPane(releasePanel);
				break;
		}
	}
	
}
