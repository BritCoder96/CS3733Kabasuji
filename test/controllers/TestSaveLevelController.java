package controllers;

import junit.framework.TestCase;
import models.*;
import java.awt.event.ActionEvent;

public class TestSaveLevelController extends TestCase {

	protected void setUp(){
	}

	protected void tearDown(){
	}
	
	public void testSaveLevelController() {
		Level level = new Level(2, 3, 4, LevelType.PUZZLE, "test");
		SaveLevelController saveLevel = new SaveLevelController(level);
		saveLevel.actionPerformed(new ActionEvent(null, 0, null));
	}

}
