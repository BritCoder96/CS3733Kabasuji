package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import models.Level;
import views.LevelSetListener;

/**
 * The controller that handles undo in the level editor. It maintains a stack of all prior
 * levels that have existed, and a call to actionPerformed() pops the stack and sets the editor
 * to show the level popped this way.
 * @author bhuchley
 * @author ejcerini
 *
 */
public class EditorLevelUndoController implements ActionListener {
	
	/** The stack of levels that can be returned to */
	Stack<Level> levelBackStack;
	/** The interactable redo controller */
	EditorLevelRedoController redo;
	/** The object that listens for the level to be set by the undo command */
	LevelSetListener listener;

	
	public EditorLevelUndoController(LevelSetListener listener, EditorLevelRedoController redo) {
		this.listener = listener;
		levelBackStack = new Stack<Level>();
		this.redo = redo;
	}
	
	/**
	 * Push a clone of the given level onto the stack. The clone is identical but unrelated
	 * to the given level, so that it won't be modified once it goes on the stack.
	 * @param level the level to push.
	 */
	public void pushLevel(Level level) {
		levelBackStack.push(level.deepClone());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if ( ! levelBackStack.empty()) {
			// Pop the level back stack.
			Level lastLevel = levelBackStack.pop();
			// Add the popped level to the level forward stack
			redo.pushLevel(lastLevel);
			System.out.println("Level Pushed to Redo");
			listener.setLevel(lastLevel);
		}
	}
	
	
	/**
	 * Creates the redo controller for this undo controller
	 * 
	 */
	public EditorLevelRedoController generateRedoController(){
		this.redo = new EditorLevelRedoController(listener, this);
		return redo;
	}

}
