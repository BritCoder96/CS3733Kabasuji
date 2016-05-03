package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.GoBackOnePanelController;
import controllers.ReplaceWithLevelController;
import main.KabasujiMain;
import models.Level;
import models.SaveFile;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Overlay that shown when the player wins.
 * @author bhuchley
 */
public class GameWinOverlay extends JPanel {
	/** The number of stars to display. */
	int numberOfStars;
	
	/** The frame that the panel is shown in. */
	private KabasujiFrame frame;

	/**
	 * Create the overlay, getting the score of the completed level.
	 * @param frame the frame to show the screen in
	 */
	public GameWinOverlay(Level level, KabasujiFrame frame) {
		this.numberOfStars = level.getNumberOfStars();
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setLayout(null);
		
		StarsDisplay starsDisplay = new StarsDisplay(numberOfStars);
		starsDisplay.setBounds(307, 307, 186, 40);
		add(starsDisplay);
		
		JLabel lblYouDidIt = new JLabel("You Did It!");
		lblYouDidIt.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblYouDidIt.setBounds(342, 140, 115, 27);
		add(lblYouDidIt);
		
		JButton btnLevelSelect = new JButton("Level Select");
		btnLevelSelect.addActionListener(new GoBackOnePanelController(frame));
		btnLevelSelect.setBounds(185, 487, 109, 23);
		add(btnLevelSelect);
		
		JButton btnReplay = new JButton("Replay");
		btnReplay.addActionListener(new ReplaceWithLevelController(SaveFile.instance().getLevel(level.getLevelNumber()), frame, this));
		btnReplay.setBounds(359, 487, 81, 23);
		add(btnReplay);
		
		JButton btnNextLevel = new JButton("Next Level");
		btnNextLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNextLevel.setBounds(484, 487, 102, 23);
		add(btnNextLevel);
		btnNextLevel.addActionListener(new ReplaceWithLevelController(SaveFile.instance().getLevel(level.getLevelNumber() + 1), frame, this));
	}
}
