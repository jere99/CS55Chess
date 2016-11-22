package pieces;

import board.*;

/**
 * an object that represents a king
 * 
 * @author JeremiahDeGreeff
 */
public class King extends Piece{

	/**
	 * false if has not moved and able to castle, true otherwise
	 */
	private boolean hasMoved = false;
	
	/**
	 * @param row integer between 0 and 7 based on coordinate 8 - 1
	 * @param column integer between 0 and 7 based on coordinate a - h
	 * @param isWhite true if white, false if black
	 * @param board board to be associated with this piece
	 */
	public King(int row, int column, boolean isWhite, Board board)
	{
		super(row, column, isWhite, board);
	}
	
	
	/**
	 * @return current column between 0 and 7 based on coordinate a - h
	 */
	public boolean hasMoved()
	{
		return hasMoved;
	}
	
	/**
	 * to be called if the king moves and can no longer castle
	 */
	public void kingMove()
	{
		hasMoved = true;
	}
	
	/**
	 * @param newRow number between 0 and 7 based on coordinate 8 - 1
	 * @param newColumn number between 0 and 7 based on coordinate a - h
	 * @return true if valid to move this piece to [newRow][newCol], false otherwise
	 */
	public boolean isValid(int newRow, int newColumn)
	{
		if(getBoard().getPiece(newRow, newColumn) == null || getBoard().getPiece(newRow, newColumn).isWhite() != isWhite())
			if(Math.abs(newRow - getRow()) <= 1 && Math.abs(newColumn - getColumn()) <= 1)
				return true;
		if(!hasMoved && getRow() == newRow && !getBoard().kingChecked(this)) //castling
		{
			if(newColumn == 6 && getBoard().getPiece(newRow, 7) instanceof Rook && ((Rook) getBoard().getPiece(newRow, 7)).hasMoved() == false) //castle into column 6
				if(getBoard().testMove(this, newRow, 5) && getBoard().getPiece(newRow, 5) == null && getBoard().getPiece(newRow, 6) == null)
					return true;
			if(newColumn == 2 && getBoard().getPiece(newRow, 0) instanceof Rook && ((Rook) getBoard().getPiece(newRow, 0)).hasMoved() == false) //castle into column 2
				if(getBoard().testMove(this, newRow, 3) && getBoard().getPiece(newRow, 3) == null && getBoard().getPiece(newRow, 2) == null && getBoard().getPiece(newRow, 1) == null)
					return true;
		}
		return false;
	}
}
