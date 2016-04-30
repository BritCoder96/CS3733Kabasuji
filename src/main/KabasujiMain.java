package main;
import java.awt.Rectangle;

import javax.swing.JFrame;

import models.SaveFile;
import views.KabasujiFrame;

/**
 * The class that launches the game.
 * @author bhuchley
 *
 */
public class KabasujiMain {
	static SaveFile Kabasuji;;
	
	public static Rectangle windowSize = new Rectangle(100, 100, 800, 650);

	private static KabasujiFrame frame;
	private static PanelBackManager backMgr;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Kabasuji = SaveFile.instance();
		backMgr = new PanelBackManager();
		frame = new KabasujiFrame(backMgr);
		frame.setContentPane(new views.Title(frame));
		frame.setVisible(true);
		frame.setBounds(KabasujiMain.windowSize);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
