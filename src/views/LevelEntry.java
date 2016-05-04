package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controllers.ToggleLevelEntryController;
import models.Level;

/**
 * Representation of a level's information on a LevelList.
 * 
 * @author sthuynh
 */
public class LevelEntry extends JPanel {
	Level level;
	
	public LevelEntry(Level level) {
		this.level = level;
		
		int number = level.getLevelNumber() + 1;
		
		String type;
		switch (level.getLvlType()) {
		case PUZZLE:
			type = "Puzzle";
			break;
		case LIGHTNING:
			type = "Lightning";
			break;
		case RELEASE:
			type = "Release";
			break;
		default:
			// TODO: probably should throw an exception instead
			type = "";
		}
		
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(0, 0, 463, 50);
		setLayout(null);
		
		JLabel nameLabel = new JLabel("Level " + number);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		nameLabel.setBounds(10, 0, 232, 50);
		add(nameLabel);
		
		JLabel typeLabel = new JLabel(type);
		typeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		typeLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		typeLabel.setBounds(221, 0, 232, 50);
		add(typeLabel);
	}
	
	/**
	 * Get the level associated with the level entry.
	 * @return The level associated with the level entry.
	 */
	public Level getLevel() {
		return level;
	}
}
