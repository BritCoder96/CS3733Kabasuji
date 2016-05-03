package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controllers.AddPieceController;
import controllers.BullpenEditorController;
import controllers.BullpenGameController;
import controllers.DecrementMoveLimitController;
import controllers.EditorComponentDragListener;
import controllers.EditorLevelUndoController;
import controllers.GoBackOnePanelController;
import controllers.IncrementMoveLimitController;
import controllers.MoveDraggingPieceEditorController;
import controllers.SaveLevelController;
import main.KabasujiMain;
import models.Board;
import models.EditorMode;
import models.Level;
import models.LevelType;
import models.LightningLevelLogic;
import models.Piece;
import models.PuzzleLevelLogic;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

/**
 * The editor screen for puzzle levels.
 * @author ejcerini
 * @author bhuchley
 * @author bjbenson
 */
public class PuzzleEditor extends JPanel implements AddPieceListener, LevelModifiedListener, LevelSetListener, LevelEditor {

	/** The frame that the panel is shown in. */
	private KabasujiFrame frame;
	
	/** The level under construction */
	Level level;
	/** The extra level logic for the level. The number of pieces isn't used for saving the level,
	 * so only the move limit is actually updated
	 */
	PuzzleLevelLogic ell;
	/** The board for the level */
	Board board;
	/** The panel that shows the board */
	EditorBoardView gameboard;
	/** The undo controller, which maintains the back stack of levels */
	EditorLevelUndoController undoController; 
	
	/** The label showing the move limit */
	JLabel moveLimitLabel;
	/** The bullpen view */
	BullpenView bullpen;
	 /** The piece being dragged, if any */
	 PieceView draggingPiece;
	/** the button to go back to the new level screen */
	private JButton btnBack;
	
	/** A map of pieces on the board to their views */
	HashMap<Piece, PieceView> boardPieceViews;
	/** the button to open the piece overlay to add a piece */
	private JButton btnAddPiece;

	/**
	 * Create the editor screen, with a rectangular level and no pieces.
	 * @param frame the frame to show the screen in
	 */
	public PuzzleEditor(KabasujiFrame frame, String levelName, int boardRows, int boardCols, int moveLimit) {
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		// Add listener for the entire screen to catch mouse movement that's not on a subwidget
		// Actually, mouse motion from subwidgets is redirected to this screen anyway, so this
		// listener will handle all mouse motion events
		setEnabled(true);
		this.addMouseMotionListener(new MoveDraggingPieceEditorController(this));
		
		board = new Board(boardRows, boardCols, LevelType.PUZZLE);
		board.fillWithSquares();
		
		ell = new PuzzleLevelLogic(0, moveLimit);
		
		level = new Level(boardRows, boardCols, Integer.parseInt(levelName), LevelType.PUZZLE, levelName);
		level.setBoard(board);
		
		boardPieceViews = new HashMap<Piece, PieceView>();
		
		gameboard = new EditorBoardView(this, board, EditorMode.EDIT);
		gameboard.setBounds(60, 71, 325, 325);
		add(gameboard);
		
		bullpen = new BullpenView(gameboard.getWidth() / boardCols, new Rectangle(412, 14, 350, 455));
		add(bullpen);
		BullpenEditorController bullpenController = new BullpenEditorController(bullpen, this);
		bullpen.addMouseListener(bullpenController);
		bullpen.addMouseMotionListener(bullpenController);
		
		moveLimitLabel = new JLabel(String.valueOf(moveLimit));
		moveLimitLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		moveLimitLabel.setBounds(10, 220, 44, 24);
		moveLimitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(moveLimitLabel);
		
		JButton btnIncrease = new JButton("");
		btnIncrease.setIcon(new ImageIcon(PuzzleEditor.class.getResource("/javax/swing/plaf/metal/icons/sortUp.png")));
		btnIncrease.setBounds(20, 195, 24, 24);
		btnIncrease.addActionListener(new IncrementMoveLimitController(this, this));
		add(btnIncrease);
		btnIncrease.addMouseMotionListener(new EditorComponentDragListener(this, btnIncrease));
		
		JButton btnDecrease = new JButton("");
		btnDecrease.setIcon(new ImageIcon(PuzzleEditor.class.getResource("/javax/swing/plaf/metal/icons/sortDown.png")));
		btnDecrease.setBounds(20, 247, 24, 24);
		btnDecrease.addActionListener(new DecrementMoveLimitController(this, this));
		add(btnDecrease);
		btnDecrease.addMouseMotionListener(new EditorComponentDragListener(this, btnDecrease));
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEdit.setBounds(46, 472, 127, 45);
		add(btnEdit);
		btnEdit.addMouseMotionListener(new EditorComponentDragListener(this, btnEdit));
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSave.setBounds(463, 472, 127, 45);
		btnSave.addActionListener(new SaveLevelController(level));
		add(btnSave);
		btnSave.addMouseMotionListener(new EditorComponentDragListener(this, btnSave));
		
		undoController = new EditorLevelUndoController(this);
		JButton btnUndo = new JButton("Undo");
		btnUndo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUndo.addActionListener(undoController);
		btnUndo.setBounds(324, 472, 127, 45);
		add(btnUndo);
		btnUndo.addMouseMotionListener(new EditorComponentDragListener(this, btnUndo));
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(10, 10, 120, 45);
		add(btnBack);
		btnBack.addActionListener(new GoBackOnePanelController(frame));
		btnBack.addMouseMotionListener(new EditorComponentDragListener(this, btnBack));
		
		btnAddPiece = new JButton("Add Piece");
		btnAddPiece.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddPiece.setBounds(185, 471, 127, 45);
		btnAddPiece.addActionListener(new AddPieceController(frame, this, this));
		add(btnAddPiece);
		btnAddPiece.addMouseMotionListener(new EditorComponentDragListener(this, btnAddPiece));
		
		JButton btnRedo = new JButton("Redo");
		btnRedo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRedo.setBounds(324, 520, 127, 45);
		add(btnRedo);
		btnRedo.addMouseMotionListener(new EditorComponentDragListener(this, btnRedo));
	}
	
