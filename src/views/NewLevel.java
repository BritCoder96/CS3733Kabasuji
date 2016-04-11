package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controllers.GoBackOnePanelController;
import main.KabasujiMain;

import java.awt.Color;

public class NewLevel extends JPanel {

	private KabasujiFrame frame;
	private JTextField txtInsertNameHere;
	private JTextField textField;
	private JTextField txtRows;
	private JTextField txtCols;

	/**
	 * Create the frame.
	 */
	public NewLevel(KabasujiFrame frame) {
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBack.setBounds(0, 0, 120, 45);
		add(btnBack);
		btnBack.addActionListener(new GoBackOnePanelController(frame));
		
		JLabel lblLevelName = new JLabel("Level Name:");
		lblLevelName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLevelName.setBounds(207, 85, 120, 37);
		add(lblLevelName);
		
		txtInsertNameHere = new JTextField();
		txtInsertNameHere.setText("Insert Name Here");
		txtInsertNameHere.setBounds(339, 95, 156, 22);
		add(txtInsertNameHere);
		txtInsertNameHere.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBounds(88, 135, 582, 213);
		panel.setLayout(null);
		add(panel);
		
		JButton btnPuzzle = new JButton("Puzzle");
		btnPuzzle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPuzzle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPuzzle.setBounds(30, 44, 153, 47);
		panel.add(btnPuzzle);
		
		JButton btnLightning = new JButton("Lightning");
		btnLightning.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLightning.setBounds(213, 44, 153, 47);
		panel.add(btnLightning);
		
		JButton btnRelease = new JButton("Release");
		btnRelease.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRelease.setBounds(396, 44, 153, 47);
		panel.add(btnRelease);
		
		JLabel lblMoveLimit = new JLabel("Move Limit:");
		lblMoveLimit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMoveLimit.setBounds(159, 135, 119, 32);
		panel.add(lblMoveLimit);
		
		textField = new JTextField();
		textField.setText("7");
		textField.setBounds(290, 140, 116, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblGridSize = new JLabel("Grid Size: ");
		lblGridSize.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGridSize.setBounds(191, 364, 120, 30);
		add(lblGridSize);
		
		txtRows = new JTextField();
		txtRows.setText("Rows");
		txtRows.setBounds(339, 368, 44, 22);
		add(txtRows);
		txtRows.setColumns(10);
		
		JLabel lblRows = new JLabel("Rows");
		lblRows.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRows.setBounds(296, 369, 52, 20);
		add(lblRows);
		
		JLabel lblX = new JLabel("x");
		lblX.setBounds(395, 371, 15, 16);
		add(lblX);
		
		JLabel lblColumns = new JLabel("Columns");
		lblColumns.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblColumns.setBounds(414, 368, 62, 22);
		add(lblColumns);
		
		txtCols = new JTextField();
		txtCols.setText("Cols");
		txtCols.setBounds(479, 368, 44, 22);
		add(txtCols);
		txtCols.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancel.setBounds(230, 451, 118, 45);
		add(btnCancel);
		btnCancel.addActionListener(new GoBackOnePanelController(frame));
		
		JButton btnGo = new JButton("Go!");
		btnGo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGo.setBounds(405, 451, 118, 45);
		add(btnGo);
	}
}
