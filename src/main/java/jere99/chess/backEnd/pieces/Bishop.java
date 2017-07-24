package jere99.chess.backEnd.pieces;

import jere99.chess.backEnd.Board;

/**
 * an object that represents a bishop
 * 
 * @author JeremiahDeGreeff
 */
public class Bishop extends Piece {
	/**
	 * the type of pieces this class represents
	 */
	private static final String STATIC_TYPE = "Bishop";
	
	/**
	 * @param row integer between 0 and 7 based on coordinate 8 - 1
	 * @param column integer between 0 and 7 based on coordinate a - h
	 * @param isWhite true if white, false if black
	 * @param board board to be associated with this piece
	 */
	public Bishop(int row, int column, boolean isWhite, Board board) {
		super(row, column, isWhite, board, STATIC_TYPE);
	}
	
	
	/**
	 * @param newRow number between 0 and 7 based on coordinate 8 - 1
	 * @param newColumn number between 0 and 7 based on coordinate a - h
	 * @return true if valid to move this piece to [newRow][newCol], false otherwise
	 */
	public boolean isValid(int newRow, int newColumn) {
		if(Math.abs(newRow - row) == Math.abs(newColumn - column)) //on diagonal line
			if(BOARD.getPieceAt(newRow, newColumn) == null || BOARD.getPieceAt(newRow, newColumn).IS_WHITE != this.IS_WHITE) {
				for(int i = 1; i < Math.abs(newRow - row); i++)
					if(BOARD.getPieceAt(i * (int)Math.signum(newRow - row) + row, i * (int)Math.signum(newColumn - column) + column) != null)
						return false;
				return true;
			}
		return false;
	}
}