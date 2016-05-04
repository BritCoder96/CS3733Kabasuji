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

	/**
	 * Get the column coordinate.
	 * 
	 * @return The column coordinate.
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Set the column coordinate.
	 * 
	 * @param col The new column coordinate.
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * Get the row coordinate.
	 * 
	 * @return The row coordinate.
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Set the row coordinate.
	 * 
	 * @param row The new row coordinate.
	 */
	public void setRow(int row) {
		this.row = row;
	}
}
