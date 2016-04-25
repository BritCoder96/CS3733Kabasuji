package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.nio.file.StandardOpenOption.*;

import java.nio.file.*;
import java.io.*;

import views.LevelSelect;
import models.Level;
import models.LevelType;
import models.LightningLevelLogic;
import models.Piece;
import models.PuzzleLevelLogic;
import models.ReleaseBoardSquareLogic;
import models.Square;

/**
 * Controller that saves a level into a txt file in the builder
 * 
 * @author Bryan
 *
 */	
public class SaveLevelController {
	Level level;

	/**
	 * The Constructor for a SaveLevelController
	 * 
	 * @param ls - The level select screen being modified
	 * @param levels - The list of levels in the current file
	 */
	public SaveLevelController(Level level) {
		this.level = level;
	}

	/**
	 * The function that is called when the button is pressed
	 * 
	 * @param e - the actual event that calls the function, i.e. the button press.
	 */
	public void actionPerformed(ActionEvent e) {
		    String data = level.getLevelName() + "\n" + level.getLvlType() + "\n\n";
		    int numRows = level.getBoard().getRows();
		    int numCols = level.getBoard().getColumns();
		    String[][] board = new String[numRows][numCols] ;
		    for (Square square : level.getBoard().getSquares()) {
		    	if (square.getSquareLogic() instanceof ReleaseBoardSquareLogic) {
		    		board[square.getCoordinates().getRow()][square.getCoordinates().getCol()] =
		    				"" + (((ReleaseBoardSquareLogic) square.getSquareLogic()).getNumber()
		    						+ ((ReleaseBoardSquareLogic) square.getSquareLogic()).getColorOfNumber());
		    	}
		    	else {
		    		board[square.getCoordinates().getRow()][square.getCoordinates().getCol()] = "x";
		    	}
		    }
		    for (int i = 0; i < numRows; i++) {
		    	for (int j = 0; j < numCols; j++) {
		    		if (board[i][j] != null) {
		    			data = data + board[i][j];
		    		}
		    		else {
		    			if (j == numCols - 1 && i == numRows -1) {
			    			data = data + "\n";
		    			}
			    		else {
			    			data = data + ",";
			    		}
		    		} 
		    		if (i == numRows - 1) {
		    			data = data + "\n";
		    		}
		    	}
		    }
		    if (level.getLevelType() == LevelType.LIGHTNING) {
		    	data = data + ((LightningLevelLogic)level.getLevelLogic()).getAllottedSeconds();
		    }
		    else {
		    	for (Piece piece : level.getBullpen()) {
		    		data = data + piece.getPieceNumber() + ",";
		    	}
		    }
		    data = data + "\n\n";
		    
		    if (level.getLevelType() == LevelType.PUZZLE) {
		    	data = data + ((PuzzleLevelLogic)level.getLevelLogic()).getAllottedMoves();
		    }
		    data = data + "\n\n";
		    data = data + level.getNumberOfStars();
		    try {
			    File file = new File("./" + level.getLevelName() + ".txt");
				if (!file.exists()) {
					file.createNewFile();
				}
	
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(data);
				bw.close();
		    } catch (IOException e1) {
				e1.printStackTrace();
			}
	}

}