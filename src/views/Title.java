package views;

import models.SaveFile;

import java.awt.EventQueue;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.KabasujiMain;
import models.Piece;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JSpinner;

/**
 * The splash screen for the game.
 * @author bhuchley
 */
public class Title extends JPanel {

	/** The frame that the panel is shown in. */
	KabasujiFrame frame;
	JButton btnPlay;
	

	/**
	 * Create the screen.
	 * @param frame the frame to show the screen in
	 */
	public Title(KabasujiFrame frame) {
		this.frame = frame;
		initialize();
	}

	/**
	 * Initialize the contents of the screen.
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
		
		btnPlay = new JButton("Play");
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPlay.setBounds(332, 244, 120, 45);
		add(btnPlay);
		btnPlay.addActionListener(new controllers.MoveToLevelSelectController(frame, this));
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.setBounds(332, 302, 120, 45);
		add(btnExit);
		
		JLabel lblEuphorbus = new JLabel("Euphorbus");
		lblEuphorbus.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEuphorbus.setBounds(315, 404, 155, 39);
		add(lblEuphorbus);
		
		JLabel lblErikCerini = new JLabel("Erik Cerini");
		lblErikCerini.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblErikCerini.setBounds(347, 499, 90, 27);
		add(lblErikCerini);
		
		JLabel lblBryanBenson = new JLabel("Bryan Benson");
		lblBryanBenson.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBryanBenson.setBounds(331, 445, 123, 27);
		add(lblBryanBenson);
		
		JLabel lblJonathanBerry = new JLabel("Jon Berry");
		lblJonathanBerry.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblJonathanBerry.setBounds(351, 472, 83, 27);
		add(lblJonathanBerry);
		
		JLabel lblBenHuchley = new JLabel("Ben Huchley");
		lblBenHuchley.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBenHuchley.setBounds(337, 526, 111, 27);
		add(lblBenHuchley);
		
		JLabel lblStevenHuynh = new JLabel("Steven Huynh");
		lblStevenHuynh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStevenHuynh.setBounds(329, 553, 127, 27);
		add(lblStevenHuynh);

		// Doing this here so that it's only done once on startup
		Piece.initializeValidPieces();
	}
	
	/**
	 * gets the play btn for testing
	 * @return the play btn
	 */
	public JButton getBtnPlay() {
		return btnPlay;
	}
}
