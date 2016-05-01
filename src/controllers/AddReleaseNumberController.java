package controllers;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import models.Board;
import models.ReleaseBoardSquareLogic;
import models.Square;
import views.LevelModifiedListener;
import views.ReleaseEditor;

public class AddReleaseNumberController implements MouseListener{

	/** The board the square is on */
	Board board;
	/** The row of the square */
	int row;
	/** The column of the square */
	int col;
	/** The visible label for the square, should correspond to the row and column but not checked */
	JLabel squareLabel;
	/** The current screen that holds reference to the number and color */
	ReleaseEditor editor;
	
	public AddReleaseNumberController(Board board, int row, int col, JLabel squareLabel, ReleaseEditor editor){
		this.board = board;
		this.row = row;
		this.col = col;
		this.squareLabel = squareLabel;
		this.editor = editor;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		editor.onLevelChanged();
		String currentText = squareLabel.getText();
		
		String newText = editor.getNumber();
		Color newColor = editor.getColor();
		
		ReleaseBoardSquareLogic thisSquare;
		
		boolean hasText = currentText != "";
		
		// If the square didn't exist, make a new one in that spot. Otherwise delete the existing one.
		if(squareLabel.isOpaque()){
			if (hasText) {
				squareLabel.setText("");
				squareLabel.setForeground(Color.BLACK);
				thisSquare = (ReleaseBoardSquareLogic) board.getSquareAt(row, col).getSquareLogic();
				thisSquare.setNumber(-1);
				thisSquare.setColorOfNumber(Color.BLACK);
				
			} else {
				squareLabel.setText(newText);
				squareLabel.setForeground(newColor);
				thisSquare = (ReleaseBoardSquareLogic) board.getSquareAt(row, col).getSquareLogic();
				
				Character numberData = new Character(newText.charAt(0));
				int theNumber = Character.getNumericValue(numberData);
				
				
				thisSquare.setNumber(theNumber);
				thisSquare.setColorOfNumber(newColor);
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
