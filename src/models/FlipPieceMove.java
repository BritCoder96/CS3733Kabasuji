package models;

public class FlipPieceMove implements Move {
	Piece piece;
	Bullpen bullpen;
	
	// TODO: pass in direction?
	public FlipPieceMove(Piece piece, Bullpen bullpen) {
		this.piece = piece;
		this.bullpen = bullpen;
	}
	
	public boolean execute() {
		if (!isValid()) {
			return false;
		}
		piece.flipPiece(Directions.EAST);
		return true;
	}

	public boolean isValid() {
		return bullpen.isMember(piece);
	}

	public boolean undo() {
		piece.flipPiece(Directions.WEST);
		return true;
	}

}
