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
	 */
	public Piece(int row, int column, boolean isWhite, Board board) {
		this.row = row;
		this.column = column;
		this.isWhite = isWhite;
		this.board = board;
	}
	
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
	 * Tests if a move is valid for the particular type of piece.
	 * 
	 * @param newRow [0, 7] based on coordinate [8, 1]
	 * @param newColumn [0, 7] based on coordinate [a, h]
	 * @return true if valid to move this piece to the new position, false otherwise
	 */
	public abstract boolean isValid(int newRow, int newColumn);
	
	/**
	 * Creates a copy of this Piece which is on a different Board.
	 * Intended to be used only when cloning a Board object.
	 * 
	 * @param newBoard the Board which the new piece is on
	 * @return the new Piece
	 */
	public Piece copyPiece(Board newBoard) {
		try {return this.getClass().getConstructor(int.class, int.class, boolean.class, Board.class).newInstance(row, column, isWhite, newBoard);}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Changes the piece's internal coordinates to those which are passed.
	 * 
	 * @param newRow number between 0 and 7 based on coordinate 8 - 1
	 * @param newColumn number between 0 and 7 based on coordinate a - h
	 */
	public void move(int newRow, int newColumn) {
		row = newRow;
		column = newColumn;
		if(this instanceof Castleable)
			((Castleable)this).castleableMove();
	}
	
}
