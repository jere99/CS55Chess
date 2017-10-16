package jere99.chess.backEnd.pieces;

import jere99.chess.backEnd.Board;

/**
 * Represents a pawn.
 * 
 * @author JeremiahDeGreeff
 */
public class Pawn extends Piece {

	/**
	 * @param row integer between 0 and 7 based on coordinate 8 - 1
	 * @param column integer between 0 and 7 based on coordinate a - h
	 * @param isWhite true if white, false if black
	 * @param board board to be associated with this piece
	 */
	public Pawn(int row, int column, boolean isWhite, Board board) {
		super(row, column, isWhite, board);
	}


	/**
	 * Tests if a move is valid for a pawn.
	 * 
	 * @param newRow [0, 7] based on coordinate [8, 1]
	 * @param newColumn [0, 7] based on coordinate [a, h]
	 * @return true if valid to move this piece to the new position, false otherwise
	 */
	@Override
	public boolean isValid(int newRow, int newColumn) {
		//forward one square
		if((isWhite && newRow - row == -1) || (!isWhite && newRow - row == 1)) {
			//to empty square directly ahead
			if(newColumn == column && board.getPieceAt(newRow, newColumn) == null)
				return true;
			//take opponent in diagonal square
			if(Math.abs(newColumn - column) == 1 && board.getPieceAt(newRow, newColumn) != null && board.getPieceAt(newRow, newColumn).isWhite != this.isWhite)
				return true;
		}
		//forward two empty squares as initial move
		return row == (isWhite ? 6 : 1) && newRow == (isWhite ? 4 : 3) && newColumn == column && board.getPieceAt(isWhite ? 5 : 2, newColumn) == null && board.getPieceAt(isWhite ? 4 : 3, newColumn) == null;
	}

}
