package models;

/**
 * Represents the squares that make up both pieces and boards
 * 
 * @author bjbenson
 * @author jberry
 *
 */
public class Square {
	/** The color of the square	 */
	int color;
	/** The type of the square (Piece or Board) */
	SquareTypes type;
	/** The squares attached to this square */
	public Square[] attachedSquares;
	/** The coordinates of the square */
	Coordinate coordinates;
	/** The square logic of the square */
	ExtraBoardSquareLogic squareLogic;
	
	/**
	 * The constructor for the square
	 * 
	 * @param color The color of the square
	 * @param type The type of the square (Piece or Board)
	 * @param squareLogic The coordinates of the square
	 * @param coordinate The square logic of the square
	 */
	public Square (int color, SquareTypes type, ExtraBoardSquareLogic squareLogic, Coordinate coordinate) {
		this.color = color;
		this.type = type;
		this.squareLogic = squareLogic;
		this.coordinates = coordinate;
		this.attachedSquares = new Square[4];
	}
	
	/**
	 * Attaches a square to the given side.
	 * 
	 * @param square the square to attach.
	 * @param direction the direction to attach to.
	 */
	public void attachToOtherSide (Square square, Directions direction) {
		if (direction == Directions.NORTH) {
			attachedSquares[0] = square;
		}
		else if (direction == Directions.EAST) {
			attachedSquares[1] = square;
		}
		else if (direction == Directions.SOUTH) {
			attachedSquares[2] = square;
		}
		else {
			attachedSquares[3] = square;
		}
	}
}
