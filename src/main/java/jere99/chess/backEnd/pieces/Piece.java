package jere99.chess.backEnd.pieces;

import java.lang.reflect.InvocationTargetException;

import jere99.chess.backEnd.Board;

/**
 * a class that is the template for a chess piece
 * 
 * @author JeremiahDeGreeff
 */
public abstract class Piece {

	/**
	 * True if white, false if black.
	 */
	protected final boolean isWhite;
	/**
	 * The board associated with this piece.
	 */
	protected final Board board;
	/**
	 * Coordinate 8 - 1 of this piece expressed as 0 - 7.
	 */
	protected int row;
	/**
	 * Coordinate a - h of this piece expressed as 0 - 7.
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
	 * Tests if a move is valid for the particular type of piece.
	 * 
	 * @param newRow [0, 7] based on coordinate [8, 1]
	 * @param newColumn [0, 7] based on coordinate [a, h]
	 * @return true if valid to move this piece to the new position, false otherwise
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
	 * Changes the row of this piece.
	 * 
	 * @param row new row value between 0 and 7 based on coordinate 8 - 1
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * Changes the column of this piece.
	 * 
	 * @param column new column value between 0 and 7 based on coordinate a - h
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
	/**
	 * Creates a copy of this Piece which is on a different Board.
	 * Intended to be used only when cloning a Game object.
	 * 
	 * @param newBoard the Board which the new piece is on
	 * @return the new Piece
	 */
	public Piece copyPiece(Board newBoard) {
		try {return getClass().getConstructor(int.class, int.class, boolean.class, Board.class).newInstance(row, column, isWhite, newBoard);}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Moves the piece to the passed location if it is a valid move.
	 * 
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
