package models;
import java.util.HashSet;

/**
 * The generic board for all levels.
 * 
 * @author bjbenson
 * @author jberry
 * 
 */
public class Board {
	/** The number of rows in the board. */
	int rows;
	/** The number of columns in the board. */
	int columns;
	/** The type of level the board is associated with. */
	LevelType levelType;
	/** The squares that make up the board. */
	HashSet<Square> squares;	// TODO: consider Hashmap with Coordinate as Key
	/** The pieces that are on the board. */
	HashSet<Piece> pieces;
	
	
	/**
	 * Board constructor.
	 * 
	 * @param rows	The initial number of rows in the board.
	 * @param cols	The initial number of columns in the board.
	 * @param levelType	The type of the level that the board is associated with.
	 */
	public Board (int rows, int columns, LevelType levelType) {
		this.rows = rows;
		this.columns = columns;
	}
	
	/**
	 * Get the board's pieces.
	 * 
	 * @return The pieces on the board.
	 */
	public HashSet<Piece> getPieces() {
		return pieces;
	}
	/**
	 * Get the board's squares.
	 * 
	 * @return The squares on the board.
	 */
	public HashSet<Square> getSquares() {
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
	
	// TODO: remove? squares are added and removed one at a time
	/** 
	 * Set the board's squares.
	 * 
	 * @param squares
	 */
	void setSquares(HashSet<Square> squares) {
		this.squares = squares;
	}

	// TODO: remove? pieces are added and removed one at a time
	/**
	 * Set the board's pieces.
	 * 
	 * @param pieces
	 */
	void setPieces(HashSet<Piece> pieces) {
		this.pieces = pieces;
	}
	
	/*	TODO: implement
	void addSquare(Square square) {
		switch (square.type) {
		case PUZZLEBOARDSQUARE :
			if (levelType == LevelType.PUZZLE) {
				squares.
			}
		}
	}
	*/
}
