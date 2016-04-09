package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.LevelSelect;

public class PreviousLevelController implements ActionListener {
	
	LevelSelect levelSelect;
	
	public PreviousLevelController(LevelSelect ls) {
		levelSelect = ls;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		levelSelect.moveToPreviousLevel();
	}

}
