package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import main.KabasujiMain;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.CardLayout;

public class AddPieceOverlay extends JPanel {

	private JFrame frame;

	/**
	 * Create the frame.
	 */
	public AddPieceOverlay(JFrame frame) {
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblSelectAPiece = new JLabel("Select a Piece...");
		lblSelectAPiece.setFont(new Font("Tahoma", Font.PLAIN, 32));
		add(lblSelectAPiece, BorderLayout.NORTH);

	}

}
