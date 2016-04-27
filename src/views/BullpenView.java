package views;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import models.Piece;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;

/**
 * A view that shows a list of pieces in a bullpen.
 * @author bhuchley
 *
 */
public class BullpenView extends JPanel {
	
	/** The distance that the piece views appear from the edges and from each other */
	public static final int PIECE_SPACING = 15;
	
	/** The edge length that squares of pieces in the bullpen should have */
	int squareSize;
	/** The pieces in the bullpen */
	ArrayList<Piece> pieces;
	/** The views of the pieces in the bullpen */
	ArrayList<PieceView> pieceViews;
	
	/** The wrapper for the panel that gives it a scroll bar */
	JScrollPane scrollPane;
	/** The panel that has a scroll bar */
	JPanel scrollingPanel;

	/**
	 * Create the view. It starts out empty. It is the caller's responsibility to make sure that 
	 * the bullpen view is wide enough to fit all pieces with the given square size.
	 * @param squareSize the edge size to use for squares in the pieces
	 */
	public BullpenView(int squareSize, Rectangle bounds) {
		this.squareSize = squareSize;
		pieces = new ArrayList<Piece>();
		pieceViews = new ArrayList<PieceView>();
		setBounds(bounds);
		setLayout(null);
		scrollingPanel = new JPanel();
		scrollingPanel.setLayout(null);
		scrollingPanel.setBounds(0, 0, bounds.width, 0);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(scrollingPanel);
		scrollPane.setBounds(new Rectangle(0, 0, bounds.width, bounds.height));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, bounds.width, bounds.height);
		add(scrollPane);
	}
	
	/**
	 * Add a piece to the bullpen. This method assumes that it'll only be used when initializing
	 * the bullpen and that therefore no pieces have been added other than via this method yet.
	 * @param p the piece to add
	 * @return the PieceView that now holds the piece p
	 */
	public PieceView addPiece(Piece p) {
		int priorPieceBottom = 0;
		// If there are existing pieces, place this below the last one.
		// This assumes that the last one in the list is the lowest, which
		// will be true if the pieces have only been added via this method so far.
		if (pieceViews.size() > 0) {
			PieceView priorPieceView = pieceViews.get(pieceViews.size() - 1);
			priorPieceBottom = priorPieceView.getY() + priorPieceView.getHeight();
		}
		PieceView pv = new PieceView(p, squareSize, PIECE_SPACING, priorPieceBottom + PIECE_SPACING);
		pieces.add(p);
		pieceViews.add(pv);
		scrollingPanel.add(pv);
		scrollingPanel.setBounds(scrollingPanel.getX(), scrollingPanel.getY(), scrollingPanel.getWidth(), pv.getY() + pv.getHeight());
		// Apparently for scroll pane to work you also need to set preferred size
		scrollingPanel.setPreferredSize(new Dimension(scrollingPanel.getWidth(), scrollingPanel.getHeight()));
		return pv;
	}
}
