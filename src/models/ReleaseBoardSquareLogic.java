package models;

/**
 * Extra square logic exclusive to squares for a lightning level's board.
 */
public class ReleaseBoardSquareLogic extends ExtraBoardSquareLogic {
	/**
	 * Associated number.
	 * Ranges from 1 to 6.
	 * -1 means that the square doesn't have an associated number.
	 */
	int number;
	
	/**
	 * Associated color hex.
	 * -1 means that the square doesn't have an associated color.
	 */
	int colorOfNumber;
	
	/**
	 * Constructor to initialize ReleaseBoardSquareLogic fields.
	 * 
	 * @param number		Number to associate with the square.
	 * @param colorOfNumber	Color hex to associate with the square.
	 */
	public ReleaseBoardSquareLogic(int number, int colorOfNumber) {
		this.number = number;
		this.colorOfNumber = colorOfNumber;
	}
}
