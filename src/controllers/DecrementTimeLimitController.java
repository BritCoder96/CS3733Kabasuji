package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.LightningLevelLogic;
import views.LevelModifiedListener;
import views.LightningEditor;

/**
 * The controller that decrements the time limit of a lightning level.
 * @author bhuchley
 *
 */
public class DecrementTimeLimitController implements ActionListener {
	
	/** The editor that this is being used from */
	LightningEditor levelEditor;
	/** The listener that detects if the level was changed */
	LevelModifiedListener listener;
	
	/**
	 * Make a new IncrementTimeLimitController with the given level editor.
	 * @param editor the level editor containing the time limit to change
	 */
	public DecrementTimeLimitController(LightningEditor editor, LevelModifiedListener listener) {
		levelEditor = editor;
		this.listener = listener;
	}

	/** 
	 * Decrements the time limit for the level.
	 * @param arg0 the action event that triggered this
	 */
	public void actionPerformed(ActionEvent arg0) {
		listener.onLevelChanged();
		levelEditor.updateTimeLimitDisplay(false);
	}

}
