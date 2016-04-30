package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import controllers.NextLevelController;
import controllers.PreviousLevelController;
import main.KabasujiMain;
import models.ExtraLevelLogic;
import models.Level;
import models.LevelType;
import models.SaveFile;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

/**
 * The screen from which the player can select which unlocked level to play.
 * @author bhuchley
 */
public class LevelSelect extends JPanel {
	
	/** TODO generic set of levels for testing this screen, the only thing that varies is number of stars and level type */
	//public static final ArrayList<Level> TEST_LEVELS = new ArrayList<Level>();

	
	/** The frame that the panel is shown in. */
	KabasujiFrame frame;
	
	/** The panel that the info for the currently selected level is shown in. */
	JPanel levelInfoPanel;
	/** The panel showing the stars earned on the currently selected level. */
	StarsDisplay starsDisplay;
	/** The label showing which level is selected, by number. */
	JLabel currentLevelIndexLabel;
	/** The label showing what type of level the currently selected level is. */
	JLabel levelTypeLabel;
	/** The button that allows the user to play the currently selected level. */
	JButton btnPlay;
	
	/** The index of the currently selected level in the list of levels. */
	int currentLevelIndex;

	/**
	 * Load the first level in the list and create the frame showing that level.
	 * @param frame the frame to show the screen in
	 * @param levels the list of levels in the game
	 */
	public LevelSelect(KabasujiFrame frame) {
		this.frame = frame;
		currentLevelIndex = 0;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		/*
		TEST_LEVELS.add(new Level(1, 6, 0, LevelType.PUZZLE, "Level 1"));
		TEST_LEVELS.get(0).setNumberOfStars(3);
		TEST_LEVELS.add(new Level(2, 3, 1, LevelType.LIGHTNING, "Level 2"));
		TEST_LEVELS.get(1).setNumberOfStars(2);
		TEST_LEVELS.add(new Level(3, 2, 2, LevelType.RELEASE, "Level 3"));
		TEST_LEVELS.get(2).setNumberOfStars(1);
		for (int i = 3; i < 15; i++) {
			TEST_LEVELS.add(new Level(6, 1, i, LevelType.PUZZLE, "Level " + (i + 1)));
			TEST_LEVELS.get(i).setNumberOfStars(0);
		}
		*/
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(0, 0, 120, 45);
		add(btnBack);
		btnBack.addActionListener(new controllers.GoBackOnePanelController(frame));
		
		levelInfoPanel = new JPanel();
		levelInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		levelInfoPanel.setBounds(222, 141, 338, 268);
		add(levelInfoPanel);
		levelInfoPanel.setLayout(null);
		
		currentLevelIndexLabel = new JLabel("Level X");
		currentLevelIndexLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentLevelIndexLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		currentLevelIndexLabel.setBounds(121, 43, 96, 28);
		levelInfoPanel.add(currentLevelIndexLabel);
		
		starsDisplay = new StarsDisplay();
		starsDisplay.setBounds(76, 185, 186, 40);
		levelInfoPanel.add(starsDisplay);
		
		levelTypeLabel = new JLabel("Lightning");
		levelTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		levelTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		levelTypeLabel.setBounds(116, 114, 106, 28);
		levelInfoPanel.add(levelTypeLabel);
		
		btnPlay = new JButton("Play");
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPlay.setBounds(332, 467, 120, 45);
		add(btnPlay);
		btnPlay.addActionListener(new controllers.MoveToLevelController(frame, this));
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPrevious.setBounds(51, 253, 120, 45);
		add(btnPrevious);
		btnPrevious.addActionListener(new PreviousLevelController(this, SaveFile.instance().getLevels()));
		
		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNext.setBounds(611, 253, 120, 45);
		add(btnNext);
		btnNext.addActionListener(new NextLevelController(this, SaveFile.instance().getLevels()));
		
		updateLevelDisplay();
	}
	
	/** 
	 * Load the level given by the current index and refresh the level info display. 
	 */
	private void updateLevelDisplay() {
		Level currentLevel = SaveFile.instance().getLevel(currentLevelIndex);
		int numStars = currentLevel.getNumberOfStars();
		LevelType lvlType = currentLevel.getLvlType();
		currentLevelIndexLabel.setText("Level " + (currentLevelIndex + 1));
		starsDisplay.setNumStarsFilled(numStars);
		levelTypeLabel.setText(lvlType.name()); // TODO should be lowercased?
		boolean currentLvlComplete = numStars > 0;
		boolean isFirstLevel = currentLevelIndex == 0;
		boolean previousLevelComplete = isFirstLevel || (SaveFile.instance().getLevel(currentLevelIndex - 1).getNumberOfStars() > 0);
		boolean currentLevelIsUnlocked = previousLevelComplete;
		btnPlay.setText(currentLevelIsUnlocked ? "Play" : "Locked");
		btnPlay.setEnabled(currentLevelIsUnlocked);
	}
	
	public int getCurrentLevelIndex() {
		return currentLevelIndex;
	}
	
	public void moveToPreviousLevel() {
		currentLevelIndex--;
		updateLevelDisplay();
	}
	
	public void moveToNextLevel() {
		currentLevelIndex++;
		updateLevelDisplay();
	}
}
