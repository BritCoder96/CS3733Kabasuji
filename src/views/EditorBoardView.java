package views;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.AddHintController;
import controllers.AddReleaseNumberController;
import controllers.EditorSquareController;
import controllers.EditorSquareDragListener;
import controllers.ToggleBoardSquareController;
import models.Board;
import models.EditorMode;
import models.ExtraBoardSquareLogic;
import models.LevelType;
import models.ReleaseBoardSquareLogic;
import models.Square;

/**
 * The view of the board for use in the editor. It allows squares to be clicked to toggle 
 * whether or not they exist. <br>
 * If the screen that created this is a ReleaseEditor, it will automatically detect whether 
 * clicks on the squares are intended to toggle the square's existence or add a number to it.
 * @author bhuchley
 *
 */
public class EditorBoardView extends JPanel {
	public static final Color lighterGray = new Color(180, 180, 180);
	public static final Color darkerGray = new Color(140, 140, 140);
	public static final Color hintColor = Color.BLUE;
	
	int rows;
	int cols;
	LevelEditor parent;
	JLabel[][] squares;
	EditorSquareController[][] squareControllers;

	/**
	 * Create the view and add the controllers to each square in it.
	 * @param em 
	 */
	public EditorBoardView(LevelEditor parent, Board initialBoard, EditorMode em) {
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
		
		Square[][] bsquares = initialBoard.getSquares();
		ExtraBoardSquareLogic currentSquareLogic;
		Integer number;
		Color color;
		
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				JLabel square = new JLabel();
				Color squareBackground = (((r+c)%2)==0) ? lighterGray : darkerGray;
				if (initialBoard.getSquares()[r][c] != null) {
					square.setOpaque(true);
					
					currentSquareLogic = bsquares[r][c].getSquareLogic();
					if(initialBoard.getLevelType() == LevelType.RELEASE){
						number = ((ReleaseBoardSquareLogic) currentSquareLogic).getNumber();
						color = ((ReleaseBoardSquareLogic) currentSquareLogic).getColorOfNumber();

						if(number < 0){
							square.setText("");
							square.setForeground(Color.BLACK);
						}
						else{
							square.setText(number.toString());
							square.setForeground(color);
						}
					}
				}
				square.setBackground(squareBackground);
				if(bsquares[r][c] != null && bsquares[r][c].getSquareLogic().getIsHint())
					square.setBackground(hintColor);
				// Set on click listener to toggle the square
				squareControllers[r][c] = setSquareListener(square, initialBoard, r, c, parent, em);
				square.addMouseListener(squareControllers[r][c]);
				square.addMouseMotionListener(new EditorSquareDragListener(this, square, parent));
				squares[r][c] = square;
				add(square);
			}
		}
	}
	
	public void setVisibleBoard(Board board) {
		Square[][] bsquares = board.getSquares();
		ExtraBoardSquareLogic currentSquareLogic;
		Integer number;
		Color color;
		
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				squares[r][c].setOpaque(bsquares[r][c] != null);
				if(bsquares[r][c] != null){
					currentSquareLogic = bsquares[r][c].getSquareLogic();
					if(board.getLevelType() == LevelType.RELEASE){
						number = ((ReleaseBoardSquareLogic) currentSquareLogic).getNumber();
						color = ((ReleaseBoardSquareLogic) currentSquareLogic).getColorOfNumber();
						if(number < 0){
							squares[r][c].setText("");
							squares[r][c].setForeground(Color.BLACK);
						}
						else{
							squares[r][c].setText(number.toString());
							squares[r][c].setForeground(color);
						}
					}
					if(bsquares[r][c].getSquareLogic().getIsHint()){
						squares[r][c].setBackground(hintColor);
					}
				}
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
			return new AddHintController[rows][cols];
		case NUMBER:
			return new AddReleaseNumberController[rows][cols];
		
		}
		
		return null;
		
	}
	
	private EditorSquareController setSquareListener(JLabel square, Board initialBoard, int r, int c, LevelEditor listener, EditorMode em){
		switch(em){
		case EDIT:
			return new ToggleBoardSquareController(square, initialBoard, r, c, listener);
		case HINT:
			return new AddHintController(square, initialBoard, r, c, listener);
		case NUMBER:
			return new AddReleaseNumberController(r, c, square, (ReleaseEditor) listener);
		}
		
		
		return null;
		
	}
	
	/**
	 *  gets the square at the given row and column
	 * @param row the row the square is at 
	 * @param col the column the square is at 
	 * @return the square at those coordinates
	 */
	public JLabel getSquareAt(int row, int col) {
		return squares[row][col];
	}
}

