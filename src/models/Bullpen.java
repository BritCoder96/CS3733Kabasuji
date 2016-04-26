package models;

import java.util.ArrayList;

public class Bullpen {
	ArrayList<Piece> pieces;	// TODO: change date structure; capacity may increase above 35 as pieces are added to the end of the ArrayList
	int numberOfPieces;
	
	public Bullpen() {
		pieces = new ArrayList<Piece>(35);
		numberOfPieces = 0;
	}
	
	public ArrayList<Piece> getPieces() {
		return pieces;
	}
	
	void addPiece(Piece piece) {
		pieces.add(piece);
		numberOfPieces = pieces.size();
	}
	
	void removePiece(Piece piece) {
		pieces.remove(piece);
		numberOfPieces = pieces.size();
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
