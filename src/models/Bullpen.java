package models;

public class Bullpen {
	Piece[] pieces;
	
	public Bullpen(Piece[] pieces) {
		this.pieces = pieces;
	}
	
	public boolean isMember(Piece piece) {
		for (Piece i : pieces) {
			if (i == piece) {
				return true;
			}
		}
		return false;
	}
}
