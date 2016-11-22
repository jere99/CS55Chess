package pieces;

import board.*;

/**
 * an object that represents a knight
 * 
 * @author JeremiahDeGreeff
 */
public class Knight extends Piece{

	/**
	 * @param row integer between 0 and 7 based on coordinate 8 - 1
	 * @param column integer between 0 and 7 based on coordinate a - h
	 * @param isWhite true if white, false if black
	 * @param board board to be associated with this piece
	 */
	public Knight(int row, int column, boolean isWhite, Board board)
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
		if(getBoard().getPiece(newRow, newColumn) == null || getBoard().getPiece(newRow, newColumn).isWhite() != this.isWhite()) //empty square or opponent
		{
			if(Math.abs(newRow - getRow()) == 2 && Math.abs(newColumn - getColumn()) == 1) //right or left 1, up or down 2
				return true;
			if(Math.abs(newRow - getRow()) == 1 && Math.abs(newColumn - getColumn()) == 2) //right or left 2, up or down 1
				return true;
		}
		return false;
		
	}
}
