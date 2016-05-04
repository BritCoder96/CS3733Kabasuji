package controllers;

import java.util.Random;

import models.Board;
import models.Coordinate;
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
 * Deals with moving pieces around the board and ending the game.
 * 
 * @author ejcerini
 * @author bhuchley
 * @author bjbenson
 * @author sthuynh
 */
public class PlayerBoardController extends java.awt.event.MouseAdapter {
	protected GameScreen gamescreen;
	protected Level level;
	int row;
	int col;
		
	public PlayerBoardController(GameScreen gamescreen, Level level, int row, int col){
		this.gamescreen = gamescreen;
		this.level = level;
		this.row = row;
		this.col = col;
	}
	
	public void mouseClicked(java.awt.event.MouseEvent me){
		
		Board b  = level.getBoard();
		PieceView p = gamescreen.getActiveDraggingWidget();
		
		// If no dragging piece, check and see if there's a piece at this point on the board.
		// If so, start dragging that piece.
		if (p == null && level.getLvlType() != LevelType.RELEASE) {
			if (b.getSquareAt(row, col) != null) {
				Piece coveringPiece = b.getPieceAt(row, col);
				if (coveringPiece == null) {
					return;
				} else {
					b.removePiece(coveringPiece);
					gamescreen.setActiveDraggingPiece(coveringPiece);
				}
			}
		} else {
			int markedSquares = 0;
			// Place the piece if it's valid. If it worked, tell the game screen to release the widget
			if(level.getLvlType() == LevelType.LIGHTNING) {
				for (Square s : p.getPiece().getSquares()) {
					int squareX = row + s.getCoordinates().getRow();
					int squareY = col + s.getCoordinates().getCol();
					if (((squareX >= 0 && squareX < b.getRows())&& (squareY >= 0 && squareY < b.getColumns())) 
							&& level.getBoard().getSquareAt(squareX, squareY) != null) {
						LightningBoardSquareLogic lsbl = (LightningBoardSquareLogic)level.getBoard().getSquareAt(squareX, squareY).getSquareLogic();
						if (!lsbl.getMarked()) {
							markedSquares++;
						}
					}
				}
			}
			if (level.getBoard().addPiece(gamescreen.getActiveDraggingWidget().getPiece(), new Coordinate(row, col))) {
				gamescreen.releaseActiveDraggingWidget();
				if(level.getLvlType() == LevelType.LIGHTNING) {
					Random rand = new Random();
					Piece[] pieces = Piece.allValidPieces;
					gamescreen.getBullpen().addPiece(pieces[rand.nextInt(pieces.length -1)]);
					gamescreen.getBullpen().addPiece(pieces[rand.nextInt(pieces.length -1)]);
					gamescreen.getBullpen().addPiece(pieces[rand.nextInt(pieces.length -1)]);
					LightningLevelLogic logic = ((LightningLevelLogic) gamescreen.getLevel().getLevelLogic());
					for (int i = 0; i < markedSquares; i++) {
						logic.decrementUnmarkedSquares();
					}
					if (logic.getUnmarkedSquares() == 0) {
						saveStars(3);
						gamescreen.endGame();
					}
					else if (logic.getUnmarkedSquares() <= 6 && logic.getUnmarkedSquares() > 0) {
						saveStars(2);							
					}
					else if (logic.getUnmarkedSquares() <= 12 && logic.getUnmarkedSquares() > 6) {
						saveStars(1);
					}
					
				}
				else if(level.getLvlType() == LevelType.PUZZLE) {
					PuzzleLevelLogic logic = ((PuzzleLevelLogic) level.getLevelLogic());
					if (logic.getRemainingMoves() > 0) {
						logic.decrementRemainingMoves();
					}
					gamescreen.updateMovesDisplay();
					if (gamescreen.getBullpen().getNumPieces() == 0) {
						saveStars(3);
						gamescreen.endGame();
					}
					else if (gamescreen.getBullpen().getNumPieces() == 1) {
						saveStars(2);
					}
					else if (gamescreen.getBullpen().getNumPieces() == 2) {
						saveStars(1);
					}
					if (((PuzzleLevelLogic)level.getLevelLogic()).getRemainingMoves() == 0) {
						gamescreen.endGame();
					}
				}
				else if(level.getLvlType() == LevelType.RELEASE) {
					ReleaseLevelLogic logic = ((ReleaseLevelLogic)level.getLevelLogic());
					logic.checkSets();
					int  numUnreleasedSets = logic.getNumberOfUnreleasedSets();
					if (numUnreleasedSets == 0) {
						saveStars(3);
						gamescreen.endGame();
					}
					else if (numUnreleasedSets == 1) {
						saveStars(2);
					}
					else if (numUnreleasedSets == 2) {
						saveStars(1);
					}
					if (gamescreen.getBullpen().getNumPieces() == 0) {
						gamescreen.endGame();
					}
				}
			}
		}
	}
	/** 
	 * Saves the stars and the winning state of the level on level end to a file.
	 * @param numStars the number of stars the player acheived.
	 */
	private void saveStars(int numStars) {
		Level originalLevel = gamescreen.getOriginalLevel(); // in case level was won
		originalLevel.setNumberOfStars(numStars);
		originalLevel.setHasWon(true);
		level.setNumberOfStars(numStars);
		level.setHasWon(true);
		SaveLevelController saveLevelController = new SaveLevelController(originalLevel);
		saveLevelController.saveLevel();
		gamescreen.updateStarsDisplay();
	}
}
