package views;

import java.awt.Container;
import java.awt.Point;

import javax.swing.JFrame;

import main.PanelBackManager;
import models.Level;
import models.Piece;
import models.PieceSet;
import models.Square;

/**
 * The frame used for the entire game and builder. Screens are implemented as panels that are placed inside it.
 * @author bhuchley
 * @author ejcerini
 */
public class KabasujiFrame extends JFrame {
	/** The back stack of panels that the back button uses. */
	PanelBackManager backMgr;
	
	/**
	 * Construct a new KabasujiFrame with the given back manager.
	 * @param backMgr the back manager to use (should be empty)
	 */
	public KabasujiFrame(PanelBackManager backMgr) {
		super();
		this.backMgr = backMgr;
	}
	
	@Override
	/**
	 * As normal setContentPane, but also adds the pane to the back stack.
	 * @param contentPane the new pane to show
	 */
	public void setContentPane(Container contentPane) {
		super.setContentPane(contentPane);
		backMgr.pushContainer(contentPane);
	}
	
	/**
	 * Pops the backstack, sets the content pane to the last one, then makes that pane visible.
	 */
	public void returnToLastContentPane() {
		super.setContentPane(backMgr.popContainerAndPeek());
		getContentPane().setVisible(true);
	}
}
