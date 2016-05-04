package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import models.Board;
import models.Level;
import models.LevelType;
import models.LightningBoardSquareLogic;
import models.LightningLevelLogic;
import models.Piece;
import models.PuzzleLevelLogic;
import models.ReleaseLevelLogic;
import models.Square;
import views.LevelEditor;
import views.PieceView;

/**
 * 
 * An abstract class for Controllers that are used to edit Squares
 * in the editor. Its default onClick method will query the editor screen
 * to see if it has a piece picked up and place the piece if so, or if not
 * it will check to see if there's a piece covering the square and pick
 * that one up.
 * 
 * @author ejcerini
 * @author bhuchley
 *
 */
public abstract class EditorSquareController implements MouseListener {
	protected LevelEditor editorscreen;
	protected Square square;
	protected Board b;
	
	public EditorSquareController(LevelEditor editorscreen, Square square, Board b) {
		this.editorscreen = editorscreen;
		this.square = square;
		this.b = b;
	}
	
	/**
	 * Set the board that the square is on. Used when the board that is being displayed is reset
	 * but the views aren't deleted and recreated.
	 * @param b the board the square is now on
	 */
	public void setBoard(Board b) {
		this.b = b;
	}
	
	
	/**
	 * If there's a piece on this square, picks it up, otherwise drops the currently dragged piece.
	 * @param e the mouse event that triggered this
	 * @return true if a piece was picked up or dropped, false otherwise
	 */
	public boolean checkForPieceDragOnClick(MouseEvent e) {
		PieceView p = editorscreen.getDraggingPiece();
		
		// If no dragging piece, check and see if there's a piece at this point on the board.
		// If so, start dragging that piece.
		if (p == null) {
			Piece coveringPiece = b.getPieceAt(square.getCoordinates().getRow(), square.getCoordinates().getCol());
			if (coveringPiece == null) {
				return false;
			} else {
				b.removePiece(coveringPiece);
				editorscreen.setDraggingPiece(coveringPiece);
				return true;
			}
		} else {
			if (b.addPiece(editorscreen.getDraggingPiece().getPiece(), square.getCoordinates())) {
				editorscreen.setDraggingPiece((PieceView) null);
			}
			return true;
		}
	}
}
