package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import views.BuilderTitle;
import views.FileSelect;
import views.KabasujiFrame;
import views.LevelList;
import views.Title;

/**
 * Controller that brings the builder from the Title Screen
 * to the List of levels that are available for editing.
 * 
 * @author ejcerini
 */
public class MoveToBuilderLevelListController implements ActionListener {
	KabasujiFrame frame;
	BuilderTitle buildertitle;
	
	/**
	 * The constructor for a MoveToBuilderLevelListController
	 * 
	 * @param frame - the static frame that is being passed along throughout the application
	 * @param btitle - the previous screen, in this case, the Builder Title.
	 */
	public MoveToBuilderLevelListController(KabasujiFrame frame, BuilderTitle btitle) {
		this.frame = frame;
		this.buildertitle = btitle;
	}

	/**
	 * The function that is called when the button is pressed
	 * 
	 * @param e - the actual event that calls the function, i.e. the button press.
	 */
	public void actionPerformed(ActionEvent e) {
		//Hide the previous screen.
		buildertitle.setVisible(false);
		
		//Pass the frame along to the next screen
		LevelList newPanel = new LevelList(frame);
		
		//Set the new screen to visible
		newPanel.setVisible(true);
		
		//Loading the content of the new window.
		frame.setContentPane(newPanel);
	}

}
