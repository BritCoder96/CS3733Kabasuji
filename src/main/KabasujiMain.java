package main;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class KabasujiMain {

	public static Rectangle windowSize = new Rectangle(100, 100, 800, 650);

	private static JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(KabasujiMain.windowSize);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new views.Title(frame));
	}
}
