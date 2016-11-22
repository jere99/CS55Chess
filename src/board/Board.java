/**
 * 
 */
package board;
import pieces.*;
import gui.*;

/**
 * @author Megha
 *
 * an object that represents a chess board
 */

public class Board {
	/**
	 * 2D-array that holds all of the pieces in the appropriate locations
	 */
	private Piece[][] board;
	/**
	 * whose turn it is currently: true if white, false if black
	 */
	private boolean isWhiteTurn = true;
	/**
	 * Alias of the white king to make locating easier
	 */
	private Piece wKing;
	/**
	 * Alias of the black king to make locating easier
	 */
	private Piece bKing;
	
	/**
	 * creates a new Board object and initializes all pieces
	 * creates a BoardGUI to represent the board
	 */
	public Board() {
		
		board = new Piece[8][8];
		
		// top is black...
		board[0][0] = new Rook(0,0,false, this);
		board[0][1] = new Knight(0,1,false, this);
		board[0][2] = new Bishop(0,2,false, this);
		board[0][3] = new Queen(0,3,false, this);
		board[0][4] = new King(0,4,false, this);
		board[0][5] = new Bishop(0,5,false, this);
		board[0][6] = new Knight(0,6,false, this);
		board[0][7] = new Rook(0,7,false, this);
		for (int i = 0; i < board[1].length; i++)
			board[1][i] = new Pawn(1,i,false, this);
		//...and bottom is white
		board[7][0] = new Rook(7,0,true, this);
		board[7][1] = new Knight(7,1,true, this);
		board[7][2] = new Bishop(7,2,true, this);
		board[7][3] = new Queen(7,3,true, this);
		board[7][4] = new King(7,4,true, this);
		board[7][5] = new Bishop(7,5,true, this);
		board[7][6] = new Knight(7,6,true, this);
		board[7][7] = new Rook(7,7,true, this);
		for (int i = 0; i < board[6].length; i++)
			board[6][i] = new Pawn(6,i,true, this);
		
		bKing = board[0][4];
		wKing = board[7][4];
		
		new BoardGUI(this); //creates a GUI to represent this board
	}
	
	/**
	 * @param row row of wanted Piece
	 * @param col column of wanted Piece
	 * @return Piece at board[row][col]
	 */
	public Piece getPiece (int row, int col) {
		return board[row][col];
	}
	
	/**
	 * Changes turn to other player
	 */
	public void turnOver() {
		isWhiteTurn = !isWhiteTurn;
		if(isWhiteTurn)
			System.out.println("\nWhite's turn:");
		else
			System.out.println("\nBlack's turn:");
	}
	
	/**
	 * @return true if it is white's turn, false if it is black's turn
	 */
	public boolean isWhiteTurn()
	{
		return isWhiteTurn;
	}
	
	/**
	 * @return the object representing the white king
	 */
	public Piece getWKing()
	{
		return wKing;
	}
	
	/**
	 * @return the object representing the black king
	 */
	public Piece getBKing()
	{
		return bKing;
	}
	
	/**
	 * tests if king is in check
	 * 
	 * @param king king to test
	 * @return true if king is checked, false otherwise
	 */
	public boolean kingChecked (Piece king) {
		for (Piece[] row : board) {
			for (Piece p : row) {
				if (p != null && king.isWhite() != p.isWhite()) {
					//System.out.println("Testing if valid to move from (" + p.getRow() + ", " + p.getColumn() + ") to (" + king.getRow() + ", " + king.getColumn() + ")");
					if (p.isValid(king.getRow(), king.getColumn())) {
						return true; } } } }
		return false;
	}
	
