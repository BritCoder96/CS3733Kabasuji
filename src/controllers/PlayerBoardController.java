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
import views.KabasujiFrame;

public class PlayerBoardController extends java.awt.event.MouseAdapter {
	protected Level level;
	protected Square square;
		
	public PlayerBoardController(Level level, Square square){
		this.level = level;
		this.square = square;
	}
	
	public void mouseReleased(java.awt.event.MouseEvent me){
		
		KabasujiFrame k = level.getFrame();
		
		Board b  = level.getBoard();
		Piece p = k.getActiveDraggingPiece();
		PieceSet src = k.getDragSource();
		
		if (p == new Piece())
			return;
		
		Move m = null;
		
		boolean successfulMove = false;

		if(src.equals(level.getBullpen())){
			m = new BullpenToBoardMove(level, p, square);
			if(m.execute()){
				successfulMove = true;
			}
		}
		else if(src.equals(b)){
			m = new BoardToBoardMove(level, p, square, k.getOldSquareLocations());
			if(m.execute()){
				successfulMove = true;
			}
		}
	}
}
