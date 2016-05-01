package views;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.ToggleBoardSquareController;
import models.Piece;
import models.Square;

import java.awt.Color;
import java.awt.GridLayout;

/**
 * A view that shows a single piece.
 * @author bhuchley
 *
 */
public class PieceView extends JPanel {
	
	public static final Color pieceSquareColor = new Color(128, 128, 128);
	
	protected boolean beingDragged = false; 
	protected Piece piece;

	/**
	 * Create the panel with the given piece and the given size for the squares, with the given upper left corner.
	 * This also sets the bounds automatically, keeping the upper left corner the same as the parameter
	 * and determining the width and height depending on the square size and the shape of the piece.
	 */
	public PieceView(Piece piece, int squareSize, int topLeftX, int topLeftY) {
		this.piece = piece;
		int rows = piece.getNumRows();
		int cols = piece.getNumCols();
		setLayout(new GridLayout(rows, cols, 0, 0));
		setBounds(topLeftX, topLeftY, squareSize * cols, squareSize * rows);
		setOpaque(false); // so that it doesn't draw a background for the entire pieceview
							// and only shows the squares we want it to
		
		JLabel[][] squareViews = new JLabel[rows][cols];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				JLabel square = new JLabel();
				square.setBackground(pieceSquareColor);
				squareViews[r][c] = square;
				square.setOpaque(false);
				square.setVisible(false);
				add(square);
			}
		}
		for (Square s : piece.getSquares()) {
			squareViews[s.getCoordinates().getRow()][s.getCoordinates().getCol()].setOpaque(true);
			squareViews[s.getCoordinates().getRow()][s.getCoordinates().getCol()].setVisible(true);
		}
	}

	public PieceView() {
	}

	public boolean isBeingDragged(){
		return beingDragged;
	}
	
	public void setBeingDragged(boolean drag){
		beingDragged = drag;
	}
	
	public Piece getPiece(){
		return piece;
	}
}
