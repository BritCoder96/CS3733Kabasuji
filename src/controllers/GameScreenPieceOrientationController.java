package controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import views.GameScreen;
import models.Directions;
import models.Piece;

/**
 * Deals with rotating and flipping a piece on key press on the game screen in the player.
 * 
 * @author bjbenson
 *
 */
public class GameScreenPieceOrientationController implements KeyListener {
	
		GameScreen gamescreen;
	
		public GameScreenPieceOrientationController(GameScreen gs) {
			this.gamescreen = gs;
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (gamescreen.getActiveDraggingWidget() != null) {
				Piece piece = gamescreen.getActiveDraggingWidget().getPiece();
				if (e.getKeyCode() == 'Q') {
					piece.rotatePiece(Directions.SOUTH);
				}
				else if (e.getKeyCode() == 'W') {
					piece.rotatePiece(Directions.NORTH);
				}
				else if (e.getKeyCode() == 'A') {
					piece.flipPiece(Directions.NORTH);
				}
				else if (e.getKeyCode() == 'S') {
					piece.flipPiece(Directions.WEST);
				}
				gamescreen.getActiveDraggingWidget().updatePieceView();
				gamescreen.revalidate();
				gamescreen.repaint();
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {	
		}
}
