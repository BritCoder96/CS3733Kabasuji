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

import main.KabasujiMain;
import models.ExtraLevelLogic;
import models.Level;
import models.LevelType;
import models.SaveFile;
import controllers.MoveToLevelController;
import controllers.PreviousLevelController;
import controllers.ResetLevelsController;
import controllers.ToggleLevelEntryController;
import controllers.NextLevelController;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

/**
 * The screen from which the player can select which unlocked level to play.
 * @author bhuchley
 * @author bjbenson
 */
public class LevelSelect extends JPanel {
	
	/** TODO generic set of levels for testing this screen, the only thing that varies is number of stars and level type */
	//public static final ArrayList<Level> TEST_LEVELS = new ArrayList<Level>();

	
	/** The frame that the panel is shown in. */
	KabasujiFrame frame;
	
	/** The levels that exist for play */
	ArrayList<Level> levels = new ArrayList<Level>();
	
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
    /** The button that allows the user to navigate to a previous level. */
    JButton btnPrevious;
    /** The button that allows the user to navigate to a succeeding level. */
    JButton btnNext;
    /** The button that allows the user to reset the stars in all the levels. */
    JButton btnReset;
    
	/** The controller for btnPlay. */
	MoveToLevelController moveToLevelController;
	
	/** The index of the currently selected level in the list of levels. */
	int currentLevelIndex;
	
	/** The index of the last unlocked level in the list of levels. */
	int lastUnlockedLevelIndex;

	/**
	 * Load the first level in the list and create the frame showing that level.
	 * @param frame the frame to show the screen in
	 */
	public LevelSelect(KabasujiFrame frame) {
		this.frame = frame;
		currentLevelIndex = 0;
		lastUnlockedLevelIndex = 0;
		loadLevels();
		
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
		moveToLevelController = new MoveToLevelController(levels.get(currentLevelIndex), frame, this);
		btnPlay.addActionListener(moveToLevelController);
		
		btnPrevious = new JButton("Previous");
		btnPrevious.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPrevious.setBounds(51, 253, 120, 45);
		add(btnPrevious);
		btnPrevious.addActionListener(new PreviousLevelController(this));
		
		btnReset = new JButton("Reset Levels");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReset.setBounds(51, 253, 120, 45);
		add(btnReset);
		btnReset.addActionListener(new ResetLevelsController(this));
		
		
		btnNext = new JButton("Next");
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNext.setBounds(611, 253, 120, 45);
		add(btnNext);
		btnNext.addActionListener(new NextLevelController(this, levels.size()));
	}
	
	@Override
	public void setVisible(boolean aFlag) {
		updateLevelDisplay();
		super.setVisible(aFlag);
	}
	
	/** 
	 * Load the level given by the current index and refresh the level info display. 
	 */
	private void updateLevelDisplay() {
        // get current level info
		Level currentLevel = levels.get(currentLevelIndex);
		int numStars = currentLevel.getNumberOfStars();
		LevelType lvlType = currentLevel.getLvlType();
		if (numStars > 0 && lastUnlockedLevelIndex < currentLevelIndex + 1) {
			lastUnlockedLevelIndex = currentLevelIndex + 1;
		}

        // display level info
		currentLevelIndexLabel.setText("Level " + (currentLevel.getLevelNumber() + 1));
		starsDisplay.setNumStarsFilled(numStars);
		levelTypeLabel.setText(lvlType.name()); // TODO should be lowercased?

        // enable or disable Play button
		boolean currentLvlComplete = numStars > 0;
		boolean isFirstLevel = currentLevelIndex == 0;
		//boolean previousLevelComplete = isFirstLevel || (SaveFile.instance().getLevel(currentLevelIndex - 1).getNumberOfStars() > 0);
		//boolean currentLevelIsUnlocked = previousLevelComplete;
		boolean currentLevelIsUnlocked = currentLevelIndex <= lastUnlockedLevelIndex;
		btnPlay.setText(currentLevelIsUnlocked ? "Play" : "Locked");
		btnPlay.setEnabled(currentLevelIsUnlocked);
		
        // enable or disable Previous and Next buttons
        btnPrevious.setEnabled(!isFirstLevel);
        btnNext.setEnabled(currentLevelIndex < levels.size() - 1);

        // point Play button to the current level
		btnPlay.removeActionListener(moveToLevelController);
		moveToLevelController = new MoveToLevelController(levels.get(currentLevelIndex), frame, this);
		btnPlay.addActionListener(moveToLevelController);
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
	
	/**
	 * gets the play btn for testing
	 * @return the play btn
	 */
	public JButton getBtnPlay() {
		return btnPlay;
	}
	
	/**
	 * gets the next btn for the next level
	 * @return the next btn
	 */
	public JButton getBtnNext() {
		return btnNext;
	}
	
	/**
	 * gets all the levels
	 * @return the next levels
	 */
	public ArrayList<Level> getLevels() {
		return levels;
	}
	
	/**
	 * clears all the levels
	 */
	public void clearLevels() {
		levels = new ArrayList<Level>();
	}
	
	/**
	 *	Loads all of the levels into the ArrayList
	 */
	public void loadLevels(){
		for (int i = 0, length = SaveFile.instance().getMaxLevelNumber() + 1; i < length; i++) {
			try{
				Level level = SaveFile.instance().getLevel(i);
				levels.add(level);
			}
			catch(IllegalArgumentException e){
				
			}
		}
	}
}
