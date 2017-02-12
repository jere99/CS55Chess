package main.java.com.jere99.chess.backEnd;

/**
 * a class that is the template for a chess piece
 * 
 * @author JeremiahDeGreeff
 */
public abstract class Piece {
	/**
	 * the type of piece this object represents
	 */
	private final String TYPE;
	/**
	 * true if white, false if black
	 */
	private final boolean IS_WHITE;
	/**
	 * the board associated with this piece
	 */
	private final Board BOARD;
	/**
	 * coordinate 8 - 1 of this piece expressed as 0 - 7
	 */
	private int row;
	/**
	 * coordinate a - h of this piece expressed as 0 - 7
	 */
	private int column;
	
	
	/**
	 * @param row integer between 0 and 7 based on coordinate 8 - 1
	 * @param column integer between 0 and 7 based on coordinate a - h
	 * @param isWhite true if white, false if black
	 * @param board board to be associated with this piece
	 * @param type the type of this object represents
	 */
	public Piece(int row, int column, boolean isWhite, Board board, String type) {
		this.row = row;
		this.column = column;
		IS_WHITE = isWhite;
		BOARD = board;
		TYPE = type;
	}
	
	
	/**
	 * @return current row between 0 and 7 based on coordinate 8 - 1
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * @return current column between 0 and 7 based on coordinate a - h
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * @return true if this piece is white, false if black
	 */
	public boolean isWhite() {
		return IS_WHITE;
	}
	
	/**
	 * @return the board associated with this piece
	 */
	public Board getBoard() {
		return BOARD;
	}
	
	/**
	 * @return a string which represents the piece's color and type
	 */
	public String getPieceID() {
		if(IS_WHITE)
			return "w" + TYPE;
		else
			return "b" + TYPE;
	}
	
	/**
	 * @param row new row value between 0 and 7 based on coordinate 8 - 1
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * @param column new column value between 0 and 7 based on coordinate a - h
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
	/**
	 * Moves the piece to the passed location if it is a valid move
	 * @param newRow number between 0 and 7 based on coordinate 8 - 1
	 * @param newColumn number between 0 and 7 based on coordinate a - h
	 * @return true if move completed, false otherwise
	 */
	public boolean move(int newRow, int newColumn) {
		//If the move is valid
		if(BOARD.testMove(this, newRow, newColumn)) {
			//Then perform the move
			BOARD.makeMove(this, newRow, newColumn);
			return true;
		}
		return false;
	}
	
	/**
	 * Tests if a move is valid for the particular type of piece
	 * @param newRow number between 0 and 7 based on coordinate 8 - 1
	 * @param newColumn number between 0 and 7 based on coordinate a - h
	 * @return true if valid to move this piece to [newRow][newCol], false otherwise
	 */
	public abstract boolean isValid(int newRow, int newColumn);
}
