package controllers;

import javax.swing.JLabel;

import junit.framework.TestCase;
import main.PanelBackManager;
import models.Board;
import models.Bullpen;
import models.Level;
import models.LevelType;
import models.ReleaseLevelLogic;
import views.KabasujiFrame;
import views.LevelEditor;
import views.ReleaseEditor;

public class TestToggleBoardSquareController extends TestCase {

	JLabel squareLabel;
	Board board;
	int row;
	int col;
	LevelEditor listener;
	
	public TestToggleBoardSquareController(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		squareLabel = new JLabel();
		board = new Board(1, 1, LevelType.RELEASE);
		row = 0;
		col = 0;
		listener = new ReleaseEditor(new KabasujiFrame(new PanelBackManager()),
				new Level(board, new Bullpen(), 0, 0, new ReleaseLevelLogic(), "Name"));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testToggleBoardControllerFunctions() {
		ToggleBoardSquareController tbsc = new ToggleBoardSquareController(squareLabel, board, row, col, listener);
		
		tbsc.mouseClicked(null);
		
		assertTrue(squareLabel.isOpaque());
		
	}

}
