package models;
import java.util.HashMap;
import java.util.HashSet;

/**
 * The generic board for all levels.
 * 
 * @author bjbenson
 * @author jberry
 * @author bhuchley
 * @author sthuynh
 */
public class Board {
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
	/** A map of the pieces that are on the board to the locations of their anchor squares. */
	HashMap<Piece, Coordinate> pieces;
	
	
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
		pieces = new HashMap<Piece, Coordinate>();
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
	 * Get the board's pieces and their associated locations.
	 * 
	 * @return a map of the pieces on the board to the locations of their anchor squares
	 * */
	public HashMap<Piece, Coordinate> getPieces() {
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
	
	/**
	 * Adds a piece to the board at the given point, if possible. For Puzzle and Release, it adds it to the list of pieces
	 * on the board and covers all the squares that the piece covers. For Lightning, it just
	 * marks all the squares the piece covers.
	 * @param p the piece to add
	 * @param c the coordinates to add it at
	 * @return whether or not the piece was added (whether or not the placement was valid)
	 */
	public boolean addPiece(Piece p, Coordinate c){
		if (isValidPiecePlacement(p, c.getRow(), c.getCol())) {
			// Don't actually add the piece in lightning mode
			if (levelType == LevelType.PUZZLE || levelType == LevelType.RELEASE) {
				this.pieces.put(p, c);
			}
			// Set the squares in the piece to covered/marked
			for (Square s : p.squares) {
				int squareX = c.getRow() + s.getCoordinates().getRow();
				int squareY = c.getCol() + s.getCoordinates().getCol();
				// Set if square to covered in puzzle and release cases, marked in lighting case
				switch(levelType) {
				case PUZZLE:
					PuzzleBoardSquareLogic pbsl = (PuzzleBoardSquareLogic) squares[squareX][squareY].getSquareLogic();
					pbsl.setCovered(true);
					break;
				case RELEASE:
					ReleaseBoardSquareLogic rbsl = (ReleaseBoardSquareLogic) squares[squareX][squareY].getSquareLogic();
					rbsl.setCovered(true);
					break;
				case LIGHTNING:
					LightningBoardSquareLogic lbsl = (LightningBoardSquareLogic) squares[squareX][squareY].getSquareLogic();
					lbsl.setMarked(true);
					break;
				default:
					break;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Removes a piece from the list of pieces on the board and uncovers the squares that were covered
	 * by it. Only works in Puzzle and Release, as it's meaningless in Lightning.
	 * @param p the piece to remove
	 * @return whether or not the piece was removed (whether or not it was there)
	 */
	public boolean removePiece(Piece p){
		if (! pieces.containsKey(p)) {
			return false;
		}
		Coordinate c = pieces.get(p);
		// Set the squares to uncovered
		for (Square s : p.squares) {
			int squareX = c.getRow() + s.getCoordinates().getRow();
			int squareY = c.getCol() + s.getCoordinates().getCol();
			switch(levelType) {
			case PUZZLE:
				PuzzleBoardSquareLogic pbsl = (PuzzleBoardSquareLogic) squares[squareX][squareY].getSquareLogic();
				pbsl.setCovered(false);
				break;
			case RELEASE:
				ReleaseBoardSquareLogic rbsl = (ReleaseBoardSquareLogic) squares[squareX][squareY].getSquareLogic();
				rbsl.setCovered(false);
				break;
			default:
				break;
			}
		}
		pieces.remove(p);
		return true;
	}
	
	/**
	 * Gets whether or not a specific piece is on the board.
	 * @param p the piece to check for
	 * @return whether or not p is on this board
	 */
	public boolean containsPiece(Piece p){
		return this.pieces.containsKey(p);
	}
	
	/**
	 * Checks whether or not it is valid to place the given piece with the anchor at the given coordinates.
	 * @param p the piece to check
	 * @param x the x coordinate to place the anchor of the piece at
	 * @param y the y coordinate to place the anchor of the piece at
	 * @return whether or not the space the piece would take up is valid and unoccupied
	 */
	public boolean isValidPiecePlacement(Piece p, int x, int y) {
		// For each square, test whether or not that square is valid and unoccupied
		for (Square s : p.getSquares()) {
			int squareX = s.getCoordinates().getRow() + x;
			int squareY = s.getCoordinates().getCol() + y;
			// Test to see if the square exists
			if ((squareX < 0) || (squareX >= columns) || (squareY < 0) || (squareY >= rows)) {
				return false;
			}
			if (squares[squareX][squareY] == null) {
				return false;
			}
			// Test if square is covered in puzzle and release cases
			switch(levelType) {
			case PUZZLE:
				PuzzleBoardSquareLogic pbsl = (PuzzleBoardSquareLogic) squares[squareX][squareY].getSquareLogic();
				if (pbsl.isCovered) {
					return false;
				}
				break;
			case RELEASE:
				ReleaseBoardSquareLogic rbsl = (ReleaseBoardSquareLogic) squares[squareX][squareY].getSquareLogic();
				if (rbsl.isCovered) {
					return false;
				}
				break;
			default:
				break;
			}
		}
		return true;
	}
	
	/**
	 * Gets the level type of the board.
	 * @return the level type the board is being used for
	 */
	public LevelType getLevelType() {
		return levelType;
	}
	
	/**
	 * Get the number of squares on the board.
	 * @return the number of squares in the board
	 */
	public int getNumberOfSquares() {
		return numberOfSquares;
	}
	
	/**
	 * Gets the piece covering the given location, or null if there isn't one.
	 * @param x the x coordinate to check
	 * @param y the y coordinate to check
	 * @return the piece that covers (x,y), or null if (x,y) is uncovered
	 */
	public Piece getPieceAt(int x, int y) {
		for (Piece p : pieces.keySet()) {
			int baseX = pieces.get(p).getRow();
			int baseY = pieces.get(p).getCol();
			for (Square s : p.getSquares()) {
				if ((s.getCoordinates().getRow() + baseX == x) && (s.getCoordinates().getCol() + baseY == y)) {
					return p;
				}
			}
		}
		return null;
	}
	
	public Square getSquareAt(int row, int col){
		return squares[row][col];
	}
}
