package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controllers.AddPieceToListenerController;
import controllers.GoBackOnePanelController;
import main.KabasujiMain;
import models.Piece;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.CardLayout;
import javax.swing.SwingConstants;

/**
 * The overlay that appears in the builder when adding another piece to a puzzle or release level.
 * @author ejcerini
 * @author bhuchley
 */
public class AddPieceOverlay extends JPanel {

	/** The frame that the view is shown in. */
	private KabasujiFrame frame;
	
	/** The listener that the selected piece should be added to */
	private AddPieceListener thingToAddTo;

	/**
	 * Create the frame. Right now it just makes a blank window with a header, no pieces appear yet.
	 * @param frame the frame to show the screen in
	 */
	public AddPieceOverlay(KabasujiFrame frame, AddPieceListener thingToAddTo) {
		this.frame = frame;
		this.thingToAddTo = thingToAddTo;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JLabel lblSelectAPiece = new JLabel("Select a Piece...");
		lblSelectAPiece.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectAPiece.setBounds(5, 25, 790, 39);
		lblSelectAPiece.setFont(new Font("Tahoma", Font.PLAIN, 32));
		add(lblSelectAPiece);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(10, 10, 120, 45);
		add(btnBack);
		btnBack.addActionListener(new GoBackOnePanelController(frame));
		
		BullpenView bpView = new BullpenView(this.getWidth() / 8, new Rectangle(0, 75, 700, 500));
		add(bpView);
		
		for (Piece p : Piece.allValidPieces) {
			PieceView pv = bpView.addPiece(p);
			// add on click listener for the views that will go back to the puzzle editor screen and add that piece
			pv.addMouseListener(new AddPieceToListenerController(frame, thingToAddTo, p));
		}
		bpView.repaint();

	}

}
