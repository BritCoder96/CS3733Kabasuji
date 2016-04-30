package controllers;

import models.Board;
import models.BoardToBoardMove;
import models.Bullpen;
import models.BullpenToBoardMove;
import models.ExtraBoardSquareLogic;
import models.Level;
import models.Move;
import models.Piece;
import models.PieceSet;
import models.Square;
import views.GameScreen;
import views.KabasujiFrame;
import views.PieceView;

/**
 * 
 * @author ejcerini
 * @author bhuchley
 */
public class PlayerBoardController extends java.awt.event.MouseAdapter {
	protected GameScreen gamescreen;
	protected Square square;
	protected Level level;
		
	public PlayerBoardController(GameScreen gamescreen, Level level, Square square){
		this.gamescreen = gamescreen;
		this.square = square;
		this.level = level;
	}
	
	public void mouseClicked(java.awt.event.MouseEvent me){
		
		Board b  = level.getBoard();
		PieceView p = gamescreen.getActiveDraggingWidget();
		
		// If no dragging piece, check and see if there's a piece at this point on the board.
		// If so, start dragging that piece.
		if (p == null) {
			Piece coveringPiece = b.getPieceAt(square.getCoordinates().getCol(), square.getCoordinates().getRow());
			if (coveringPiece == null) {
				return;
			} else {
				b.removePiece(coveringPiece);
				gamescreen.setActiveDraggingPiece(coveringPiece);
			}
		} else {
			// Place the piece if it's valid. If it worked, tell the game screen to release the widget
			if (level.getBoard().addPiece(gamescreen.getActiveDraggingWidget().getPiece(), square.getCoordinates())) {
				gamescreen.releaseActiveDraggingWidget();
			}
		}
	}
	
	
}
