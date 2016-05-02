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
import controllers.EditorLevelUndoController;
import controllers.EditorModeController;
import controllers.GoBackOnePanelController;
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
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * The editor screen for release levels.
 * @author bhuchley
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
	 
	 JComboBox releaseColor;
	 
	 JComboBox releaseNumber;
	 
	/**
	 * Create the screen, with a rectangular level and no numbers yet.
	 * @param frame the frame to show the screen in
	 */
	public ReleaseEditor(KabasujiFrame frame, String levelName, int boardRows, int boardCols) {
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		board = new Board(boardRows, boardCols, LevelType.RELEASE);
		board.fillWithSquares();
		
		ell = new ReleaseLevelLogic();
		
		level = new Level(boardRows, boardCols, Integer.parseInt(levelName), LevelType.RELEASE, levelName);
		level.setBoard(board);
		
		gameboard = new EditorBoardView(this, board, this, EditorMode.EDIT);
		gameboard.setBounds(60, 71, 325, 325);
		add(gameboard);
		
		bullpen = new BullpenView(gameboard.getWidth() / boardCols, new Rectangle(412, 14, 350, 455));
		add(bullpen);
		
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEdit.setBounds(46, 495, 127, 45);
		add(btnEdit);
		
		JButton btnNum = new JButton("Numbers");
		btnNum.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNum.setBounds(185, 552, 127, 45);
		btnNum.addActionListener(new EditorModeController(this, this, board, EditorMode.NUMBER));
		add(btnNum);
		
		JButton btnMove = new JButton("Move");
		btnMove.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMove.setBounds(324, 552, 127, 45);
		add(btnMove);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.setBounds(602, 495, 127, 45);
		add(btnSave);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(10, 10, 120, 45);
		add(btnBack);
		btnBack.addActionListener(new GoBackOnePanelController(frame));
		
		
		JButton btnAddPiece = new JButton("Add Piece");
		btnAddPiece.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddPiece.setBounds(324, 495, 127, 45);
		btnAddPiece.addActionListener(new AddPieceController(frame, this, this));
		add(btnAddPiece);
		
		releaseColor = new JComboBox();
		releaseColor.setModel(new DefaultComboBoxModel(new String[] {"Red", "Green", "Yellow"}));
		releaseColor.setBounds(185, 495, 127, 22);
		add(releaseColor);
		
		releaseNumber = new JComboBox();
		releaseNumber.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
		releaseNumber.setBounds(185, 518, 127, 22);
		add(releaseNumber);
		
		undoController = new EditorLevelUndoController(this);
		JButton btnUndo = new JButton("Undo");
		btnUndo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUndo.addActionListener(undoController);
		btnUndo.setBounds(463, 495, 127, 45);
		add(btnUndo);
		
		JButton btnRedo = new JButton("Redo");
		btnRedo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRedo.setBounds(463, 552, 127, 45);
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
}
