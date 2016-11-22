package pieces;

import board.*;

/**
 * an object that represents a pawn
 * 
 * @author JeremiahDeGreeff
 */
public class Pawn extends Piece{
	
	/**
	 * @param row integer between 0 and 7 based on coordinate 8 - 1
	 * @param column integer between 0 and 7 based on coordinate a - h
	 * @param isWhite true if white, false if black
	 * @param board board to be associated with this piece
	 */
	public Pawn(int row, int column, boolean isWhite, Board board)
	{
		super(row, column, isWhite, board);
	}
	
	
	/**
	 * @param newRow number between 0 and 7 based on coordinate 8 - 1
	 * @param newColumn number between 0 and 7 based on coordinate a - h
	 * @return true if valid to move this piece to [newRow][newCol], false otherwise
	 */
	public boolean isValid(int newRow, int newColumn)
	{
		if((isWhite() && newRow - getRow() == -1) || (!isWhite() && newRow - getRow() == 1))
		{
			if(newColumn == getColumn() && getBoard().getPiece(newRow, newColumn) == null)
				return true; //to empty square directly ahead
			if(Math.abs(newColumn - getColumn()) == 1 && getBoard().getPiece(newRow, newColumn) != null && getBoard().getPiece(newRow, newColumn).isWhite() != isWhite())
				return true;  //take opponent in diagonal square
		}
		
		if(isWhite() && getRow() == 6 && newRow == 4 && newColumn == getColumn())
			if(getBoard().getPiece(5, newColumn) == null && getBoard().getPiece(4, newColumn) == null)
				return true; //forward two empty squares as initial move
		if(!isWhite() && getRow() == 1 && newRow == 3 && newColumn == getColumn())
			if(getBoard().getPiece(2, newColumn) == null && getBoard().getPiece(3, newColumn) == null)
				return true; //forward two empty squares as initial move
		
		return false;
	}
}
