package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.LevelType;
import views.KabasujiFrame;
import views.LightningEditor;
import views.NewLevel;
import views.PuzzleEditor;
import views.ReleaseEditor;

public class MoveToEditorController implements ActionListener{
	
	NewLevel newlevel;
	KabasujiFrame frame;
	
	public MoveToEditorController(NewLevel nlevel, KabasujiFrame frame){
		this.newlevel = nlevel;
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		LevelType leveltype = newlevel.getLevelType();
		
		switch(leveltype){
			case PUZZLE:
				newlevel.setVisible(false);
				PuzzleEditor puzzlePanel = new PuzzleEditor(frame);
				puzzlePanel.setVisible(true);
				frame.setContentPane(puzzlePanel);
				break;
				
			case LIGHTNING:
				newlevel.setVisible(false);
				LightningEditor lightningPanel = new LightningEditor(frame);
				lightningPanel.setVisible(true);
				frame.setContentPane(lightningPanel);
				break;

			case RELEASE:
				newlevel.setVisible(false);
				ReleaseEditor releasePanel = new ReleaseEditor(frame);
				releasePanel.setVisible(true);
				frame.setContentPane(releasePanel);
				break;
		}
	}
	
}
