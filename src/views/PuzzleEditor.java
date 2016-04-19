package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controllers.GoBackOnePanelController;
import main.KabasujiMain;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The editor screen for puzzle levels.
 * @author ejcerini
 */
public class PuzzleEditor extends JPanel {

	/** The frame that the panel is shown in. */
	private KabasujiFrame frame;

	/**
	 * Create the editor screen, with a rectangular level and no pieces.
	 * @param frame the frame to show the screen in
	 */
	public PuzzleEditor(KabasujiFrame frame) {
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JPanel gameboard = new JPanel();
		gameboard.setBounds(60, 71, 325, 325);
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
		
		JPanel bullpen = new JPanel();
		bullpen.setBorder(new LineBorder(new Color(0, 0, 0)));
		bullpen.setBounds(412, 14, 350, 455);
		add(bullpen);
		bullpen.setLayout(null);
		
		int tileSize = gameboard.getHeight() / 6;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < (3-i); j++) {
				JLabel lblBox = new JLabel();
				lblBox.setBackground(new Color(128, 128, 128));
				lblBox.setBounds(10 + (tileSize * i), 10 + (tileSize * j), tileSize, tileSize);
				lblBox.setBorder(new LineBorder(Color.BLACK));
				lblBox.setOpaque(true);
				bullpen.add(lblBox);
			}
		}
		
		JLabel label = new JLabel("7");
		label.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label.setBounds(24, 445, 16, 24);
		add(label);
		
		JButton btnIncrease = new JButton("");
		btnIncrease.setIcon(new ImageIcon(PuzzleEditor.class.getResource("/javax/swing/plaf/metal/icons/sortUp.png")));
		btnIncrease.setBounds(20, 420, 24, 24);
		add(btnIncrease);
		
		JButton btnDecrease = new JButton("");
		btnDecrease.setIcon(new ImageIcon(PuzzleEditor.class.getResource("/javax/swing/plaf/metal/icons/sortDown.png")));
		btnDecrease.setBounds(20, 472, 24, 24);
		add(btnDecrease);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(30, 551, 120, 45);
		add(btnDelete);
		
		JButton btnDraw = new JButton("Draw");
		btnDraw.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDraw.setBounds(180, 551, 120, 45);
		add(btnDraw);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSave.setBounds(330, 551, 120, 45);
		add(btnSave);
		
		JButton btnPublish = new JButton("Undo");
		btnPublish.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPublish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPublish.setBounds(480, 551, 120, 45);
		add(btnPublish);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(10, 10, 120, 45);
		add(btnBack);
		btnBack.addActionListener(new GoBackOnePanelController(frame));
		
		JButton btnSolveForMe = new JButton("Solve for Me");
		btnSolveForMe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSolveForMe.setBounds(71, 436, 159, 46);
		add(btnSolveForMe);
		
		JButton btnAddPiece = new JButton("Add Piece");
		btnAddPiece.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddPiece.setBounds(257, 436, 136, 46);
		add(btnAddPiece);
		
		JButton btnRedo = new JButton("Redo");
		btnRedo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRedo.setBounds(630, 551, 120, 45);
		add(btnRedo);
	}

}
