package pieces;

import board.*;

/**
 *	an object that represents a queen
 *
 * @author JeremiahDeGreeff
 */
public class Queen extends Piece{

	/**
	 * @param row integer between 0 and 7 based on coordinate 8 - 1
	 * @param column integer between 0 and 7 based on coordinate a - h
	 * @param isWhite true if white, false if black
	 * @param board board to be associated with this piece
	 */
	public Queen(int row, int column, boolean isWhite, Board board)
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
		if(newRow == getRow() && (getBoard().getPiece(newRow, newColumn) == null || getBoard().getPiece(newRow, newColumn).isWhite() != isWhite())) //same row
		{
			for(int c = getColumn() + (int) Math.signum(newColumn - getColumn()); c != newColumn; c += (int) Math.signum(newColumn - getColumn()))
				if(getBoard().getPiece(newRow, c) != null)
					return false;
			return true;
		}
		if(newColumn == getColumn() && (getBoard().getPiece(newRow, newColumn) == null || getBoard().getPiece(newRow, newColumn).isWhite() != isWhite())) //same column
		{
			for(int r = getRow() + (int) Math.signum(newRow - getRow()); r != newRow; r += (int) Math.signum(newRow - getRow()))
				if(getBoard().getPiece(r, newColumn) != null)
					return false;
			return true;
		}
		if(Math.abs(newRow - getRow()) == Math.abs(newColumn - getColumn())) //on diagonal line
			if(getBoard().getPiece(newRow, newColumn) == null || getBoard().getPiece(newRow, newColumn).isWhite() != isWhite())
			{
				for(int i = 1; i < Math.abs(newRow - getRow()); i++)
					if(getBoard().getPiece(i * (int)Math.signum(newRow - getRow()) + getRow(), i * (int)Math.signum(newColumn - getColumn()) + getColumn()) != null)
						return false;
				return true;
			}
		return false;
	}
}
