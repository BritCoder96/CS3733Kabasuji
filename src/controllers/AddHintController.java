package controllers;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import models.Board;
import views.EditorBoardView;
import views.LevelEditor;
import views.LevelModifiedListener;
/**
 * Adds hints to the squares in the editor.
 * 
 * @author ejcerini
 *
 */
public class AddHintController extends EditorSquareController{

	/** The board the square is on */
	Board board;
	/** The row of the square */
	int row;
	/** The column of the square */
	int col;
	/** The visible label for the square, should correspond to the row and column but not checked */
	JLabel squareLabel;
	/** The listener for when this square gets toggled */
	LevelModifiedListener listener;
	
	public AddHintController(JLabel squareLabel, Board board, int row, int col, LevelEditor listener) {
		super(listener, board.getSquareAt(row, col), board);
		this.squareLabel = squareLabel;
		this.board = board;
		this.row = row;
		this.col = col;
		this.listener = listener;	
		}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (super.checkForPieceDragOnClick(e)) {
			return;
		}
		if(squareLabel.isOpaque()){
			listener.onLevelChanged();

			boolean wasHint = board.getSquareAt(row, col).getSquareLogic().getIsHint();

			board.getSquareAt(row, col).getSquareLogic().setHint(!wasHint);

			if (!wasHint) {
				squareLabel.setBackground(EditorBoardView.hintColor);
			} 
			else {
				Color squareBackground = (((row+col)%2)==0) ? EditorBoardView.lighterGray : EditorBoardView.darkerGray;
				squareLabel.setBackground(squareBackground);
			}
			
			squareLabel.repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
