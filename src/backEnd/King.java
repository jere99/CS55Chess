package backEnd;

/**
 * an object that represents a king
 * 
 * @author JeremiahDeGreeff
 */
public class King extends Piece {
	/**
	 * the type of pieces this class represents
	 */
	private static final String CLASS_TYPE = "King";
	
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
	public King(int row, int column, boolean isWhite, Board board) {
		super(row, column, isWhite, board, CLASS_TYPE);
	}
	
	
	/**
	 * @return current column between 0 and 7 based on coordinate a - h
	 */
	public boolean hasMoved() {
		return hasMoved;
	}
	
	/**
	 * to be called if the king moves and can no longer castle
	 */
	public void kingMove() {
		hasMoved = true;
	}
	
	/**
	 * Tests if a move is valid for the particular type of piece
	 * @param newRow number between 0 and 7 based on coordinate 8 - 1
	 * @param newColumn number between 0 and 7 based on coordinate a - h
	 * @return true if valid to move this piece to [newRow][newCol], false otherwise
	 */
	@Override
	public boolean isValid(int newRow, int newColumn) {
		//regular move
		if(getBoard().getPieceAt(newRow, newColumn) == null || getBoard().getPieceAt(newRow, newColumn).isWhite() != isWhite())
			if(Math.abs(newRow - getRow()) <= 1 && Math.abs(newColumn - getColumn()) <= 1)
				return true;
		//castle
		if(!hasMoved && getRow() == newRow && !getBoard().kingChecked(this)) {
			//castle into column 6
			if(newColumn == 6 && getBoard().getPieceAt(newRow, 7) instanceof Rook && ((Rook) getBoard().getPieceAt(newRow, 7)).hasMoved() == false)
				if(getBoard().testMove(this, newRow, 5) && getBoard().getPieceAt(newRow, 5) == null && getBoard().getPieceAt(newRow, 6) == null)
					return true;
			//castle into column 2
			if(newColumn == 2 && getBoard().getPieceAt(newRow, 0) instanceof Rook && ((Rook) getBoard().getPieceAt(newRow, 0)).hasMoved() == false)
				if(getBoard().testMove(this, newRow, 3) && getBoard().getPieceAt(newRow, 3) == null && getBoard().getPieceAt(newRow, 2) == null && getBoard().getPieceAt(newRow, 1) == null)
					return true;
		}
		return false;
	}
}
