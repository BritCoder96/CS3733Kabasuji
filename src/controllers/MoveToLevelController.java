package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.FileSelect;
import views.GameScreen;
import views.KabasujiFrame;
import views.LevelSelect;
import views.Title;

public class MoveToLevelController implements ActionListener {
	KabasujiFrame frame;
	LevelSelect levelSelect;
	
	public MoveToLevelController(KabasujiFrame frame, LevelSelect levelSelect) {
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
