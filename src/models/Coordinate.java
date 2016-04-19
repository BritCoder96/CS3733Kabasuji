package models;

/**
 * A representation of coordinates in rows and columns for placement of pieces and squares.
 * 
 * @author jberry
 *
 */
public class Coordinate {
	
	/** the row of the coordinate */
	int row;
	/** the column of the coordinate */
	int col;
	
	/** 
	 * Default Constructor
	 * 
	 * @param row
	 * @param col
	 */
	public Coordinate (int row, int col) {
		this.row = row;
		this.col = col;
	}
}
