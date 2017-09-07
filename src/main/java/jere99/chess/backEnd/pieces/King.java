package jere99.chess.backEnd.pieces;

import jere99.chess.backEnd.Board;

/**
 * Represents a king.
 * 
 * @author JeremiahDeGreeff
 */
public class King extends Piece {
	
	/**
	 * True if the King has moved and thus cannot castle, false otherwise.
	 */
	private boolean hasMoved = false;

	/**
	 * @param row integer between 0 and 7 based on coordinate 8 - 1
	 * @param column integer between 0 and 7 based on coordinate a - h
	 * @param isWhite true if white, false if black
	 * @param board board to be associated with this piece
	 */
	public King(int row, int column, boolean isWhite, Board board) {
		super(row, column, isWhite, board);
	}

	/**
	 * @return current column between 0 and 7 based on coordinate a - h
	 */
	public boolean hasMoved() {
		return hasMoved;
	}

	/**
	 * Should be called when the king moves and thus can no longer castle.
	 */
	public void kingMove() {
		hasMoved = true;
	}

	/**
	 * Tests if a move is valid for a King.
	 * 
	 * @param newRow [0, 7] based on coordinate [8, 1]
	 * @param newColumn [0, 7] based on coordinate [a, h]
	 * @return true if valid to move this piece to the new position, false otherwise
	 */
	@Override
	public boolean isValid(int newRow, int newColumn) {
		//regular move
		if(board.getPieceAt(newRow, newColumn) == null || board.getPieceAt(newRow, newColumn).isWhite != this.isWhite)
			if(Math.abs(newRow - row) <= 1 && Math.abs(newColumn - column) <= 1)
				return true;
		//castle
		if(!hasMoved && row == newRow && !board.kingChecked(this)) {
			//castle into column 6
			if(newColumn == 6 && board.getPieceAt(newRow, 7) instanceof Rook && ((Rook) board.getPieceAt(newRow, 7)).hasMoved() == false)
				if(board.testMove(this, newRow, 5) && board.getPieceAt(newRow, 5) == null && board.getPieceAt(newRow, 6) == null)
					return true;
			//castle into column 2
			if(newColumn == 2 && board.getPieceAt(newRow, 0) instanceof Rook && ((Rook) board.getPieceAt(newRow, 0)).hasMoved() == false)
				if(board.testMove(this, newRow, 3) && board.getPieceAt(newRow, 3) == null && board.getPieceAt(newRow, 2) == null && board.getPieceAt(newRow, 1) == null)
					return true;
		}
		return false;
	}
	
}
