package models;

public class BullpenToBoardMove implements Move {

	Bullpen bullpen;
	Board board;
	Piece piece;
	Square startSquare;
	Level level;
	Square[] squares = new Square[6];
	
	public BullpenToBoardMove(Level level, Piece p, Square square) {
		this.bullpen = level.getBullpen();
		this.board = level.getBoard();
		this.piece = p;
		this.startSquare = square;
		this.level = level;
	}

	@Override
	public boolean execute() {
		if(!isValid())
			return false;
		
		bullpen.removePiece(piece);
		board.addPiece(piece);
		ExtraBoardSquareLogic currentSquare;
		boolean decremented = false;
		
		for(int i = 0; i < squares.length; i++){
			switch(level.getLvlType()){
			case LIGHTNING:
				currentSquare = (LightningBoardSquareLogic) squares[i].getSquareLogic();
				((LightningBoardSquareLogic) currentSquare).setMarked(true);
				break;
			case PUZZLE:
				currentSquare = (PuzzleBoardSquareLogic) squares[i].getSquareLogic();
				currentSquare.setCovered(true);
				if(!decremented){
					((PuzzleLevelLogic) level.getLevelLogic()).decrementRemainingMoves();
					((PuzzleLevelLogic) level.getLevelLogic()).decrementRemainingPieces();
					decremented = true;
				}
				break;
			case RELEASE:
				currentSquare = (ReleaseBoardSquareLogic) squares[i].getSquareLogic();
				currentSquare.setCovered(true);
				break;
			default:
				break;
			}
		}
		
		return true;
	}

	@Override
	public boolean isValid() {
		int curx = startSquare.getCoordinates().getCol();
		int cury = startSquare.getCoordinates().getRow();
		
		Square[] pSquares = piece.getSquares();
		Square[][] bSquares = board.getSquares();
		
		boolean validation = true;
		
		for(int i = 0; i < pSquares.length; i++){
			int offsetx = curx + pSquares[i].getCoordinates().getCol();
			int offsety = cury + pSquares[i].getCoordinates().getRow();
			
			if((offsetx < board.getColumns() && offsetx >= 0) && (offsety < board.getRows() && offsety >= 0) ){
				squares[i] = bSquares[offsetx][offsety];
			}
			else{
				validation = false;
				return validation;
			}
		}
		
		for(int i = 0; i < squares.length; i++){
			if(squares[i].getSquareLogic().isCovered){
				validation = false;
			}
		}
		
		
		return validation;
	}

	@Override
	public boolean undo() {
		for(int i = 0; i < squares.length; i++){
			if(!squares[i].getSquareLogic().isCovered){
				return false;
			}
		}
		
		
		bullpen.addPiece(piece);
		board.removePiece(piece);
		ExtraBoardSquareLogic currentSquare;
		boolean incremented = false;
		
		for(int i = 0; i < squares.length; i++){
			switch(level.getLvlType()){
			case LIGHTNING:
				currentSquare = (LightningBoardSquareLogic) squares[i].getSquareLogic();
				((LightningBoardSquareLogic) currentSquare).setMarked(false);
				break;
			case PUZZLE:
				currentSquare = (PuzzleBoardSquareLogic) squares[i].getSquareLogic();
				currentSquare.setCovered(false);
				if(!incremented){
					((PuzzleLevelLogic) level.getLevelLogic()).incrementRemainingMoves();
					((PuzzleLevelLogic) level.getLevelLogic()).incrementRemainingPieces();
					incremented = true;
				}
				break;
			case RELEASE:
				currentSquare = (ReleaseBoardSquareLogic) squares[i].getSquareLogic();
				currentSquare.setCovered(true);
				break;
			default:
				break;
			}
		}
		
		
		
		return true;

	}

}
