package controllers;

import java.awt.Color;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JButton;

import views.LevelList;

public class ToggleLevelEntryController implements MouseListener {
	LevelList levelList;
	JPanel levelEntry;
	
	public ToggleLevelEntryController(LevelList levelList, JPanel levelEntry) {
		this.levelList = levelList;
		this.levelEntry = levelEntry;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JPanel toggled = levelList.getToggledLevelEntry();
		if (toggled == null) {
			levelList.getBtnEdit().setEnabled(true);
			levelList.getBtnDelete().setEnabled(true);
			levelEntry.setBackground(Color.YELLOW);
			levelList.setToggledLevelEntry(levelEntry);
		}
		else if (toggled != levelEntry) {
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
