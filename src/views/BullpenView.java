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
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
	
	/**
	 * Clear the bullpen view.
	 */
	public void clearPieces() {
		for (PieceView pv : pieceViews) {
			scrollingPanel.remove(pv);
		}
		pieceViews.clear();
		pieces.clear();
		// Set the height of the scrolling panel to 0
		scrollingPanel.setBounds(scrollingPanel.getX(), scrollingPanel.getY(), scrollingPanel.getWidth(), 0);
		scrollingPanel.setPreferredSize(new Dimension(scrollingPanel.getWidth(), 0));
	}
	
	/**
	 * Since pieces are stored in a vertical line, this gets the piece at the given y coordinate, if it exists.
	 * @param y the y coordinate to test
	 * @return the piece view that covers y, if any. null if none
	 */
	public PieceView getPieceAtY(int y) {
		for (PieceView pv : pieceViews) {
			int top = pv.getY();
			int bottom = top + pv.getHeight();
			if ((top <= y) && (bottom >= y)) {
				return pv;
			}
		}
		return null;
	}
	
	// For some reason addMouseListener / MouseMotionListener doesn't work without this, probably because
	// the scroll pane is eating the mouse events.
	// But this moves the mouse listener to the correct level.
	
	// Add the mouse listener to the scrolling panel, as that one is intended to use the scrolled position
	public void addMouseListener(MouseListener listener) {
		scrollingPanel.addMouseListener(listener);
	}
	// Add the mouse motion listener to the scroll pane, as that one is intended to use the unscrolled position
	public void addMouseMotionListener(MouseMotionListener listener) {
		scrollingPanel.addMouseMotionListener(listener);
	}
	
	/**
	 * Gets the amount by which the scrolling panel is offset from the top.
	 * @return how far the scrolling panel is offset
	 */
	public int getScrollOffset() {
		return scrollingPanel.getY();
	}
}
