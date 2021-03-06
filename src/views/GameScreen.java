package views;

import models.Level;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

import java.awt.Rectangle;

import controllers.BullpenGameController;
import controllers.EndGameController;
import controllers.MoveDraggingPieceController;
import controllers.GameScreenPieceOrientationController;
import main.KabasujiMain;
import models.LevelType;
import models.LightningLevelLogic;
import models.Piece;
import models.PuzzleLevelLogic;

import java.awt.Font;

/**
 * The screen that the game is shown in.
 * @author bhuchley
 * @author bjbenson
 */
public class GameScreen extends JPanel {
	
	/** The frame that the panel is shown in. */
	private KabasujiFrame frame;
	private BullpenView bullpen;
	private GameBoardView boardView; 
	/** The level currently being played */
	Level level;
	/** The original model object for the level being played (that we modify when saving score) */
	private Level originalLevel;
	
	/** The PieceView currently being dragged, or null if none */
	PieceView draggingWidget;
	/** The map of pieces on the board to their respective views so that they can be picked up.
	 * Pieces being dragged around or in the bullpen might show up in this, but they're never going to be used. */
	HashMap<Piece, PieceView> viewsForLevelPiecesOnBoard;
	/** The JLabel showing how many moves are left */
	JLabel movesLeftDisplay;
	/** The display showing how many stars the player has */
	StarsDisplay starsDisplay;
	
	/** The timer that ticks down the time left in lightning levels */
	Timer lightningTimer;

	/**
	 * Load the initial state of the level and set the game board and bullpen to it.
	 * Presumably it should take a level as a parameter eventually but that doesn't work yet
	 * @param frame the frame to show the screen in
	 */
	public GameScreen(Level lvl, KabasujiFrame frame) {
		this.frame = frame;
		setBounds(KabasujiMain.windowSize);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		this.addKeyListener(new GameScreenPieceOrientationController(this));
		
		// Due to the fact that the level is used to keep track of the pieces on the board,
		// we need to clone the level for this screen.
		// To score it, we need to use originalLevel.
		this.level = lvl.deepClone();
		setOriginalLevel(lvl);
		viewsForLevelPiecesOnBoard = new HashMap<Piece, PieceView>();
		
		// Add listener for the entire screen to catch mouse movement that's not on a subwidget
		// Actually, mouse motion from subwidgets is redirected to this screen anyway, so this
		// listener will handle all mouse motion events
		setEnabled(true);
		this.addMouseMotionListener(new MoveDraggingPieceController(this));
		boardView = new GameBoardView(this, this.level.getBoard());
		boardView.setBounds(10, 88, 356, 356);
		
		add(boardView);
		
		JButton btnNewButton = new JButton("Quit");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new controllers.GoBackOnePanelController(frame));
		btnNewButton.setBounds(0, 0, 120, 45);
		btnNewButton.addMouseMotionListener(new MoveDraggingPieceController(this));
		add(btnNewButton);
		
		Rectangle bullpenBounds = new Rectangle(388, 11, 386, 481);
		bullpen = new BullpenView(boardView.getSquareSize(), bullpenBounds);
		add(bullpen);
		for (Piece p : level.getBullpen().getPieces()) {
			bullpen.addPiece(new Piece(p));
		}
		if (level.getLvlType() == LevelType.LIGHTNING) {
			Random rand = new Random();
			Piece[] pieces = Piece.allValidPieces;
			bullpen.addPiece(new Piece(pieces[rand.nextInt(pieces.length -1)]));
			bullpen.addPiece(new Piece(pieces[rand.nextInt(pieces.length -1)]));
			bullpen.addPiece(new Piece(pieces[rand.nextInt(pieces.length -1)]));
		}
		BullpenGameController bullpenController = new BullpenGameController(bullpen, this);
		bullpen.addMouseListener(bullpenController);
		bullpen.addMouseMotionListener(bullpenController);
		
		starsDisplay = new StarsDisplay(0);
		starsDisplay.setBounds(250, 552, 186, 40);
		add(starsDisplay);
		if (level.getLvlType() == LevelType.LIGHTNING) {
			LightningLevelLogic logic = ((LightningLevelLogic)level.getLevelLogic());
			logic.setRemainingSeconds(logic.getAllottedSeconds());
			JLabel lblNewLabel_1 = new JLabel("Time Left: " + logic.getAllottedSeconds());
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
			lblNewLabel_1.setBounds(517, 552, 186, 29);
			add(lblNewLabel_1);
		    lightningTimer = new Timer();
			lightningTimer.schedule(new DecrementTimer(lblNewLabel_1, logic), 0, 1 * 1000);
		}
		else if (level.getLvlType() == LevelType.PUZZLE) {
			PuzzleLevelLogic logic = ((PuzzleLevelLogic)level.getLevelLogic());
			movesLeftDisplay = new JLabel("Moves Left: " + logic.getRemainingMoves());
			movesLeftDisplay.setFont(new Font("Tahoma", Font.PLAIN, 24));
			movesLeftDisplay.setBounds(517, 552, 186, 29);
			add(movesLeftDisplay);
		}
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	 class DecrementTimer extends TimerTask {
	 	JLabel timeLeftLabel;
	 	LightningLevelLogic logic;
	 	DecrementTimer(JLabel timeLeftLabel, LightningLevelLogic logic) {
	 		this.timeLeftLabel = timeLeftLabel;
	 		this.logic = logic;
	 	}
	 	
	 	public void run() {
	 		if (logic.getRemainingSeconds() > 0) {
	 			logic.decrementRemainingSeconds();
	 		} else {
				endGame();
	 		}
 			timeLeftLabel.setText("Time Left " + logic.getRemainingSeconds());
	 	}
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
		setComponentZOrder(draggingWidget, 0);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	public void setActiveDraggingPiece(PieceView pv) {
		draggingWidget = pv;
		viewsForLevelPiecesOnBoard.put(pv.getPiece(), pv);
		add(pv);
		setComponentZOrder(pv, 0);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	public boolean isOptimizedDrawingEnabled() {
		return false;
	}

	public Level getOriginalLevel() {
		return originalLevel;
	}

	public void setOriginalLevel(Level originalLevel) {
		this.originalLevel = originalLevel;
	}
	
	/**
	 * Update the moves display label.
	 * Will cause an error if that label doesn't exist (i.e. in lightning levels)
	 */
	public void updateMovesDisplay() {
		movesLeftDisplay.setText("Moves Left: " + ((PuzzleLevelLogic)level.getLevelLogic()).getRemainingMoves());
	}
	
	/** Ends the level */
	public void endGame() {
		if (lightningTimer != null) {
			lightningTimer.cancel();
		}
		new EndGameController(level, frame);
	}
	
	/**
	 * gets the bullpenview
	 * @return the bullpenview
	 */
	public BullpenView getBullpen() {
		return bullpen;
	}
	
	/**
	 * gets the boardview
	 * @return the boardview
	 */
	public GameBoardView getBoardView() {
		return boardView;
	}
	
	/**
	 * gets the boardview
	 * @return the boardview
	 */
	public HashMap<Piece, PieceView> getPiecesOnBoard() {
		return viewsForLevelPiecesOnBoard;
	}
	
	/**
	 * Updates the number of stars to show how many are filled
	 */
	public void updateStarsDisplay() {
		starsDisplay.setNumStarsFilled(level.getNumberOfStars());
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (lightningTimer != null && ! visible) {
			lightningTimer.cancel();
		}
	}
	
}
