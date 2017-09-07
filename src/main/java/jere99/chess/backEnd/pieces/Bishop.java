package jere99.chess.backEnd.pieces;

import jere99.chess.backEnd.Board;

/**
 * Represents a bishop.
 * 
 * @author JeremiahDeGreeff
 */
public class Bishop extends Piece {
	
	/**
	 * @param row integer between 0 and 7 based on coordinate 8 - 1
	 * @param column integer between 0 and 7 based on coordinate a - h
	 * @param isWhite true if white, false if black
	 * @param board board to be associated with this piece
	 */
	public Bishop(int row, int column, boolean isWhite, Board board) {
		super(row, column, isWhite, board);
	}
	
	
	/**
	 * Tests if a move is valid for a bishop.
	 * 
	 * @param newRow [0, 7] based on coordinate [8, 1]
	 * @param newColumn [0, 7] based on coordinate [a, h]
	 * @return true if valid to move this piece to the new position, false otherwise
	 */
	public boolean isValid(int newRow, int newColumn) {
		if(Math.abs(newRow - row) == Math.abs(newColumn - column)) //on diagonal line
			if(board.getPieceAt(newRow, newColumn) == null || board.getPieceAt(newRow, newColumn).isWhite != this.isWhite) {
				for(int i = 1; i < Math.abs(newRow - row); i++)
					if(board.getPieceAt(i * (int)Math.signum(newRow - row) + row, i * (int)Math.signum(newColumn - column) + column) != null)
						return false;
				return true;
			}
		return false;
	}
}
