package backEnd;

/**
 * an object that represents a rook
 * 
 * @author JeremiahDeGreeff
 */
public class Rook extends Piece{
	/**
	 * the type of pieces this class represents
	 */
	private static final String CLASS_TYPE = "Rook";
	
	/**
	 * false if has not moved and able to castle, true otherwise
	 */
	private boolean hasMoved;
	
	/**
	 * @param row integer between 0 and 7 based on coordinate 8 - 1
	 * @param column integer between 0 and 7 based on coordinate a - h
	 * @param isWhite true if white, false if black
	 * @param board board to be associated with this piece
	 */
	public Rook(int row, int column, boolean isWhite, Board board)
	{
		super(row, column, isWhite, board, CLASS_TYPE);
		hasMoved = false;
	}
	
	
	/**
	 * @return value of hasMoved
	 */
	public boolean hasMoved()
	{
		return hasMoved;
	}
	
	/**
	 * to be called if the rook moves and can no longer castle
	 */
	public void rookMove()
	{
		hasMoved = true;
	}
	
	/**
	 * @param newRow number between 0 and 7 based on coordinate 8 - 1
	 * @param newColumn number between 0 and 7 based on coordinate a - h
	 * @return true if valid to move this piece to [newRow][newColumn], false otherwise
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
		return false;
	}
}
