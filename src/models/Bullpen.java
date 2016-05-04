package models;

import java.util.ArrayList;

/**
 * Class to hold a set of pieces with a fancy name.
 * @author bhuchley
 * @author sthuynh
 *
 */
public class Bullpen{
	ArrayList<Piece> pieces;	// TODO: enact pieces.trimToSize() occasionally 
	int numberOfPieces;
	
	public Bullpen() {
		super();
		
		pieces = new ArrayList<Piece>(35);
		numberOfPieces = 0;
	}
	
	public ArrayList<Piece> getPieces() {
		return pieces;
	}
	
	public void addPiece(Piece piece) {
		pieces.add(piece);
		numberOfPieces = pieces.size();
	}
	
	public void removePiece(Piece piece) {
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
	
	public int getNumberOfPieces() {
		return numberOfPieces;
	}

	public boolean containsPiece(Piece p) {
		return pieces.contains(p);
	}
}
