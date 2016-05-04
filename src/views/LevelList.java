package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controllers.CreateNewLevelController;
import controllers.GoBackOnePanelController;
import controllers.MoveToBuilderLevelListController;
import controllers.MoveToLevelController;
import controllers.MoveToEditorController;
import controllers.ToggleLevelEntryController;
import main.KabasujiMain;
import models.Level;
import models.LevelType;
import models.SaveFile;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

/**
 * The screen that shows the list of existing levels and allows you to select one to edit or delete.
 * @author ejcerini
 * @author bjbenson
 */
public class LevelList extends JPanel {

	/** The frame that the panel is shown in. */
	private KabasujiFrame frame;
	
	/** The toggled level entry */
	JPanel toggledLevelEntry;
	
	/** The panel that holds level entries. */
	JPanel panel;
	
	/** The button that edits an existing level. */
	JButton btnEdit;
	
	/** The button that deletes an existing level. */
	JButton btnDelete;
	
	/** The levels on the screen */
	ArrayList<LevelEntry> entries = new ArrayList<LevelEntry>();
	
	/** The button that creates a new level. */
	private JButton btnNew;
	
	/**
	 * Get the existing levels and make the frame to show them.
	 * @param frame the frame to show the screen in
	 */
	public LevelList(KabasujiFrame frame) {
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(0, 0, 120, 45);
		add(btnBack);
		btnBack.addActionListener(new GoBackOnePanelController(frame));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(150, 100, 500, 350);
		add(scrollPane);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(null);
		
		btnNew = new JButton("New");
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNew.setBounds(504, 480, 120, 45);
		add(btnNew);
		btnNew.addActionListener(new CreateNewLevelController(frame, this));
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEdit.setBounds(160, 480, 120, 45);
		add(btnEdit);
		btnEdit.setEnabled(false);
		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(332, 480, 120, 45);
		add(btnDelete);
		btnDelete.setEnabled(false);
		
		updateLevelEntries();
	}
	
	/** 
	 * Load the level given by the current index and refresh the level info display. 
	 */
	private void updateLevelEntries() {
		for (int i = 0, length = SaveFile.instance().getNumberOfLevels(); i < length; i++) {
			LevelEntry levelEntry = new LevelEntry(SaveFile.instance().getLevel(i));
			entries.add(levelEntry);
			levelEntry.setBounds(10, 10 + 70 * (levelEntry.getLevel().getLevelNumber()), 463, 50);
			panel.add(levelEntry);
			levelEntry.addMouseListener(new ToggleLevelEntryController(levelEntry, this, frame));
		}
	}

	/**
	 * Gets the toggled level entry
	 * @return the entry
	 */
	public JPanel getToggledLevelEntry() {
		return toggledLevelEntry;
	}
	
	/**
	 * Sets the toggled level entry
	 * @param levelEntry the new toggled entry
	 */
	public void setToggledLevelEntry(JPanel levelEntry) {
		toggledLevelEntry = levelEntry;
	}
	
	/**
	 * Gets the edit level button
	 * @return the btn
	 */
	public JButton getBtnEdit() {
		return btnEdit;
	}
	
	/**
	 * Gets the delete level button
	 * @return the btn
	 */
	public JButton getBtnDelete() {
		return btnDelete;
	}
	
	/**
	 * Gets the new level button
	 * @return the btn
	 */
	public JButton getBtnNew() {
		return btnNew;
	}
	
	public void reload(){
		removeEntries();
		updateLevelEntries();
	}

	private void removeEntries() {
		for(LevelEntry i : entries){
			panel.remove(i);
		}
		
		entries = new ArrayList<LevelEntry>();
	}

}
