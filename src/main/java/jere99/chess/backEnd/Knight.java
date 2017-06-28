package main.java.jere99.chess.backEnd;

/**
 * an object that represents a knight
 * 
 * @author JeremiahDeGreeff
 */
public class Knight extends Piece {
	/**
	 * the type of pieces this class represents
	 */
	private static final String CLASS_TYPE = "Knight";
	
	/**
	 * @param row integer between 0 and 7 based on coordinate 8 - 1
	 * @param column integer between 0 and 7 based on coordinate a - h
	 * @param isWhite true if white, false if black
	 * @param board board to be associated with this piece
	 */
	public Knight(int row, int column, boolean isWhite, Board board){
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
		//empty square or opponent
		if(getBoard().getPieceAt(newRow, newColumn) == null || getBoard().getPieceAt(newRow, newColumn).isWhite() != this.isWhite()) {
			//right or left 1, up or down 2
			if(Math.abs(newRow - getRow()) == 2 && Math.abs(newColumn - getColumn()) == 1)
				return true;
			//right or left 2, up or down 1
			if(Math.abs(newRow - getRow()) == 1 && Math.abs(newColumn - getColumn()) == 2)
				return true;
		}
		return false;	
	}
}
