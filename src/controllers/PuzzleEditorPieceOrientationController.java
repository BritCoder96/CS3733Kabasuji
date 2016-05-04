package controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import views.PuzzleEditor;
import models.Directions;
import models.Piece;

public class PuzzleEditorPieceOrientationController implements KeyListener {
	
		PuzzleEditor editor;
	
		public PuzzleEditorPieceOrientationController(PuzzleEditor e) {
			this.editor = e;
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			Piece piece = editor.getDraggingPiece().getPiece();
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
			editor.getDraggingPiece().updatePieceView();
			editor.revalidate();
			editor.repaint();
		}

		@Override
		public void keyTyped(KeyEvent arg0) {	
		}
}
