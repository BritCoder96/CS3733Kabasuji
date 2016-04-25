package models;

/**
 * A representation of coordinates in rows and columns for placement of pieces and squares.
 * 
 * @author jberry
 *
 */
public class Coordinate {
	
	/** the row of the coordinate */
	private int row;
	/** the column of the coordinate */
	private int col;
	
	/** 
	 * Default Constructor
	 * 
	 * @param row
	 * @param col
	 */
	public Coordinate (int row, int col) {
		this.setRow(row);
		this.setCol(col);
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
}
