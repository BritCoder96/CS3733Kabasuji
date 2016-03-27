import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameWinOverlay extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWinOverlay frame = new GameWinOverlay();
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
	public GameWinOverlay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		StarsDisplay starsDisplay = new StarsDisplay();
		starsDisplay.setBounds(49, 66, 186, 40);
		contentPane.add(starsDisplay);
		
		JLabel lblYouDidIt = new JLabel("You Did It!");
		lblYouDidIt.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblYouDidIt.setBounds(84, 21, 115, 27);
		contentPane.add(lblYouDidIt);
		
		JLabel lblScore = new JLabel("Score: 25");
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblScore.setBounds(104, 112, 76, 27);
		contentPane.add(lblScore);
		
		JButton btnLevelSelct = new JButton("Level Select");
		btnLevelSelct.setBounds(35, 177, 109, 23);
		contentPane.add(btnLevelSelct);
		
		JButton btnReplay = new JButton("Replay");
		btnReplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReplay.setBounds(154, 177, 81, 23);
		contentPane.add(btnReplay);
		
		JButton btnNextLevel = new JButton("Next Level");
		btnNextLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNextLevel.setBounds(91, 150, 102, 23);
		contentPane.add(btnNextLevel);
	}
}
