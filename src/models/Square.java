package models;
public class Square {
	int color;
	SquareTypes type;
	public Square[] attachedSquares;
	Coordinate coordinates;
	ExtraSquareLogic squareLogic;
	
	public Square (int color, SquareTypes type, ExtraSquareLogic squareLogic, Coordinate coordinate) {
		this.color = color;
		this.type = type;
		this.squareLogic = squareLogic;
		this.coordinates = coordinate;
		this.attachedSquares = new Square[4];
	}
	
	
	
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
