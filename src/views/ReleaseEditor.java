package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controllers.AddPieceController;
import controllers.BullpenEditorController;
import controllers.EditorComponentDragListener;
import controllers.EditorLevelUndoController;
import controllers.EditorModeController;
import controllers.GoBackOnePanelController;
import controllers.MoveDraggingPieceController;
import controllers.MoveDraggingPieceEditorController;
import controllers.SaveLevelController;
import main.KabasujiMain;
import models.Board;
import models.EditorMode;
import models.Level;
import models.LevelType;
import models.Piece;
import models.PuzzleLevelLogic;
import models.ReleaseLevelLogic;

import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * The editor screen for release levels.
 * @author bhuchley
 * @author bjbenson
 */
public class ReleaseEditor extends JPanel implements AddPieceListener, LevelModifiedListener, LevelSetListener, LevelEditor{

	/** The frame that the panel is shown in. */
	 private KabasujiFrame frame;
	 
	 /** The level under construction */
	 Level level;
	 /** The extra level logic for the level. The number of pieces isn't used for saving the level,
	  * so only the move limit is actually updated
	  */
	 ReleaseLevelLogic ell;
	 /** The board for the level */
	 Board board;
	 /** The panel that shows the board */
	 EditorBoardView gameboard;
	 /** The undo controller, which maintains the back stack of levels */
	 EditorLevelUndoController undoController; 
		
	 /** The bullpen view */
	 BullpenView bullpen;
	 
	 /** The piece being dragged, if any */
	 PieceView draggingPiece;
		
	 /** A map of pieces on the board to their views */
	 HashMap<Piece, PieceView> boardPieceViews;
	 
	 JComboBox releaseColor;
	 
	 JComboBox releaseNumber;
	/** the button to go back to the new level screen */
	private JButton btnBack;
	/** the button to save the level */
	private JButton btnSave;
	 
	/**
	 * Create the screen, with a rectangular level and no numbers yet.
	 * @param frame the frame to show the screen in
	 */
	public ReleaseEditor(KabasujiFrame frame, String levelName, int boardRows, int boardCols) {
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		// Add listener for the entire screen to catch mouse movement that's not on a subwidget
		// Actually, mouse motion from subwidgets is redirected to this screen anyway, so this
		// listener will handle all mouse motion events
		setEnabled(true);
		this.addMouseMotionListener(new MoveDraggingPieceEditorController(this));
		
		board = new Board(boardRows, boardCols, LevelType.RELEASE);
		board.fillWithSquares();
		
		ell = new ReleaseLevelLogic();
		
		level = new Level(boardRows, boardCols, Integer.parseInt(levelName), LevelType.RELEASE, levelName);
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
		
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEdit.setBounds(46, 495, 127, 45);
		add(btnEdit);
		btnEdit.addMouseMotionListener(new EditorComponentDragListener(this, btnEdit));
		
		JButton btnNum = new JButton("Numbers");
		btnNum.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNum.setBounds(185, 552, 127, 45);
		btnNum.addActionListener(new EditorModeController(this, board, EditorMode.NUMBER));
		add(btnNum);
		btnNum.addMouseMotionListener(new EditorComponentDragListener(this, btnNum));
		
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSave.addActionListener(new SaveLevelController(level));
		btnSave.setBounds(602, 495, 127, 45);
		add(btnSave);
		btnSave.addMouseMotionListener(new EditorComponentDragListener(this, btnSave));
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(10, 10, 120, 45);
		add(btnBack);
		btnBack.addActionListener(new GoBackOnePanelController(frame));
		btnBack.addMouseMotionListener(new EditorComponentDragListener(this, btnBack));
		
		
		JButton btnAddPiece = new JButton("Add Piece");
		btnAddPiece.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddPiece.setBounds(324, 495, 127, 45);
		btnAddPiece.addActionListener(new AddPieceController(frame, this, this));
		add(btnAddPiece);
		btnAddPiece.addMouseMotionListener(new EditorComponentDragListener(this, btnAddPiece));
		
		releaseColor = new JComboBox();
		releaseColor.setModel(new DefaultComboBoxModel(new String[] {"Red", "Green", "Yellow"}));
		releaseColor.setBounds(185, 495, 127, 22);
		add(releaseColor);
		releaseColor.addMouseMotionListener(new EditorComponentDragListener(this, releaseColor));
		
		releaseNumber = new JComboBox();
		releaseNumber.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
		releaseNumber.setBounds(185, 518, 127, 22);
		add(releaseNumber);
		releaseNumber.addMouseMotionListener(new EditorComponentDragListener(this, releaseNumber));
		
		undoController = new EditorLevelUndoController(this);
		JButton btnUndo = new JButton("Undo");
		btnUndo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUndo.addActionListener(undoController);
		btnUndo.setBounds(463, 495, 127, 45);
		add(btnUndo);
		btnUndo.addMouseMotionListener(new EditorComponentDragListener(this, btnUndo));
		
		JButton btnRedo = new JButton("Redo");
		btnRedo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRedo.setBounds(463, 552, 127, 45);
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
		ell = (ReleaseLevelLogic) level.getLevelLogic();
		board = level.getBoard();
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

	public String getNumber() {
		return (String) releaseNumber.getSelectedItem();
	}

	public Color getColor() {
		Color c = Color.BLACK;
		
		String color = (String) releaseColor.getSelectedItem();
		
		if(color == "Red")
			c = Color.RED;
		if(color == "Green")
			c = Color.GREEN;
		if(color == "Yellow")
			c = Color.YELLOW;
		
		return c;
	}
	
	@Override
	public void setGameBoard(EditorBoardView ebv){
		this.gameboard = ebv;
	}

	@Override
	public void updateOptionsDisplay(EditorMode em) {
		// TODO Make this do a thing
		
	}
	
	/**
	 * Gets the back button
	 * @return the back button
	 */
	public JButton getBtnBack() {
		return btnBack;
	}
	
	/**
	 * Gets the save button
	 * @return the save button
	 */
	public JButton getBtnSave() {
		return btnSave;
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
}
