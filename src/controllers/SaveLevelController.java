package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;

import models.Level;
import models.LevelType;
import models.LightningLevelLogic;
import models.Piece;
import models.PuzzleLevelLogic;
import models.ReleaseBoardSquareLogic;
import models.SaveFile;
import models.Square;

/**
 * Controller that saves a level into a txt file in the builder
 * 
 * @author bjbenson
 * @author bhuchley
 *
 */	
public class SaveLevelController implements ActionListener {
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
	
	@Override
	/**
	 * Write the serialized level to a file
	 * 
	 * @param e - the actual event that calls the function, i.e. the button press.
	 */
	public void actionPerformed(ActionEvent e) {
		saveLevel();
		if(! SaveFile.instance().levelExists(level.getLevelNumber())){
			SaveFile.instance().addLevel(level);
		}
	}
	
	/**
	 * Saves a level to a txt file
	 */
	public void saveLevel() {
		String data = serializeLevel(level);
		File file = new File("levels/" + level.getLevelNumber() + ".txt");
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(data);
			bw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Get the string representation of the level as it would be written in a text file
	 * @param level the level to serialize
	 * @return the serialized form of the level
	 */
	public static String serializeLevel(Level level) {
		// write level number/name and level type
	    String data = level.getLevelNumber() + "\n" + level.getLvlType() + "\n\n";
	    
	    int numRows = level.getBoard().getRows();
	    int numCols = level.getBoard().getColumns();
	    String[][] board = new String[numRows][numCols];
	    for (int r = 0; r < numRows; r++) {
	    	for (int c = 0; c < numCols; c++) {
	    		Square square = level.getBoard().getSquares()[r][c];
	    		if (square != null) {
	    			if (square.getSquareLogic() instanceof ReleaseBoardSquareLogic) {
	    				ReleaseBoardSquareLogic releaseLogic = (ReleaseBoardSquareLogic) square.getSquareLogic();
	    				
	    				String colorChar;
	    				switch (releaseLogic.getColorOfNumber().getRGB()) {
	    				case -65536:
	    					colorChar = "R";
	    					break;
	    				case -16711936:
	    					colorChar = "G";
	    					break;
	    				case -256:
	    					colorChar = "Y";
	    					break;
	    				default:
	    					// should never get here
	    					colorChar = "";
	    				}
	    				if (square.getSquareLogic().getIsHint()) {
	    					board[r][c] = releaseLogic.getNumber() + colorChar + " H";
	    				} 
	    				else {
	    					board[r][c] = releaseLogic.getNumber() + colorChar;
	    				}
	    			}
	    			else {
	    				if (square.getSquareLogic().getIsHint()) {
		    				board[r][c] = "x H";
	    				}
	    				else {
	    					board[r][c] = "x";
	    				}
	    			}
	    		} else {
	    			board[r][c] = " ";
	    		}
	    	}
	    }
	    for (int i = 0; i < numRows; i++) {
	    	for (int j = 0; j < numCols; j++) {
	    		data = data + board[i][j];
	    		if (j == numCols - 1) {
	    			data = data + "\n";
	    		} else {
	    			data = data + ",";
	    		}
	    	}
	    }
	    data = data + "\n";
	    if (level.getLvlType() == LevelType.LIGHTNING) {
	    	data = data + ((LightningLevelLogic)level.getLevelLogic()).getAllottedSeconds();
	    }
	    else {
	    	for (Piece piece : level.getBullpen().getPieces()) {
	    		data = data + piece.getPieceNumber() + ",";
	    	}
	    	if (level.getLvlType() == LevelType.PUZZLE) {
	    		data = data + "\n";
		    	data = data + ((PuzzleLevelLogic)level.getLevelLogic()).getAllottedMoves();
		    }
	    }
	    
	    data = data + "\n";
	    data = data + level.getNumberOfStars();
		return data;
	}
}