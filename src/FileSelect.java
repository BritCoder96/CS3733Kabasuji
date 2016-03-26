import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class FileSelect extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileSelect frame = new FileSelect();
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
	public FileSelect() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(0, 0, 62, 23);
		contentPane.add(btnBack);
		
		JButton btnOptions = new JButton("Options");
		btnOptions.setBounds(65, 0, 69, 23);
		contentPane.add(btnOptions);
		
		JLabel lblSelectFile = new JLabel("Select File");
		lblSelectFile.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblSelectFile.setBounds(160, 22, 114, 39);
		contentPane.add(lblSelectFile);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		panel.setBounds(38, 72, 367, 44);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFile = new JLabel("File 1");
		lblFile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFile.setBounds(10, 11, 56, 22);
		panel.add(lblFile);
		
		JLabel lblLevel = new JLabel("Level 7/15");
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLevel.setBounds(92, 11, 77, 22);
		panel.add(lblLevel);
		
		JLabel label = new JLabel("8/45");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(271, 11, 37, 22);
		panel.add(label);
		
		JLabel lblStar = new JLabel("Star");
		lblStar.setBounds(311, 11, 22, 22);
		StarsDisplay.setFilledStarIcon(lblStar);
		panel.add(lblStar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(38, 123, 367, 44);
		contentPane.add(panel_1);
		
		JLabel lblFile_1 = new JLabel("File 2");
		lblFile_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFile_1.setBounds(10, 11, 56, 22);
		panel_1.add(lblFile_1);
		
		JLabel lblLevel_1 = new JLabel("Level 3/15");
		lblLevel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLevel_1.setBounds(92, 11, 77, 22);
		panel_1.add(lblLevel_1);
		
		JLabel label_3 = new JLabel("9/45");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(271, 11, 37, 22);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("Star");
		label_4.setBounds(311, 11, 22, 22);
		StarsDisplay.setFilledStarIcon(label_4);
		panel_1.add(label_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(38, 172, 367, 44);
		contentPane.add(panel_2);
		
		JLabel lblNewGame = new JLabel("New Game");
		lblNewGame.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewGame.setBounds(10, 11, 77, 22);
		panel_2.add(lblNewGame);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setBounds(85, 227, 89, 23);
		contentPane.add(btnPlay);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.setBounds(259, 227, 89, 23);
		contentPane.add(btnNewButton);
	}

}
