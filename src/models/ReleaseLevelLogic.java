package models;

/**
 * Extra level logic exclusive to release levels.
 */
public class ReleaseLevelLogic implements ExtraLevelLogic{
	/**
	 * Number of unreleased sets. 
	 * A set has six board squares that are numbered from 1 to 6 and have the same color.
	 */
	private int numberOfUnreleasedSets;
	
	/**
	 * Constructor to initialize ReleaseLevelLogic fields.
	 */
	public ReleaseLevelLogic() {
		setNumberOfUnreleasedSets(3);
	}
	
	/**
	 * Decrements the number of unreleased sets by one.
	 */
	public void decrementNumberOfUnreleasedSets() {
		setNumberOfUnreleasedSets(getNumberOfUnreleasedSets() - 1);
	}

	public int getNumberOfUnreleasedSets() {
		return numberOfUnreleasedSets;
	}

	public void setNumberOfUnreleasedSets(int numberOfUnreleasedSets) {
		this.numberOfUnreleasedSets = numberOfUnreleasedSets;
	}
}
