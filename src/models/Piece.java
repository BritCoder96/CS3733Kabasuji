package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * Represents a piece. A piece is a hexomino made up of six squares.
 * 
 * @author bjbenson
 * @author jberry
 * @author bhuchley
 *
 */
public class Piece {
	
	/** The list of all 35 distinct valid pieces. Initialized when the app launches */
	public static final Piece[] allValidPieces = new Piece[35];
	
	/** The squares that make up the piece. */
	Square[] squares = new Square[6];
	/** The number of the piece.*/
	int pieceNumber;
	
	/**
	 * The constructor for the piece that passes in all the squares for the piece separately.
	 * 
	 * @param square0
	 * @param square1
	 * @param square2
	 * @param square3
	 * @param square4
	 * @param square5
	 * 	@param pieceNumber
	 */
	public Piece(Square square0, Square square1, Square square2, Square square3, Square square4, Square square5, int pieceNumber) {
		this.pieceNumber = pieceNumber;
		squares = new Square[6];
		squares[0] = square0;
		squares[1] = square1;
		squares[2] = square2;
		squares[3] = square3;
		squares[4] = square4;
		squares[5] = square5;
	}
	
	public Piece(){
		
	}
	
	/**
	 * The constructor for the piece that passes in all the squares for the piece together as an array.
	 * 
	 * @param squares the squares that make up the piece
	 */
	public Piece(Square[] squares) {
		if (squares.length != 6) {
			throw new IllegalArgumentException("tried to make a piece with " + squares.length + " squares");
		}
		this.squares = squares;
	}
	
	/**
	 * Gets the associated number of the piece
	 * 
	 * @return the associated number of the piece
	 */
	public int getPieceNumber() {
		return this.pieceNumber;
	}
	
	/**
	 * Gets the squares of the piece
	 * 
	 * @return the squares of the piece
	 */
	public Square[] getSquares() {
		return this.squares;
	}
	
	/**
	 * Rotates the piece.
	 * 
	 * @param direction the direction to rotate the piece.
	 */
	public void rotatePiece(Directions direction) {
		if (direction == Directions.SOUTH || direction == Directions.WEST) {
			for (Square square : squares) {
				Square last = square.attachedSquares[square.attachedSquares.length-1];
				System.arraycopy(square.attachedSquares, 0, square.attachedSquares, 1, square.attachedSquares.length-1 );
				squares[0] = last;
			}
		}
	
		else {
			for (Square square : squares) {
				Square start = squares[0];
			    System.arraycopy(square.attachedSquares, 1, square.attachedSquares, 0, square.attachedSquares.length - 1);
			    squares[square.attachedSquares.length - 1] = start;
			}
		}
	}
	
	/**
	 * Flip the piece.
	 * 
	 * @param piece The piece to flip.
	 * @param direction the direction to flip the piece.
	 */
	public void flipPiece(Directions direction) {
		if (direction == Directions.SOUTH || direction == Directions.NORTH ) {
			for (Square square : squares) {
				Square temp = square.attachedSquares[0];
				square.attachedSquares[0] = square.attachedSquares[2];
				square.attachedSquares[2] = temp;
			}
		}
		else {
			for (Square square : squares) {
				Square temp = square.attachedSquares[1];
				square.attachedSquares[1] = square.attachedSquares[3];
				square.attachedSquares[3] = temp;
			}
		}
	}
	
	// getWidth and getHeight are convenience methods to make pieces easier to render
	
	/**
	 * Gets the width of the piece, meaning how many columns are necessary to show all the squares in a grid.
	 * Note that this may change if the piece is rotated.
	 * @return the number of columns that the piece takes up
	 */
	public int getNumCols() {
		int width = 0;
		for (Square square : squares) {
			width = Math.max(width, square.getCoordinates().getCol() + 1);
		}
		return width;
	}
	
	/**
	 * Gets the height of the piece, meaning how many rows are necessary to show all the squares in a grid.
	 * Note that this may change if the piece is rotated.
	 * @return the number of rows that the piece takes up
	 */
	public int getNumRows() {
		int height = 0;
		for (Square square : squares) {
			height = Math.max(height, square.getCoordinates().getRow() + 1);
		}
		return height;
	}
	
	/**
	 * Read the pieces.txt file and parse all the pieces into the allValidPieces array.
	 */
	public static void initializeValidPieces() {
		File file = new File("Resources/pieces.txt");
		try {
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			// Each piece is on 3 lines. There are 35 of them.
			for (int i = 0; i < 35; i++) {
				// Each piece fits in a 3x6 grid. This is used to keep track of piece connections.
				Square[][] grid = new Square[3][6];
				// The squares in the grid.
				ArrayList<Square> squares = new ArrayList<Square>(6);
				// Read the next 3 lines that represent the piece.
				String line1;
				String line2;
				String line3;
				line1 = br.readLine();
				line2 = br.readLine();
				line3 = br.readLine();
				if (line1.length() != 6) {
					throw new RuntimeException("line " + (i * 3 + 1) + " size is " + line1.length());
				}
				if (line2.length() != 6) {
					throw new RuntimeException("line " + (i * 3 + 2) + " size is " + line2.length());
				}
				if (line3.length() != 6) {
					throw new RuntimeException("line " + (i * 3 + 3) + " size is " + line3.length());
				}
				// Add squares for each x found this way
				for (int index = 0; index < 6; index++) {
					if (line1.charAt(index) == 'x') {
						Square s = new Square(0, SquareTypes.PIECESQUARE, 0, index);
						grid[0][index] = s;
						if (index > 0 && grid[0][index-1] != null) {
							Square left = grid[0][index-1];
							s.attachToOtherSide(left, Directions.WEST);
							left.attachToOtherSide(s, Directions.EAST);
						}
						squares.add(s);
					}
					if (line2.charAt(index) == 'x') {
						Square s = new Square(0, SquareTypes.PIECESQUARE, 1, index);
						grid[1][index] = s;
						if (index > 0 && grid[1][index-1] != null) {
							Square left = grid[1][index-1];
							s.attachToOtherSide(left, Directions.WEST);
							left.attachToOtherSide(s, Directions.EAST);
						}
						if (grid[0][index] != null) {
							Square up = grid[0][index];
							s.attachToOtherSide(up, Directions.NORTH);
							up.attachToOtherSide(s, Directions.SOUTH);
						}
						squares.add(s);
					}
					if (line3.charAt(index) == 'x') {
						Square s = new Square(0, SquareTypes.PIECESQUARE, 2, index);
						grid[2][index] = s;
						if (index > 0 && grid[2][index-1] != null) {
							Square left = grid[2][index-1];
							s.attachToOtherSide(left, Directions.WEST);
							left.attachToOtherSide(s, Directions.EAST);
						}
						if (grid[1][index] != null) {
							Square up = grid[1][index];
							s.attachToOtherSide(up, Directions.NORTH);
							up.attachToOtherSide(s, Directions.SOUTH);
						}
						squares.add(s);
					}
				}
				if (squares.size() != 6) {
					throw new RuntimeException("Not 6 x's in piece " + i + ", found " + squares.size());
				}
				Square[] squaresArray = new Square[6];
				Piece p = new Piece(squares.toArray(squaresArray));
				p.pieceNumber = i;
				allValidPieces[i] = p;
			}
			br.close();
		} catch (IOException e1) {
			throw new RuntimeException("Error reading pieces.txt");
		}
		
	}
}
