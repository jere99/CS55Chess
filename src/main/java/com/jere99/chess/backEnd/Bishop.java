package main.java.com.jere99.chess.backEnd;

/**
 * an object that represents a bishop
 * 
 * @author JeremiahDeGreeff
 */
public class Bishop extends Piece {
	/**
	 * the type of pieces this class represents
	 */
	private static final String CLASS_TYPE = "Bishop";
	
	/**
	 * @param row integer between 0 and 7 based on coordinate 8 - 1
	 * @param column integer between 0 and 7 based on coordinate a - h
	 * @param isWhite true if white, false if black
	 * @param board board to be associated with this piece
	 */
	public Bishop(int row, int column, boolean isWhite, Board board) {
		super(row, column, isWhite, board, CLASS_TYPE);
	}
	
	
	/**
	 * @param newRow number between 0 and 7 based on coordinate 8 - 1
	 * @param newColumn number between 0 and 7 based on coordinate a - h
	 * @return true if valid to move this piece to [newRow][newCol], false otherwise
	 */
	public boolean isValid(int newRow, int newColumn) {	
		if(Math.abs(newRow - getRow()) == Math.abs(newColumn - getColumn())) //on diagonal line
			if(getBoard().getPieceAt(newRow, newColumn) == null || getBoard().getPieceAt(newRow, newColumn).isWhite() != isWhite()) {
				for(int i = 1; i < Math.abs(newRow - getRow()); i++)
					if(getBoard().getPieceAt(i * (int)Math.signum(newRow - getRow()) + getRow(), i * (int)Math.signum(newColumn - getColumn()) + getColumn()) != null)
						return false;
				return true;
			}
		return false;
	}
}
