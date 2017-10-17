package jere99.chess.backEnd.pieces;

import jere99.chess.backEnd.Board;

/**
 * Represents a king.
 * 
 * @author JeremiahDeGreeff
 */
public class King extends Piece implements Castleable {
	
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

	@Override
	public boolean hasMoved() {
		return hasMoved;
	}
	
	@Override
	public void castleableMove() {
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
		if(!hasMoved && row == newRow && newColumn == 2 || newColumn == 6 && !board.kingChecked(this)) {
			boolean kingSide = newColumn == 6; //true if into column 6, false if into column 2
			if(board.getPieceAt(newRow, kingSide ? 7 : 0) instanceof Rook && ((Rook) board.getPieceAt(newRow, kingSide ? 7 : 0)).hasMoved() == false) {
				Board testBoard = board.clone();
				return testBoard.testMove(testBoard.getPieceAt(row, column), newRow, kingSide ? 5 : 3) && board.getPieceAt(newRow, kingSide ? 5 : 3) == null && board.getPieceAt(newRow, kingSide ? 6 : 2) == null && (kingSide || board.getPieceAt(newRow, 1) == null);
			}
		}
		return false;
	}
	
}
