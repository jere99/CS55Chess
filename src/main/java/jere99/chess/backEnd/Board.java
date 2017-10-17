package jere99.chess.backEnd;

import jere99.chess.backEnd.pieces.*;
import jere99.chess.reference.Pieces;

/**
 * Represents a chess board.
 * 
 * @author Megha
 * @author JeremiahDeGreeff
 */

public class Board implements Cloneable {
	
	/**
	 * The game which this board is a part of.
	 */
	private final Game game;
	/**
	 * All the Pieces on this Board.
	 */
	private final Piece[][] board = new Piece[8][8];;
	/**
	 * Alias of the white king to make locating easier.
	 */
	private final King whiteKing;
	/**
	 * Alias of the black king to make locating easier.
	 */
	private final King blackKing;
	
	/**
	 * Initializes a Board with the starting piece positions.
	 * 
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
				for(int column = 0; column < board[1].length; column++)
					board[row][column] = new Pawn(row, column, row == 6, this);
		}
		
		blackKing = (King)board[0][4];
		whiteKing = (King)board[7][4];
	}
	
	/**
	 * Initializes a Board from an existing Board.
	 * Intended to be used for cloning.
	 * 
	 * @param board the board whose state will be copied
	 */
	private Board(Board oldBoard) {
		this.game = oldBoard.game;
		for(int row = 0; row < 8; row++)
			for(int column = 0; column < 8; column++) {
				Piece p = oldBoard.board[row][column];
				if(p != null) {
					Piece newPiece = p.copyPiece(this);
					if(p instanceof Castleable && ((Castleable) p).hasMoved())
						((Castleable) newPiece).castleableMove();
					this.board[row][column] = newPiece;
				}
			}
		blackKing = (King)board[oldBoard.blackKing.getRow()][oldBoard.blackKing.getColumn()];
		whiteKing = (King)board[oldBoard.whiteKing.getRow()][oldBoard.whiteKing.getColumn()];
	}
	
