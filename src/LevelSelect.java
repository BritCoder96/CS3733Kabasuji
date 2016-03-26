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

public class LevelSelect extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LevelSelect frame = new LevelSelect();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LevelSelect() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(0, 0, 55, 23);
		contentPane.add(btnBack);
		
		JButton btnPause = new JButton("Options");
		btnPause.setBounds(65, 0, 69, 23);
		contentPane.add(btnPause);
		
		JPanel panel = new JPanel();
		panel.setBounds(103, 57, 186, 125);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCurrentLevel = new JLabel("Current Level");
		lblCurrentLevel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCurrentLevel.setBounds(41, 11, 102, 28);
		panel.add(lblCurrentLevel);
		
		StarsDisplay starsDisplay = new StarsDisplay();
		starsDisplay.setBounds(0, 46, 186, 40);
		panel.add(starsDisplay);
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblScore.setBounds(75, 97, 35, 14);
		panel.add(lblScore);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setBounds(152, 205, 89, 23);
		contentPane.add(btnPlay);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setBounds(10, 108, 89, 23);
		contentPane.add(btnPrevious);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(299, 108, 89, 23);
		contentPane.add(btnNext);
	}
}
