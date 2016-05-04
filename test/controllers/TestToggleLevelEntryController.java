package controllers;

import junit.framework.TestCase;
import main.PanelBackManager;
import models.Level;
import models.LevelType;
import views.KabasujiFrame;
import views.LevelEntry;
import views.LevelList;

public class TestToggleLevelEntryController extends TestCase {
	ToggleLevelEntryController toggler;
	KabasujiFrame frame;
	LevelList list;
	public TestToggleLevelEntryController(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		frame = new KabasujiFrame(new PanelBackManager());
		list =new LevelList(frame);
		toggler = new ToggleLevelEntryController(new LevelEntry(new Level(0, 0, LevelType.RELEASE, "test", false)), new LevelList(frame), frame);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testToggleLevelEntryController() {
		list.setToggledLevelEntry(null);
		toggler.mouseClicked(null);
		assertTrue(list.getBtnEdit().getActionListeners().length == 1);
		assertTrue(list.getBtnDelete().getActionListeners().length == 1);
		list.setToggledLevelEntry(new LevelEntry(new Level(1, 1, LevelType.RELEASE, "test", false)));
		toggler.mouseClicked(null);
		assertTrue(list.getBtnEdit().getActionListeners().length == 1);
		assertTrue(list.getBtnDelete().getActionListeners().length == 1);
	}
}
