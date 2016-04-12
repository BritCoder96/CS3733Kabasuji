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

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class LevelSelect extends JPanel {
	
	// TODO generic set of levels for testing this screen, the only thing that varies is number of stars and level type
	public static final ArrayList<Level> TEST_LEVELS = new ArrayList<Level>();
	static {
		TEST_LEVELS.add(new Level(0, 3, null, null, LevelType.PUZZLE, new ExtraLevelLogic()));
		TEST_LEVELS.add(new Level(1, 2, null, null, LevelType.LIGHTNING, new ExtraLevelLogic()));
		TEST_LEVELS.add(new Level(2, 1, null, null, LevelType.RELEASE, new ExtraLevelLogic()));
		for (int i = 3; i < 15; i++) {
			TEST_LEVELS.add(new Level(i, 0, null, null, LevelType.PUZZLE, new ExtraLevelLogic()));
		}
	}
	
	KabasujiFrame frame;
	
	JPanel levelInfoPanel;
	StarsDisplay starsDisplay;
	JLabel currentLevelIndexLabel;
	JLabel levelTypeLabel;
	JButton btnPlay;
	
	ArrayList<Level> levels;
	int currentLevelIndex;

	/**
	 * Create the frame.
	 */
	public LevelSelect(KabasujiFrame frame, ArrayList<Level> levels) {
		this.frame = frame;
		this.levels = levels;
		currentLevelIndex = 0;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
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
		btnPrevious.addActionListener(new PreviousLevelController(this, levels));
		
		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNext.setBounds(611, 253, 120, 45);
		add(btnNext);
		btnNext.addActionListener(new NextLevelController(this, levels));
		
		updateLevelDisplay();
	}
	
	private void updateLevelDisplay() {
		Level currentLevel = levels.get(currentLevelIndex);
		int numStars = currentLevel.getNumStars();
		LevelType lvlType = currentLevel.getLevelType();
		currentLevelIndexLabel.setText("Level " + (currentLevelIndex + 1));
		starsDisplay.setNumStarsFilled(numStars);
		levelTypeLabel.setText(lvlType.name()); // TODO should be lowercased?
		boolean currentLvlComplete = numStars > 0;
		boolean isFirstLevel = currentLevelIndex == 0;
		boolean previousLevelComplete = isFirstLevel || (levels.get(currentLevelIndex - 1).getNumStars() > 0);
		boolean currentLevelIsUnlocked = lvlType == LevelType.PUZZLE && (currentLvlComplete || previousLevelComplete);
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
