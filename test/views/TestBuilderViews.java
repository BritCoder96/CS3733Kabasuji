package views;

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
	    Thread.sleep(500);

	    // Simulate a mouse click using reflection.
		// click the play on the title screen.
	    BuilderTitle title = new BuilderTitle(frame);
	    title.getBtnEdit().doClick();;
	    Thread.sleep(500);
	    // click new level on level list screen
	    LevelList lvlSelect = new LevelList(frame);
	    lvlSelect.getBtnNew().doClick();
	    Thread.sleep(500);
	    // click to make a new Puzzle level on level list screen
	    NewLevel level = new NewLevel(frame);
	    level.getNameField().setText("3");
	    level.getBtnGo().doClick();
	    Thread.sleep(500);
	    PuzzleEditor pEditor = new PuzzleEditor(frame, level.getName(), level.getRows(), level.getCols(), level.getMoveLimit());
	    pEditor.getBtnBack().doClick();
	    Thread.sleep(500);
	    level.getNameField().setText("4");
	    level.getLightningBtn().doClick();
	    Thread.sleep(500);
	    LightningEditor lEditor = new LightningEditor(frame, level.getName(), level.getRows(), level.getCols(), level.getTimeLimit());
	    lEditor.getBtnBack().doClick();
	    Thread.sleep(500);
	    level.getNameField().setText("5");
	    level.getReleaseBtn().doClick();
	    Thread.sleep(500);
	    ReleaseEditor rEditor = new ReleaseEditor(frame, level.getName(), level.getRows(), level.getCols());
	    rEditor.getBtnSave().doClick();
	    rEditor.getBtnBack().doClick();
	    Thread.sleep(500);
	    
	  }
}
