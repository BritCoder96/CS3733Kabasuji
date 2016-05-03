package views;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;

import javax.swing.JButton;

import controllers.MoveToLevelSelectController;
import main.KabasujiMain;
import junit.framework.TestCase;

/**
 * Tests the player views
 * @author bjbenson
 *
 */
public class TestPlayerViews extends TestCase {

	public void testMouseClickTriggersEvent() throws Exception { 
		MoveToLevelSelectController.pathToLevelsFolder = "test_levels/";
		KabasujiMain player = new KabasujiMain();
		player.main(new String [0]);
		KabasujiFrame frame = player.getFrame();

	    // Simulate a mouse click using reflection.
		// click the play on the title screen.
	    Title title = (Title)frame.getContentPane();
	    title.getBtnPlay().doClick();;
	    // click select on level select screen
	    LevelSelect lvlSelect = (LevelSelect)frame.getContentPane();
	    lvlSelect.getBtnNext().doClick();
	    lvlSelect.getBtnPlay().doClick();
	    GameScreen gameScreen = (GameScreen)frame.getContentPane();
	    MouseEvent me = new MouseEvent(gameScreen.getBullpen().scrollingPanel, MouseEvent.MOUSE_CLICKED, 0, MouseEvent.BUTTON1_MASK, 111, 94, 1, false, MouseEvent.BUTTON1);
	    gameScreen.getBullpen().scrollingPanel.dispatchEvent(me);
	    me = new MouseEvent(gameScreen, MouseEvent.MOUSE_MOVED, 0, 0, 200, 200, 0, false, 0);
	    gameScreen.dispatchEvent(me);me = new MouseEvent(gameScreen.getBoardView().getSquareAt(1, 1), MouseEvent.MOUSE_MOVED, 0, 0, 10, 10, 0, false, 0);
	    gameScreen.getBoardView().getSquareAt(1, 1).dispatchEvent(me);
	    me = new MouseEvent(gameScreen.getBoardView().getSquareAt(1, 1), MouseEvent.MOUSE_CLICKED, 0, 0, 10, 10, 1, false, MouseEvent.BUTTON1);
	    Thread.sleep(3000);
	  }
}
