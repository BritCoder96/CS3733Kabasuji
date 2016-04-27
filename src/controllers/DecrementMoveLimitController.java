package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.LightningLevelLogic;
import models.PuzzleLevelLogic;
import views.LightningEditor;
import views.PuzzleEditor;

/**
 * The controller that decrements the move limit of a puzzle level.
 * @author bhuchley
 *
 */
public class DecrementMoveLimitController implements ActionListener {
	
	/** The editor that this is being used from */
	PuzzleEditor levelEditor;
	/** The puzzle level logic keeping track of the move limit being updated */
	PuzzleLevelLogic ell;
	
	/**
	 * Make a new DecrementMoveLimitController with the given level editor.
	 * @param editor the level editor containing the move limit to change
	 */
	public DecrementMoveLimitController(PuzzleEditor editor, PuzzleLevelLogic ell) {
		levelEditor = editor;
		this.ell = ell;
	}

	/** 
	 * Decrements the move limit for the level.
	 * @param arg0 the action event that triggered this
	 */
	public void actionPerformed(ActionEvent arg0) {
		if (ell.getAllottedMoves() > 1) {
			ell.setAllottedMoves(ell.getAllottedMoves() - 1);
		}
		levelEditor.updateMoveLimit();
	}

}
