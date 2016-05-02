package views;

import java.awt.event.MouseEvent;

import controllers.SaveLevelController;
import main.KabuildsujiMain;
import junit.framework.TestCase;

/**
 * Tests the builder views
 * @author bjbenson
 *
 */
public class TestBuilderViews extends TestCase {
	public void testMouseClickTriggersEvent() throws Exception { 
		KabuildsujiMain player = new KabuildsujiMain();
		player.main(new String [0]);
		KabasujiFrame frame = player.getFrame();

	    // Simulate a mouse click using reflection.
		// click the play on the title screen.
	    BuilderTitle title = new BuilderTitle(frame);
	    title.getBtnEdit().doClick();;
	    // click new level on level list screen
	    LevelList lvlSelect = new LevelList(frame);
	    lvlSelect.getBtnNew().doClick();
	    // click to make a new Puzzle level on level list screen
	    NewLevel level = new NewLevel(frame);
	    level.getNameField().setText("3");
	    level.getBtnGo().doClick();
	    PuzzleEditor pEditor = new PuzzleEditor(frame, level.getName(), level.getRows(), level.getCols(), level.getMoveLimit());
	    pEditor.getBtnBack().doClick();
	    pEditor.getBtnAddPiece().doClick();
	    AddPieceOverlay apo = new AddPieceOverlay(frame, pEditor);
	    apo.getBullpenView().dispatchEvent(new MouseEvent(apo, 0, 0, 100, 40, 0, 0, false, 0));
	    level.getNameField().setText("4");
	    level.getLightningBtn().doClick();
	    LightningEditor lEditor = new LightningEditor(frame, level.getName(), level.getRows(), level.getCols(), level.getTimeLimit());
	    lEditor.getBtnBack().doClick();
	    level.getNameField().setText("5");
	    level.getReleaseBtn().doClick();
	    ReleaseEditor rEditor = new ReleaseEditor(frame, level.getName(), level.getRows(), level.getCols());
	    rEditor.getBtnBack().doClick();
	    SaveLevelController slc = new SaveLevelController(rEditor.getLevel());
	    slc.serializeLevel(rEditor.getLevel());
	  }
}
