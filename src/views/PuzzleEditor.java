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
import controllers.DecrementMoveLimitController;
import controllers.EditorLevelUndoController;
import controllers.GoBackOnePanelController;
import controllers.IncrementMoveLimitController;
import controllers.SaveLevelController;
import main.KabasujiMain;
import models.Board;
import models.Level;
import models.LevelType;
import models.LightningLevelLogic;
import models.Piece;
import models.PuzzleLevelLogic;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The editor screen for puzzle levels.
 * @author ejcerini
 * @author bhuchley
 */
public class PuzzleEditor extends JPanel implements AddPieceListener, LevelModifiedListener, LevelSetListener {

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

	/**
	 * Create the editor screen, with a rectangular level and no pieces.
	 * @param frame the frame to show the screen in
	 */
	public PuzzleEditor(KabasujiFrame frame, String levelName, int boardRows, int boardCols, int moveLimit) {
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		board = new Board(boardRows, boardCols, LevelType.PUZZLE);
		board.fillWithSquares();
		
		ell = new PuzzleLevelLogic(0, moveLimit);
		
		level = new Level(boardRows, boardCols, 0, 0, LevelType.PUZZLE, ell, levelName);
		level.setBoard(board);
		
		gameboard = new EditorBoardView(this, board, this);
		gameboard.setBounds(60, 71, 325, 325);
		add(gameboard);
		
		bullpen = new BullpenView(gameboard.getWidth() / boardCols, new Rectangle(412, 14, 350, 455));
		add(bullpen);
		
		moveLimitLabel = new JLabel(String.valueOf(moveLimit));
		moveLimitLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		moveLimitLabel.setBounds(10, 445, 44, 24);
		moveLimitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(moveLimitLabel);
		
		JButton btnIncrease = new JButton("");
		btnIncrease.setIcon(new ImageIcon(PuzzleEditor.class.getResource("/javax/swing/plaf/metal/icons/sortUp.png")));
		btnIncrease.setBounds(20, 420, 24, 24);
		btnIncrease.addActionListener(new IncrementMoveLimitController(this, this));
		add(btnIncrease);
		
		JButton btnDecrease = new JButton("");
		btnDecrease.setIcon(new ImageIcon(PuzzleEditor.class.getResource("/javax/swing/plaf/metal/icons/sortDown.png")));
		btnDecrease.setBounds(20, 472, 24, 24);
		btnDecrease.addActionListener(new DecrementMoveLimitController(this, this));
		add(btnDecrease);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(30, 551, 120, 45);
		add(btnDelete);
		
		JButton btnDraw = new JButton("Draw");
		btnDraw.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDraw.setBounds(180, 551, 120, 45);
		add(btnDraw);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSave.setBounds(330, 551, 120, 45);
		btnSave.addActionListener(new SaveLevelController(level));
		add(btnSave);
		
		undoController = new EditorLevelUndoController(this);
		JButton btnUndo = new JButton("Undo");
		btnUndo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUndo.addActionListener(undoController);
		btnUndo.setBounds(480, 551, 120, 45);
		add(btnUndo);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(10, 10, 120, 45);
		add(btnBack);
		btnBack.addActionListener(new GoBackOnePanelController(frame));
		
		JButton btnSolveForMe = new JButton("Solve for Me");
		btnSolveForMe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSolveForMe.setBounds(71, 436, 159, 46);
		add(btnSolveForMe);
		
		JButton btnAddPiece = new JButton("Add Piece");
		btnAddPiece.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddPiece.setBounds(257, 436, 136, 46);
		btnAddPiece.addActionListener(new AddPieceController(frame, this, this));
		add(btnAddPiece);
		
		JButton btnRedo = new JButton("Redo");
		btnRedo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRedo.setBounds(630, 551, 120, 45);
		add(btnRedo);
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
	}

}
