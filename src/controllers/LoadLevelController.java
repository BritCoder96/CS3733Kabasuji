package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.util.ArrayList;

import models.Level;
import models.LevelType;
import models.Square;
import models.SquareTypes;
import models.Piece;

/**
 * Controller that saves a level into a txt file in the builder
 * 
 * @author bjbenson
 * @author bhuchley
 *
 */	
public class LoadLevelController implements ActionListener {
	File file;

	/**
	 * The Constructor for a SaveLevelController
	 * 
	 * @param ls - The level select screen being modified
	 * @param levels - The list of levels in the current file
	 */
	public LoadLevelController(File file) {
		this.file = file;
	}
	
	@Override
	/**
	 * Write the serialized level to a file
	 * 
	 * @param e - the actual event that calls the function, i.e. the button press.
	 */
	public void actionPerformed(ActionEvent e) {
		String data = "";
		try {
			FileReader fr = new FileReader(file.getAbsolutePath());
			BufferedReader br = new BufferedReader(fr);
			for (String i = br.readLine(); i != null; i = br.readLine()) {
				data = data + i;
			}
			br.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Level level = parseData(data);
	}
	
	/**
	 * Get the string representation of the level as it would be written in a text file
	 * @param level the level to serialize
	 * @return the serialized form of the level
	 */
	public static Level parseData(String data) {
        // TODO: you have to recombine tokens before spliting them
		// get level name and type
		String[] dataTokens = data.split("\n");
		String levelName = dataTokens[0];
		String lvlType = dataTokens[1];
		
		// other variables that we maybe need
		int allottedSeconds;
        int[] pieceNumbers;
        int allottedMoves;
        int numberOfStars;
		
		// keep relevant data
		data = concatArray(dataTokens, 3);
		dataTokens = data.split("\n");
		
		// get number of board rows, columns, and squares
		int numberOfBoardRows = 0;
		int numberOfBoardCols = 0;
		ArrayList<Square> squares = new ArrayList<Square>(); 
		for (; dataTokens[numberOfBoardRows].charAt(0) != '\n'; numberOfBoardRows++) {
			String[] rowEntries = dataTokens[numberOfBoardRows].split(",");
			for (; numberOfBoardCols < rowEntries.length; numberOfBoardCols++) {
				if (rowEntries[numberOfBoardCols].equals(" ")) {
				}
				// TODO: alternating colors, this can probably be more concise
				else if (rowEntries[numberOfBoardCols].equals("x")) {
					squares.add(new Square(0x808080, lvlType.equals("LIGHTNING") ? SquareTypes.LIGHTNINGBOARDSQUARE : SquareTypes.PUZZLEBOARDSQUARE,numberOfBoardRows, numberOfBoardCols));
				}
				else {
					squares.add(new Square(0x808080, SquareTypes.RELEASEBOARDSQUARE, numberOfBoardRows, numberOfBoardCols));
				}
			}
		}
		
		// keep relevant data
		data = concatArray(dataTokens, numberOfBoardRows + 1);
		dataTokens = data.split("\n");

        // get allotted seconds if lighting level
        if (lvlType.equals("LIGHTNING")) {
            allottedSeconds = Integer.parseInt(dataTokens[0]);
        }
        else { // get pieces
            String[] pieceEntries = dataTokens[0].split(",");
            pieceNumbers = new int[pieceEntries.length];
            for (int i = 0; i < pieceEntries.length; i++) {
                pieceNumbers[i] = Integer.parseInt(pieceEntries[i]);
            }
        }

		// keep relevant data
		data = concatArray(dataTokens, 1);
		dataTokens = data.split("\n");

        // get alotted moves if necessary
        if (lvlType.equals("PUZZLE")) {
            allottedMoves = Integer.parseInt(dataTokens[0]);
        }

		// keep relevant data
		data = concatArray(dataTokens, 1);
		dataTokens = data.split("\n");

        // get number of stars
        numberOfStars = Integer.parseInt(dataTokens[0]);

        // TODO: make bullpen, conditionals for level type
		return new Level(numberOfBoardCols, numberOfBoardCols, levelNumber, numberOfStars, lvlType, new ExtraLevelLogic, levelName);
	}

    static String concatArray(String[] array, int startingIndex) {
        for (; startingIndex < array.length - 1; startingIndex++) {
            array[startingIndex].concat(array[startingIndex + 1]);
        }
        return array[array.length - 1];
    }
}
