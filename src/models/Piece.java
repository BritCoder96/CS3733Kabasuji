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
}
