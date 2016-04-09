package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import views.BuilderTitle;
import views.FileSelect;
import views.KabasujiFrame;
import views.LevelSelect;
import views.Title;

public class MoveToBuilderLevelListController implements ActionListener {
	KabasujiFrame frame;
	BuilderTitle buildertitle;
	
	public MoveToBuilderLevelListController(KabasujiFrame frame, BuilderTitle btitle) {
		this.frame = frame;
		this.buildertitle = btitle;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO add title to some kind of panel backstack for use in the back button controller
		buildertitle.setVisible(false);
		LevelSelect newPanel = new LevelSelect(frame);
		newPanel.setVisible(true);
		frame.setContentPane(newPanel);
	}

}
