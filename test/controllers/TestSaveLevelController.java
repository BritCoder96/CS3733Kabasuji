package controllers;

import junit.framework.TestCase;
import models.*;

public class TestSaveLevelController extends TestCase {

	protected void setUp(){
		Level level = new Level(2, 3, 4, 1, LevelType.PUZZLE, new PuzzleLevelLogic(3, 2), "temp");
	}

	protected void tearDown(){
	}
	
	public TestSaveLevelController(String name) {
	}

}
