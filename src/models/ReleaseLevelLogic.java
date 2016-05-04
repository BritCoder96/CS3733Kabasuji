package models;

//import models.SquareTypes;

/**
 * Extra level logic exclusive to release levels.
 * 
 * @author sthuynh
 */
public class ReleaseLevelLogic implements ExtraLevelLogic{
	/**
	 * Number of unreleased sets. 
	 * A set has six board squares that are numbered from 1 to 6 and have the same color.
	 */
	int numberOfUnreleasedSets;
	
	/** 
	 * Set of six board squares that are numbered from 1 to 6 and have the color red.
	 */
	Square[] redSquares;
	
	/** 
	 * Set of six board squares that are numbered from 1 to 6 and have the color green.
	 */
	Square[] greenSquares;
	
	/** 
	 * Set of six board squares that are numbered from 1 to 6 and have the color yellow.
	 */
	Square[] yellowSquares;
	
	/**
	 * Constructor to initialize ReleaseLevelLogic fields.
	 */
	public ReleaseLevelLogic() {
		numberOfUnreleasedSets = 3;
		redSquares = new Square[6];
		greenSquares = new Square[6];
		yellowSquares = new Square[6];
	}

	public int getNumberOfUnreleasedSets() {
		return numberOfUnreleasedSets;
	}

	public void setNumberOfUnreleasedSets(int numberOfUnreleasedSets) {
		this.numberOfUnreleasedSets = numberOfUnreleasedSets;
	}
	
	public void fillSets(Board board) {
		for (Square[] i : board.getSquares()) {
			for (Square j : i) {
				if (j != null && j.getType() == SquareTypes.RELEASEBOARDSQUARE) {
					ReleaseBoardSquareLogic releaseLogic = (ReleaseBoardSquareLogic) j.getSquareLogic();
					if (releaseLogic.getColorOfNumber() != null) {
						switch (releaseLogic.getColorOfNumber().getRGB()) {
						case -65536:
							redSquares[releaseLogic.getNumber() - 1] = j;
							break;
						case -16711936:
							greenSquares[releaseLogic.getNumber() - 1] = j;
							break;
						case -256:
							yellowSquares[releaseLogic.getNumber() - 1] = j;
							break;
						}
					}
				}
			}
		}
	}
	
	public void checkSets() {
		int numberOfUnreleasedSets = 3;
		boolean entirelyCovered = true;
		for (Square i : redSquares) {
			if (!i.getSquareLogic().isCovered) {
				entirelyCovered = false;
				break;
			}
		}
		if (entirelyCovered) {
			numberOfUnreleasedSets--;
		}
		
		entirelyCovered = true;
		for (Square i : greenSquares) {
			if (!i.getSquareLogic().isCovered) {
				entirelyCovered = false;
				break;
			}
		}
		if (entirelyCovered) {
			numberOfUnreleasedSets--;
		}
		
		entirelyCovered = true;
		for (Square i : yellowSquares) {
			if (!i.getSquareLogic().isCovered) {
				entirelyCovered = false;
				break;
			}
		}
		if (entirelyCovered) {
			numberOfUnreleasedSets--;
		}
		
		setNumberOfUnreleasedSets(numberOfUnreleasedSets);
	}
}