	/**
	 * Gets the level currently under construction in the editor.
	 * @return the level under construction
	 */
	public Level getLevel() {
		return level;
	}

	@Override
	public void addPiece(Piece p) {
		level.getBullpen().addPiece(p);
		bullpen.addPiece(p);
	}
	
	/**
	 * Update the move limit display
	 */
	public void updateMoveLimitDisplay() {
		moveLimitLabel.setText(String.valueOf(ell.getAllottedMoves()));
	}

	@Override
	public void onLevelChanged() {
		pushBackStack();
	}
	
	/**
	 * Pushes a clone of the current level to the back stack.
	 */
	private void pushBackStack() {
		undoController.pushLevel(level);
	}

	@Override
	public void setLevel(Level level) {
		this.level = level;
		ell = (PuzzleLevelLogic) level.getLevelLogic();
		board = level.getBoard();
		updateMoveLimitDisplay();
		gameboard.setVisibleBoard(board);
		bullpen.clearPieces();
		for (Piece p : level.getBullpen().getPieces()) {
			bullpen.addPiece(p);
		}
		for (PieceView p : boardPieceViews.values()) {
			remove(p);
		}
		boardPieceViews.clear();
		removeDraggingPiece();
	}

	@Override
	public void setGameBoard(EditorBoardView ebv) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOptionsDisplay(EditorMode em) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Gets the back button
	 * @return the back button
	 */
	public JButton getBtnBack() {
		return btnBack;
	}
	
	public void setDraggingPiece(Piece p) {
		setDraggingPiece(boardPieceViews.get(p));
	}

	@Override
	public void setDraggingPiece(PieceView pv) {
		draggingPiece = pv;
		if (pv != null) {
			setComponentZOrder(pv, 0);
		}
	}

	@Override
	public void addDraggingPiece(PieceView pv) {
		add(pv);
		setDraggingPiece(pv);
		boardPieceViews.put(pv.getPiece(), pv);
	}
	
	@Override
	public void removeDraggingPiece() {
		if (draggingPiece != null) {
			remove(draggingPiece);
			draggingPiece = null;
			revalidate();
			repaint();
		}
	}

	@Override
	public PieceView getDraggingPiece() {
		if (draggingPiece == null) {
			return null;
		} else {
			return draggingPiece;
		}
	}

	@Override
	public void moveDraggingWidgetTo(int x, int y) {
		if (draggingPiece != null) {
			draggingPiece.setBounds(x, y, draggingPiece.getWidth(), draggingPiece.getHeight());
		}
	}
	/**
	 * Gets the add Piece button
	 * @return the add Piece button
	 */
	public JButton getBtnAddPiece() {
		return btnAddPiece;
	}

}
