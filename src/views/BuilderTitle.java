package views;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.MoveToBuilderLevelListController;
import main.KabasujiMain;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuilderTitle extends JPanel {

	private KabasujiFrame frame;
	
	/**
	 * Create the panel.
	 */
	public BuilderTitle(KabasujiFrame frame) {
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JLabel lblKabuildsuji = new JLabel("Ka-Build-Suji");
		lblKabuildsuji.setFont(new Font("Tahoma", Font.PLAIN, 44));
		lblKabuildsuji.setBounds(267, 109, 262, 50);
		add(lblKabuildsuji);
		
		JLabel lblALevelEditor = new JLabel("A level editor for Kabasuji");
		lblALevelEditor.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblALevelEditor.setBounds(214, 166, 368, 50);
		add(lblALevelEditor);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEdit.setBounds(338, 305, 120, 45);
		add(btnEdit);
		btnEdit.addActionListener(new MoveToBuilderLevelListController(frame, this));

		
	}
}
