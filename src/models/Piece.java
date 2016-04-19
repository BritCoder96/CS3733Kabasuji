package models;

/**
 * 
 * Represents a piece. A piece is a hexomino made up of six squares.
 * 
 * @author bjbenson
 * @author jberry
 *
 */
public class Piece {
	
	/** The squares that make up the piece */
	Square[] squares = new Square[6];
	
	/**
	 * The constructor for the piece that passes in all the squares for the piece separately.
	 * 
	 * @param square0
	 * @param square1
	 * @param square2
	 * @param square3
	 * @param square4
	 * @param square5
	 */
	public Piece(Square square0, Square square1, Square square2, Square square3, Square square4, Square square5) {
		this.squares[0] = square0;
		this.squares[1] = square1;
		this.squares[2] = square2;
		this.squares[3] = square3;
		this.squares[4] = square4;
		this.squares[5] = square5;
	}
	/**
	 * The constructor for the piece that passes in all the squares for the piece together as an array.
	 * 
	 * @param squares the squares that make up the piece
	 */
	public Piece(Square[] squares) {
		this.squares = squares;
	}
	/**
	 * Gets the squares of the piece
	 * 
	 * @return the squares of the piece
	 */
	public Square[] getSquares() {
		return this.squares;
	}
}
