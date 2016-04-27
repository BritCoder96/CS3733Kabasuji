package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import views.AddPieceListener;
import views.AddPieceOverlay;
import views.BuilderTitle;
import views.BullpenView;
import views.KabasujiFrame;
import views.LevelList;
/**
 * Controller that opens the add piece overlay.
 * 
 * @author bhuchley
 */
public class AddPieceController implements ActionListener {
	KabasujiFrame frame;
	JPanel priorScreen;
	
	/** The listener that the selected piece should be added to */
	private AddPieceListener thingToAddTo;
	
	/**
	 * Make a new add piece controller with the given frame and prior screen
	 * 
	 * @param frame - the static frame that is being passed along throughout the application
	 * @param btitle - the previous screen, in this case, the Builder Title.
	 */
	public AddPieceController(KabasujiFrame frame, JPanel priorScreen, AddPieceListener thingToAddTo) {
		this.frame = frame;
		this.priorScreen = priorScreen;
		this.thingToAddTo = thingToAddTo;
	}

	/**
	 * The function that is called when the button is pressed
	 * 
	 * @param e - the actual event that calls the function, i.e. the button press.
	 */
	public void actionPerformed(ActionEvent e) {
		//Hide the previous screen.
		priorScreen.setVisible(false);
		
		//Pass the frame along to the next screen
		AddPieceOverlay newPanel = new AddPieceOverlay(frame, thingToAddTo);
		
		//Set the new screen to visible
		newPanel.setVisible(true);
		
		//Loading the content of the new window.
		frame.setContentPane(newPanel);
	}

}
