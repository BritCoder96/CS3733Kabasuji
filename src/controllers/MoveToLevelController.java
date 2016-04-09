package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import views.FileSelect;
import views.GameScreen;
import views.LevelSelect;
import views.Title;

public class MoveToLevelController implements ActionListener {
	JFrame frame;
	LevelSelect levelSelect;
	
	public MoveToLevelController(JFrame frame, LevelSelect levelSelect) {
		this.frame = frame;
		this.levelSelect = levelSelect;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO add title to some kind of panel backstack for use in the back button controller
		levelSelect.setVisible(false);
		GameScreen newPanel = new GameScreen(frame);
		newPanel.setVisible(true);
		frame.setContentPane(newPanel);
	}

}
