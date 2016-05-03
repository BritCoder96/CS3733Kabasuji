package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.GoBackOnePanelController;
import controllers.MoveToLevelController;
import controllers.ReplaceWithLevelController;
import main.KabasujiMain;
import models.Level;
import models.SaveFile;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Overlay that shows when you lose a level. (If you run out of moves in Puzzle or time in Lightning)
 * @author bhuchley
 * @author bjbenson
 */
public class GameLossOverlay extends JPanel {

	/** The frame that the panel is shown in. */
	private KabasujiFrame frame;
	/** the button that goes back to the level select screen. */
	private JButton btnLevelSelect;

	/**
	 * Create the frame. It should get the score of the level that was failed but that doesn't exist yet
	 * @param frame the frame to show the screen in
	 */
	public GameLossOverlay(Level level, KabasujiFrame frame) {
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setLayout(null);
		
		JLabel lblOhTooBad = new JLabel("Oh! Too bad!");
		lblOhTooBad.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblOhTooBad.setBounds(330, 199, 141, 27);
		add(lblOhTooBad);
		
		btnLevelSelect = new JButton("Level Select");
		btnLevelSelect.addActionListener(new GoBackOnePanelController(frame));
		btnLevelSelect.setBounds(255, 424, 109, 23);
		add(btnLevelSelect);
		
		JButton btnTryAgain = new JButton("Try Again");
		btnTryAgain.setBounds(427, 424, 98, 23);
		add(btnTryAgain);
		btnTryAgain.addActionListener(new ReplaceWithLevelController(SaveFile.instance().getLevel(level.getLevelNumber()), frame, this));
	}
	
	/**
	 * gets the level select button
	 * @return the level select button
	 */
	public JButton getBtnLvlSelect() {
		return btnLevelSelect;
	}
}
