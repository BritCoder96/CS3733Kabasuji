package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.GoBackOnePanelController;
import main.KabasujiMain;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
 * The screen that allows the user to edit a lightning level.
 * @author ejcerini
 */
public class LightningEditor extends JPanel {

	/** The frame that the panel is shown in. */
	private KabasujiFrame frame;

	/**
	 * Create the frame with an rectangular lightning level of the specified size and time.
	 * @param frame the frame to show the screen in
	 */
	public LightningEditor(KabasujiFrame frame) {
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JPanel gameboard = new JPanel();
		gameboard.setBounds(283, 94, 430, 430);
		gameboard.setLayout(new GridLayout(6, 6, 0, 0));
		// TODO hack add 36 JLabels with alternating backgrounds
		Color lighterGray = new Color(230, 230, 230);
		Color darkerGray = new Color(200, 200, 200);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				JLabel square = new JLabel();
				Color squareBackground = (((i+j)%2)==0) ? lighterGray : darkerGray;
				square.setOpaque(true);
				square.setBackground(squareBackground);
				gameboard.add(square);
			}
		}
		add(gameboard);
		
		JLabel label = new JLabel("1:00");
		label.setFont(new Font("Tahoma", Font.PLAIN, 28));
		label.setBounds(99, 141, 63, 34);
		add(label);
		
		JButton btnIncrease = new JButton("");
		btnIncrease.setIcon(new ImageIcon(LightningEditor.class.getResource("/javax/swing/plaf/metal/icons/sortUp.png")));
		btnIncrease.setBounds(117, 117, 24, 24);
		add(btnIncrease);
		
		JButton btnDecrease = new JButton("");
		btnDecrease.setIcon(new ImageIcon(LightningEditor.class.getResource("/javax/swing/plaf/metal/icons/sortDown.png")));
		btnDecrease.setBounds(117, 174, 24, 24);
		add(btnDecrease);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(69, 248, 120, 45);
		add(btnDelete);
		
		JButton btnDraw = new JButton("Draw");
		btnDraw.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDraw.setBounds(69, 321, 120, 45);
		add(btnDraw);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSave.setBounds(69, 391, 120, 45);
		add(btnSave);
		
		JButton btnPublish = new JButton("Undo");
		btnPublish.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPublish.setBounds(69, 460, 120, 45);
		add(btnPublish);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(10, 10, 120, 45);
		add(btnBack);
		btnBack.addActionListener(new GoBackOnePanelController(frame));
		
		JButton btnRedo = new JButton("Redo");
		btnRedo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRedo.setBounds(69, 516, 120, 45);
		add(btnRedo);
	}
}
