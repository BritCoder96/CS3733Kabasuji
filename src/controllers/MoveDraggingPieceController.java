package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

import views.GameScreen;

/**
 * The controller that moves the currently dragging piece around whenever the mouse is moved.
 * @author bhuchley
 *
 */
public class MoveDraggingPieceController implements MouseMotionListener {
	
	/** The game screen to move the dragging widget in */
	GameScreen gameScreen;
	
	/**
	 * Make a new MoveDraggingPieceController to move the piece in the given GameScreen.
	 * @param gameScreen the GameScreen to move the widget in
	 */
	public MoveDraggingPieceController(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		gameScreen.moveDraggingWidgetTo(e.getX(), e.getY());
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO should also call move dragging widget?
	}
}
