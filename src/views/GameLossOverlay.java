package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

/**
 * Overlay that shows when you lose a level. (If you run out of moves in Puzzle or time in Lightning)
 * @author bhuchley
 */
public class GameLossOverlay extends JPanel {

	/** The frame that the panel is shown in. */
	private KabasujiFrame frame;

	/**
	 * Create the frame. It should get the score of the level that was failed but that doesn't exist yet
	 * @param frame the frame to show the screen in
	 */
	public GameLossOverlay(KabasujiFrame frame) {
		this.frame = frame;
		setBounds(100, 100, 300, 250);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JLabel lblOhTooBad = new JLabel("Oh! Too bad!");
		lblOhTooBad.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblOhTooBad.setBounds(71, 28, 141, 27);
		add(lblOhTooBad);
		
		// TODO Once points actually work make this show how many points away you were
		JLabel lblNewLabel = new JLabel("<html><center>You were 2 points away from the star!</center></html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(71, 79, 141, 36);
		add(lblNewLabel);
		
		JButton button = new JButton("Level Select");
		button.setBounds(30, 162, 109, 23);
		add(button);
		
		JButton btnTryAgain = new JButton("Try Again");
		btnTryAgain.setBounds(149, 162, 98, 23);
		add(btnTryAgain);
	}

}
