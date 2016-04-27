package views;

import models.Piece;

/**
 * An interface that things can implement to indicate they will listen for pieces to be added.
 * @author bhuchley
 *
 */
public interface AddPieceListener {
	/**
	 * Add the given piece to this. For instance, if this is implemented by a puzzle editor, add it to
	 * the puzzle level.
	 * @param p the piece to add.
	 */
	public void addPiece(Piece p);
}
