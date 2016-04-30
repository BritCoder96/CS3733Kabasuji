package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.util.ArrayList;

import models.Level;
import models.LevelType;
import models.Square;
import models.SquareTypes;
import models.Board;
import models.Bullpen;
import models.Piece;
import models.PuzzleLevelLogic;
import models.LightningLevelLogic;
import models.ReleaseLevelLogic;
import views.KabasujiFrame;

/**
 * Controller that loads levels from a folder
 * 
 * @author bjbenson
 * @author bhuchley
 *
 */	
public class LoadLevelsController implements ActionListener {
	KabasujiFrame kframe;
	String pathToLevelsFolder;

	/**
	 * The Constructor for a LoadLevelsController
	 * 
	 * @param kframe
	 */
	public LoadLevelsController(KabasujiFrame kframe) {
		this.kframe = kframe;
		pathToLevelsFolder = "levels/";
	}
	
	@Override
	/**
	 * Load levels from disk.
	 * 
	 * @param e - the actual event that calls the function, i.e. the button press.
	 */
	public void actionPerformed(ActionEvent e) {
		ArrayList<Level> levels = new ArrayList<Level>();
		File[] levelFiles = new File(pathToLevelsFolder).listFiles();
		
		for (File i : levelFiles) {
			levels.add(parseData(loadLevel(i)));
		}
		
		// TODO: pass levels to something
	}
	
	/**
	 * Load a level from disk in the form of a String.
	 * 
	 * @param file	A text file representation of the Level
	 * @return A String representation of the Level
	 */
	String loadLevel(File file) {
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
		
		return data;
	}
	
	/**
	 * Parse the string representation of the level as it would be written in a text file
	 * @param level the level to serialize
	 * @return the serialized form of the level
	 */
	Level parseData(String data) {
        // TODO: you have to recombine tokens before spliting them
		String[] dataTokens = data.split("\n");
		
		// get level name
		String levelName = dataTokens[0];
		
		// get levelType
		LevelType lvlType;
		switch (dataTokens[1]) {
		case "PUZZLE":
			lvlType = LevelType.PUZZLE;
			break;
		case "LIGHTNING":
			lvlType = LevelType.LIGHTNING;
			break;
		case "RELEASE":
			lvlType = LevelType.RELEASE;
			break;
		default:
			throw new IllegalArgumentException();
		}
		
		// other variables that we maybe need
		int allottedSeconds = 0;
        int[] pieceNumbers;
        int allottedMoves = 0;
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
					squares.add(new Square(0x808080, lvlType == LevelType.LIGHTNING ? SquareTypes.LIGHTNINGBOARDSQUARE : SquareTypes.PUZZLEBOARDSQUARE,numberOfBoardRows, numberOfBoardCols));
				}
				else {
					squares.add(new Square(0x808080, SquareTypes.RELEASEBOARDSQUARE, numberOfBoardRows, numberOfBoardCols));
				}
			}
		}
		
        // make board
        Board board = new Board(numberOfBoardRows, numberOfBoardCols, lvlType);
        for (Square i : squares) {
        	board.addSquare(i);
        }
        
		// keep relevant data
		data = concatArray(dataTokens, numberOfBoardRows + 1);
		dataTokens = data.split("\n");

        Bullpen bullpen = new Bullpen();
        // get allotted seconds if lighting level
        if (lvlType == LevelType.LIGHTNING) {
            allottedSeconds = Integer.parseInt(dataTokens[0]);
        }
        else { // get pieces
            String[] pieceEntries = dataTokens[0].split(",");
            pieceNumbers = new int[pieceEntries.length];
            for (int i = 0; i < pieceEntries.length; i++) {
                pieceNumbers[i] = Integer.parseInt(pieceEntries[i]);
            }
            for (int i : pieceNumbers) {
            	bullpen.addPiece(Piece.allValidPieces[i]);
            }
        }

		// keep relevant data
		data = concatArray(dataTokens, 1);
		dataTokens = data.split("\n");

        // get alotted moves if necessary
        if (lvlType == LevelType.PUZZLE) {
            allottedMoves = Integer.parseInt(dataTokens[0]);
        }

		// keep relevant data
		data = concatArray(dataTokens, 1);
		dataTokens = data.split("\n");

        // get number of stars
        numberOfStars = Integer.parseInt(dataTokens[0]);
        
        // make extra level logic, return level
        // TODO: get level number
        int levelNumber = -1;
		switch (lvlType) {
		case PUZZLE:
			return new Level(board, bullpen, levelNumber, numberOfStars, new PuzzleLevelLogic(bullpen.getNumberOfPieces(), allottedMoves), levelName);
		case LIGHTNING:
			return new Level(board, bullpen, levelNumber, numberOfStars, new LightningLevelLogic(board.getNumberOfSquares(), allottedSeconds), levelName);
		case RELEASE:
			return new Level(board, bullpen, levelNumber, numberOfStars, new ReleaseLevelLogic(), levelName);
		default:
			throw new IllegalArgumentException();
		}
	}

    static String concatArray(String[] array, int startingIndex) {
        for (; startingIndex < array.length - 1; startingIndex++) {
            array[startingIndex].concat(array[startingIndex + 1]);
        }
        return array[array.length - 1];
    }
}
