package models;

/**
 * Represents the squares that make up both pieces and boards.
 * 
 * @author bjbenson
 * @author jberry
 *
 */
public class Square {
	/** The color of the square. */
	int color;
	/** The type of the square (piece, puzzle board, etc.). */
	SquareTypes type;
	/** The squares attached to this square. */
	public Square[] attachedSquares;
	/** The coordinates of the square. */
	private Coordinate coordinates;
	/** The square logic of the square. */
	private ExtraBoardSquareLogic squareLogic;
	
	/**
	 * The constructor for the square.
	 * 
	 * @param color The color of the square.
	 * @param type The square logic of the square
	 * @param row	Row for the square's coordinates.
	 * @param col	Column for the square's coordinates.
	 */
	public Square (int color, SquareTypes type, int row, int col) {
		this.color = color;
		this.type = type;
		setCoordinates(new Coordinate(row, col));
		
		attachedSquares = new Square[4];
		switch (type) {
		case PUZZLEBOARDSQUARE:
			squareLogic = new PuzzleBoardSquareLogic();
			break;
		case LIGHTNINGBOARDSQUARE:
			squareLogic = new LightningBoardSquareLogic();
			break;
		case RELEASEBOARDSQUARE:
			squareLogic = new ReleaseBoardSquareLogic();
			break;
		default:
			squareLogic = null;
		}
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

	public Coordinate getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinate coordinates) {
		this.coordinates = coordinates;
	}

	public ExtraBoardSquareLogic getSquareLogic() {
		return squareLogic;
	}

	public void setSquareLogic(ExtraBoardSquareLogic squareLogic) {
		this.squareLogic = squareLogic;
	}
	
	public SquareTypes getType() {
		return type;
	}
}
