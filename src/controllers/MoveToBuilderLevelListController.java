package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import models.Board;
import models.Bullpen;
import models.Level;
import models.LevelType;
import models.LightningLevelLogic;
import models.Piece;
import models.PuzzleLevelLogic;
import models.ReleaseBoardSquareLogic;
import models.ReleaseLevelLogic;
import models.SaveFile;
import models.Square;
import models.SquareTypes;
import views.BuilderTitle;
import views.KabasujiFrame;
import views.LevelList;
import views.Title;

/**
 * Controller that brings the builder from the Title Screen
 * to the List of levels that are available for editing.
 * 
 * @author ejcerini
 */
public class MoveToBuilderLevelListController implements ActionListener {
	KabasujiFrame frame;
	BuilderTitle buildertitle;
	public static String pathToLevelsFolder = "levels/";
	
	/**
	 * The constructor for a MoveToBuilderLevelListController
	 * 
	 * @param frame - the static frame that is being passed along throughout the application
	 * @param btitle - the previous screen, in this case, the Builder Title.
	 */
	public MoveToBuilderLevelListController(KabasujiFrame frame, BuilderTitle btitle) {
		this.frame = frame;
		this.buildertitle = btitle;
	}

	/**
	 * The function that is called when the button is pressed
	 * 
	 * @param e - the actual event that calls the function, i.e. the button press.
	 */
	public void actionPerformed(ActionEvent e) {
		// load levels on initial launch
		if (SaveFile.instance().getNumberOfLevels() == 0) {
			File[] levelFiles = new File(pathToLevelsFolder).listFiles();
			for (File i : levelFiles) {
				SaveFile.instance().addLevel(parseData(loadLevel(i)));
			}
		}
		
		//Hide the previous screen.
		buildertitle.setVisible(false);
		
		//Pass the frame along to the next screen
		LevelList newPanel = new LevelList(frame);
		
		//Set the new screen to visible
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
				else if (rowEntries[numberOfBoardCols].equalsIgnoreCase("x H")) {
					Square s = new Square(0x808080, lvlType == LevelType.LIGHTNING ? SquareTypes.LIGHTNINGBOARDSQUARE : SquareTypes.PUZZLEBOARDSQUARE,numberOfBoardRows, numberOfBoardCols);
					s.getSquareLogic().setHint(true);
					squares.add(s);
				}
				else {
					// TODO: this can DEFINATELY be more concise
					Square releaseBoardSquare = new Square(0x808080, SquareTypes.RELEASEBOARDSQUARE, numberOfBoardRows, numberOfBoardCols);
					Character c = rowEntries[numberOfBoardCols].toCharArray()[0];
					if (c == '1' || c == '2' || c == '3'|| c == '4' || c == '5' || c == '6') {						
						((ReleaseBoardSquareLogic) releaseBoardSquare.getSquareLogic()).setNumber(Character.getNumericValue(c));
						
						switch (rowEntries[numberOfBoardCols].toCharArray()[1]) { 
						case 'R':
							((ReleaseBoardSquareLogic) releaseBoardSquare.getSquareLogic()).setColorOfNumber(Color.RED);
							break;
						case 'G':
							((ReleaseBoardSquareLogic) releaseBoardSquare.getSquareLogic()).setColorOfNumber(Color.GREEN);
							break;
						case 'Y':
							((ReleaseBoardSquareLogic) releaseBoardSquare.getSquareLogic()).setColorOfNumber(Color.YELLOW);
							break;
						default:
							throw new IllegalArgumentException();
						}
					}
					char[] entryData = rowEntries[numberOfBoardCols].toCharArray();
					if(entryData[entryData.length - 1] == 'H'){
						releaseBoardSquare.getSquareLogic().setHint(true);
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
			ReleaseLevelLogic releaseLogic = new ReleaseLevelLogic();
			releaseLogic.fillSets(board);
			return new Level(board, bullpen, levelNumber, numberOfStars, releaseLogic, Integer.toString(levelNumber));
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
