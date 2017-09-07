package jere99.chess.backEnd.pieces;

import jere99.chess.backEnd.Board;

/**
 * Represents a knight.
 * 
 * @author JeremiahDeGreeff
 */
public class Knight extends Piece {

	/**
	 * @param row integer between 0 and 7 based on coordinate 8 - 1
	 * @param column integer between 0 and 7 based on coordinate a - h
	 * @param isWhite true if white, false if black
	 * @param board board to be associated with this piece
	 */
	public Knight(int row, int column, boolean isWhite, Board board){
		super(row, column, isWhite, board);
	}

	/**
	 * Tests if a move is valid for a Knight.
	 * 
	 * @param newRow [0, 7] based on coordinate [8, 1]
	 * @param newColumn [0, 7] based on coordinate [a, h]
	 * @return true if valid to move this piece to the new position, false otherwise
	 */
	@Override
	public boolean isValid(int newRow, int newColumn) {
		//empty square or opponent
		if(board.getPieceAt(newRow, newColumn) == null || board.getPieceAt(newRow, newColumn).isWhite != this.isWhite) {
			//right or left 1, up or down 2
			if(Math.abs(newRow - row) == 2 && Math.abs(newColumn - column) == 1)
				return true;
			//right or left 2, up or down 1
			if(Math.abs(newRow - row) == 1 && Math.abs(newColumn - column) == 2)
				return true;
		}
		return false;	
	}

}
