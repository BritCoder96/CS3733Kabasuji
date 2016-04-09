package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.KabasujiFrame;

public class GoBackOnePanelController implements ActionListener {
	KabasujiFrame frame;
	
	public GoBackOnePanelController(KabasujiFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		frame.returnToLastContentPane();
	}

}
