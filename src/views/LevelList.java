package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.ScrollPaneConstants;

/**
 * The screen that shows the list of existing levels and allows you to select one to edit or delete.
 * 
 * @author ejcerini
 * @author bjbenson
 * @author sthuynh
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
		
		panel = new JPanel();
		panel.setBounds(0, 0, 500, 1500);
		panel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(panel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(150, 100, 500, 350);
		add(scrollPane);
		
		btnNew = new JButton("New");
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNew.setBounds(504, 480, 120, 45);
		add(btnNew);
		btnNew.addActionListener(new CreateNewLevelController(frame, this));
		
		btnEdit = new JButton("Edit");
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
		int addedLevels = 0;
		
		for (int i = 0, length = SaveFile.instance().getMaxLevelNumber(); i < length; i++) {
			try{
				LevelEntry levelEntry = new LevelEntry(SaveFile.instance().getLevel(i));
				entries.add(levelEntry);
				levelEntry.setBounds(10, 10 + 70 * (addedLevels), 463, 50);
				panel.add(levelEntry);
				levelEntry.addMouseListener(new ToggleLevelEntryController(levelEntry, this, frame));
				addedLevels += 1;
			}
			catch(IllegalArgumentException e){
				
			}
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
	
	/**
	 * Gets the level panel at the index of the  panel
	 * @return the level panel
	 */
	public LevelEntry getLevelPanel(int index) {
		return entries.get(index);
	}
	
	public void reload(){
		removeEntries();
		updateLevelEntries();
		toggledLevelEntry = null;
		revalidate();
		repaint();
	}

	private void removeEntries() {
		for(LevelEntry i : entries){
			panel.remove(i);
		}
		
		entries = new ArrayList<LevelEntry>();
	}

}
