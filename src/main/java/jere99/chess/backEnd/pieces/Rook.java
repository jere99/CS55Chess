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
		if(newRow == row && (board.getPieceAt(newRow, newColumn) == null || board.getPieceAt(newRow, newColumn).isWhite != this.isWhite)) {
			for(int c = column + (int) Math.signum(newColumn - column); c != newColumn; c += (int) Math.signum(newColumn - column))
				if(board.getPieceAt(newRow, c) != null)
					return false;
			return true;
		}
		//same column
		if(newColumn == column && (board.getPieceAt(newRow, newColumn) == null || board.getPieceAt(newRow, newColumn).isWhite != this.isWhite)) {
			for(int r = row + (int) Math.signum(newRow - row); r != newRow; r += (int) Math.signum(newRow - row))
				if(board.getPieceAt(r, newColumn) != null)
					return false;
			return true;
		}
		return false;
	}
}
