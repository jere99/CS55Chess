package jere99.chess.backEnd.pieces;

import jere99.chess.backEnd.Board;

/**
 * a class that is the template for a chess piece
 * 
 * @author JeremiahDeGreeff
 */
public abstract class Piece {

	/**
	 * true if white, false if black
	 */
	protected final boolean isWhite;
	/**
	 * the board associated with this piece
	 */
	protected final Board board;
	/**
	 * coordinate 8 - 1 of this piece expressed as 0 - 7
	 */
	protected int row;
	/**
	 * coordinate a - h of this piece expressed as 0 - 7
	 */
	protected int column;
	
	
	/**
	 * @param row integer between 0 and 7 based on coordinate 8 - 1
	 * @param column integer between 0 and 7 based on coordinate a - h
	 * @param isWhite true if white, false if black
	 * @param board board to be associated with this piece
	 * @param type the type of this object represents
	 */
	public Piece(int row, int column, boolean isWhite, Board board) {
		this.row = row;
		this.column = column;
		this.isWhite = isWhite;
		this.board = board;
	}
	
	/**
	 * Tests if a move is valid for the particular type of piece
	 * @param newRow number between 0 and 7 based on coordinate 8 - 1
	 * @param newColumn number between 0 and 7 based on coordinate a - h
	 * @return true if valid to move this piece to [newRow][newCol], false otherwise
	 */
	public abstract boolean isValid(int newRow, int newColumn);
	
	/**
	 * @return current row between 0 and 7 based on coordinate 8 - 1
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * @return current column between 0 and 7 based on coordinate a - h
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * @return true if this piece is white, false if black
	 */
	public boolean isWhite() {
		return isWhite;
	}
	
	/**
	 * @param row new row value between 0 and 7 based on coordinate 8 - 1
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * @param column new column value between 0 and 7 based on coordinate a - h
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
	/**
	 * Moves the piece to the passed location if it is a valid move
	 * @param newRow number between 0 and 7 based on coordinate 8 - 1
	 * @param newColumn number between 0 and 7 based on coordinate a - h
	 * @return true if move completed, false otherwise
	 */
	public boolean move(int newRow, int newColumn) {
		//If the move is valid
		if(board.testMove(this, newRow, newColumn)) {
			//Then perform the move
			board.makeMove(this, newRow, newColumn);
			return true;
		}
		return false;
	}
	
}
