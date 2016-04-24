package models;

public class RotatePieceMove implements Move {
	Piece piece;
	Bullpen bullpen;
	
	public RotatePieceMove(Piece piece, Bullpen bullpen) {
		this.piece = piece;
		this.bullpen = bullpen;
	}
	
	public boolean execute() {
		if (!isValid()) {
			return false;
		}
		piece.rotatePiece(Directions.EAST);
		return true;
	}

	public boolean isValid() {
		return bullpen.isMember(piece);
	}

	public void undo() {
		piece.rotatePiece(Directions.WEST);
	}

}
