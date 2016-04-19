package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.FileSelect;
import views.KabasujiFrame;
import views.LevelSelect;
import views.Title;

/**
 * Controller that brings the user from the File Select menu
 * to the Level Select menu
 * 
 * @author bhuchley
 */
public class MoveToLevelSelectController implements ActionListener {
	KabasujiFrame frame;
	FileSelect fileSelect;
	
	/**
	 * Constructor for a MoveToLevelSelectController
	 * 
	 * @param frame - the static frame that is being passed along throughout the application
	 * @param fselect - the previous screen, in this case, the File Select screen
	 */
	public MoveToLevelSelectController(KabasujiFrame frame, FileSelect fselect) {
		this.frame = frame;
		this.fileSelect = fselect;
	}

	/**
	 * The function to be called as a result of the user's interaction
	 * 
	 * @param e - The event, i.e. the button press.
	 */
	public void actionPerformed(ActionEvent e) {
		fileSelect.setVisible(false);
		LevelSelect newPanel = new LevelSelect(frame, LevelSelect.TEST_LEVELS);
		newPanel.setVisible(true);
		frame.setContentPane(newPanel);
	}

}
