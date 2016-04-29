package controllers;

import javax.swing.SwingUtilities;

import models.Board;
import models.Bullpen;
import models.FlipPieceMove;
import models.Level;
import models.RotatePieceMove;
import views.GameScreen;
import views.KabasujiFrame;
import views.PieceView;

public class PlayerPieceController extends java.awt.event.MouseAdapter{

	protected Level level;
	protected PieceView pieceview;
	protected GameScreen gamescreen;
	
	public PlayerPieceController(GameScreen gamescreen, Level level, PieceView pieceview){
		this.level = level;
		this.pieceview = pieceview;
		this.gamescreen = gamescreen;
	}
	
	public void mousePressed(java.awt.event.MouseEvent me){
		KabasujiFrame k = gamescreen.getFrame();
		Board b = level.getBoard();
		Bullpen bp = level.getBullpen();
		
		if(!pieceview.isBeingDragged()){
			if(b.containsPiece(pieceview.getPiece())){
				if(SwingUtilities.isLeftMouseButton(me)){
					k.setDraggingPiece(pieceview, me);
					k.setDragSource(b);
				}
				else if(SwingUtilities.isRightMouseButton(me)){
					
				}
			}
			else if(bp.containsPiece(pieceview.getPiece())){
				if(SwingUtilities.isLeftMouseButton(me)){
					k.setDraggingPiece(pieceview, me);
					k.setDragSource(bp);
				}
			}
		}
		else{
			if(SwingUtilities.isRightMouseButton(me)){
				FlipPieceMove fpm = new FlipPieceMove(pieceview.getPiece(), bp);
				fpm.execute();
			}
		}
		
	}
	
	public void mouseWheelScroll(java.awt.event.MouseWheelEvent mwe){
		
		int notches = mwe.getWheelRotation();
		
		if(notches != 0){
			RotatePieceMove rpm = new RotatePieceMove(pieceview.getPiece(), level.getBullpen());
		}
		
		
	}
	
}
