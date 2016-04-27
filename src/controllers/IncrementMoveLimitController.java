package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.LightningLevelLogic;
import models.PuzzleLevelLogic;
import views.LevelModifiedListener;
import views.LightningEditor;
import views.PuzzleEditor;

/**
 * The controller that increments the move limit of a puzzle level.
 * @author bhuchley
 *
 */
public class IncrementMoveLimitController implements ActionListener {
	
	/** The editor that this is being used from */
	PuzzleEditor levelEditor;
	/** The listener that detects if the level was changed */
	LevelModifiedListener listener;
	
	/**
	 * Make a new IncrementMoveLimitController with the given level editor.
	 * @param editor the level editor containing the move limit to change
	 */
	public IncrementMoveLimitController(PuzzleEditor editor, LevelModifiedListener listener) {
		levelEditor = editor;
		this.listener = listener;
	}

	/** 
	 * Increments the move limit for the level.
	 * @param arg0 the action event that triggered this
	 */
	public void actionPerformed(ActionEvent arg0) {
		listener.onLevelChanged();
		PuzzleLevelLogic ell = (PuzzleLevelLogic) levelEditor.getLevel().getLevelLogic();
		ell.setAllottedMoves(ell.getAllottedMoves() + 1);
		levelEditor.updateMoveLimitDisplay();
	}

}
