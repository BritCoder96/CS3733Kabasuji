package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import models.Board;
import models.Coordinate;
import models.ExtraBoardSquareLogic;
import models.LightningBoardSquareLogic;
import models.PuzzleBoardSquareLogic;
import models.Square;
import models.SquareTypes;
import views.LevelEditor;
import views.LevelModifiedListener;

/**
 * The controller that listens for mouse clicks on a square in the editor view and toggles
 * whether or not the square actually exists in the board.
 * @author bhuchley
 */
public class ToggleBoardSquareController extends EditorSquareController {
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
	
	/**
	 * Create a new ToggleBoardSquareController that toggles the existence of the square at the
	 * given row and column in the given board and toggles the visibility of the given square label.
	 * The square label should correspond to the square at that position in the board, but it 
	 * doesn't check for this.
	 * @param squareLabel the visible label for the square
	 * @param board the board containing the square
	 * @param row the row the square is at
	 * @param col the column the square is at
	 */
	public ToggleBoardSquareController(JLabel squareLabel, Board board, int row, int col, LevelEditor listener) {
		super(listener, board.getSquareAt(row, col), board);
		this.squareLabel = squareLabel;
		this.board = board;
		this.row = row;
		this.col = col;
		this.listener = listener;
	}

	public void mouseClicked(MouseEvent arg0) {
		
		if (super.checkForPieceDragOnClick(arg0)) {
			return;
		}
		
		listener.onLevelChanged();
		boolean wasOff = board.getSquares()[row][col] == null;
		// Set the square label to be opaque if it exists now, otherwise transparent
		squareLabel.setOpaque(wasOff);
		// If the square didn't exist, make a new one in that spot. Otherwise delete the existing one.
		if (wasOff) {
			board.addSquareAt(row, col);
		} else {
			board.removeSquare(row, col);
		}
		squareLabel.repaint();
	}

	public void mouseEntered(MouseEvent arg0) {
		// do nothing
	}

	public void mouseExited(MouseEvent arg0) {
		// do nothing
	}

	public void mousePressed(MouseEvent arg0) {
		// do nothing, we only want to toggle the thing once in mouseClicked
	}

	public void mouseReleased(MouseEvent e) {
		// do nothing, we only want to toggle the thing once in mouseClicked
	}
	
	@Override
	public void setBoard(Board b) {
		super.setBoard(b);
		board = b;
	}

}
