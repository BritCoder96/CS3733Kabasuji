package models;
import java.util.HashSet;


public class Board {
	int rows;
	int columns;
	HashSet<Square> squares;
	HashSet<Piece> pieces;
	
	Board (int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
	}
	
	HashSet<Piece> getPieces() {
		return pieces;
	}
	
	HashSet<Square> getSquares() {
		return squares;
	}
	
	void setSquares(HashSet<Square> squares) {
		this.squares = squares;
	}
	
	void setPieces(HashSet<Piece> pieces) {
		this.pieces = pieces;
	}
}
