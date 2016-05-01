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
import models.PuzzleLevelLogic;
import models.ReleaseLevelLogic;
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
 * @author bjbenson
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
			Level originalLevel = gamescreen.getOriginalLevel();
			// Place the piece if it's valid. If it worked, tell the game screen to release the widget
			if (level.getBoard().addPiece(gamescreen.getActiveDraggingWidget().getPiece(), square.getCoordinates())) {
				gamescreen.releaseActiveDraggingWidget();
				if(level.getLvlType() == LevelType.LIGHTNING) {
					int squaresFilled = 0;
					int numberOfBoaredSquares = originalLevel.getBoard().getNumberOfSquares();
					if (((LightningLevelLogic) originalLevel.getLevelLogic()).getRemainingSeconds() > 0) {
						for (Piece piece : originalLevel.getBoard().getPieces().keySet()) {
							squaresFilled += piece.getSquares().length;
						}
					}
					else {
						if (squaresFilled == numberOfBoaredSquares) {
							originalLevel.setNumberOfStars(3);
							doWin(originalLevel);
						}
						else if (squaresFilled >= numberOfBoaredSquares - 6 && squaresFilled < numberOfBoaredSquares) {
							originalLevel.setNumberOfStars(2);
							doWin(originalLevel);							
						}
						else if (squaresFilled >= numberOfBoaredSquares - 12 && squaresFilled  < numberOfBoaredSquares - 6) {
							originalLevel.setNumberOfStars(1);
							doWin(originalLevel);
						}
					}
				}
				else if(originalLevel.getLvlType() == LevelType.PUZZLE) {
					PuzzleLevelLogic logic = ((PuzzleLevelLogic) originalLevel.getLevelLogic());
					if (logic.getRemainingMoves() > 0) {
						logic.decrementRemainingMoves();
					}
					int totalNumPieces = originalLevel.getBullpen().getNumberOfPieces() + originalLevel.getBoard().getPieces().size();
					if (originalLevel.getBoard().getPieces().size() == totalNumPieces) {
						originalLevel.setNumberOfStars(3);
						doWin(originalLevel);
					}
					else if (originalLevel.getBoard().getPieces().size() == totalNumPieces - 1) {
						originalLevel.setNumberOfStars(2);
						doWin(originalLevel);
					}
					else if (originalLevel.getBoard().getPieces().size() == totalNumPieces - 2) {
						originalLevel.setNumberOfStars(1);
						doWin(originalLevel);
					}
				}
				else if(originalLevel.getLvlType() == LevelType.RELEASE) {
					ReleaseLevelLogic logic = ((ReleaseLevelLogic)originalLevel.getLevelLogic());
					int  numUnreleasedSets = logic.getNumberOfUnreleasedSets();
					if (numUnreleasedSets == 0) {
						originalLevel.setNumberOfStars(3);
						doWin(originalLevel);
					}
					else if (numUnreleasedSets == 1) {
						originalLevel.setNumberOfStars(2);
						doWin(originalLevel);
					}
					else if (numUnreleasedSets == 2) {
						originalLevel.setNumberOfStars(1);
						doWin(originalLevel);
					}
				}
			}
		}
	}

	private void doWin(Level level) {
		level.setHasWon(true);
		SaveLevelController saveLevelController = new SaveLevelController(level);
		saveLevelController.saveLevel();
	}
}
