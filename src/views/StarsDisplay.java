package views;

import javax.swing.JPanel;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Widget that displays 3 star icons that can be either empty or filled.
 * @author bhuchley
 */
public class StarsDisplay extends JPanel {
	
	/** The first star icon */
	JLabel star1;
	/** The second star icon */
	JLabel star2;
	/** The third star icon */
	JLabel star3;

	/**
	 * Create the widget, with 2 stars filled and 1 empty (only used for testing).
	 */
	public StarsDisplay() {
		this(2);
	}
	
	/** 
	 * Create the widget with the specified number of stars filled.
	 * @param numStarsFilled the number of stars that should start out filled
	 */
	public StarsDisplay(int numStarsFilled) {
		setLayout(null);
		
		star1 = new JLabel("Star1");
		star1.setBounds(30, 0, 28, 28);
		add(star1);
		
		star2 = new JLabel("Star2");
		star2.setBounds(79, 0, 28, 28);
		add(star2);
		
		star3 = new JLabel("Star3");
		star3.setBounds(128, 0, 28, 28);
		add(star3);
		
		setNumStarsFilled(numStarsFilled);
	}
	
	/**
	 * Redraws the widget with the specified number of stars filled.
	 * @param numStarsFilled number of stars to fill
	 */
	public void setNumStarsFilled(int numStarsFilled) {
		if (numStarsFilled > 0) {
			setFilledStarIcon(star1);
		} else {
			setEmptyStarIcon(star1);
		}
		if (numStarsFilled > 1) {
			setFilledStarIcon(star2);
		} else {
			setEmptyStarIcon(star2);
		}
		if (numStarsFilled > 2) {
			setFilledStarIcon(star3);
		} else {
			setEmptyStarIcon(star3);
		}
	}
	
	/**
	 * Sets the icon of the specified JLabel to a filled star.
	 * Works on any JLabel, although it will look strange if its dimensions aren't the same as the star's.
	 * @param star the label to give a star icon
	 */
	public static void setFilledStarIcon(JLabel star) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("Resources/3dyellowstar.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image scaledImage = img.getScaledInstance(star.getWidth(), star.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(scaledImage);
		star.setIcon(icon);
	}
	
	/**
	 * Sets the icon of the specified JLabel to an empty star.
	 * Works on any JLabel, although it will look strange if its dimensions aren't the same as the star's.
	 * @param star the label to give a star icon
	 */
	public static void setEmptyStarIcon(JLabel star) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("Resources/3demptystar.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image scaledImage = img.getScaledInstance(star.getWidth(), star.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(scaledImage);
		star.setIcon(icon);
	}

}
