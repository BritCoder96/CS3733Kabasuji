package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import views.FileSelect;
import views.Title;

public class MoveToFileSelectController implements ActionListener {
	JFrame frame;
	Title title;
	
	public MoveToFileSelectController(JFrame frame, Title t) {
		this.frame = frame;
		this.title = t;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO add title to some kind of panel backstack for use in the back button controller
		title.setVisible(false);
		FileSelect newPanel = new FileSelect(frame);
		newPanel.setVisible(true);
		frame.setContentPane(newPanel);
	}

}
