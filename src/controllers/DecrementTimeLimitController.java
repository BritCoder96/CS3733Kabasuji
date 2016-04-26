package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.LightningLevelLogic;
import views.LightningEditor;

/**
 * The controller that decrements the time limit of a lightning level.
 * @author bhuchley
 *
 */
public class DecrementTimeLimitController implements ActionListener {
	
	/** The editor that this is being used from */
	LightningEditor levelEditor;
	/** The lightning level logic keeping track of the time being updated */
	LightningLevelLogic ell;
	
	/**
	 * Make a new IncrementTimeLimitController with the given level editor.
	 * @param editor the level editor containing the time limit to change
	 */
	public DecrementTimeLimitController(LightningEditor editor, LightningLevelLogic ell) {
		levelEditor = editor;
		this.ell = ell;
	}

	/** 
	 * Decrements the time limit for the level.
	 * @param arg0 the action event that triggered this
	 */
	public void actionPerformed(ActionEvent arg0) {
		if (ell.getAllottedSeconds() > 1) {
			ell.setAllottedSeconds(ell.getAllottedSeconds() - 1);
		}
		levelEditor.updateTimeLimit();
	}

}
