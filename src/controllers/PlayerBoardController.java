package controllers;

import java.io.File;
import java.util.ArrayList;

import models.Board;
import models.BoardToBoardMove;
import models.Bullpen;
import models.BullpenToBoardMove;
import models.ExtraBoardSquareLogic;
import models.Level;
import models.LevelType;
import models.LightningLevelLogic;
import models.Move;
import models.Piece;
import models.PieceSet;
import models.Square;
import views.GameScreen;
import views.KabasujiFrame;
import views.LevelSelect;
import views.PieceView;
import views.Title;

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
			Piece coveringPiece = b.getPieceAt(square.getCoordinates().getRow(), square.getCoordinates().getCol());
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
				if(level.getLvlType() == LevelType.LIGHTNING) {
					int squaresFilled = 0;
					if (((LightningLevelLogic) level.getLevelLogic()).getRemainingSeconds() > 0) {
						for (Piece piece : level.getBoard().getPieces().keySet()) {
							squaresFilled += piece.getSquares().length;
						}
					}
					else {
						if (squaresFilled == level.getBoard().getNumberOfSquares()) {
							level.setNumberOfStars(3);
							level.setHasWon(true);
						}
						else if (squaresFilled >= level.getBoard().getNumberOfSquares() - 6 && squaresFilled < level.getBoard().getNumberOfSquares()) {
							level.setNumberOfStars(2);
							level.setHasWon(true);
						}
						else if (squaresFilled >= level.getBoard().getNumberOfSquares() - 12 && squaresFilled  < level.getBoard().getNumberOfSquares() - 6) {
							level.setNumberOfStars(1);
							level.setHasWon(true);
						}
					}
				}
				else if(level.getLvlType() == LevelType.PUZZLE) {
					int totalNumPieces = level.getBullpen().getNumberOfPieces() + level.getBoard().getPieces().size();
					if (level.getBoard().getPieces().size() == totalNumPieces) {
						level.setNumberOfStars(3);
						level.setHasWon(true);
					}
					else if (level.getBoard().getPieces().size() == totalNumPieces - 1) {
						level.setNumberOfStars(2);
						level.setHasWon(true);
					}
					else if (level.getBoard().getPieces().size() == totalNumPieces - 2) {
						level.setNumberOfStars(1);
						level.setHasWon(true);
					}
				}
			}
		}
	}
	
	
}
