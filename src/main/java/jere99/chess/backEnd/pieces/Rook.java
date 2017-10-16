package jere99.chess.backEnd.pieces;

import jere99.chess.backEnd.Board;

/**
 * Represents a rook.
 * 
 * @author JeremiahDeGreeff
 */
public class Rook extends Piece implements Castleable {
	
	/**
	 * True if the Rook has moved and thus cannot castle, false otherwise.
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
	
	@Override
	public boolean hasMoved() {
		return hasMoved;
	}
	
	@Override
	public void castleableMove() {
		hasMoved = true;
	}
	
	/**
	 * Tests if a move is valid for a rook.
	 * 
	 * @param newRow [0, 7] based on coordinate [8, 1]
	 * @param newColumn [0, 7] based on coordinate [a, h]
	 * @return true if valid to move this piece to the new position, false otherwise
	 */
	@Override
	public boolean isValid(int newRow, int newColumn) {
		if(board.getPieceAt(newRow, newColumn) == null || board.getPieceAt(newRow, newColumn).isWhite != this.isWhite) {
			//same row
			if(newRow == row) {
				for(int c = column + (int) Math.signum(newColumn - column); c != newColumn; c += (int) Math.signum(newColumn - column))
					if(board.getPieceAt(newRow, c) != null)
						return false;
				return true;
			}
			//same column
			if(newColumn == column) {
				for(int r = row + (int) Math.signum(newRow - row); r != newRow; r += (int) Math.signum(newRow - row))
					if(board.getPieceAt(r, newColumn) != null)
						return false;
				return true;
			}
		}
		return false;
	}
	
}
