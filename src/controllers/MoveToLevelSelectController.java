package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import models.Board;
import models.Bullpen;
import models.Level;
import models.LevelType;
import models.LightningLevelLogic;
import models.Piece;
import models.PuzzleLevelLogic;
import models.ReleaseLevelLogic;
import models.SaveFile;
import models.Square;
import models.SquareTypes;
import models.ReleaseBoardSquareLogic;

import views.KabasujiFrame;
import views.LevelSelect;
import views.Title;

/**
 * A controller to move from the Player Title to the
 * Level Select Screen.
 * 
 * @author bhuchley
 */
public class MoveToLevelSelectController implements ActionListener {
	KabasujiFrame frame;
	Title title;
	public static String pathToLevelsFolder = "levels/";
	
	/**
	 * The Constructor for the MoveToLevelSelectController
	 * 
	 * @param frame - The static frame that's being passed throughout the application
	 * @param t - The previous screen, in this case, the Player Title Screen.
	 */
	public MoveToLevelSelectController(KabasujiFrame frame, Title t) {
		this.frame = frame;
		this.title = t;
	}

	/**
	 * The function that's called as a result of the interaction from the user
	 * 
	 * @param e - The actual event, i.e. the Button Press.
	 */
	public void actionPerformed(ActionEvent e) {
		// load levels on initial launch
		if (SaveFile.instance().getNumberOfLevels() == 0) {
			File[] levelFiles = new File(pathToLevelsFolder).listFiles();
			for (File i : levelFiles) {
				SaveFile.instance().addLevel(parseData(loadLevel(i)));
			}
		}
		
		//Hide the previous screen
		title.setVisible(false);
		
		//Pass the frame to the new screen
		LevelSelect newPanel = new LevelSelect(frame);
		
		//Load the new screen
		newPanel.setVisible(true);
		
		//Loading the content of the new window.
		frame.setContentPane(newPanel);
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
				data = data + i + "\n";
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
		
		// get level number
		int levelNumber = Integer.parseInt(dataTokens[0]);
		
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
		data = combineLines(dataTokens, 3);
		dataTokens = data.split("\n");
		
		// get number of board rows, columns, and squares
		int numberOfBoardRows = 0;
		int numberOfBoardCols = 0;
		ArrayList<Square> squares = new ArrayList<Square>(); 
		for (; !dataTokens[numberOfBoardRows].equals(""); numberOfBoardRows++) {
			String[] rowEntries = dataTokens[numberOfBoardRows].split(",");
			numberOfBoardCols = 0;
			for (; numberOfBoardCols < rowEntries.length; numberOfBoardCols++) {
				if (rowEntries[numberOfBoardCols].equals(" ")) {
				}
				// TODO: alternating colors?, this can probably be more concise
				else if (rowEntries[numberOfBoardCols].equals("x")) {
					squares.add(new Square(0x808080, lvlType == LevelType.LIGHTNING ? SquareTypes.LIGHTNINGBOARDSQUARE : SquareTypes.PUZZLEBOARDSQUARE,numberOfBoardRows, numberOfBoardCols));
				}
				else {
					// TODO: this can DEFINATELY be more concise
					Square releaseBoardSquare = new Square(0x808080, SquareTypes.RELEASEBOARDSQUARE, numberOfBoardRows, numberOfBoardCols);
					((ReleaseBoardSquareLogic) releaseBoardSquare.getSquareLogic()).setNumber(Integer.parseInt(rowEntries[numberOfBoardCols].substring(0, 0)));
					switch (rowEntries[numberOfBoardCols].substring(1, 1)) {
					case "R":
						((ReleaseBoardSquareLogic) releaseBoardSquare.getSquareLogic()).setColorOfNumber(Color.RED);
						break;
					case "G":
						((ReleaseBoardSquareLogic) releaseBoardSquare.getSquareLogic()).setColorOfNumber(Color.GREEN);
						break;
					case "Y":
						((ReleaseBoardSquareLogic) releaseBoardSquare.getSquareLogic()).setColorOfNumber(Color.YELLOW);
						break;
					default:
						throw new IllegalArgumentException();
					}
					squares.add(releaseBoardSquare);
				}
			}
		}
		
        // make board
        Board board = new Board(numberOfBoardRows, numberOfBoardCols, lvlType);
        for (Square i : squares) {
        	board.addSquare(i);
        }
        
		// keep relevant data
		data = combineLines(dataTokens, numberOfBoardRows + 1);
		dataTokens = data.split("\n");

        Bullpen bullpen = new Bullpen();
        // get allotted seconds if lighting level
        if (lvlType == LevelType.LIGHTNING) {
            allottedSeconds = Integer.parseInt(dataTokens[0]);
        }
        else { // else get pieces
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
		data = combineLines(dataTokens, 1);
		dataTokens = data.split("\n");

        // get alotted moves if puzzle
        if (lvlType == LevelType.PUZZLE) {
            allottedMoves = Integer.parseInt(dataTokens[0]);
    		// keep relevant data
    		data = combineLines(dataTokens, 1);
    		dataTokens = data.split("\n");
        }

        // get number of stars
        numberOfStars = Integer.parseInt(dataTokens[0]);
        
        // make extra level logic, return level
		switch (lvlType) {
		case PUZZLE:
			return new Level(board, bullpen, levelNumber, numberOfStars, new PuzzleLevelLogic(bullpen.getNumberOfPieces(), allottedMoves), Integer.toString(levelNumber));
		case LIGHTNING:
			return new Level(board, bullpen, levelNumber, numberOfStars, new LightningLevelLogic(board.getNumberOfSquares(), allottedSeconds), Integer.toString(levelNumber));
		case RELEASE:
			return new Level(board, bullpen, levelNumber, numberOfStars, new ReleaseLevelLogic(), Integer.toString(levelNumber));
		default:
			throw new IllegalArgumentException();
		}
	}

    static String combineLines(String[] array, int startingIndex) {
    	String combinedLines = array[startingIndex] + "\n";
        for (int i = startingIndex + 1; i < array.length; i++) {
        	combinedLines = combinedLines + array[i] + "\n";
        }
        return combinedLines;
    }
}
