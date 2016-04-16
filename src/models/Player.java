package models;

public class Player {
	// eager implementation of the singleton pattern
	
	// TODO: use some other Level array for the parameter
	static final Player inst = new Player(new Level[15]);
	
	Level[] levels;
	
	Level currentLevel;
	
	Player(Level[] levels) {
		this.levels = levels;
	}
	
	public Player instance() {
		return inst;
	}
	
	public void playLevel(int levelNumber) {
		for(Level i : levels) {
			if(i.levelNumber == levelNumber){
				currentLevel = i;
				// TODO: more stuff?
				return;
			}
		}
		// must play a level that exists
		throw new IllegalArgumentException();
	}
}
