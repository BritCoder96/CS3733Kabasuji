package models;

public class Piece {
	Square[] squares = new Square[6];
	
	public Piece(Square square0, Square square1, Square square2, Square square3, Square square4, Square square5) {
		this.squares[0] = square0;
		this.squares[1] = square1;
		this.squares[2] = square2;
		this.squares[3] = square3;
		this.squares[4] = square4;
		this.squares[5] = square5;
	}
	
	public Piece(Square[] squares) {
		this.squares = squares;
	}
}
