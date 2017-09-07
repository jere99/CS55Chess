package jere99.chess.backEnd;

import jere99.chess.backEnd.pieces.*;
import jere99.chess.reference.Pieces;

/**
 * an object that represents a chess board
 * 
 * @author Megha
 * @author JeremiahDeGreeff
 */

public class Board {
	
	/**
	 * the game which this board is a part of
	 */
	private final Game game;
	/**
	 * 2D-array that holds all of the pieces in the appropriate locations
	 */
	private final Piece[][] board = new Piece[8][8];;
	/**
	 * Alias of the white king to make locating easier
	 */
	private final King whiteKing;
	/**
	 * Alias of the black king to make locating easier
	 */
	private final King blackKing;
	
	/**
	 * creates a new Board object and initializes all pieces
	 * @param game the game which this board is a part of
	 */
	protected Board(Game game) {
		this.game = game;
		for(int row = 0; row < 8; row++) {
			if(row == 0 || row == 7) {
				board[row][0] = new Rook(row, 0, row == 7, this);
				board[row][1] = new Knight(row, 1, row == 7, this);
				board[row][2] = new Bishop(row, 2, row == 7, this);
				board[row][3] = new Queen(row, 3, row == 7, this);
				board[row][4] = new King(row, 4, row == 7, this);
				board[row][5] = new Bishop(row, 5, row == 7, this);
				board[row][6] = new Knight(row, 6, row == 7, this);
				board[row][7] = new Rook(row, 7, row == 7, this);
			}
			else if(row == 1 || row == 6)
				for (int column = 0; column < board[1].length; column++)
					board[row][column] = new Pawn(row, column, row == 6, this);
		}
		
		blackKing = (King)board[0][4];
		whiteKing = (King)board[7][4];
	}
	
	/**
	 * @param row the row of the wanted Piece
	 * @param column the column of the wanted Piece
	 * @return The piece at board[row][column]
	 */
	public Piece getPieceAt(int row, int column) {
		return board[row][column];
	}
	
	/**
	 * Copies the contents of a Board into this Board.
	 * Intended to be used only when cloning a Game object.
	 * 
	 * @param oldBoard the Board whose contents will be copied
	 */
	protected void copyBoard(Board oldBoard) {
		for(int row = 0; row < 8; row++)
			for(int column = 0; column < 8; column++) {
				Piece p = oldBoard.board[row][column];
				if(p != null)
					board[row][column] = p.copyPiece(this);
			}
	}
	
