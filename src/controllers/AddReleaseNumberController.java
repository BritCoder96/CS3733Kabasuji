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

/**
 * 
 * A controller that allows a user to add a number to a Release Square
 * in the editor;
 * 
 * @author ejcerini
 */

public class AddReleaseNumberController implements EditorSquareController{

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
	
	/**
	 * The constructor for an AddReleaseNumberController
	 * 
	 * 
	 * @param board - the Board the square is on
	 * @param row - the row of the square
	 * @param col - the column of the square
	 * @param squareLabel - the visible label for the square
	 * @param editor - the editor screen that holds the color and number info.
	 */
	public AddReleaseNumberController(Board board, int row, int col, JLabel squareLabel, ReleaseEditor editor){
		this.board = board;
		this.row = row;
		this.col = col;
		this.squareLabel = squareLabel;
		this.editor = editor;
	}
	
	/**
	 * Checks for a mouse click on the current square.
	 * 
	 * @param e - The mouse event in question
	 */
	public void mouseClicked(MouseEvent e) {
		//Record the current state of the level
		editor.onLevelChanged();
				
		//Gets the value currently showing on the label 
		String currentText = squareLabel.getText();
				
		//Get the number and color to be added
		String newText = editor.getNumber();
		Color newColor = editor.getColor();
		
		//Gets the information for the square in question
		ReleaseBoardSquareLogic thisSquare;
		thisSquare = (ReleaseBoardSquareLogic) board.getSquareAt(row, col).getSquareLogic();
		
		//Checks whether or not the Jlabel currently has text.
		boolean hasText = currentText != "";
		
		//If we're dealing with an active square
		if(squareLabel.isOpaque()){
			//And the JLabel /has/ text
			if (hasText) {
				//Get rid of the text. We don't want it.
				squareLabel.setText("");
				squareLabel.setForeground(Color.black);
				
				//Set the information of the square to reflect the fact that it no longer has a number
				thisSquare.setNumber(-1);
				thisSquare.setColorOfNumber(null);
				
			} else {
				//Otherwise set the text and color to reflect the new text and color
				squareLabel.setText(newText);
				squareLabel.setForeground(newColor);
				
				//Get the number as a number
				Character numberData = new Character(newText.charAt(0));
				int theNumber = Character.getNumericValue(numberData);
				
				//Store the information in the square
				thisSquare.setNumber(theNumber);
				thisSquare.setColorOfNumber(newColor);
			}
			squareLabel.repaint();
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//We don't need this
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//We don't need this
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//We don't need this
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//We don't need this
	}
	
	@Override
	public void setBoard(Board b) {
		board = b;
	}
}
