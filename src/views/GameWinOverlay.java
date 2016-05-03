package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.GoBackOnePanelController;
import main.KabasujiMain;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Overlay that shown when the player wins.
 * @author bhuchley
 *  @author bjbenson
 */
public class GameWinOverlay extends JPanel {
	/** The number of stars to display. */
	int numberOfStars;
	
	/** The frame that the panel is shown in. */
	private KabasujiFrame frame;
	/** the button that goes back to the level select screen. */
	private JButton btnLevelSelect;

	/**
	 * Create the overlay, getting the score of the completed level.
	 * @param frame the frame to show the screen in
	 */
	public GameWinOverlay(int numberOfStars, KabasujiFrame frame) {
		this.numberOfStars = numberOfStars;
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setLayout(null);
		
		StarsDisplay starsDisplay = new StarsDisplay(numberOfStars);
		starsDisplay.setBounds(49, 66, 186, 40);
		add(starsDisplay);
		
		JLabel lblYouDidIt = new JLabel("You Did It!");
		lblYouDidIt.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblYouDidIt.setBounds(84, 21, 115, 27);
		add(lblYouDidIt);
		
		btnLevelSelect = new JButton("Level Select");
		btnLevelSelect.addActionListener(new GoBackOnePanelController(frame));
		btnLevelSelect.setBounds(35, 177, 109, 23);
		add(btnLevelSelect);
		
		JButton btnReplay = new JButton("Replay");
		btnReplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReplay.setBounds(154, 177, 81, 23);
		add(btnReplay);
		
		JButton btnNextLevel = new JButton("Next Level");
		btnNextLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNextLevel.setBounds(91, 150, 102, 23);
		add(btnNextLevel);
	}
	
	/**
	 * gets the level select button
	 * @return the level select button
	 */
	public JButton getBtnLvlSelect() {
		return btnLevelSelect;
	}
}
