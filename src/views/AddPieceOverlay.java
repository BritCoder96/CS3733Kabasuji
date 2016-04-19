package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import main.KabasujiMain;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.CardLayout;

/**
 * The overlay that appears in the builder when adding another piece to a puzzle or release level.
 * @author ejcerini
 */
public class AddPieceOverlay extends JPanel {

	/** The frame that the view is shown in. */
	private KabasujiFrame frame;

	/**
	 * Create the frame. Right now it just makes a blank window with a header, no pieces appear yet.
	 * @param frame the frame to show the screen in
	 */
	public AddPieceOverlay(KabasujiFrame frame) {
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblSelectAPiece = new JLabel("Select a Piece...");
		lblSelectAPiece.setFont(new Font("Tahoma", Font.PLAIN, 32));
		add(lblSelectAPiece, BorderLayout.NORTH);

	}

}
