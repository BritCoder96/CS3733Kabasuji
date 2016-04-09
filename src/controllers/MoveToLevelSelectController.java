package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.FileSelect;
import views.KabasujiFrame;
import views.LevelSelect;
import views.Title;

public class MoveToLevelSelectController implements ActionListener {
	KabasujiFrame frame;
	FileSelect fileSelect;
	
	public MoveToLevelSelectController(KabasujiFrame frame, FileSelect fselect) {
		this.frame = frame;
		this.fileSelect = fselect;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO add title to some kind of panel backstack for use in the back button controller
		fileSelect.setVisible(false);
		LevelSelect newPanel = new LevelSelect(frame);
		newPanel.setVisible(true);
		frame.setContentPane(newPanel);
	}

}
