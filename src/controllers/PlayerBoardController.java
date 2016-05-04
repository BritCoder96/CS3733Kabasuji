package controllers;

import java.util.Random;

import models.Board;
import models.Level;
import models.LevelType;
import models.LightningBoardSquareLogic;
import models.LightningLevelLogic;
import models.PuzzleLevelLogic;
import models.ReleaseLevelLogic;
import models.Piece;
import models.Square;
import views.GameScreen;
import views.PieceView;

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
			int markedSquares = 0;
			// Place the piece if it's valid. If it worked, tell the game screen to release the widget
			if(level.getLvlType() == LevelType.LIGHTNING) {
				for (Square s : p.getPiece().getSquares()) {
					int squareX = square.getCoordinates().getRow() + s.getCoordinates().getRow();
					int squareY = square.getCoordinates().getCol() + s.getCoordinates().getCol();
					if (((squareX >= 0 && squareX < b.getRows())&& (squareY >= 0 && squareY < b.getColumns())) 
							&& level.getBoard().getSquareAt(squareX, squareY) != null) {
						LightningBoardSquareLogic lsbl = (LightningBoardSquareLogic)level.getBoard().getSquareAt(squareX, squareY).getSquareLogic();
						if (!lsbl.getMarked()) {
							markedSquares++;
						}
					}
				}
			}
			if (level.getBoard().addPiece(gamescreen.getActiveDraggingWidget().getPiece(), square.getCoordinates())) {
				Level originalLevel = gamescreen.getOriginalLevel(); // in case level was won
				gamescreen.releaseActiveDraggingWidget();
				if(level.getLvlType() == LevelType.LIGHTNING) {
					Random rand = new Random();
					Piece[] pieces = Piece.allValidPieces;
					gamescreen.getBullpen().addPiece(pieces[rand.nextInt(pieces.length -1)]);
					gamescreen.getBullpen().addPiece(pieces[rand.nextInt(pieces.length -1)]);
					gamescreen.getBullpen().addPiece(pieces[rand.nextInt(pieces.length -1)]);
					LightningLevelLogic logic = ((LightningLevelLogic) originalLevel.getLevelLogic());
					for (int i = 0; i < markedSquares; i++) {
						logic.decrementUnmarkedSquares();
					}
					if (logic.getUnmarkedSquares() == 0) {
						originalLevel.setNumberOfStars(3);
						saveStars(originalLevel);
						gamescreen.endGame();
					}
					else if (logic.getUnmarkedSquares() <= 6 && logic.getUnmarkedSquares() > 0) {
						originalLevel.setNumberOfStars(2);
						saveStars(originalLevel);							
					}
					else if (logic.getUnmarkedSquares() <= 12 && logic.getUnmarkedSquares() > 6) {
						originalLevel.setNumberOfStars(1);
						saveStars(originalLevel);
					}
					else {
						level.setHasWon(false);
						originalLevel.setNumberOfStars(0);
						SaveLevelController saveLevelController = new SaveLevelController(level);
						saveLevelController.saveLevel();
					}
					
				}
				else if(level.getLvlType() == LevelType.PUZZLE) {
					PuzzleLevelLogic logic = ((PuzzleLevelLogic) level.getLevelLogic());
					if (logic.getRemainingMoves() > 0) {
						logic.decrementRemainingMoves();
					}
					gamescreen.updateMovesDisplay();
					int totalNumPieces = level.getBullpen().getNumberOfPieces() + gamescreen.getPiecesOnBoard().size();
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
						System.out.println(totalNumPieces);
						originalLevel.setNumberOfStars(1);
						saveStars(originalLevel);
					}
					else {
						level.setHasWon(false);
						originalLevel.setNumberOfStars(0);
						SaveLevelController saveLevelController = new SaveLevelController(level);
						saveLevelController.saveLevel();
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
					else {
						level.setHasWon(false);
						originalLevel.setNumberOfStars(0);
						SaveLevelController saveLevelController = new SaveLevelController(level);
						saveLevelController.saveLevel();
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
