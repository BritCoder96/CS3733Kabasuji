package views;

import java.awt.EventQueue;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.KabasujiMain;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JSpinner;

public class Title extends JPanel {

	KabasujiFrame frame;

	/**
	 * Create the application.
	 */
	public Title(KabasujiFrame frame) {
		this.frame = frame;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(KabasujiMain.windowSize);
		setLayout(null);
		
		JLabel lblKabasuji = new JLabel("Kabasuji");
		lblKabasuji.setFont(new Font("Tahoma", Font.PLAIN, 44));
		lblKabasuji.setBounds(307, 58, 169, 63);
		add(lblKabasuji);
		
		JLabel lblATileLaying = new JLabel("A tile laying game");
		lblATileLaying.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblATileLaying.setBounds(265, 163, 253, 39);
		add(lblATileLaying);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPlay.setBounds(332, 244, 120, 45);
		add(btnPlay);
		btnPlay.addActionListener(new controllers.MoveToFileSelectController(frame, this));
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.setBounds(332, 418, 120, 45);
		add(btnExit);
	}
}
