package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controllers.DecrementTimeLimitController;
import controllers.EditorLevelUndoController;
import controllers.EditorModeController;
import controllers.GoBackOnePanelController;
import controllers.IncrementTimeLimitController;
import controllers.SaveLevelController;
import main.KabasujiMain;
import models.Board;
import models.EditorMode;
import models.Level;
import models.LevelType;
import models.LightningLevelLogic;
import models.Piece;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
 * The screen that allows the user to edit a lightning level.
 * @author bhuchley
 * @author ejcerini
 * @author bjbenson
 */
public class LightningEditor extends JPanel implements LevelModifiedListener, LevelSetListener, LevelEditor {

	/** The frame that the panel is shown in. */
	private KabasujiFrame frame;
	
	/** The level under construction */
	Level level;
	/** The extra level logic for the level. The number of squares isn't used for saving the level,
	 * so only the time limit is actually updated
	 */
	LightningLevelLogic ell;
	/** The board for the level */
	Board board;
	/** The undo controller, which maintains the back stack of levels */
	EditorLevelUndoController undoController; 
	/** The mode that the Editor is in */
	private EditorMode editMode;
	/** The label that shows the time limit */
	JLabel timeLimitLabel;
	/** The panel that shows the board */
	EditorBoardView gameboard;
	/** the button to go back to the new level screen */
	private JButton btnBack;
	/** the button to switch into Hint Mode */
	private JButton btnHint;
	/** the button to switch into Edit Mode */
	private JButton btnEdit;

	/**
	 * Create the frame with an rectangular lightning level of the specified size and time.
	 * @param frame the frame to show the screen in
	 */
	public LightningEditor(KabasujiFrame frame, String levelName, int boardRows, int boardCols, int timeLimit) {
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		board = new Board(boardRows, boardCols, LevelType.LIGHTNING);
		board.fillWithSquares();
		
		ell = new LightningLevelLogic(boardRows * boardCols, timeLimit);
		
		level = new Level(boardRows, boardCols, Integer.parseInt(levelName), LevelType.LIGHTNING, levelName);
		level.setBoard(board);
		level.setLevelLogic(ell);
		
		gameboard = new EditorBoardView(this, board, EditorMode.EDIT);
		gameboard.setBounds(283, 94, 430, 430);
		add(gameboard);
		
		JButton btnIncrease = new JButton("");
		btnIncrease.setIcon(new ImageIcon(LightningEditor.class.getResource("/javax/swing/plaf/metal/icons/sortUp.png")));
		btnIncrease.setBounds(117, 117, 24, 24);
		btnIncrease.addActionListener(new IncrementTimeLimitController(this, this));
		add(btnIncrease);
		
		JButton btnDecrease = new JButton("");
		btnDecrease.setIcon(new ImageIcon(LightningEditor.class.getResource("/javax/swing/plaf/metal/icons/sortDown.png")));
		btnDecrease.setBounds(117, 174, 24, 24);
		btnDecrease.addActionListener(new DecrementTimeLimitController(this, this));
		add(btnDecrease);
		
		btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEdit.setBounds(69, 247, 120, 45);
		btnEdit.addActionListener(new EditorModeController(this, EditorMode.EDIT));
		add(btnEdit);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSave.setBounds(69, 479, 120, 45);
		btnSave.addActionListener(new SaveLevelController(level));
		add(btnSave);
		
		undoController = new EditorLevelUndoController(this);
		JButton btnUndo = new JButton("Undo");
		btnUndo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUndo.setBounds(69, 363, 120, 45);
		btnUndo.addActionListener(undoController);
		add(btnUndo);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(10, 10, 120, 45);
		add(btnBack);
		btnBack.addActionListener(new GoBackOnePanelController(frame));
		
		JButton btnRedo = new JButton("Redo");
		btnRedo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRedo.setBounds(69, 421, 120, 45);
		add(btnRedo);
		
		timeLimitLabel = new JLabel();
		timeLimitLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		timeLimitLabel.setBounds(90, 141, 81, 34);
		timeLimitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(timeLimitLabel);
		
		btnHint = new JButton("Hint");
		btnHint.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnHint.setBounds(69, 305, 120, 45);
		btnHint.addActionListener(new EditorModeController(this, EditorMode.HINT));
		add(btnHint);
		updateTimeLimitDisplay();
		
		editMode = EditorMode.EDIT;
		updateOptionsDisplay();
	}
	
	/**
	 * Gets the level currently under construction in the editor.
	 * @return the level under construction
	 */
	public Level getLevel() {
		return level;
	}
	
	/**
	 * Redraws the time limit display.
	 */
	public void updateTimeLimitDisplay() {
		int allottedSeconds = ell.getAllottedSeconds();
		int minutes = allottedSeconds / 60;
		int seconds = allottedSeconds % 60;
		String connector = ":";
		if (seconds < 10) {
			connector = connector + "0";
		}
		timeLimitLabel.setText(minutes + connector + seconds);
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
		ell = (LightningLevelLogic) level.getLevelLogic();
		board = level.getBoard();
		updateTimeLimitDisplay();
		gameboard.setVisibleBoard(board);
	}

	@Override
	public void setGameBoard(EditorBoardView ebv) {
		this.gameboard = ebv;
	}
	
	/**
	 * Gets the back button
	 * @return the back button
	 */
	public JButton getBtnBack() {
		return btnBack;
	}

	@Override
	public void setEditorMode(EditorMode em) {
		this.editMode = em;
	}

	@Override
	public void updateOptionsDisplay() {
		btnHint.setEnabled(true);
		btnEdit.setEnabled(true);
		
		switch(editMode){
		case EDIT:
			btnEdit.setEnabled(false);
			break;
		case HINT:
			btnHint.setEnabled(false);
			break;
		case NUMBER:
			break;
		}	
	}

	public void setDraggingPiece(PieceView pv) {
		// Do nothing, no bullpen in lightning levels so no dragged pieces
	}

	@Override
	public void setDraggingPiece(Piece p) {
		// Do nothing, no bullpen in lightning levels so no dragged pieces
	}

	@Override
	public void addDraggingPiece(PieceView pv) {
		// Do nothing, no bullpen in lightning levels so no dragged pieces
	}
	
	@Override
	public void removeDraggingPiece() {
		// Do nothing, no bullpen in lightning levels so no dragged pieces
	}

	@Override
	public PieceView getDraggingPiece() {
		// There isn't one, no bullpen in lightning levels so no dragged pieces
		return null;
	}

	@Override
	public void moveDraggingWidgetTo(int x, int y) {
		// Do nothing, no bullpen in lightning levels so no dragged pieces
	}
}
