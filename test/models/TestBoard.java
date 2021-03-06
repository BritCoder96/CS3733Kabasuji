package models;
import java.util.HashMap;
import java.util.HashSet;

import junit.framework.TestCase;

/**
 * Test for the Board class
 * @author bjbenson
 * @author bhuchley
 *
 */
public class TestBoard extends TestCase {
	public void testBoard () {
		Square square1 = new Square(255, SquareTypes.PIECESQUARE, 0, 0);
		Square square2 = new Square(16711680, SquareTypes.PIECESQUARE, 0,1);
		Square square3 = new Square(255, SquareTypes.PIECESQUARE, 1,1);
		Square square4 = new Square(16711680, SquareTypes.PIECESQUARE, 1,2);
		Square square5 = new Square(255, SquareTypes.PIECESQUARE, 1,3);
		Square square6 = new Square(16711680, SquareTypes.PIECESQUARE, 2,3);
		Square square7 = new Square(255, SquareTypes.PIECESQUARE, -1, 0);
		Square square8 = new Square(16711680, SquareTypes.PIECESQUARE, -1,-1);
		Square square9 = new Square(255, SquareTypes.PIECESQUARE, -2,-1);
		Square square10 = new Square(16711680, SquareTypes.PIECESQUARE, -2,-2);
		Square square11 = new Square(255, SquareTypes.PIECESQUARE, -3,-2);
		Square square12 = new Square(16711680, SquareTypes.PIECESQUARE, -3,-3);
		
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

		Piece piece1 = new Piece(pieceSquares1);
		Piece piece2 = new Piece(pieceSquares2);
		
		Square square13 = new Square(0, SquareTypes.PUZZLEBOARDSQUARE, 0,0);
		Square square14 = new Square(16777215, SquareTypes.PUZZLEBOARDSQUARE, 0,1);
		Square square15 = new Square(0, SquareTypes.PUZZLEBOARDSQUARE, 0,2);
		Square square16 = new Square(16777215, SquareTypes.PUZZLEBOARDSQUARE, 1,2);
		Square square17 = new Square(0, SquareTypes.PUZZLEBOARDSQUARE, 2,2);
		Square square18 = new Square(16777215, SquareTypes.PUZZLEBOARDSQUARE, 1,1);
		Square square19 = new Square(0, SquareTypes.PUZZLEBOARDSQUARE, 1, 3);
		Square square20 = new Square(16777215, SquareTypes.PUZZLEBOARDSQUARE, 2,3);
		Square square21 = new Square(0, SquareTypes.PUZZLEBOARDSQUARE, 3,3);
		Square square22 = new Square(0, SquareTypes.PUZZLEBOARDSQUARE, 4,0);
		Square square23 = new Square(16777215, SquareTypes.PUZZLEBOARDSQUARE, 0,4);
		Square square24 = new Square(0, SquareTypes.PUZZLEBOARDSQUARE, 4,4);
		Square square25 = new Square(16777215, SquareTypes.PUZZLEBOARDSQUARE, 4,5);

		Board board = new Board(6, 6, LevelType.PUZZLE);
		assertTrue(board.addSquare(square13));
		assertTrue(board.addSquare(square14));
		assertTrue(board.addSquare(square15));
		assertTrue(board.addSquare(square16));
		assertTrue(board.addSquare(square17));
		assertTrue(board.addSquare(square18));
		assertTrue(board.addSquare(square19));
		assertTrue(board.addSquare(square20));
		assertTrue(board.addSquare(square21));
		assertTrue(board.addSquare(square22));
		assertTrue(board.addSquare(square23));
		assertTrue(board.addSquare(square24));
		assertTrue(board.addSquare(square25));
		board.addPiece(piece1, piece1.squares[0].getCoordinates());
		board.addPiece(piece2, piece2.squares[0].getCoordinates());

		assertTrue(board.getPieces().containsKey(piece1));
		assertEquals(square25, board.getSquares()[4][5]);
		
		assertTrue(board.removeSquare(4, 5));
		assertTrue(board.getSquares()[4][5] == null);
		assertFalse(board.removeSquare(4, 5));

	}
}
