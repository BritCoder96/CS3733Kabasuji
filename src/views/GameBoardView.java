package views;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.ToggleBoardSquareController;
import models.Board;

/**
 * The view of the board for use in the game screen.
 */
public class GameBoardView extends JPanel {
	public static final Color lighterGray = new Color(230, 230, 230);
	public static final Color darkerGray = new Color(200, 200, 200);
	
	int rows;
	int cols;
	JPanel parent;
	JLabel[][] squares;

	/**
	 * Create the view and add the controllers to each square in it.
	 */
	public GameBoardView(JPanel parent, Board initialBoard) {
		// Make a whole bunch of squares and store them in an array, and set visibility
		// depending on whether or not the square exists.
		this.parent = parent;
		rows = initialBoard.getRows();
		cols = initialBoard.getColumns();
		setLayout(new GridLayout(rows, cols, 0, 0));
		squares = new JLabel[rows][cols];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				JLabel square = new JLabel();
				Color squareBackground = (((r+c)%2)==0) ? lighterGray : darkerGray;
				if (initialBoard.getSquares()[r][c] != null) {
					square.setOpaque(true);
				}
				square.setBackground(squareBackground);
				squares[r][c] = square;
				add(square);
			}
		}
	}

	public void setVisibleBoard(Board board) {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				squares[r][c].setOpaque(board.getSquares()[r][c] != null);
				squares[r][c].repaint();
			}
		}
	}
}
