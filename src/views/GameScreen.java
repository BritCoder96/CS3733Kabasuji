package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.KabasujiMain;

import javax.swing.border.LineBorder;
import java.awt.Font;

/**
 * The screen that the game is shown in. At the moment basically just a mockup
 * @author bhuchley
 */
public class GameScreen extends JPanel {

	/** The frame that the panel is shown in. */
	private KabasujiFrame frame;

	/**
	 * Load the initial state of the level and set the game board and bullpen to it.
	 * Presumably it should take a level as a parameter eventually but that doesn't work yet
	 * @param frame the frame to show the screen in
	 */
	public GameScreen(KabasujiFrame frame) {
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JButton btnNewButton = new JButton("Quit");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new controllers.GoBackOnePanelController(frame));
		btnNewButton.setBounds(0, 0, 120, 45);
		add(btnNewButton);
		
		JPanel gameboard = new JPanel();
		gameboard.setBounds(10, 88, 356, 356);
		gameboard.setLayout(new GridLayout(6, 6, 0, 0));
		// TODO hack add 36 JLabels with alternating backgrounds
		Color lighterGray = new Color(230, 230, 230);
		Color darkerGray = new Color(200, 200, 200);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				JLabel square = new JLabel();
				Color squareBackground = (((i+j)%2)==0) ? lighterGray : darkerGray;
				square.setOpaque(true);
				square.setBackground(squareBackground);
				gameboard.add(square);
			}
		}
		add(gameboard);
		
		JPanel bullpen = new JPanel();
		bullpen.setBorder(new LineBorder(new Color(0, 0, 0)));
		bullpen.setBounds(388, 11, 386, 481);
		add(bullpen);
		bullpen.setLayout(null);
		
		int tileSize = gameboard.getHeight() / 6;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < (3-i); j++) {
				JLabel lblBox = new JLabel();
				lblBox.setBackground(new Color(128, 128, 128));
				lblBox.setBounds(10 + (tileSize * i), 10 + (tileSize * j), tileSize, tileSize);
				lblBox.setBorder(new LineBorder(Color.BLACK));
				lblBox.setOpaque(true);
				bullpen.add(lblBox);
			}
		}
		
		JLabel lblNewLabel = new JLabel("Score: 6");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(81, 552, 88, 29);
		add(lblNewLabel);
		
		StarsDisplay starsDisplay = new StarsDisplay();
		starsDisplay.setBounds(250, 552, 186, 40);
		add(starsDisplay);
		
		JLabel lblNewLabel_1 = new JLabel("Moves/Time Left");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(517, 552, 186, 29);
		add(lblNewLabel_1);
	}
	
	public KabasujiFrame getFrame(){
		return frame;
	}
}
