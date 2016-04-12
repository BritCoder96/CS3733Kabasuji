package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import views.LevelSelect;
import models.Level;

public class NextLevelController implements ActionListener {
	
	LevelSelect levelSelect;
	ArrayList<Level> levels;
	
	public NextLevelController(LevelSelect ls, ArrayList<Level> levels) {
		levelSelect = ls;
		this.levels = levels;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (levelSelect.getCurrentLevelIndex() < levels.size() - 1) {
			levelSelect.moveToNextLevel();
		}
	}

}
