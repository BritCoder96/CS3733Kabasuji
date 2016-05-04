package views;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import controllers.MoveToBuilderLevelListController;
import controllers.MoveToLevelSelectController;
import controllers.SaveLevelController;
import main.KabuildsujiMain;
import models.Level;
import models.SaveFile;
import junit.framework.TestCase;

/**
 * Tests the builder views
 * @author bjbenson
 *
 */
public class TestBuilderViews extends TestCase {
	public void testMouseClickTriggersEvent() throws Exception { 
		SaveFile.instance().clear();
		MoveToLevelSelectController.pathToLevelsFolder = "test_levels/";
		MoveToBuilderLevelListController.pathToLevelsFolder = "test_levels/";
		KabuildsujiMain player = new KabuildsujiMain();
		player.main(new String [0]);
		KabasujiFrame frame = player.getFrame();

	    // Simulate a mouse click using reflection.
		// click the play on the title screen.
	    BuilderTitle title = (BuilderTitle) frame.getContentPane();
	    title.getBtnEdit().doClick();;
	    // click new level on level list screen
	    LevelList lvlSelect = (LevelList) frame.getContentPane();
	    lvlSelect.getBtnNew().doClick();
	    // click to make a new Puzzle level on level list screen
	    NewLevel level = (NewLevel) frame.getContentPane();
	    level.getNameField().setText("3");
	    level.getBtnGo().doClick();
	    PuzzleEditor pEditor = (PuzzleEditor) frame.getContentPane();
	    pEditor.getBtnAddPiece().doClick();
	    AddPieceOverlay apo = (AddPieceOverlay) frame.getContentPane();
	    MouseEvent me = new MouseEvent(apo.getBullpenView().getPieceAtY(40), MouseEvent.MOUSE_CLICKED, 0, MouseEvent.BUTTON1_MASK, 100, 10, 1, false, MouseEvent.BUTTON1);
	    apo.getBullpenView().getPieceAtY(40).dispatchEvent(me);
	    pEditor.getBtnBack().doClick();
	    level.getNameField().setText("4");
	    level.getLightningBtn().doClick();
	    level.getBtnGo().doClick();
	    LightningEditor lEditor = (LightningEditor) frame.getContentPane();
	    lEditor.getBtnBack().doClick();
	    level.getNameField().setText("5");
	    level.getReleaseBtn().doClick();
	    level.getBtnGo().doClick();
	    ReleaseEditor rEditor = (ReleaseEditor) frame.getContentPane();
	    rEditor.getBtnNum().doClick();
	    me = new MouseEvent(rEditor.getBoardView().getSquareAt(1, 1), MouseEvent.MOUSE_CLICKED, 0, MouseEvent.BUTTON1_MASK, 100, 10, 1, false, MouseEvent.BUTTON1);
	    rEditor.getBoardView().getSquareAt(1, 1).dispatchEvent(me);
	    SaveLevelController.serializeLevel(rEditor.getLevel());
	    rEditor.getBtnBack().doClick();	   
	  }
}
