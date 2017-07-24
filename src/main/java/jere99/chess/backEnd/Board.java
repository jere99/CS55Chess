package jere99.chess.backEnd;

import jere99.chess.backEnd.pieces.*;
import jere99.chess.frontEnd.CheckmateGUI;
import jere99.chess.frontEnd.PawnChangeGUI;

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
	private final Game GAME;
	/**
	 * 2D-array that holds all of the pieces in the appropriate locations
	 */
	private final Piece[][] BOARD = new Piece[8][8];;
	/**
	 * Alias of the white king to make locating easier
	 */
	private final King WHITE_KING;
	/**
	 * Alias of the black king to make locating easier
	 */
	private final King BLACK_KING;

	/**
	 * creates a new Board object and initializes all pieces
	 * @param game the game which this board is a part of
	 */
	public Board(Game game) {
		GAME = game;

		// top is black...
		BOARD[0][0] = new Rook(0,0,false, this);
		BOARD[0][1] = new Knight(0,1,false, this);
		BOARD[0][2] = new Bishop(0,2,false, this);
		BOARD[0][3] = new Queen(0,3,false, this);
		BOARD[0][4] = new King(0,4,false, this);
		BOARD[0][5] = new Bishop(0,5,false, this);
		BOARD[0][6] = new Knight(0,6,false, this);
		BOARD[0][7] = new Rook(0,7,false, this);
		for (int i = 0; i < BOARD[1].length; i++)
			BOARD[1][i] = new Pawn(1,i,false, this);
		//...and bottom is white
		BOARD[7][0] = new Rook(7,0,true, this);
		BOARD[7][1] = new Knight(7,1,true, this);
		BOARD[7][2] = new Bishop(7,2,true, this);
		BOARD[7][3] = new Queen(7,3,true, this);
		BOARD[7][4] = new King(7,4,true, this);
		BOARD[7][5] = new Bishop(7,5,true, this);
		BOARD[7][6] = new Knight(7,6,true, this);
		BOARD[7][7] = new Rook(7,7,true, this);
		for (int i = 0; i < BOARD[6].length; i++)
			BOARD[6][i] = new Pawn(6,i,true, this);

		BLACK_KING = (King)BOARD[0][4];
		WHITE_KING = (King)BOARD[7][4];
	}

	/**
	 * @param row row of wanted Piece
	 * @param column column of wanted Piece
	 * @return The piece at board[row][column]
	 */
	public Piece getPieceAt(int row, int column) {
		return BOARD[row][column];
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
		int tempRow = p.getRow();
		int tempCol = p.getColumn();
		if(!(tempRow == newRow && tempCol == newColumn) && p.isValid(newRow, newColumn)) {
			Piece temp = movePiece(p, newRow, newColumn);
			if((p.isWhite() && kingChecked(WHITE_KING)) || (!p.isWhite() && kingChecked(BLACK_KING))) {
				movePiece(p, tempRow, tempCol);
				BOARD[newRow][newColumn] = temp;
				return false;
			} else {
				movePiece(p, tempRow, tempCol);
				BOARD[newRow][newColumn] = temp;
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
		int startRow = p.getRow();
		int startColumn = p.getColumn();

		//move the piece
		movePiece(p, newRow, newColumn);

		//update the GUI
		GAME.getGUI().updateSquare(startRow, startColumn);
		GAME.getGUI().updateSquare(newRow, newColumn);

		//Prevent King or Rook from being able to castle in the future
		if(p instanceof King && !((King)p).hasMoved())
			((King)p).kingMove();
		if(p instanceof Rook && !((Rook)p).hasMoved())
			((Rook)p).rookMove();

		//If the move is a castle also moves the rook
		if(p instanceof King && startColumn == 4 && newColumn == 6)
			movePiece(BOARD[newRow][7], newRow, 5);
		if(p instanceof King && startColumn == 4 && newColumn == 2)
			movePiece(BOARD[newRow][0], newRow, 3);

		//Creates a GUI for pawn promotion
		if(p instanceof Pawn && (newRow == 0 || newRow == 7))
			new PawnChangeGUI(newRow, newColumn, this);

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
		Piece captured = BOARD[newRow][newColumn];

		BOARD[newRow][newColumn] = p;
		BOARD[p.getRow()][p.getColumn()] = null;
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
		if(BOARD[row][column].isWhite() && kingChecked(BLACK_KING)) {
			System.out.println("The Black king is in check!");
			if(checkmate(BLACK_KING)) {
				System.out.println("Checkmate!\nWhite Wins");
				new CheckmateGUI(GAME.getGUI(), "White");
			}
		}
		else if(!BOARD[row][column].isWhite() && kingChecked(WHITE_KING)) {
			System.out.println("The White king is in check!");
			if(checkmate(WHITE_KING)) {
				System.out.println("Checkmate!\nBlack Wins");
				new CheckmateGUI(GAME.getGUI(), "Black");
			}
		}
	}

	/**
	 * tests if king is in check
	 * @param king king to test
	 * @return true if king is checked, false otherwise
	 */
	public boolean kingChecked (King king) {
		for (Piece[] row : BOARD)
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
		for (Piece[] row : BOARD)
			for (Piece p : row)
				if (p != null && p.isWhite() != king.isWhite() && testMove(p, king.getRow(), king.getColumn())) {
					if (temp == null)
						temp = p;
					else
						return true;
				}

		//find out if temp can be taken
		for (Piece[] row : BOARD)
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
					for(Piece[] boardRow : BOARD)
						for(Piece p : boardRow)
							if (p != null && p.isWhite() != temp.isWhite() && testMove(p, row, c))
								return false;
				for (int c = col; c > king.getColumn(); c--)
					for(Piece[] boardRow : BOARD)
						for(Piece p : boardRow)
							if (p != null && p.isWhite() != temp.isWhite() && testMove(p, row, c))
								return false;
			} else if (king.getColumn() > col) { //same column
				for (int r = row; r < king.getRow(); r++)
					for(Piece[] boardRow : BOARD)
						for(Piece p : boardRow)
							if (p != null && p.isWhite() != temp.isWhite() && testMove(p, r, col))
								return false;
				for (int r = row; r > king.getRow(); r--)
					for(Piece[] boardRow : BOARD)
						for(Piece p : boardRow)
							if (p != null && p.isWhite() != temp.isWhite() && testMove(p, r, col))
								return false;
			}

		//if temp is Bishop (or Queen)...
		if (temp instanceof Bishop || temp instanceof Queen)
			for (Piece[] boardRow : BOARD)
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
	 * @param type name of the desired piece
	 * @param gui the GUI which this method needs to affect
	 */
	public void pawnChange(int row, int column, String type) {
		boolean pawnIsWhite = BOARD[row][column].isWhite();
		switch(type) {
		case "Queen":
			BOARD[row][column] = new Queen(row, column, pawnIsWhite, this);
			break;
		case "Knight":
			BOARD[row][column] = new Knight(row, column, pawnIsWhite, this);
			break;
		case "Rook":
			BOARD[row][column] = new Rook(row, column, pawnIsWhite, this);
			break;
		case "Bishop":
			BOARD[row][column] = new Bishop(row, column, pawnIsWhite, this);
			break;
		}
		GAME.getGUI().updateSquare(row, column);
		detectCheck(row, column);
	}

}
