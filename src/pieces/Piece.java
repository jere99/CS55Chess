package pieces;
import board.*;

/**
 * a class that is the template for a chess piece
 * 
 * @author JeremiahDeGreeff
 */
public abstract class Piece {
	
	/**
	 * coordinate 8 - 1 of this piece expressed as 0 - 7
	 */
	private int row;
	/**
	 * coordinate a - h of this piece expressed as 0 - 7
	 */
	private int column;
	/**
	 * true if white, false if black
	 */
	private boolean isWhite;
	/**
	 * the board associated with this piece
	 */
	private Board board;
	
	
	/**
	 * @param row integer between 0 and 7 based on coordinate 8 - 1
	 * @param column integer between 0 and 7 based on coordinate a - h
	 * @param isWhite true if white, false if black
	 * @param board board to be associated with this piece
	 */
	public Piece(int row, int column, boolean isWhite, Board board)
	{
		this.row = row;
		this.column = column;
		this.isWhite = isWhite;
		this.board = board;
	}
	
	
	/**
	 * @return current row between 0 and 7 based on coordinate 8 - 1
	 */
	public int getRow()
	{
		return row;
	}
	
	/**
	 * @return current column between 0 and 7 based on coordinate a - h
	 */
	public int getColumn()
	{
		return column;
	}
	
	/**
	 * @return true if this piece is white, false if black
	 */
	public boolean isWhite()
	{
		return isWhite;
	}
	
	/**
	 * @return the board associated with this piece
	 */
	public Board getBoard()
	{
		return board;
	}
	
	/**
	 * @param row new row value between 0 and 7 based on coordinate 8 - 1
	 */
	public void setRow(int row)
	{
		this.row = row;
	}
	
	/**
	 * @param column new column value between 0 and 7 based on coordinate a - h
	 */
	public void setColumn(int column)
	{
		this.column = column;
	}
	
	/**
	 * @param newRow number between 0 and 7 based on coordinate 8 - 1
	 * @param newColumn number between 0 and 7 based on coordinate a - h
	 * @return true if move completed, false otherwise
	 */
	public boolean move(int newRow, int newColumn)
	{
		if(newRow == row && newColumn == column)
		{
			return false;
		}
		if(board.testMove(this, newRow, newColumn))
		{
			Piece captured = board.movePiece(this, newRow, newColumn);
			if (captured != null)
				System.out.println("Piece Captured");
			return true;
		}
		return false;
	}
	
	/**
	 * @param newRow number between 0 and 7 based on coordinate 8 - 1
	 * @param newColumn number between 0 and 7 based on coordinate a - h
	 * @return true if valid to move this piece to [newRow][newCol], false otherwise
	 */
	public abstract boolean isValid(int newRow, int newColumn);
}
