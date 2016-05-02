package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

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
			// Place the piece if it's valid. If it worked, tell the game screen to release the widget
			if (level.getBoard().addPiece(gamescreen.getActiveDraggingWidget().getPiece(), square.getCoordinates())) {
				Level originalLevel = gamescreen.getOriginalLevel(); // in case level was won
				gamescreen.releaseActiveDraggingWidget();
				if(level.getLvlType() == LevelType.LIGHTNING) {
					int squaresFilled = 0;
					Random rand = new Random();
					Piece[] pieces = Piece.allValidPieces;
					gamescreen.getBullpen().addPiece(pieces[rand.nextInt(pieces.length -1)]);
					LightningLevelLogic logic = ((LightningLevelLogic) originalLevel.getLevelLogic());
					if (logic.getRemainingSeconds() > 0) {
						logic.decrementUnmarkedSquares();
						System.out.println(logic.getUnmarkedSquares());
						if (logic.getUnmarkedSquares() == 0) {
							originalLevel.setNumberOfStars(3);
							saveStars(originalLevel);
							gamescreen.endGame();
						}
						else if (logic.getUnmarkedSquares() <= 6 && logic.getUnmarkedSquares() > 12) {
							originalLevel.setNumberOfStars(2);
							saveStars(originalLevel);							
						}
						else if (logic.getUnmarkedSquares() <= 12 && logic.getUnmarkedSquares() > 6) {
							originalLevel.setNumberOfStars(1);
							saveStars(originalLevel);
						}
					}
				}
				else if(level.getLvlType() == LevelType.PUZZLE) {
					PuzzleLevelLogic logic = ((PuzzleLevelLogic) level.getLevelLogic());
					if (logic.getRemainingMoves() > 0) {
						logic.decrementRemainingMoves();
					}
					gamescreen.updateMovesDisplay();
					int totalNumPieces = level.getBullpen().getNumberOfPieces() + level.getBoard().getPieces().size();
					if (level.getBoard().getPieces().size() == totalNumPieces) {
						originalLevel.setNumberOfStars(3);
						saveStars(originalLevel);
						gamescreen.endGame();
					}
					else if (level.getBoard().getPieces().size() == totalNumPieces - 1) {
						originalLevel.setNumberOfStars(2);
						saveStars(originalLevel);
					}
					else if (level.getBoard().getPieces().size() == totalNumPieces - 2) {
						originalLevel.setNumberOfStars(1);
						saveStars(originalLevel);
					}
					if (((PuzzleLevelLogic)level.getLevelLogic()).getRemainingMoves() == 0) {
						gamescreen.endGame();
					}
				}
				else if(level.getLvlType() == LevelType.RELEASE) {
					ReleaseLevelLogic logic = ((ReleaseLevelLogic)level.getLevelLogic());
					int  numUnreleasedSets = logic.getNumberOfUnreleasedSets();
					if (numUnreleasedSets == 0) {
						originalLevel.setNumberOfStars(3);
						saveStars(originalLevel);
						gamescreen.endGame();
					}
					else if (numUnreleasedSets == 1) {
						originalLevel.setNumberOfStars(2);
						saveStars(originalLevel);
					}
					else if (numUnreleasedSets == 2) {
						originalLevel.setNumberOfStars(1);
						saveStars(originalLevel);
					}
				}
			}
		}
	}

	private void saveStars(Level level) {
		level.setHasWon(true);
		SaveLevelController saveLevelController = new SaveLevelController(level);
		saveLevelController.saveLevel();
	}
}
