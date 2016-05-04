package controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import views.ReleaseEditor;
import models.Directions;
import models.Piece;

public class ReleaseEditorPieceOrientationController implements KeyListener {
	
		ReleaseEditor editor;
	
		public ReleaseEditorPieceOrientationController(ReleaseEditor e) {
			this.editor = e;
			System.out.println("sgd");
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println(editor.getDraggingPiece().getPiece());
			System.out.println(e.getKeyCode());
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
