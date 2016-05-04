package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import models.Level;
import views.LevelSetListener;

public class EditorLevelRedoController implements ActionListener {

	/** The stack of levels that can be returned to */
	Stack<Level> levelForwardStack;
	/** The object that listens for the level to be set by the undo command */
	LevelSetListener listener;
	/** The interactable undo controller */
	EditorLevelUndoController undo;

	
	public EditorLevelRedoController(LevelSetListener listener, EditorLevelUndoController undo) {
		this.listener = listener;
		levelForwardStack = new Stack<Level>();
		this.undo = undo;
	}
	
	/**
	 * Push a clone of the given level onto the stack. The clone is identical but unrelated
	 * to the given level, so that it won't be modified once it goes on the stack.
	 * @param level the level to push.
	 */
	public void pushLevel(Level level) {
		levelForwardStack.push(level.deepClone());
	}
	
	public void clearForwardStack(){
		levelForwardStack = new Stack<Level>();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if( ! levelForwardStack.empty()){
			// Pop the level Forward Stack
			Level lastLevel = levelForwardStack.pop();
			// Add the popped level to the back stack
			undo.pushLevel(listener.getLevel().deepClone());
			listener.setLevel(lastLevel);
		}
	}
	
	/**
	 * Sets the undo controller for this redo controller
	 * 
	 * @param undo - the undo controller
	 */
	public void setRedoController(EditorLevelUndoController undo){
		this.undo = undo;
	}

}
