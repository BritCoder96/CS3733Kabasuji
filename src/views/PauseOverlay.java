package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

/**
 * Overlay that is shown when the game is paused.
 * @author bhuchley
 */
public class PauseOverlay extends JPanel {

	/** The frame that the panel is shown in. */
	private KabasujiFrame frame;

	/**
	 * Create the overlay.
	 * @param frame the frame to show the screen in
	 */
	public PauseOverlay(KabasujiFrame frame) {
		this.frame = frame;
		setBounds(100, 100, 250, 350);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JLabel lblPaused = new JLabel("Paused");
		lblPaused.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblPaused.setBounds(67, 28, 99, 34);
		add(lblPaused);
		
		JButton btnResume = new JButton("Resume");
		btnResume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnResume.setBounds(57, 100, 120, 45);
		add(btnResume);
		
		JButton btnTitle = new JButton("Title");
		btnTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTitle.setBounds(57, 163, 120, 45);
		add(btnTitle);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnQuit.setBounds(57, 230, 120, 45);
		add(btnQuit);
	}

}
