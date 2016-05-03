package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

import views.GameScreen;
import views.LevelEditor;

/**
 * The controller that moves the currently dragging piece around whenever the mouse is moved.
 * @author bhuchley
 *
 */
public class MoveDraggingPieceEditorController implements MouseMotionListener {
	
	/** The game screen to move the dragging widget in */
	LevelEditor lvlEditor;
	
	/**
	 * Make a new MoveDraggingPieceController to move the piece in the given GameScreen.
	 * @param gameScreen the GameScreen to move the widget in
	 */
	public MoveDraggingPieceEditorController(LevelEditor editor) {
		this.lvlEditor = editor;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		lvlEditor.moveDraggingWidgetTo(e.getX(), e.getY());
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO should also call move dragging widget?
	}
}
