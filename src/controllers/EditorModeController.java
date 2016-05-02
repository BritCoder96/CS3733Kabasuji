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
	JPanel gamescreen;
	LevelModifiedListener listener;
	Board board;
	
	
	public EditorModeController(JPanel gamescreen, LevelModifiedListener listener, Board board, EditorMode em){
		this.gamescreen = gamescreen;
		this.listener = listener;
		this.em = em;
		this.board = board;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.print(em);
		((LevelEditor) listener).setGameBoard(new EditorBoardView(gamescreen, board, listener, em));
		((LevelEditor) listener).updateOptionsDisplay(em);
	}
	
	
	
}
