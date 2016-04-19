package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.FileSelect;
import views.KabasujiFrame;
import views.Title;

/**
 * A controller to move from the Player Title to the
 * File Select Screen.
 * 
 * @author bhuchley
 */
public class MoveToFileSelectController implements ActionListener {
	KabasujiFrame frame;
	Title title;
	
	/**
	 * The Constructor for the MoveToFileSelectController
	 * 
	 * @param frame - The static frame that's being passed throughout the application
	 * @param t - the previous screen, in this case, the Player Title Screen.
	 */
	public MoveToFileSelectController(KabasujiFrame frame, Title t) {
		this.frame = frame;
		this.title = t;
	}

	/**
	 * The function that's called as a result of the interaction from the user
	 * 
	 * @param e - The actual event, i.e. the Button Press.
	 */
	public void actionPerformed(ActionEvent e) {
		//Hide the previous screen
		title.setVisible(false);
		
		//Pass the frame to the new screen
		FileSelect newPanel = new FileSelect(frame);
		
		//Load the new screen
		newPanel.setVisible(true);
		
		//Loading the content of the new window.
		frame.setContentPane(newPanel);
	}

}
