package models;

import java.util.ArrayList;

/**
 * Class to hold a set of pieces with a fancy name.
 * @author bhuchley
 * @author sthuynh
 *
 */
public class Bullpen{
	/** Collection of pieces. */
	ArrayList<Piece> pieces;	// TODO: enact pieces.trimToSize() occasionally
	/** Number of pieces. */
	int numberOfPieces;
	
	/**
	 * Constructor for a Bullpen.
	 */
	public Bullpen() {
		super();
		
		pieces = new ArrayList<Piece>(35);
		numberOfPieces = 0;
	}
	
	/**
	 * Returns an iterable collection of all pieces from the bullpen. 
	 * 
	 * @return The collection of all bullpen pieces.
	 */
	public ArrayList<Piece> getPieces() {
		return pieces;
	}
	
	/**
	 * Adds a piece to the bullpen.
	 * 
	 * @param piece The piece to add.
	 */
	public void addPiece(Piece piece) {
		pieces.add(piece);
		numberOfPieces = pieces.size();
	}
	
	/**
	 * Removes a piece from the bullpen.
	 * 
	 * @param piece The piece to remove.
	 */
	public void removePiece(Piece piece) {
		pieces.remove(piece);
		numberOfPieces = pieces.size();
	}
	
	/**
	 * Gets the number of pieces in the bullpen.
	 * 
	 * @return The number of bullpen pieces.
	 */
	public int getNumberOfPieces() {
		return numberOfPieces;
	}
}
