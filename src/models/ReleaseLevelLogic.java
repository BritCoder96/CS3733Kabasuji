package models;

/**
 * Extra level logic exclusive to release levels.
 */
public class ReleaseLevelLogic implements ExtraLevelLogic{
	/**
	 * Number of unreleased sets. 
	 * A set has six board squares that are numbered from 1 to 6 and have the same color.
	 */
	int numberOfUnreleasedSets;
	
	/**
	 * Constructor to initialize ReleaseLevelLogic fields.
	 */
	public ReleaseLevelLogic() {
		numberOfUnreleasedSets = 3;
	}
	
	/**
	 * Decrements the number of unreleased sets by one.
	 */
	public void decrementNumberOfUnreleasedSets() {
		numberOfUnreleasedSets--;
	}
}