	@Override
	public Board clone() {
		return new Board(this);
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
	 * Moves the passed Piece to the specified spot on this Board.
	 * Should only be run when the move is valid according to piece rules.
	 * 
	 * @param piece the Piece to move
	 * @param newRow the row to move piece to
	 * @param newColumn the column to move piece to
	 */
	private void movePiece(Piece piece, int newRow, int newColumn) {
		board[newRow][newColumn] = piece;
		board[piece.getRow()][piece.getColumn()] = null;
		piece.move(newRow, newColumn);
	}
	
	/**
	 * Tests if moving piece to board[newRow][newColumn] is valid and does not check own king.
	 * Should only be called on a Board which is not associated with a GUI and can be discarded afterwards.
	 * 
	 * @param piece Piece to test move
	 * @param newRow row to move piece to
	 * @param newColumn column to move piece to
	 * @return true if move is valid and will not check own king, false otherwise
	 * @throws IllegalStateException if this Board is associated with a GUI
	 */
	public boolean testMove(Piece piece, int newRow, int newColumn) {
		if(game.isRepresentedBoard(this))
			throw new IllegalStateException("Cannot run move testing methods on a baord associated with a GUI");
		if(piece.getRow() == newRow && piece.getColumn() == newColumn || !piece.isValid(newRow, newColumn)) //actually a move and is valid for the piece
			return false;
		movePiece(piece, newRow, newColumn);
		return !kingChecked(piece.isWhite() ? whiteKing : blackKing); //doesn't check own king
	}
	
	/**
	 * Moves the passed piece to the specified spot on this Board.
	 * Updates the GUI accordingly.
	 * Should only be run if the move has been tested and is actually intended to be represented on the GUI.
	 * 
	 * @param piece the Piece to move
	 * @param newRow the row to move p to
	 * @param newColumn the column to move p to
	 */
	protected void makeMove(Piece piece, int newRow, int newColumn) {
		int startRow = piece.getRow(), startColumn = piece.getColumn();
		//move the piece
		movePiece(piece, newRow, newColumn);
		
		//update the GUI
		game.updateSquare(startRow, startColumn);
		game.updateSquare(newRow, newColumn);
		
		//if the move is a castle also move the rook
		if(piece instanceof King && startColumn == 4 && (newColumn == 6 || newColumn == 2)) {
			boolean kingSide = newColumn == 6; //true if into column 6, false if into column 2
			movePiece(board[newRow][kingSide ? 7 : 0], newRow, kingSide ? 5 : 3);
			game.updateSquare(newRow, kingSide ? 7 : 0);
			game.updateSquare(newRow, kingSide ? 5 : 3);
		}
		
		//create a GUI for pawn promotion if necessary
		if(piece instanceof Pawn && (newRow == 0 || newRow == 7))
			game.pawnPromotionInit(newRow, newColumn);
		
		//see if the move has put the opposing king in check or checkmate
		detectCheck(newRow, newColumn);
	}
	
	/**
	 * Tests if a move to a particular row and column has resulted in check or checkmate for the opposing player.
	 * 
	 * @param row the row of the piece that just moved
	 * @param column the column of the piece that just moved
	 */
	private void detectCheck(int row, int column) {
		boolean isWhiteMove = board[row][column].isWhite();
		if(kingChecked(isWhiteMove ? blackKing : whiteKing)) {
			System.out.println("The " + (isWhiteMove ? "black" : "white") + " king is in check!");
			Board testBoard = this.clone();
			if(testBoard.checkmate(isWhiteMove ? testBoard.blackKing : testBoard.whiteKing))
				game.checkmateInit(isWhiteMove);
		}
	}
	
	/**
	 * Tests if a King is in check.
	 * 
	 * @param king the King to test
	 * @return true if the King is checked, false otherwise
	 */
	public boolean kingChecked(King king) {
		for(Piece[] row : board)
			for(Piece p : row)
				if(p != null && king.isWhite() != p.isWhite())
					if(p.isValid(king.getRow(), king.getColumn()))
						return true;
		return false;
	}
	
	/**
	 * Tests if a King is in checkmate.
	 * Should only be called on a Board which is not associated with a GUI and can be discarded afterwards.
	 * 
	 * @param king the King to test
	 * @return true if the King is in checkmate, false otherwise
	 * @throws IllegalStateException if this Board is associated with a GUI
	 */
	private boolean checkmate(King king) {
		if(game.isRepresentedBoard(this))
			throw new IllegalStateException("Cannot run move testing methods on a baord associated with a GUI");
		
		//test if king can move
		for(int r = king.getRow() - 1; r < king.getRow() + 1; r++)
			for(int c = king.getColumn() - 1; c < king.getColumn() + 1; c++)
				if(r >= 0 && r <= 7 && c >= 0 && c <= 7 && testMove(king, r, c))
					return false;
		
		
		//find Piece checking king
		Piece checking = null;
		for(Piece[] row : board)
			for(Piece p : row)
				if(p != null && p.isWhite() != king.isWhite() && p.isValid(king.getRow(), king.getColumn()))
					if(checking == null)
						checking = p;
					else
						return true;
		
		//find out if checking can be taken
		for(Piece[] row : board)
			for(Piece p : row)
				if(p != null && p.isWhite() != checking.isWhite() && testMove(p, checking.getRow(), checking.getColumn()))
					return false;
		
		//find out if checking can be blocked
		if(checking instanceof Pawn || checking instanceof Knight)
			return true;
		int row = checking.getRow();
		int column = checking.getColumn();
		//if checking is Rook (or Queen)...
		if(checking instanceof Rook || checking instanceof Queen)
			if(king.getRow() == row) { //same row
				for(int c = column; column < king.getColumn() ? c < king.getColumn() : c > king.getColumn(); c += (column < king.getColumn() ? 1 : -1))
					for(Piece[] boardRow : board)
						for(Piece p : boardRow)
							if(p != null && p.isWhite() != checking.isWhite() && testMove(p, row, c))
								return false;
			} else if(king.getColumn() == column) { //same column
				for(int r = row; row < king.getRow() ? r < king.getRow() : r > king.getRow(); r += (row < king.getRow() ? 1 : -1))
					for(Piece[] boardRow : board)
						for(Piece p : boardRow)
							if(p != null && p.isWhite() != checking.isWhite() && testMove(p, r, column))
								return false;
			}
		//if checking is Bishop (or Queen)...
		if(checking instanceof Bishop || checking instanceof Queen)
			for(Piece[] boardRow : board)
				for(Piece p : boardRow)
					if(p != null && p.isWhite() != checking.isWhite())
						for(int i = 1; i < Math.abs(row - king.getRow()); i++)
							if(testMove(p, i * (int)Math.signum(king.getRow() - row) + row, i * (int)Math.signum(king.getColumn() - column) + column))
								return false;
		
		return true;
	}
	
	/**
	 * Promotes a Pawn that reached far row to a new Piece of player's choice.
	 * Updates GUI accordingly.
	 * Tests if promotion puts king in check.
	 * 
	 * @param row the row of pawn to change
	 * @param column the column of pawn to change
	 * @param type the name of the selected piece
	 */
	protected void pawnPromotion(int row, int column, Pieces piece) {
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