	/**
	 * Tests if moving p to board[newRow][newColumn] is valid and does not check own king
	 * Changes everything back to how it was before call
	 * @param p Piece to test move
	 * @param newRow row to move p to
	 * @param newColumn column to move p to
	 * @return true if move is valid and will not check own king, false otherwise
	 */
	public boolean testMove (Piece p, int newRow, int newColumn) {
		int tempRow = p.getRow(), tempCol = p.getColumn();
		if(!(tempRow == newRow && tempCol == newColumn) && p.isValid(newRow, newColumn)) {
			Piece temp = movePiece(p, newRow, newColumn);
			if((p.isWhite() && kingChecked(whiteKing)) || (!p.isWhite() && kingChecked(blackKing))) {
				movePiece(p, tempRow, tempCol);
				board[newRow][newColumn] = temp;
				return false;
			} else {
				movePiece(p, tempRow, tempCol);
				board[newRow][newColumn] = temp;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Moves p to board[newRow][newColumn]
	 * Updates the GUI accordingly
	 * Should only be run if the move has been tested
	 * @param p the piece to move
	 * @param newRow the row to move p to
	 * @param newColumn the column to move p to
	 */
	public void makeMove(Piece p, int newRow, int newColumn) {
		int startRow = p.getRow(), startColumn = p.getColumn();
		
		//move the piece
		movePiece(p, newRow, newColumn);
		
		//update the GUI
		game.updateSquare(startRow, startColumn);
		game.updateSquare(newRow, newColumn);
		
		//Prevent King or Rook from being able to castle in the future
		if(p instanceof King && !((King)p).hasMoved())
			((King)p).kingMove();
		if(p instanceof Rook && !((Rook)p).hasMoved())
			((Rook)p).rookMove();
		
		//If the move is a castle also moves the rook
		if(p instanceof King && startColumn == 4 && newColumn == 6)
			movePiece(board[newRow][7], newRow, 5);
		if(p instanceof King && startColumn == 4 && newColumn == 2)
			movePiece(board[newRow][0], newRow, 3);
		
		//Creates a GUI for pawn promotion
		if(p instanceof Pawn && (newRow == 0 || newRow == 7))
			game.pawnChangeInit(newRow, newColumn);
		
		//See if the move has put the opposing king in check or checkmate
		detectCheck(newRow, newColumn);
	}
	
	/**
	 * Moves p to board[newRow][newColumn]
	 * Should only be run when the move is valid according to piece rules
	 * @param p Piece to move
	 * @param newRow the row to move p to
	 * @param newColumn the column to move p to
	 * @return The piece that was captured (null if no piece was captured)
	 */
	private Piece movePiece(Piece p, int newRow, int newColumn) {
		Piece captured = board[newRow][newColumn];
		
		board[newRow][newColumn] = p;
		board[p.getRow()][p.getColumn()] = null;
		p.setRow(newRow);
		p.setColumn(newColumn);
		
		return captured;
	}
	
	/**
	 * Tests if a move to a particular row and column has resulted in check or checkmate for the opposing player
	 * @param row the row of the piece that just moved
	 * @param column the column of the piece that just moved
	 */
	public void detectCheck(int row, int column) {
		if(board[row][column].isWhite() && kingChecked(blackKing)) {
			System.out.println("The Black king is in check!");
			if(checkmate(blackKing))
				game.checkmateInit(true);
		}
		else if(!board[row][column].isWhite() && kingChecked(whiteKing)) {
			System.out.println("The White king is in check!");
			if(checkmate(whiteKing))
				game.checkmateInit(false);
		}
	}
	
	/**
	 * tests if king is in check
	 * @param king king to test
	 * @return true if king is checked, false otherwise
	 */
	public boolean kingChecked (King king) {
		for (Piece[] row : board)
			for (Piece p : row)
				if (p != null && king.isWhite() != p.isWhite())
					if (p.isValid(king.getRow(), king.getColumn()))
						return true;
		return false;
	}
	
	/**
	 * Tests if king is in checkmate
	 * @param king king to test if in checkmate
	 * @return true if king is in checkmate, false otherwise
	 */
	private boolean checkmate (King king) {
		//tests if king can move
		for (int r = king.getRow() - 1; r < king.getRow() + 1; r++)
			for (int c = king.getColumn() - 1; c < king.getColumn() + 1; c++)
				if (r >= 0 && r <= 7 && c >= 0 && c <= 7 && testMove(king, r, c))
					return false;
		
		Piece temp = null;
		//find Piece checking king and set temp to Piece
		for (Piece[] row : board)
			for (Piece p : row)
				if (p != null && p.isWhite() != king.isWhite() && testMove(p, king.getRow(), king.getColumn())) {
					if (temp == null)
						temp = p;
					else
						return true;
				}
		
		//find out if temp can be taken
		for (Piece[] row : board)
			for (Piece p : row)
				if (p != null && p.isWhite() != temp.isWhite() && testMove(p, temp.getRow(), temp.getColumn()))
					return false;
		
		//find out if temp can be blocked
		if (!(temp instanceof Rook || temp instanceof Queen || temp instanceof Bishop))
			return true;
		
		int row = temp.getRow();
		int col = temp.getColumn();
		//if temp is Rook (or Queen)...
		if (temp instanceof Rook || temp instanceof Queen)
			if (king.getRow() == row) { //same row
				for (int c = col; c < king.getColumn(); c++)
					for(Piece[] boardRow : board)
						for(Piece p : boardRow)
							if (p != null && p.isWhite() != temp.isWhite() && testMove(p, row, c))
								return false;
				for (int c = col; c > king.getColumn(); c--)
					for(Piece[] boardRow : board)
						for(Piece p : boardRow)
							if (p != null && p.isWhite() != temp.isWhite() && testMove(p, row, c))
								return false;
			} else if (king.getColumn() > col) { //same column
				for (int r = row; r < king.getRow(); r++)
					for(Piece[] boardRow : board)
						for(Piece p : boardRow)
							if (p != null && p.isWhite() != temp.isWhite() && testMove(p, r, col))
								return false;
				for (int r = row; r > king.getRow(); r--)
					for(Piece[] boardRow : board)
						for(Piece p : boardRow)
							if (p != null && p.isWhite() != temp.isWhite() && testMove(p, r, col))
								return false;
			}
		
		//if temp is Bishop (or Queen)...
		if (temp instanceof Bishop || temp instanceof Queen)
			for (Piece[] boardRow : board)
				for (Piece p : boardRow)
					if(p != null && p.isWhite() != temp.isWhite())
						for(int i = 1; i < Math.abs(row - king.getRow()); i++)
							if(testMove(p, i * (int)Math.signum(king.getRow() - row) + row, i * (int)Math.signum(king.getColumn() - col) + col))
								return false;
		return true;
	}
	
	/**
	 * Changes pawn that reached far row to new Piece of player's choice
	 * Updates GUI accordingly
	 * Tests if promotion puts king in check
	 * @param row row of pawn to change
	 * @param col column of pawn to change
	 * @param type name of the selected piece
	 */
	protected void pawnChange(int row, int column, Pieces piece) {
		boolean pawnIsWhite = board[row][column].isWhite();
		switch(piece.toString()) {
		case "QUEEN":
			board[row][column] = new Queen(row, column, pawnIsWhite, this);
			break;
		case "KNIGHT":
			board[row][column] = new Knight(row, column, pawnIsWhite, this);
			break;
		case "ROOK":
			board[row][column] = new Rook(row, column, pawnIsWhite, this);
			break;
		case "BISHOP":
			board[row][column] = new Bishop(row, column, pawnIsWhite, this);
			break;
		}
		game.updateSquare(row, column);
		detectCheck(row, column);
	}

}
