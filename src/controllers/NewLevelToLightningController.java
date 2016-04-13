package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.LevelType;
import views.NewLevel;

public class NewLevelToLightningController implements ActionListener {

	NewLevel nlevel;
	
	public NewLevelToLightningController(NewLevel nlevel){
		this.nlevel = nlevel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		nlevel.setLevelType(LevelType.LIGHTNING);
		nlevel.updateOptionDisplay();
	}
}
