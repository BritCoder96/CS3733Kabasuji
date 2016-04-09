package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;

import main.KabasujiMain;

import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FileSelect extends JPanel {
	
	KabasujiFrame frame;

	/**
	 * Create the frame.
	 */
	public FileSelect(KabasujiFrame frame) {
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(0, 0, 120, 45);
		add(btnBack);
		btnBack.addActionListener(new controllers.GoBackOnePanelController(frame));
		
		JLabel lblSelectFile = new JLabel("Select File");
		lblSelectFile.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblSelectFile.setBounds(314, 97, 155, 39);
		add(lblSelectFile);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.YELLOW);
		panel.setBounds(142, 168, 500, 60);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblFile = new JLabel("File 1");
		lblFile.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblFile.setBounds(33, 20, 72, 22);
		panel.add(lblFile);
		
		JLabel lblLevel = new JLabel("Level 7/15");
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblLevel.setBounds(170, 20, 111, 22);
		panel.add(lblLevel);
		
		JLabel label = new JLabel("8/45");
		label.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label.setBounds(365, 20, 48, 22);
		panel.add(label);
		
		JLabel lblStar = new JLabel("Star");
		lblStar.setBounds(423, 20, 22, 22);
		StarsDisplay.setFilledStarIcon(lblStar);
		panel.add(lblStar);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setLayout(null);
		panel_2.setBounds(142, 316, 500, 60);
		add(panel_2);
		
		JLabel lblNewGame = new JLabel("New Game");
		lblNewGame.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewGame.setBounds(34, 15, 117, 22);
		panel_2.add(lblNewGame);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPlay.setBounds(215, 411, 120, 45);
		add(btnPlay);
		btnPlay.addActionListener(new controllers.MoveToLevelSelectController(frame, this));
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(457, 411, 120, 45);
		add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(142, 239, 500, 60);
		add(panel_1);
		
		JLabel lblFile_1 = new JLabel("File 2");
		lblFile_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblFile_1.setBounds(34, 20, 72, 22);
		panel_1.add(lblFile_1);
		
		JLabel lblLevel_1 = new JLabel("Level 3/15");
		lblLevel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblLevel_1.setBounds(170, 20, 111, 22);
		panel_1.add(lblLevel_1);
		
		JLabel label_3 = new JLabel("9/45");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_3.setBounds(365, 20, 48, 22);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("Star");
		label_4.setBounds(423, 20, 22, 22);
		StarsDisplay.setFilledStarIcon(label_4);
		panel_1.add(label_4);
	}
}
