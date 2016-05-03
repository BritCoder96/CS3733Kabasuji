package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import models.Board;
import models.EditorMode;
import views.EditorBoardView;
import views.LevelEditor;
import views.LevelModifiedListener;

public class EditorModeController implements ActionListener {

	EditorMode em;
	LevelEditor screen;
	Board board;
	
	
	public EditorModeController(LevelEditor screen, Board board, EditorMode em){
		this.screen = screen;
		this.em = em;
		this.board = board;
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
		System.out.print(em);
		screen.setGameBoard(new EditorBoardView(screen, board, em));
		screen.updateOptionsDisplay(em);
=======
		((LevelEditor) listener).setGameBoard(new EditorBoardView(gamescreen, board, listener, em));
		((LevelEditor) listener).updateOptionsDisplay(em);
>>>>>>> a0df4a604f71db0d1029e153a65797f6e7350416
	}
	
	
	
}
