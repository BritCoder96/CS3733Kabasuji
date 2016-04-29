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
 *
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
	
	public void mouseReleased(java.awt.event.MouseEvent me){
		
		KabasujiFrame k = gamescreen.getFrame();
		
		Board b  = level.getBoard();
		PieceView p = k.getActiveDraggingPiece();
		PieceSet src = k.getDragSource();
		
		if (p.getPiece() == new Piece())
			return;
		
		Move m = null;
		

		if(src.equals(level.getBullpen())){
			m = new BullpenToBoardMove(level, p.getPiece(), square);
			if(m.execute()){
				
			}
		}
		else if(src.equals(b)){
			m = new BoardToBoardMove(level, p.getPiece(), square, k.getOldSquareLocations());
			if(m.execute()){
				
			}
		}
	}
	
	
}
