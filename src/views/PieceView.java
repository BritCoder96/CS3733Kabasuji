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
	
	public Color pieceSquareColor;
	
	protected boolean beingDragged = false; 
	protected Piece piece;
	protected int squareSize;
	JLabel[][] squareViews;

	/**
	 * Create the panel with the given piece and the given size for the squares, with the given upper left corner.
	 * This also sets the bounds automatically, keeping the upper left corner the same as the parameter
	 * and determining the width and height depending on the square size and the shape of the piece.
	 */
	public PieceView(Piece piece, int squareSize, int topLeftX, int topLeftY) {
		this.piece = piece;
		this.squareSize = squareSize;
		int rows = piece.getNumRows();
		int cols = piece.getNumCols();
		setLayout(new GridLayout(rows, cols, 0, 0));
		setBounds(topLeftX, topLeftY, squareSize * cols, squareSize * rows);
		setOpaque(false); // so that it doesn't draw a background for the entire pieceview
							// and only shows the squares we want it to
		
		pieceSquareColor = new Color((new Double(Math.random() * 255).intValue()), 
				(new Double(Math.random() * 255).intValue()), 
				(new Double(Math.random() * 255).intValue()));
		
		squareViews = new JLabel[rows][cols];
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
	

	/**
	 * Create the panel with the given piece and the given size for the squares, with the given upper left corner.
	 * This also sets the bounds automatically, keeping the upper left corner the same as the parameter
	 * and determining the width and height depending on the square size and the shape of the piece.
	 */
	public void updatePieceView() {
		int rows = piece.getNumRows();
		int cols = piece.getNumCols();
		for (JLabel[] j : squareViews) {
			for (JLabel k : j) {
				remove(k);
			}
		}
		squareViews = new JLabel[rows][cols];
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
		
		setLayout(new GridLayout(rows, cols, 0, 0));
		setBounds(getX(), getY(), squareSize * cols, squareSize * rows);
		setOpaque(false); // so that it doesn't draw a background for the entire pieceview
							// and only shows the squares we want it to
		
		pieceSquareColor = new Color((new Double(Math.random() * 255).intValue()), 
				(new Double(Math.random() * 255).intValue()), 
				(new Double(Math.random() * 255).intValue()));
		for (Square s : piece.getSquares()) {
			squareViews[s.getCoordinates().getRow()][s.getCoordinates().getCol()].setOpaque(true);
			squareViews[s.getCoordinates().getRow()][s.getCoordinates().getCol()].setVisible(true);
		}
		revalidate();
		repaint();
	}

	
}
