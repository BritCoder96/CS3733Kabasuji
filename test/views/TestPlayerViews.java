package views;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;

import javax.swing.JButton;

import controllers.MoveToBuilderLevelListController;
import controllers.MoveToLevelSelectController;
import main.KabasujiMain;
import models.SaveFile;
import junit.framework.TestCase;

/**
 * Tests the player views
 * @author bjbenson
 *
 */
public class TestPlayerViews extends TestCase {
	
	public void testMouseClickTriggersEvent() throws Exception { 
		SaveFile.instance().clear();
		MoveToLevelSelectController.pathToLevelsFolder = "test_levels/";
		MoveToBuilderLevelListController.pathToLevelsFolder = "test_levels/";
		KabasujiMain player = new KabasujiMain();
		player.main(new String [0]);
		KabasujiFrame frame = player.getFrame();

	    // Simulate a mouse click using reflection.
		// click the play on the title screen.
	    Title title = (Title)frame.getContentPane();
	    title.getBtnPlay().doClick();;
	    // click select on level select screen
	    LevelSelect lvlSelect = (LevelSelect)frame.getContentPane();
	    lvlSelect.getBtnPlay().doClick();
	    GameScreen gameScreen = (GameScreen)frame.getContentPane();
	    MouseEvent me = new MouseEvent(gameScreen.getBullpen().scrollingPanel, MouseEvent.MOUSE_CLICKED, 0, MouseEvent.BUTTON1_MASK, 111, 94, 1, false, MouseEvent.BUTTON1);
	    gameScreen.getBullpen().scrollingPanel.dispatchEvent(me);
	    me = new MouseEvent(gameScreen, MouseEvent.MOUSE_MOVED, 0, 0, 200, 200, 0, false, 0);
	    gameScreen.dispatchEvent(me);me = new MouseEvent(gameScreen.getBoardView().getSquareAt(1, 1), MouseEvent.MOUSE_MOVED, 0, 0, 10, 10, 0, false, 0);
	    gameScreen.getBoardView().getSquareAt(1, 1).dispatchEvent(me);
	    me = new MouseEvent(gameScreen.getBoardView().getSquareAt(1, 1), MouseEvent.MOUSE_CLICKED, 0, 0, 10, 10, 1, false, MouseEvent.BUTTON1);
	    gameScreen.getBoardView().getSquareAt(1, 1).dispatchEvent(me);
	    Thread.sleep(1000);
	    GameLossOverlay lossScreen = (GameLossOverlay)frame.getContentPane();
	    lossScreen.getBtnLvlSelect().doClick();
	    LevelSelect lvlSelect2 = (LevelSelect)frame.getContentPane();
	    lvlSelect2.getBtnNext().doClick();
	    lvlSelect2.getBtnPlay().doClick();
	    gameScreen = (GameScreen)frame.getContentPane();
	    KeyEvent key = new KeyEvent(gameScreen, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Q');
	    gameScreen.getKeyListeners()[0].keyPressed(key);
	    me = new MouseEvent(gameScreen.getBullpen().scrollingPanel, MouseEvent.MOUSE_CLICKED, 0, MouseEvent.BUTTON1_MASK, 111, 94, 1, false, MouseEvent.BUTTON1);
	    gameScreen.getBullpen().scrollingPanel.dispatchEvent(me);
	    me = new MouseEvent(gameScreen, MouseEvent.MOUSE_MOVED, 0, 0, 200, 200, 0, false, 0);
	    gameScreen.dispatchEvent(me);
	    me = new MouseEvent(gameScreen.getBoardView().getSquareAt(1, 1), MouseEvent.MOUSE_MOVED, 0, 0, 10, 10, 0, false, 0);
	    gameScreen.getBoardView().getSquareAt(1, 1).dispatchEvent(me);
	    me = new MouseEvent(gameScreen.getBoardView().getSquareAt(1, 1), MouseEvent.MOUSE_CLICKED, 0, 0, 10, 10, 1, false, MouseEvent.BUTTON1);
	    gameScreen.getBoardView().getSquareAt(1, 1).dispatchEvent(me);
    	Thread.sleep(3000);
	    GameWinOverlay winScreen = (GameWinOverlay)frame.getContentPane();
	    winScreen.getBtnLvlSelect().doClick();
	  }
}
