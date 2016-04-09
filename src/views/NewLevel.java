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
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(0, 0, 120, 45);
		add(btnNewButton);
		
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
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Puzzle");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(12, 13, 153, 47);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Lightning");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_3.setBounds(217, 13, 153, 47);
		panel.add(btnNewButton_3);
		
		JButton btnRelease = new JButton("Release");
		btnRelease.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRelease.setBounds(417, 13, 153, 47);
		panel.add(btnRelease);
		
		JLabel lblMoveLimit = new JLabel("Move Limit:");
		lblMoveLimit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMoveLimit.setBounds(170, 112, 119, 32);
		panel.add(lblMoveLimit);
		
		textField = new JTextField();
		textField.setText("7");
		textField.setBounds(340, 117, 116, 22);
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
		
		JButton btnGo = new JButton("Go!");
		btnGo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGo.setBounds(405, 451, 118, 45);
		add(btnGo);
	}
}
