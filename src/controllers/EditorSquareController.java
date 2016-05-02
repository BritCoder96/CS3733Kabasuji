package controllers;

import java.awt.event.MouseListener;

import models.Board;

/**
 * 
 * An interface for Controllers that are used to edit Squares
 * in the editor.
 * 
 * @author ejcerini
 *
 */
public interface EditorSquareController extends MouseListener{
	
	/**
	 * Set the board that the square is on. Used when the board that is being displayed is reset
	 * but the views aren't deleted and recreated.
	 * @param b the board the square is now on
	 */
	public void setBoard(Board b);
}
