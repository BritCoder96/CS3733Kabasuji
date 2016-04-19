package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.KabasujiFrame;

/**
 * Overall controller that allows players to go back one screen
 * from anywhere they are in the application.
 * 
 * @author bhuchley
 */
public class GoBackOnePanelController implements ActionListener {
	KabasujiFrame frame;
	
	/**
	 * Constructor for the GoBackOnePanelController
	 * 
	 * @param frame - the frame that is being held static throughout the application
	 */
	public GoBackOnePanelController(KabasujiFrame frame) {
		this.frame = frame;
	}

	/**
	 * What actually happens on the button press.
	 * 
	 * @param e - The performed action, i.e. the button press.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.returnToLastContentPane();
	}

}
