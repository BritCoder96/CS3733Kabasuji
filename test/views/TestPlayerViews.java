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
	    Title title = new Title(frame);
	    title.getBtnPlay().doClick();;
	    // click select on level select screen
	    LevelSelect lvlSelect = new LevelSelect(frame);
	    lvlSelect.getBtnNext().doClick();
	    lvlSelect.getBtnPlay().doClick();
	    Thread.sleep(1500);
	  }
}
