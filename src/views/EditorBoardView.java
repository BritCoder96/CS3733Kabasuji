package views;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.AddReleaseNumberController;
import controllers.EditorSquareController;
import controllers.ToggleBoardSquareController;
import models.Board;
import models.EditorMode;

/**
 * The view of the board for use in the editor. It allows squares to be clicked to toggle 
 * whether or not they exist. <br>
 * If the screen that created this is a ReleaseEditor, it will automatically detect whether 
 * clicks on the squares are intended to toggle the square's existence or add a number to it.
 * @author bhuchley
 *
 */
public class EditorBoardView extends JPanel {
	public static final Color lighterGray = new Color(230, 230, 230);
	public static final Color darkerGray = new Color(200, 200, 200);
	
	int rows;
	int cols;
	JPanel parent;
	JLabel[][] squares;
	EditorSquareController[][] squareControllers;

	/**
	 * Create the view and add the controllers to each square in it.
	 * @param em 
	 */
	public EditorBoardView(JPanel parent, Board initialBoard, LevelModifiedListener listener, EditorMode em) {
		// Make a whole bunch of squares and store them in an array, and set visibility
		// depending on whether or not the square exists.
		// Then add controllers to all of them. If the parent is not release, use generic controller.
		// Otherwise, use special release controller that checks to see what's selected in the release editor view.
		this.parent = parent;
		rows = initialBoard.getRows();
		cols = initialBoard.getColumns();
		setLayout(new GridLayout(rows, cols, 0, 0));
		squares = new JLabel[rows][cols];
		squareControllers = initArray(em);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				JLabel square = new JLabel();
				Color squareBackground = (((r+c)%2)==0) ? lighterGray : darkerGray;
				if (initialBoard.getSquares()[r][c] != null) {
					square.setOpaque(true);
				}
				square.setBackground(squareBackground);
				// Set on click listener to toggle the square
				squareControllers[r][c] = setSquareListener(square, initialBoard, r, c, listener, em);
				square.addMouseListener(squareControllers[r][c]);
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
				squareControllers[r][c].setBoard(board);
			}
		}
	}
	
	private EditorSquareController[][] initArray(EditorMode em){
		switch (em){
		case EDIT:
			return new ToggleBoardSquareController[rows][cols];
		case HINT:
			break;
		case MOVE:
			break;
		case NUMBER:
			return new AddReleaseNumberController[rows][cols];
		
		}
		
		return null;
		
	}
	
	private EditorSquareController setSquareListener(JLabel square, Board initialBoard, int r, int c, LevelModifiedListener listener, EditorMode em){
		switch(em){
		case EDIT:
			return new ToggleBoardSquareController(square, initialBoard, r, c, listener);
		case HINT:
			break;
		case MOVE:
			break;
		case NUMBER:
			return new AddReleaseNumberController(initialBoard, r, c, square, (ReleaseEditor) listener);
		}
		
		
		return null;
		
	}
}

