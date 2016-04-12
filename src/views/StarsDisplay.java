package views;

import javax.swing.JPanel;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class StarsDisplay extends JPanel {
	
	JLabel star1;
	JLabel star2;
	JLabel star3;

	/**
	 * Create the panel.
	 */
	public StarsDisplay() {
		this(2);
	}
	
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
