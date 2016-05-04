package views;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.GameSquareDragListener;
import controllers.MoveDraggingPieceController;
import controllers.PlayerBoardController;
import models.Board;
import models.ExtraBoardSquareLogic;
import models.LevelType;
import models.ReleaseBoardSquareLogic;
import models.Square;

/**
 * The view of the board for use in the game. It allows squares to be clicked to drop the
 * currently held piece on them. <br>
 * If the screen that created this is a ReleaseEditor, it will automatically detect whether 
 * clicks on the squares are intended to toggle the square's existence or add a number to it.
 * @author bhuchley
 *
 */
public class GameBoardView extends JPanel {
	public static final Color lighterGray = new Color(180, 180, 180);
	public static final Color darkerGray = new Color(140, 140, 140);
	
	int rows;
	int cols;
	JPanel parent;
	JLabel[][] squares;
	PlayerBoardController[][] squareControllers;

	/**
	 * Create the view and add the controllers to each square in it.
	 */
	public GameBoardView(JPanel parent, Board initialBoard) {
		// Make a whole bunch of squares and store them in an array, and set visibility
		// depending on whether or not the square exists.
		// Then add controllers to all of them. If the parent is not release, use generic controller.
		// Otherwise, use special release controller that checks to see what's selected in the release editor view.
		this.parent = parent;
		GameScreen gameScreen = (GameScreen) parent;
		rows = initialBoard.getRows();
		cols = initialBoard.getColumns();
		setLayout(new GridLayout(rows, cols, 0, 0));
		squares = new JLabel[rows][cols];
		squareControllers = new PlayerBoardController[rows][cols];
		ExtraBoardSquareLogic currentSquare = null;
		
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				JLabel square = new JLabel();
				Color squareBackground = (((r+c)%2)==0) ? lighterGray : darkerGray;
				if (initialBoard.getSquares()[r][c] != null) {
					square.setOpaque(true);
				}
				if(initialBoard.getSquareAt(r, c) != null)
					currentSquare = initialBoard.getSquares()[r][c].getSquareLogic();
				if(initialBoard.getLevelType() == LevelType.RELEASE){
					Integer number = ((ReleaseBoardSquareLogic) currentSquare).getNumber();
					Color color = ((ReleaseBoardSquareLogic) currentSquare).getColorOfNumber();

					if(number > 0){
						square.setText(number.toString());
						square.setForeground(color);
					}

				}
				squareControllers[r][c] = new PlayerBoardController(gameScreen, gameScreen.getLevel(), r, c);
				// Set on click listener to deal with placing pieces on the square
				square.addMouseListener(squareControllers[r][c]);
				square.setBackground(squareBackground);
				square.addMouseMotionListener(new GameSquareDragListener(this, square, gameScreen));
				squares[r][c] = square;
				add(square);
			}
		}
	}
	
	/**
	 * Gets the length of the edge of a square in the board.
	 * @return the size of a square in this board
	 */
	public int getSquareSize() {
		return getWidth() / cols;
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
