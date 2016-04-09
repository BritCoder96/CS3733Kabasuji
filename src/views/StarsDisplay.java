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

	/**
	 * Create the panel.
	 */
	public StarsDisplay() {
		setLayout(null);
		
		JLabel star1 = new JLabel("Star1");
		star1.setBounds(30, 0, 28, 28);
		setFilledStarIcon(star1);
		add(star1);
		
		JLabel star2 = new JLabel("Star2");
		star2.setBounds(79, 0, 28, 28);
		setFilledStarIcon(star2);
		add(star2);
		
		JLabel star3 = new JLabel("Star3");
		star3.setBounds(128, 0, 28, 28);
		setEmptyStarIcon(star3);
		add(star3);

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
