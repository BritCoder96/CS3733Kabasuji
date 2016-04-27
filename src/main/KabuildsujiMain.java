package main;
import java.awt.Rectangle;

import views.KabasujiFrame;

public class KabuildsujiMain {

	public static Rectangle windowSize = new Rectangle(100, 100, 800, 650);

	private static KabasujiFrame frame;
	private static PanelBackManager backMgr;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		backMgr = new PanelBackManager();
		frame = new KabasujiFrame(backMgr);
		frame.setVisible(true);
		frame.setBounds(KabuildsujiMain.windowSize);
		frame.setDefaultCloseOperation(KabasujiFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new views.BuilderTitle(frame));
		
	}
}
