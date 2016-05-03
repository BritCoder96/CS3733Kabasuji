package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;

import views.EditorBoardView;
import views.GameBoardView;
import views.GameScreen;
import views.LevelEditor;

/**
 * Forwards mouse events to the editor screen, from the given square in the given board. Used to make squares on
 * the board correctly handle dragging the piece over them.
 * @author bhuchley
 *
 */
public class EditorSquareDragListener implements MouseMotionListener {
	
	/** The game screen to forward the events to */
	LevelEditor editorScreen;
	/** The board the square is on */
	EditorBoardView boardView;
	/** The square that this is listening for mouse motion on */
	JLabel square;
	
	/**
	 * Create a GameSquareDragListener to listen for mouse motion on the given square, in the given board,
	 * and tell the given screen about it.
	 * @param boardView the board that the square is in
	 * @param square the square that is being listened to
	 * @param editor the screen that should eventually evaluate the event
	 */
	public EditorSquareDragListener(EditorBoardView boardView, JLabel square, LevelEditor editor) {
		this.boardView = boardView;
		this.square = square;
		this.editorScreen = editor;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// Pass the event on to the editor screen, but first change the x and y be relative to
		// the corner of the editor screen rather than the corner of the square.
		// Do this by adding the coords of the square in the game board, then the coords of the
		// game board in the game screen.
		// Also, subtract the actual x and y of the event, so that it snaps to the
		// top left corner of the square, which is where it would be if it was to be placed.
		int offsetX = square.getX() + boardView.getX() - e.getX();
		int offsetY = square.getY() + boardView.getY() - e.getY();
		e.translatePoint(offsetX, offsetY);
		editorScreen.dispatchEvent(e);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
