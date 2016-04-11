package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.KabasujiFrame;
import views.LevelList;
import views.NewLevel;

public class CreateNewLevelController implements ActionListener {
	KabasujiFrame frame;
	LevelList levellist;
	
	public CreateNewLevelController(KabasujiFrame frame, LevelList llist) {
		this.frame = frame;
		this.levellist = llist;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO add title to some kind of panel backstack for use in the back button controller
		levellist.setVisible(false);
		NewLevel newPanel = new NewLevel(frame);
		newPanel.setVisible(true);
		frame.setContentPane(newPanel);
	}

}
