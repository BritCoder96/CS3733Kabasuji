package views;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controllers.ActivateGoController;
import controllers.GoBackOnePanelController;
import controllers.MoveToEditorController;
import controllers.UpdateLevelViewController;
import main.KabasujiMain;
import models.Level;
import models.LevelType;

import java.awt.Color;

/**
 * The screen that allows the user to select a level type and size for a new level.
 * Also allows the user to select the number of moves or amount of time for puzzle and lightning levels.
 * @author ejcerini
 * @author bhuchley
 * @author bjbenson
 */
public class NewLevel extends JPanel {

	/** The frame that the panel is shown in. */
	private KabasujiFrame frame;
	/** The text field that the name of the level is typed in to. */
	private JTextField txtInsertNameHere;
	/** The text field used for either the number of moves or the time remaining. */
	private JTextField textField;
	/** The text field for the number of rows. */
	private JTextField txtRows;
	
	/** The type of the level at the moment. */
	public LevelType leveltype;
	
	/** The label indicating that the moves/time field is used for time (when the level is lightning). */
	private JLabel lblTimeLimit;
	/** The label indicating that the moves/time field is used for moves (when the level is puzzle). */
	private JLabel lblMoveLimit;
	/** The button that changes the level type to puzzle. */
	private JButton btnPuzzle;
	/** The button that changes the level type to lightning. */
	private JButton btnLightning;
	/** The button that changes the level type to release. */
	private JButton btnRelease;
	/** The button that starts the editor */
	private JButton btnGo;
	
	/** The controller for the button that starts the editor */
	ActivateGoController btnGoController;
	

	/**
	 * Create the new level screen, defaulting to puzzle.
	 * @param frame the frame to show the screen in
	 */
	public NewLevel(KabasujiFrame frame) {
		this.frame = frame;
		
		leveltype = LevelType.PUZZLE;
		
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(0, 0, 120, 45);
		add(btnBack);
		btnBack.addActionListener(new GoBackOnePanelController(frame));
		
		JLabel lblLevelName = new JLabel("Level Num:");
		lblLevelName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLevelName.setBounds(207, 85, 120, 37);
		add(lblLevelName);
		
		txtInsertNameHere = new JTextField();
		txtInsertNameHere.setText("0");
		txtInsertNameHere.setBounds(339, 95, 156, 22);
		add(txtInsertNameHere);
		txtInsertNameHere.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBounds(88, 135, 582, 213);
		panel.setLayout(null);
		add(panel);
		
		this.btnPuzzle = new JButton("Puzzle");
		btnPuzzle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPuzzle.setBounds(30, 44, 153, 47);
		panel.add(btnPuzzle);
		btnPuzzle.addActionListener(new UpdateLevelViewController(this, LevelType.PUZZLE));
				
		this.btnLightning = new JButton("Lightning");
		btnLightning.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLightning.setBounds(213, 44, 153, 47);
		panel.add(btnLightning);
		btnLightning.addActionListener(new UpdateLevelViewController(this, LevelType.LIGHTNING));

				
		this.btnRelease = new JButton("Release");
		btnRelease.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRelease.setBounds(396, 44, 153, 47);
		panel.add(btnRelease);
		btnRelease.addActionListener(new UpdateLevelViewController(this, LevelType.RELEASE));

		
		this.lblMoveLimit = new JLabel("Move Limit:");
		lblMoveLimit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMoveLimit.setBounds(159, 135, 119, 32);
		panel.add(lblMoveLimit);
		
		textField = new JTextField();
		textField.setText("7");
		textField.setBounds(290, 140, 116, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		this.lblTimeLimit = new JLabel("Time Limit:");
		lblTimeLimit.setBounds(159, 135, 119, 32);
		lblTimeLimit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblTimeLimit);
		
		JLabel lblGridSize = new JLabel("Grid Size: ");
		lblGridSize.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGridSize.setBounds(292, 364, 120, 30);
		add(lblGridSize);
		
		txtRows = new JTextField();
		txtRows.setText("6");
		txtRows.setBounds(402, 368, 52, 22);
		add(txtRows);
		txtRows.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancel.setBounds(230, 451, 118, 45);
		add(btnCancel);
		btnCancel.addActionListener(new GoBackOnePanelController(frame));
		
		btnGo = new JButton("Go!");
		btnGo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGo.setBounds(405, 451, 118, 45);
		add(btnGo);
		btnGoController = new ActivateGoController(this);
		btnGo.addActionListener(btnGoController);
		
		updateOptionDisplay();
	}
	
	/**
	 * Sets the current level type.
	 * @param ltype the level type that is desired
	 */
	public void setLevelType(LevelType ltype){
		leveltype = ltype;
	}
	
	/**
	 * Gets the current level type.
	 * @return the current level type
	 */
	public LevelType getLevelType(){
		return leveltype;
	}
	
	/** 
	 * Updates the screen to reflect which type of level is currently under construction.
	 */
	public void updateOptionDisplay(){
		
		lblTimeLimit.setVisible(false);
		lblMoveLimit.setVisible(false);
		textField.setVisible(false);
		
		btnPuzzle.setEnabled(true);
		btnLightning.setEnabled(true);
		btnRelease.setEnabled(true);
		
		switch(leveltype){
			case PUZZLE:
				lblMoveLimit.setVisible(true);
				textField.setVisible(true);
				btnPuzzle.setEnabled(false);
				
				break;
			case LIGHTNING:
				lblTimeLimit.setVisible(true);
				textField.setVisible(true);
				btnLightning.setEnabled(false);
				
				break;
			case RELEASE:
				btnRelease.setEnabled(false);
				
				break;
		}
	}
	
	public int getRows() {
		return Integer.parseInt(txtRows.getText());
	}
	
	public String getName() {
		return txtInsertNameHere.getText();
	}
	
	/**
	 * @return The time limit currently set for the level. It's taken from the type-specific text field
	 */
	public int getTimeLimit() {
		return Integer.parseInt(textField.getText());
	}
	
	/**
	 * @return The move limit currently set for the level. It's taken from the type-specific text field
	 */
	public int getMoveLimit() {
		return Integer.parseInt(textField.getText());
	}

	/**
	 * Gets the go button
	 * @return the button
	 */
	public JButton getBtnGo() {
		return btnGo;
	}
	
	/**
	 * Gets the Lightning button
	 * @return the button
	 */
	public JButton getLightningBtn() {
		return btnLightning;
	}
	
	/**
	 * Gets the Release button
	 * @return the button
	 */
	public JButton getReleaseBtn() {
		return btnRelease;
	}
	
	/**
	 * Gets the name text field
	 * @return the field
	 */
	public JTextField getNameField() {
		return 	txtInsertNameHere;
	}

	public void invokeNextScreen() {
		MoveToEditorController go = new MoveToEditorController(new Level(getRows(), Integer.parseInt(getName()), getLevelType(), getName(), true), this, frame);
		
		go.actionPerformed(null);
	}
}
