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
	 */
	public ReleaseBoardSquareLogic() {
		this.number = -1;
		this.colorOfNumber = -1;
	}
	
	/**
	 * Get the number of the square.
	 * 
	 * @return The associated number of the square.
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * Get the associated color hex of the square.
	 * 
	 * @return The associated color hex of the square.
	 */
	public int getColorOfNumber() {
		return colorOfNumber;
	}
	
	/**
	 * Set the number of the square.
	 * 
	 * @param number	Number to associate with the square.
	 */
	void setNumber(int number) {
		this.number = number;
	}
	
	/**
	 * Set the associated color hex of the square.
	 * 
	 * @param colorOfNumber	Color hex to associate with the square.
	 */
	void setColorOfNumber(int colorOfNumber) {
		this.colorOfNumber = colorOfNumber;
	}
}
