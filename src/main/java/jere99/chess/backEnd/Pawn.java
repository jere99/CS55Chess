package main.java.jere99.chess.backEnd;

/**
 * an object that represents a pawn
 * 
 * @author JeremiahDeGreeff
 */
public class Pawn extends Piece {
	/**
	 * the type of pieces this class represents
	 */
	private static final String CLASS_TYPE = "Pawn";
	
	/**
	 * @param row integer between 0 and 7 based on coordinate 8 - 1
	 * @param column integer between 0 and 7 based on coordinate a - h
	 * @param isWhite true if white, false if black
	 * @param board board to be associated with this piece
	 */
	public Pawn(int row, int column, boolean isWhite, Board board) {
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
		//forward one square
		if((isWhite() && newRow - getRow() == -1) || (!isWhite() && newRow - getRow() == 1)) {
			//to empty square directly ahead
			if(newColumn == getColumn() && getBoard().getPieceAt(newRow, newColumn) == null)
				return true;
			//take opponent in diagonal square
			if(Math.abs(newColumn - getColumn()) == 1 && getBoard().getPieceAt(newRow, newColumn) != null && getBoard().getPieceAt(newRow, newColumn).isWhite() != isWhite())
				return true;
		}
		
		//forward two empty squares as initial move
		if(isWhite() && getRow() == 6 && newRow == 4 && newColumn == getColumn())
			if(getBoard().getPieceAt(5, newColumn) == null && getBoard().getPieceAt(4, newColumn) == null)
				return true;
		if(!isWhite() && getRow() == 1 && newRow == 3 && newColumn == getColumn())
			if(getBoard().getPieceAt(2, newColumn) == null && getBoard().getPieceAt(3, newColumn) == null)
				return true;
		
		return false;
	}
}
