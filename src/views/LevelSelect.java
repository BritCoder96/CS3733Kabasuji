package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import main.KabasujiMain;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LevelSelect extends JPanel {
	
	JFrame frame;

	/**
	 * Create the frame.
	 */
	public LevelSelect(JFrame frame) {
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(0, 0, 120, 45);
		add(btnBack);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(222, 141, 338, 268);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblCurrentLevel = new JLabel("Current Level");
		lblCurrentLevel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCurrentLevel.setBounds(97, 43, 144, 28);
		panel.add(lblCurrentLevel);
		
		StarsDisplay starsDisplay = new StarsDisplay();
		starsDisplay.setBounds(76, 114, 186, 40);
		panel.add(starsDisplay);
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblScore.setBounds(141, 197, 55, 28);
		panel.add(lblScore);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPlay.setBounds(332, 467, 120, 45);
		add(btnPlay);
		btnPlay.addActionListener(new controllers.MoveToLevelController(frame, this));
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPrevious.setBounds(51, 253, 120, 45);
		add(btnPrevious);
		
		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNext.setBounds(611, 253, 120, 45);
		add(btnNext);
	}
}
