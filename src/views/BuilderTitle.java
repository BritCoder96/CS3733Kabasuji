package views;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.MoveToBuilderLevelListController;
import main.KabasujiMain;
import models.Piece;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The splash screen for the builder.
 * @author ejcerini
 */
public class BuilderTitle extends JPanel {

	/** The frame that the panel is shown in. */
	private KabasujiFrame frame;
	
	/**
	 * Create the panel. It's just a bunch of labels and a play button.
	 * @param frame the frame to show the screen in
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
		
		JLabel label = new JLabel("Euphorbus");
		label.setFont(new Font("Tahoma", Font.PLAIN, 30));
		label.setBounds(321, 433, 155, 39);
		add(label);
		
		JLabel label_1 = new JLabel("Bryan Benson");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_1.setBounds(337, 474, 123, 27);
		add(label_1);
		
		JLabel label_2 = new JLabel("Jon Berry");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_2.setBounds(357, 501, 83, 27);
		add(label_2);
		
		JLabel label_3 = new JLabel("Erik Cerini");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_3.setBounds(353, 528, 90, 27);
		add(label_3);
		
		JLabel label_4 = new JLabel("Ben Huchley");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_4.setBounds(343, 555, 111, 27);
		add(label_4);
		
		JLabel label_5 = new JLabel("Steven Huynh");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_5.setBounds(335, 582, 127, 27);
		add(label_5);

		// Doing this here so that it's only done once on startup
		Piece.initializeValidPieces();
	}
}
