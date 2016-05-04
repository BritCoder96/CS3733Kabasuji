package models;

import junit.framework.TestCase;

public class TestSaveFile extends TestCase {

	SaveFile file;
	Level level;
	
	public TestSaveFile(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		file = new SaveFile();
		level = new Level(0, 0, LevelType.PUZZLE, "test", false);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	public void testSaveFileFunctions() {
		file.addLevel(level);
		assertTrue(file.levelExists(0));
		
		file.removeLevel(level);
		assertTrue(!file.levelExists(0));
	}
}
