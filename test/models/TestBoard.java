package models;
import java.util.HashSet;

import junit.framework.TestCase;


public class TestBoard extends TestCase {
	public void testBoard () {
		Square square1 = new Square(255, SquareTypes.PIECESQUARE, null, new Coordinate(0,0));
		Square square2 = new Square(16711680, SquareTypes.PIECESQUARE, null, new Coordinate(0,1));
		Square square3 = new Square(255, SquareTypes.PIECESQUARE, null, new Coordinate(1,1));
		Square square4 = new Square(16711680, SquareTypes.PIECESQUARE, null, new Coordinate(1,2));
		Square square5 = new Square(255, SquareTypes.PIECESQUARE, null, new Coordinate(1,3));
		Square square6 = new Square(16711680, SquareTypes.PIECESQUARE, null, new Coordinate(2,3));
		Square square7 = new Square(255, SquareTypes.PIECESQUARE, null, new Coordinate(-1, 0));
		Square square8 = new Square(16711680, SquareTypes.PIECESQUARE, null, new Coordinate(-1,-1));
		Square square9 = new Square(255, SquareTypes.PIECESQUARE, null, new Coordinate(-2,-1));
		Square square10 = new Square(16711680, SquareTypes.PIECESQUARE, null, new Coordinate(-2,-2));
		Square square11 = new Square(255, SquareTypes.PIECESQUARE, null, new Coordinate(-3,-2));
		Square square12 = new Square(16711680, SquareTypes.PIECESQUARE, null, new Coordinate(-3,-3));
		
		square1.attachToOtherSide(square2, Directions.NORTH);
		square2.attachToOtherSide(square3, Directions.EAST);
		square3.attachToOtherSide(square4, Directions.NORTH);
		square4.attachToOtherSide(square5, Directions.NORTH);
		square5.attachToOtherSide(square6, Directions.EAST);
		
		square1.attachToOtherSide(square2, Directions.SOUTH);
		square2.attachToOtherSide(square3, Directions.WEST);
		square3.attachToOtherSide(square4, Directions.SOUTH);
		square4.attachToOtherSide(square5, Directions.WEST);
		square5.attachToOtherSide(square6, Directions.SOUTH);
		
		Square[] pieceSquares1 = {square1, square2, square3, square4, square5, square6};
		Square[] pieceSquares2 = {square7, square8, square9, square10, square11, square12};

		
		HashSet<Piece> pieces = new HashSet<Piece>();
		Piece piece1 = new Piece(pieceSquares1);
		pieces.add(piece1);
		pieces.add(new Piece(pieceSquares2));

		Square square13 = new Square(0, SquareTypes.PUZZLEBOARDSQUARE, null, new Coordinate(0,0));
		Square square14 = new Square(16777215, SquareTypes.PUZZLEBOARDSQUARE, null, new Coordinate(0,1));
		Square square15 = new Square(0, SquareTypes.PUZZLEBOARDSQUARE, null, new Coordinate(0,2));
		Square square16 = new Square(16777215, SquareTypes.PUZZLEBOARDSQUARE, null, new Coordinate(1,2));
		Square square17 = new Square(0, SquareTypes.PUZZLEBOARDSQUARE, null, new Coordinate(2,2));
		Square square18 = new Square(16777215, SquareTypes.PUZZLEBOARDSQUARE, null, new Coordinate(1,1));
		Square square19 = new Square(0, SquareTypes.PUZZLEBOARDSQUARE, null, new Coordinate(1, 3));
		Square square20 = new Square(16777215, SquareTypes.PUZZLEBOARDSQUARE, null, new Coordinate(2,3));
		Square square21 = new Square(0, SquareTypes.PUZZLEBOARDSQUARE, null, new Coordinate(3,3));
		Square square22 = new Square(0, SquareTypes.PUZZLEBOARDSQUARE, null, new Coordinate(-1,0));
		Square square23 = new Square(16777215, SquareTypes.PUZZLEBOARDSQUARE, null, new Coordinate(0,-1));
		Square square24 = new Square(0, SquareTypes.PUZZLEBOARDSQUARE, null, new Coordinate(-1,-1));
		Square square25 = new Square(16777215, SquareTypes.PUZZLEBOARDSQUARE, null, new Coordinate(-1,-2));
		Square square26 = new Square(0, SquareTypes.PUZZLEBOARDSQUARE, null, new Coordinate(-2,-1));
		Square square27 = new Square(16777215, SquareTypes.PUZZLEBOARDSQUARE, null, new Coordinate(-2,-2));
		Square square28 = new Square(0, SquareTypes.PUZZLEBOARDSQUARE, null, new Coordinate(-3, -3));
		Square square29 = new Square(16777215, SquareTypes.PUZZLEBOARDSQUARE, null, new Coordinate(-1,-3));
		Square square30 = new Square(0, SquareTypes.PUZZLEBOARDSQUARE, null, new Coordinate(-3,-1));
		Square square31 = new Square(16777215, SquareTypes.PUZZLEBOARDSQUARE, null, new Coordinate(-2,-3));
		Square square32 = new Square(0, SquareTypes.PUZZLEBOARDSQUARE, null, new Coordinate(-3,-2));
		
		HashSet<Square> squares3 = new HashSet<Square>();
		squares3.add(square13);
		squares3.add(square14);
		squares3.add(square15);
		squares3.add(square16);
		squares3.add(square17);
		squares3.add(square18);
		squares3.add(square19);
		squares3.add(square20);
		squares3.add(square21);
		squares3.add(square22);
		squares3.add(square23);
		squares3.add(square24);
		squares3.add(square25);
		squares3.add(square26);
		squares3.add(square27);
		squares3.add(square28);
		squares3.add(square29);
		squares3.add(square30);
		squares3.add(square31);
		squares3.add(square32);
		Board board = new Board(3, 3, LevelType.PUZZLE);
		board.setSquares(squares3);
		board.setPieces(pieces);
		
		assertTrue(board.getPieces().contains(piece1));
		assertTrue(board.getSquares().contains(square32));

	}
}
