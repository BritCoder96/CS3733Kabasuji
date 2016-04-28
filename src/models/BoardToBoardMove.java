package models;

public class BoardToBoardMove implements Move {

	Board board;
	Piece piece;
	Square startSquare;
	Level level;
	Square[] newSquares = new Square[6];
	Square[] oldSquares = new Square[6];
	
	public BoardToBoardMove(Level level, Piece p, Square newSquare, Square[] previousSquares) {
		this.board = level.getBoard();
		this.piece = p;
		this.startSquare = newSquare;
		this.level = level;
		this.oldSquares = previousSquares;
	}
	
	@Override
	public boolean execute() {
		if(!isValid())
			return false;
		
		ExtraBoardSquareLogic currentSquare;
		boolean decremented = false;
		
		for(int i = 0; i < newSquares.length; i++){
			if(!isInSquares(oldSquares[i], newSquares))
				oldSquares[i].getSquareLogic().setCovered(false);
			switch(level.getLvlType()){
			case LIGHTNING:
				break;
			case PUZZLE:
				currentSquare = (PuzzleBoardSquareLogic) newSquares[i].getSquareLogic();
				currentSquare.setCovered(true);
				if(!decremented){
					((PuzzleLevelLogic) level.getLevelLogic()).decrementRemainingMoves();
					decremented = true;
				}
				break;
			case RELEASE:
				break;
			default:
				break;
			}
		}
		
		return true;
	}

	@Override
	public boolean isValid() {
		if(level.getLvlType() == LevelType.LIGHTNING || level.getLvlType() == LevelType.RELEASE)
			return false;
		
		int curx = startSquare.getCoordinates().getCol();
		int cury = startSquare.getCoordinates().getRow();
		
		Square[] pSquares = piece.getSquares();
		Square[][] bSquares = board.getSquares();
		
		boolean validation = true;
		
		for(int i = 0; i < pSquares.length; i++){
			int offsetx = curx + pSquares[i].getCoordinates().getCol();
			int offsety = cury + pSquares[i].getCoordinates().getRow();
			
			if((offsetx < board.getColumns() && offsetx >= 0) && (offsety < board.getRows() && offsety >= 0) ){
				newSquares[i] = bSquares[offsetx][offsety];
			}
			else{
				validation = false;
				return validation;
			}
		}
		
		for(int i = 0; i < newSquares.length; i++){
			if(newSquares[i].getSquareLogic().isCovered && !isInSquares(newSquares[i], oldSquares) ){
				validation = false;
			}
		}
		
		
		return validation;
	}

	@Override
	public boolean undo() {

		for(int i = 0; i < newSquares.length; i++){
			if(!newSquares[i].getSquareLogic().isCovered){
				return false;
			}
		}
		
		ExtraBoardSquareLogic currentSquare;
		boolean incremented = false;
		
		for(int i = 0; i < oldSquares.length; i++){
			if(!isInSquares(newSquares[i], oldSquares))
				oldSquares[i].getSquareLogic().setCovered(false);
			switch(level.getLvlType()){
			case LIGHTNING:
				break;
			case PUZZLE:
				currentSquare = (PuzzleBoardSquareLogic) oldSquares[i].getSquareLogic();
				currentSquare.setCovered(true);
				if(!incremented){
					((PuzzleLevelLogic) level.getLevelLogic()).incrementRemainingMoves();
					incremented = true;
				}
				break;
			case RELEASE:
				break;
			default:
				break;
			}
		}
		
		return true;
	}
	
	boolean isInSquares(Square s, Square[] squares){
		
		for(int i = 0; i < squares.length; i++){
			if(s == squares[i])
				return true;
		}
		
		return false;
		
	}

}
