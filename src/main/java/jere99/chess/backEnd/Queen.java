package jere99.chess.backEnd;

/**
 *	an object that represents a queen
 *
 * @author JeremiahDeGreeff
 */
public class Queen extends Piece {
	/**
	 * the type of pieces this class represents
	 */
	private static final String CLASS_TYPE = "Queen";
	
	/**
	 * @param row integer between 0 and 7 based on coordinate 8 - 1
	 * @param column integer between 0 and 7 based on coordinate a - h
	 * @param isWhite true if white, false if black
	 * @param board board to be associated with this piece
	 */
	public Queen(int row, int column, boolean isWhite, Board board) {
		super(row, column, isWhite, board, CLASS_TYPE);
	}
	
	
	/**
	 * Tests if a move is valid for the particular type of piece
	 * @param newRow number between 0 and 7 based on coordinate 8 - 1
	 * @param newColumn number between 0 and 7 based on coordinate a - h
	 * @return true if valid to move this piece to [newRow][newCol], false otherwise
	 */
	@Override
	public boolean isValid(int newRow, int newColumn) {	
		//same row
		if(newRow == getRow() && (getBoard().getPieceAt(newRow, newColumn) == null || getBoard().getPieceAt(newRow, newColumn).isWhite() != isWhite())) {
			for(int c = getColumn() + (int) Math.signum(newColumn - getColumn()); c != newColumn; c += (int) Math.signum(newColumn - getColumn()))
				if(getBoard().getPieceAt(newRow, c) != null)
					return false;
			return true;
		}
		
		//same column
		if(newColumn == getColumn() && (getBoard().getPieceAt(newRow, newColumn) == null || getBoard().getPieceAt(newRow, newColumn).isWhite() != isWhite())) {
			for(int r = getRow() + (int) Math.signum(newRow - getRow()); r != newRow; r += (int) Math.signum(newRow - getRow()))
				if(getBoard().getPieceAt(r, newColumn) != null)
					return false;
			return true;
		}
		
		//on diagonal line
		if(Math.abs(newRow - getRow()) == Math.abs(newColumn - getColumn()))
			if(getBoard().getPieceAt(newRow, newColumn) == null || getBoard().getPieceAt(newRow, newColumn).isWhite() != isWhite())
			{
				for(int i = 1; i < Math.abs(newRow - getRow()); i++)
					if(getBoard().getPieceAt(i * (int)Math.signum(newRow - getRow()) + getRow(), i * (int)Math.signum(newColumn - getColumn()) + getColumn()) != null)
						return false;
				return true;
			}
		return false;
	}
}
