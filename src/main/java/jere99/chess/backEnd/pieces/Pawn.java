package jere99.chess.backEnd.pieces;

import jere99.chess.backEnd.Board;

/**
 * an object that represents a pawn
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
	 * Tests if a move is valid for the particular type of piece
	 * @param newRow number between 0 and 7 based on coordinate 8 - 1
	 * @param newColumn number between 0 and 7 based on coordinate a - h
	 * @return true if valid to move this piece to [newRow][newCol], false otherwise
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
		if(isWhite && row == 6 && newRow == 4 && newColumn == column)
			if(board.getPieceAt(5, newColumn) == null && board.getPieceAt(4, newColumn) == null)
				return true;
		if(!isWhite && row == 1 && newRow == 3 && newColumn == column)
			if(board.getPieceAt(2, newColumn) == null && board.getPieceAt(3, newColumn) == null)
				return true;

		return false;
	}

}
