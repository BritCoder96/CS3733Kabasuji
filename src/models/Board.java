package models;
import java.util.HashSet;

/**
 * The generic board for all levels.
 * 
 * @author bjbenson
 * @author jberry
 * @author bhuchley
 */
public class Board extends PieceSet {
	/** The number of rows in the board. */
	int rows;
	/** The number of columns in the board. */
	int columns;
	/** The type of level the board is associated with. */
	LevelType levelType;
	/** The squares that make up the board. */
	Square[][] squares;
	/** The number of squares that make up the board. */
	int numberOfSquares;
	/** The pieces that are on the board. */
	HashSet<Piece> pieces;
	
	
	/**
	 * Initialize a new Board with the given size and no squares
	 * 
	 * @param rows the number of rows for the board to have
	 * @param columns the number of columns for the board to have
	 * @param levelType the type of level the board will be used for (used for creating square logic)
	 */
	public Board (int rows, int columns, LevelType levelType) {
		super();
		
		this.rows = rows;
		this.columns = columns;
		squares = new Square[rows][columns];
		this.levelType = levelType;
	}
	
	/**
	 * Create a new square for every position on the board, with the correct extra level logic,
	 * and fill the board with them.
	 */
	public void fillWithSquares() {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				addSquareAt(r, c);
			}
		}
	}
	
	/**
	 * Get the board's pieces.
	 * 
	 * @return the pieces on the board
	 * */
	public HashSet<Piece> getPieces() {
		return pieces;
	}
	/** Get the board's squares, in the form of a 2d array. If there's no square at a certain point
	 * the array will be null there
	 * @return The squares on the board.
	 */
	public Square[][] getSquares() {
		return squares;
	}
	
	/**
	 * Get the number of the board's rows.
	 * 
	 * @return The number of the board's rows on the board.
	 */
	public int getRows() {
		return rows;
	}
	
	/**
	 * Get the number of the board's columns.
	 * 
	 * @return The number of the board's columns on the board.
	 */
	public int getColumns() {
		return columns;
	}
	
	/**
	 * Set the board's squares to the given 2d array of squares. If the array doesn't match
	 * the size of the board, then it will fail and return false.
	 * @param squares the array of squares to set the board's squares to
	 * @return whether or not it worked
	 */
	public boolean setSquares(Square[][] squares) {
		if (squares.length == rows && squares[0].length == columns) {
			this.squares = squares;
			numberOfSquares = 0;
			for (Square[] i : squares) {
				numberOfSquares += i.length;
			}
			return true;
		}
		return false;
	}
	
	// TODO: color?
	/**
	 * Generates a square with the correct square type and level logic and adds it at the given location
	 * @param row the row to add the square at
	 * @param col the column to add the square at
	 * @return whether or not the square was added (which will be if the location was valid and unoccupied)
	 */
	public boolean addSquareAt(int row, int col) {
		SquareTypes squareType;
		switch(levelType) {
		case PUZZLE:
			squareType = SquareTypes.PUZZLEBOARDSQUARE;
			break;
		case LIGHTNING:
			squareType = SquareTypes.LIGHTNINGBOARDSQUARE;
			break;
		case RELEASE:
			squareType = SquareTypes.RELEASEBOARDSQUARE;
			break;
		default:
			// Should never happen
			throw new IllegalStateException("Unknown levelType " + levelType);
		}
		Square s = new Square(0, squareType, row, col);
		return addSquare(s);
	}
	
	/**
	 * Add a square to the board, if that square is within the board's bounds and there is not
	 * already another square there. If it's out of bounds or the spot is occupied, it will return false
	 * and nothing will happen.
	 * @param square the square to add
	 * @return whether or not it worked
	 */
	public boolean addSquare(Square square) {
		int row = square.getCoordinates().getRow();
		int col = square.getCoordinates().getCol();
		if (row >= 0 && col >= 0 && row < rows && col < columns) {
			if (squares[row][col] == null) {
				squares[row][col] = square;
				numberOfSquares++;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Remove a square at the given coordinates from the board, if it exists. If a square was
	 * removed this way, it returns true, otherwise it returns false and nothing happens.
	 * @param row the row of the square to remove
	 * @param col the column of the square to remove
	 * @return 
	 */
	public boolean removeSquare(int row, int col) {
		if (row >= 0 && col >= 0 && row < rows && col < columns) {
			if (squares[row][col] != null) {
				squares[row][col] = null;
				numberOfSquares--;
				return true;
			}
		}
		return false;
	}

	// TODO: remove? pieces are added and removed one at a time
	/**
	 * Set the board's pieces.
	 * 
	 * @param pieces
	 */
	public void setPieces(HashSet<Piece> pieces) {
		this.pieces = pieces;
	}
	
	public boolean addPiece(Piece p){
		return this.pieces.add(p);
	}
	
	public boolean removePiece(Piece p){
		return this.pieces.remove(p);
	}
	
	/**
	 * Gets the level type of the board
	 * @return the level type the board is being used for
	 */
	public LevelType getLevelType() {
		return levelType;
	}
	
	public int getNumberOfSquares() {
		return numberOfSquares;
	}
}
