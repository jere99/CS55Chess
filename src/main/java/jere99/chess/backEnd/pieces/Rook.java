package jere99.chess.backEnd.pieces;

import jere99.chess.backEnd.Board;

/**
 * an object that represents a rook
 * 
 * @author JeremiahDeGreeff
 */
public class Rook extends Piece {
	
	/**
	 * false if has not moved and able to castle, true otherwise
	 */
	private boolean hasMoved;
	
	/**
	 * @param row integer between 0 and 7 based on coordinate 8 - 1
	 * @param column integer between 0 and 7 based on coordinate a - h
	 * @param isWhite true if white, false if black
	 * @param board board to be associated with this piece
	 */
	public Rook(int row, int column, boolean isWhite, Board board) {
		super(row, column, isWhite, board);
		hasMoved = false;
	}
	
	
	/**
	 * @return value of hasMoved
	 */
	public boolean hasMoved() {
		return hasMoved;
	}
	
	/**
	 * to be called if the rook moves and can no longer castle
	 */
	public void rookMove() {
		hasMoved = true;
	}
	
	/**
	 * Tests if a move is valid for the particular type of piece
	 * @param newRow number between 0 and 7 based on coordinate 8 - 1
	 * @param newColumn number between 0 and 7 based on coordinate a - h
	 * @return true if valid to move this piece to [newRow][newColumn], false otherwise
	 */
	@Override
	public boolean isValid(int newRow, int newColumn) {
		//same row
		if(newRow == row && (BOARD.getPieceAt(newRow, newColumn) == null || BOARD.getPieceAt(newRow, newColumn).IS_WHITE != this.IS_WHITE)) {
			for(int c = column + (int) Math.signum(newColumn - column); c != newColumn; c += (int) Math.signum(newColumn - column))
				if(BOARD.getPieceAt(newRow, c) != null)
					return false;
			return true;
		}
		//same column
		if(newColumn == column && (BOARD.getPieceAt(newRow, newColumn) == null || BOARD.getPieceAt(newRow, newColumn).IS_WHITE != this.IS_WHITE)) {
			for(int r = row + (int) Math.signum(newRow - row); r != newRow; r += (int) Math.signum(newRow - row))
				if(BOARD.getPieceAt(r, newColumn) != null)
					return false;
			return true;
		}
		return false;
	}
}
