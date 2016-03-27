import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class LightningEditor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LightningEditor frame = new LightningEditor();
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
	public LightningEditor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel gameboard = new JPanel();
		gameboard.setBounds(195, 54, 175, 175);
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
		
		JLabel label = new JLabel("1:00");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(48, 71, 33, 14);
		contentPane.add(label);
		
		JButton btnIncrease = new JButton("");
		btnIncrease.setIcon(new ImageIcon(LightningEditor.class.getResource("/javax/swing/plaf/metal/icons/sortUp.png")));
		btnIncrease.setBounds(52, 44, 24, 24);
		contentPane.add(btnIncrease);
		
		JButton btnDecrease = new JButton("");
		btnDecrease.setIcon(new ImageIcon(LightningEditor.class.getResource("/javax/swing/plaf/metal/icons/sortDown.png")));
		btnDecrease.setBounds(53, 87, 24, 24);
		contentPane.add(btnDecrease);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(21, 125, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnDraw = new JButton("Draw");
		btnDraw.setBounds(21, 159, 89, 23);
		contentPane.add(btnDraw);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(21, 193, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnPublish = new JButton("Publish");
		btnPublish.setBounds(21, 227, 89, 23);
		contentPane.add(btnPublish);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 10, 65, 23);
		contentPane.add(btnBack);
		
		JButton btnOptions = new JButton("Options");
		btnOptions.setBounds(80, 10, 78, 23);
		contentPane.add(btnOptions);
	}

}
