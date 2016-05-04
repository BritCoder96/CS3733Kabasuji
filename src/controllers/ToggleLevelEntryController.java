package controllers;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JButton;

import views.LevelEntry;
import views.LevelList;
import views.KabasujiFrame;

/**
 * Toggle the selection of a level on the level list.
 * 
 * @author sthuynh
 */
public class ToggleLevelEntryController implements MouseListener {
	LevelEntry levelEntry;
	LevelList levelList;
	KabasujiFrame frame;
	
	public ToggleLevelEntryController(LevelEntry levelEntry, LevelList levelList, KabasujiFrame frame) {
		this.levelEntry = levelEntry;
		this.levelList = levelList;
		this.frame = frame;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JPanel toggled = levelList.getToggledLevelEntry();
		JButton btnEdit = levelList.getBtnEdit();
		JButton btnDelete = levelList.getBtnDelete();
		if (toggled == null) {
			btnEdit.addActionListener(new MoveToEditorController(levelEntry.getLevel(), (JPanel) levelList, frame));
			btnEdit.setEnabled(true);
			btnDelete.setEnabled(true);
			btnDelete.addActionListener(new DeleteLevelController(levelEntry.getLevel(), levelList));
			levelEntry.setBackground(Color.YELLOW);
			levelList.setToggledLevelEntry(levelEntry);
		}
		else if (toggled != levelEntry) {
			for (ActionListener i : btnEdit.getActionListeners()) {
				btnEdit.removeActionListener(i);
			}
			for (ActionListener i : btnDelete.getActionListeners()) {
				btnDelete.removeActionListener(i);
			}
			btnEdit.addActionListener(new MoveToEditorController(levelEntry.getLevel(), (JPanel) levelList, frame));
			btnDelete.addActionListener(new DeleteLevelController(levelEntry.getLevel(), levelList));
			toggled.setBackground(toggled.getParent().getBackground());
			levelEntry.setBackground(Color.YELLOW);
			levelList.setToggledLevelEntry(levelEntry);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
