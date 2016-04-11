package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controllers.CreateNewLevelController;
import controllers.GoBackOnePanelController;
import controllers.MoveToBuilderLevelListController;
import main.KabasujiMain;

import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LevelList extends JPanel {

	private KabasujiFrame frame;

	/**
	 * Create the frame.
	 */
	public LevelList(KabasujiFrame frame) {
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(0, 0, 120, 45);
		add(btnBack);
		btnBack.addActionListener(new GoBackOnePanelController(frame));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(150, 100, 500, 350);
		add(panel);
		panel.setLayout(null);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(483, 0, 17, 400);
		panel.add(scrollBar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 10, 463, 50);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblLevel_1 = new JLabel("Level 01");
		lblLevel_1.setBounds(10, 0, 232, 50);
		panel_1.add(lblLevel_1);
		lblLevel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel lblLightning = new JLabel("Lightning");
		lblLightning.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLightning.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblLightning.setBounds(221, 0, 232, 50);
		panel_1.add(lblLightning);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 80, 463, 50);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblLevel_2 = new JLabel("Level 02");
		lblLevel_2.setLocation(10, 0);
		lblLevel_2.setSize(232, 50);
		panel_2.add(lblLevel_2);
		lblLevel_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel lblPuzzle = new JLabel("Puzzle");
		lblPuzzle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPuzzle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPuzzle.setBounds(221, 0, 232, 50);
		panel_2.add(lblPuzzle);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(10, 150, 463, 50);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel label = new JLabel("Level 03");
		label.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label.setBounds(10, 0, 232, 50);
		panel_3.add(label);
		
		JLabel lblRelease = new JLabel("Release");
		lblRelease.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRelease.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblRelease.setBounds(221, 0, 232, 50);
		panel_3.add(lblRelease);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(10, 220, 463, 50);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblLevel = new JLabel("Level 04");
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblLevel.setBounds(10, 0, 232, 50);
		panel_4.add(lblLevel);
		
		JLabel label_3 = new JLabel("Lightning");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_3.setBounds(221, 0, 232, 50);
		panel_4.add(label_3);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(10, 290, 463, 50);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblLevel_3 = new JLabel("Level 05");
		lblLevel_3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblLevel_3.setBounds(10, 0, 232, 50);
		panel_5.add(lblLevel_3);
		
		JLabel lblPuzzle_1 = new JLabel("Puzzle");
		lblPuzzle_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPuzzle_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPuzzle_1.setBounds(221, 0, 232, 50);
		panel_5.add(lblPuzzle_1);
		
		JButton btnNew = new JButton("New");
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNew.setBounds(504, 480, 120, 45);
		add(btnNew);
		btnNew.addActionListener(new CreateNewLevelController(frame, this));
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEdit.setBounds(160, 480, 120, 45);
		add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(332, 480, 120, 45);
		add(btnDelete);
	}
}