	/**
	 * Tests if king is in checkmate
	 * 
	 * @param king king to test if in checkmate
	 * @return true if king is in checkmate, false otherwise
	 */
	public boolean checkmate (Piece king) {
		//tests if king can move
		for (int r = king.getRow() - 1; r < king.getRow() + 1; r++) {
			for (int c = king.getColumn() - 1; c < king.getColumn() + 1; c++) {
				if (r >= 0 && r <= 7 && c >= 0 && c <= 7 && testMove(king, r, c)) {
					return false; } } }
		
		Piece temp = null;
		//find Piece checking king and set temp to Piece
		for (Piece[] row : board) {
			for (Piece p : row) {
				if (p != null && p.isWhite() != king.isWhite() && testMove(p, king.getRow(), king.getColumn())) {
					if (temp == null) temp = p;
					else  return true; } } }
		
		//find out if temp can be taken
		for (Piece[] row : board) {
			for (Piece p : row) {
				if (p != null && p.isWhite() != temp.isWhite() && testMove(p, temp.getRow(), temp.getColumn())) return false; } }
		
		//find out if temp can be blocked
		if (!(temp instanceof Rook || temp instanceof Queen || temp instanceof Bishop)) return true;
		
		int row = temp.getRow();
		int col = temp.getColumn();
		//if temp is Rook (or Queen)...
		if (temp instanceof Rook || temp instanceof Queen) {
			if (king.getRow() == row) { //same row
				for (int c = col; c < king.getColumn(); c++) {
					for(Piece[] boardRow : board)
						for(Piece p : boardRow)
							if (p != null && p.isWhite() != temp.isWhite() && testMove(p, row, c)) return false; }
				for (int c = col; c > king.getColumn(); c--) {
					for(Piece[] boardRow : board)
						for(Piece p : boardRow)
							if (p != null && p.isWhite() != temp.isWhite() && testMove(p, row, c)) return false; }
			} else if (king.getColumn() > col) { //same column
				for (int r = row; r < king.getRow(); r++) {
					for(Piece[] boardRow : board)
						for(Piece p : boardRow)
							if (p != null && p.isWhite() != temp.isWhite() && testMove(p, r, col)) return false; }
				for (int r = row; r > king.getRow(); r--) {
					for(Piece[] boardRow : board)
						for(Piece p : boardRow)
							if (p != null && p.isWhite() != temp.isWhite() && testMove(p, r, col)) return false; } }
		}
		
		//if temp is Bishop (or Queen)...
		if (temp instanceof Bishop || temp instanceof Queen)
			for (Piece[] boardRow : board)
				for (Piece p : boardRow)
					if(p != null && p.isWhite() != temp.isWhite())
						for(int i = 1; i < Math.abs(row - king.getRow()); i++)
							if(testMove(p, i * (int)Math.signum(king.getRow() - row) + row, i * (int)Math.signum(king.getColumn() - col) + col)) return false;
		
		//by this point, king cannot move & Piece attacking cannot be taken or blocked
		return true;
	}
	
	/**
	 * Tests if moving p to board[row][col] is valid and does not check own king
	 * Changes everything back to how it was before call
	 * 
	 * @param p Piece to test move
	 * @param row row to move p to
	 * @param col column to move p to
	 * @return true if move is valid and will not check king, false otherwise
	 */
	public boolean testMove (Piece p, int row, int col) {
		if (p.isValid(row, col)) {
			int tempRow = p.getRow();
			int tempCol = p.getColumn();
			Piece temp = movePiece(p, row, col);
			if ((p.isWhite() && kingChecked(wKing)) || (!p.isWhite() && kingChecked(bKing))) {
				movePiece(p, tempRow, tempCol);
				board[row][col] = temp;
				return false; }
			else{
				movePiece(p, tempRow, tempCol);
				board[row][col] = temp;
				return true; }
		}
		return false;
	}
	
	/**
	 * Moves p to board[row][col]
	 * 
	 * @param p Piece to move
	 * @param row row to move p to
	 * @param col column to move p to
	 * @return Piece captured (null if no Piece)
	 */
	public Piece movePiece (Piece p, int row, int col) {
		Piece captured = null;
		if(board[row][col] != null) captured = board[row][col];
		board[row][col] = p;
		board[p.getRow()][p.getColumn()] = null;
		p.setRow(row);
		p.setColumn(col);
		return captured;
	}
	
	/**
	 * Changes pawn that reached far row to new Piece of player's choice
	 * Tests if promotion puts king in check
	 * 
	 * @param row row of pawn to change
	 * @param col column of pawn to change
	 * @param type name of the desired piece
	 * @param gui the GUI which this method needs to affect
	 */
	public void pawnChange (int row, int col, String type, BoardGUI gui) {
		boolean pawnIsWhite = board[row][col].isWhite();
		if(type.equals("Queen"))
			board[row][col] = new Queen(row, col, pawnIsWhite, this);
		else if(type.equals("Knight"))
			board[row][col] = new Knight(row, col, pawnIsWhite, this);
		else if(type.equals("Rook"))
			board[row][col] = new Rook(row, col, pawnIsWhite, this);
		else if(type.equals("Bishop"))
			board[row][col] = new Bishop(row, col, pawnIsWhite, this);
		
		gui.getButton(row, col).setIcon(Button.stringToIcon(type, pawnIsWhite));
		gui.getButton(row, col).detectCheck();
	}
}
