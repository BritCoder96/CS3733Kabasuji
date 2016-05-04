package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import models.Level;
import models.SaveFile;
import views.LevelList;

/**
 * 
 * A controller that annihilates levels
 * 
 * @author ejcerini
 *
 */

public class DeleteLevelController implements ActionListener{

	Level level;
	LevelList llist;
	
	public DeleteLevelController(Level level, LevelList llist){
		this.level = level;
		this.llist = llist;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String fileName = new Integer(level.getLevelNumber()).toString();
		
		File file = new File("levels/" + fileName + ".txt");
		file.delete();
		
		SaveFile.instance().removeLevel(level);
		
		llist.reload();
	}
}
