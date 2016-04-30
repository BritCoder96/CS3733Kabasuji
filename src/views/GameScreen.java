package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.BullpenGameController;
import controllers.MoveDraggingPieceController;
import main.KabasujiMain;
import models.Level;
import models.Piece;

import javax.swing.border.LineBorder;
import java.awt.Font;

/**
 * The screen that the game is shown in. At the moment basically just a mockup
 * @author bhuchley
 */
public class GameScreen extends JPanel {

	/** The frame that the panel is shown in. */
	private KabasujiFrame frame;
	
	/** The level currently being played */
	Level level;
	/** The original model object for the level being played (that we modify when saving score) */
	Level originalLevel;
	
	/** The PieceView currently being dragged, or null if none */
	PieceView draggingWidget;
	/** The map of pieces on the board to their respective views so that they can be picked up.
	 * Pieces being dragged around or in the bullpen might show up in this, but they're never going to be used. */
	HashMap<Piece, PieceView> viewsForLevelPiecesOnBoard;

	/**
	 * Load the initial state of the level and set the game board and bullpen to it.
	 * Presumably it should take a level as a parameter eventually but that doesn't work yet
	 * @param frame the frame to show the screen in
	 */
	public GameScreen(KabasujiFrame frame, Level l) {
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		// Due to the fact that the level is used to keep track of the pieces on the board,
		// we need to clone the level for this screen.
		// To score it, we need to use originalLevel.
		level = l.deepClone();
		originalLevel = l;
		viewsForLevelPiecesOnBoard = new HashMap<Piece, PieceView>();
		
		// Add listener for the entire screen to catch mouse movement that's not on a subwidget
		// Actually, mouse motion from subwidgets is redirected to this screen anyway, so this
		// listener will handle all mouse motion events
		setEnabled(true);
		this.addMouseMotionListener(new MoveDraggingPieceController(this));
		
		GameBoardView boardView = new GameBoardView(this, level.getBoard());
		boardView.setBounds(10, 88, 356, 356);
		
		// TODO put dragging piece in at start for testing, remove later
		draggingWidget = new PieceView(Piece.allValidPieces[21], boardView.getSquareSize(), 0, 0);
		add(draggingWidget);
		viewsForLevelPiecesOnBoard.put(Piece.allValidPieces[21], draggingWidget);
		
		add(boardView);
		
		JButton btnNewButton = new JButton("Quit");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new controllers.GoBackOnePanelController(frame));
		btnNewButton.setBounds(0, 0, 120, 45);
		btnNewButton.addMouseMotionListener(new MoveDraggingPieceController(this));
		add(btnNewButton);
		
		/*JPanel bullpen = new JPanel();
		bullpen.setBorder(new LineBorder(new Color(0, 0, 0)));
		bullpen.setBounds(388, 11, 386, 481);
		add(bullpen);
		bullpen.setLayout(null);
		
		int tileSize = boardView.getHeight() / 6;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < (3-i); j++) {
				JLabel lblBox = new JLabel();
				lblBox.setBackground(new Color(128, 128, 128));
				lblBox.setBounds(10 + (tileSize * i), 10 + (tileSize * j), tileSize, tileSize);
				lblBox.setBorder(new LineBorder(Color.BLACK));
				lblBox.setOpaque(true);
				bullpen.add(lblBox);
			}
		} */
		
		Rectangle bullpenBounds = new Rectangle(388, 11, 386, 481);
		BullpenView bullpen = new BullpenView(boardView.getSquareSize(), bullpenBounds);
		add(bullpen);
		bullpen.addPiece(Piece.allValidPieces[18]);
		bullpen.addPiece(Piece.allValidPieces[13]);
		bullpen.addPiece(Piece.allValidPieces[22]);
		bullpen.addPiece(Piece.allValidPieces[6]);
		level.getBullpen().addPiece(Piece.allValidPieces[18]);
		level.getBullpen().addPiece(Piece.allValidPieces[13]);
		level.getBullpen().addPiece(Piece.allValidPieces[22]);
		level.getBullpen().addPiece(Piece.allValidPieces[6]);
		BullpenGameController bullpenController = new BullpenGameController(bullpen, this);
		bullpen.addMouseListener(bullpenController);
		bullpen.addMouseMotionListener(bullpenController);
		
		StarsDisplay starsDisplay = new StarsDisplay();
		starsDisplay.setBounds(250, 552, 186, 40);
		add(starsDisplay);
		
		JLabel lblNewLabel_1 = new JLabel("Moves/Time Left");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(517, 552, 186, 29);
		add(lblNewLabel_1);
	}
	
	public KabasujiFrame getFrame(){
		return frame;
	}
	
	// Called whenever the mouse is moved on the screen
	public void moveDraggingWidgetTo(int x, int y) {
		if (draggingWidget != null) {
			draggingWidget.setBounds(x, y, draggingWidget.getWidth(), draggingWidget.getHeight());
		}
	}
	
	public Level getLevel() {
		return level;
	}
	
	public PieceView getActiveDraggingWidget() {
		return draggingWidget;
	}
	
	public void releaseActiveDraggingWidget() {
		draggingWidget = null;
		revalidate();
		repaint();
	}
	
	public void removeActiveDraggingWidget() {
		remove(draggingWidget);
		releaseActiveDraggingWidget();
	}
	
	public void setActiveDraggingPiece(Piece p) {
		draggingWidget = viewsForLevelPiecesOnBoard.get(p);
	}
	
	public void setActiveDraggingPiece(PieceView pv) {
		draggingWidget = pv;
		viewsForLevelPiecesOnBoard.put(pv.getPiece(), pv);
		add(pv);
		setComponentZOrder(pv, 1);
	}
}
