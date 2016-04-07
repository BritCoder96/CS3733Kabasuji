import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ReleaseEditor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReleaseEditor frame = new ReleaseEditor();
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
	public ReleaseEditor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Title.windowSize);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		contentPane.add(gameboard);
		
		JPanel bullpen = new JPanel();
		bullpen.setBorder(new LineBorder(new Color(0, 0, 0)));
		bullpen.setBounds(412, 14, 350, 455);
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
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(60, 551, 120, 45);
		contentPane.add(btnDelete);
		
		JButton btnDraw = new JButton("Draw");
		btnDraw.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDraw.setBounds(240, 551, 120, 45);
		contentPane.add(btnDraw);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSave.setBounds(420, 551, 120, 45);
		contentPane.add(btnSave);
		
		JButton btnPublish = new JButton("Publish");
		btnPublish.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPublish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPublish.setBounds(600, 551, 120, 45);
		contentPane.add(btnPublish);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(10, 10, 120, 45);
		contentPane.add(btnBack);
		
		JButton btnOptions = new JButton("Options");
		btnOptions.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnOptions.setBounds(162, 11, 120, 45);
		contentPane.add(btnOptions);
		
		
		JButton btnAddPiece = new JButton("Add Piece");
		btnAddPiece.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddPiece.setBounds(224, 435, 136, 46);
		contentPane.add(btnAddPiece);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Red", "Green", "Yellow"}));
		comboBox.setBounds(60, 435, 120, 22);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
		comboBox_1.setBounds(60, 460, 120, 22);
		contentPane.add(comboBox_1);
		
	}
}
