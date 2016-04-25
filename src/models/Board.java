package models;
import java.util.HashSet;

/**
 * The generic board for all levels.s
 * 
 * @author bjbenson
 * @author jberry
 * 
 */
public class Board {
	/** The number of rows in the board */
	int rows;
	/** The number of columns in the board */
	int columns;
	/** The squares that make up the board */
	HashSet<Square> squares;
	/** The pieces that are on the board */
	HashSet<Piece> pieces;
	
	
	/**
	 * Board Constructor
	 * 
	 * @param rows
	 * @param columns
	 */
	Board (int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
	}
	
	/** Get the board's pieces
	 * 
	 * @return the pieces on the board
	 * */
	HashSet<Piece> getPieces() {
		return pieces;
	}
	/** Get the board's squares
	 * 
	 * @return the squares on the board
	 */
	public HashSet<Square> getSquares() {
		return squares;
	}
	
	/** Get the number of the board's rows
	 * 
	 * @return the number of the board's rows on the board
	 */
	public int getRows() {
		return rows;
	}
	
	/** Get the number of the board's columns
	 * 
	 * @return the number of the board's columns on the board
	 */
	public int getColumns() {
		return columns;
	}
	
	/** 
	 * Set the board's squares
	 * 
	 * @param squares
	 */
	void setSquares(HashSet<Square> squares) {
		this.squares = squares;
	}
	
	/**
	 * Set the board's pieces
	 * 
	 * @param pieces
	 */
	void setPieces(HashSet<Piece> pieces) {
		this.pieces = pieces;
	}
}
