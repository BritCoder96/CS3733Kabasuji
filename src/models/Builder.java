package models;

import java.util.Stack;
import java.util.EmptyStackException;

public class Builder {
	Builder inst;
	Level level;
	Stack<Move> moves;
	
	Builder() {
		moves = new Stack<Move>();
	}
	
	public void popMove(){
		try {
			moves.pop().undo();
		} catch (EmptyStackException e) {
		}
	}
	
	public void pushMove(Move move) {
		moves.push(move);
	}
}
