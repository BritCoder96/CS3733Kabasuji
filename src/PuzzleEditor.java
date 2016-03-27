import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PuzzleEditor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PuzzleEditor frame = new PuzzleEditor();
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
	public PuzzleEditor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel gameboard = new JPanel();
		gameboard.setBounds(44, 44, 175, 175);
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
		contentPane.add(gameboard);
		
		JPanel bullpen = new JPanel();
		bullpen.setBorder(new LineBorder(new Color(0, 0, 0)));
		bullpen.setBounds(240, 38, 184, 184);
		contentPane.add(bullpen);
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
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(18, 71, 9, 14);
		contentPane.add(label);
		
		JButton btnIncrease = new JButton("");
		btnIncrease.setIcon(new ImageIcon(LightningEditor.class.getResource("/javax/swing/plaf/metal/icons/sortUp.png")));
		btnIncrease.setBounds(10, 44, 24, 24);
		contentPane.add(btnIncrease);
		
		JButton btnDecrease = new JButton("");
		btnDecrease.setIcon(new ImageIcon(LightningEditor.class.getResource("/javax/swing/plaf/metal/icons/sortDown.png")));
		btnDecrease.setBounds(11, 87, 24, 24);
		contentPane.add(btnDecrease);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(18, 230, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnDraw = new JButton("Draw");
		btnDraw.setBounds(114, 230, 89, 23);
		contentPane.add(btnDraw);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(213, 230, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnPublish = new JButton("Publish");
		btnPublish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPublish.setBounds(314, 230, 89, 23);
		contentPane.add(btnPublish);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 10, 65, 23);
		contentPane.add(btnBack);
		
		JButton btnOptions = new JButton("Options");
		btnOptions.setBounds(80, 10, 78, 23);
		contentPane.add(btnOptions);
		
		JButton btnSolveForMe = new JButton("Solve for Me");
		btnSolveForMe.setBounds(168, 10, 105, 23);
		contentPane.add(btnSolveForMe);
		
		JButton btnAddPiece = new JButton("Add Piece");
		btnAddPiece.setBounds(283, 10, 98, 23);
		contentPane.add(btnAddPiece);
	}

}
