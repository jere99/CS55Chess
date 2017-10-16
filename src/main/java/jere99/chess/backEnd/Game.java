package jere99.chess.backEnd;

import javax.swing.ImageIcon;

import jere99.chess.backEnd.pieces.Piece;
import jere99.chess.frontEnd.BoardGUI;
import jere99.chess.frontEnd.CheckmateGUI;
import jere99.chess.frontEnd.PawnChangeGUI;
import jere99.chess.reference.Pieces;

/**
 * Represents a chess game.
 * Also serves as the interface between the Board and the GUIs of the project.
 * 
 * @author JeremiahDeGreeff
 */
public class Game {

	/**
	 * The board for this game.
	 */
	private final Board board = new Board(this);
	/**
	 * The GUI for this game.
	 */
	private final BoardGUI gui = new BoardGUI(this);

	/**
	 * Whose turn it is currently: true if white, false if black.
	 */
	private boolean isWhiteTurn = true;
	/**
	 * The first piece that the user clicked.
	 */
	private Piece selectedPiece;

	public Game() {
		System.out.println("New Game\n\nWhite's Turn");
	}
	
	/**
	 * @return whose turn it is currently: true if white, false if black.
	 */
	public boolean isWhiteTurn() {
		return isWhiteTurn;
	}
	
	/**
	 * Checks if a given Board object is currently being represented on the GUI.
	 * Intended to be used to avoid move testing methods being represented.
	 * 
	 * @param board the board to check
	 * @return true if this board is represented on the GUI, false otherwise
	 */
	protected boolean isRepresentedBoard(Board board) {
		return this.board == board;
	}
	
	/**
	 * Determines the ImageIcon corresponding to the current state of the passed square.
	 * 
	 * @param row the row of the square
	 * @param column the column of the square
	 * @return the ImageIcon corresponding to the piece in the square
	 */
	public ImageIcon getIconForSquare(int row, int column) {
		return board.getPieceAt(row, column) != null ? Pieces.getIcon(board.getPieceAt(row, column)) : null;
	}

	/**
	 * Updates the square at the passed coordinates on the BoardGUI based on the current state of the board.
	 * 
	 * @param row the row of the square to update
	 * @param column the column of the square to update
	 */
	protected void updateSquare(int row, int column) {
		gui.updateSquare(row, column);
	}
	
	/**
	 * Updates every square on the BoardGUI.
	 */
	protected void refreshGUI() {
		for(int row = 0; row < 8; row++)
			for(int column = 0; column < 8; column++)
				gui.updateSquare(row, column);
	}

	/**
	 * Creates a CheckmateGUI.
	 */
	protected void checkmateInit(boolean isWhiteWinner) {
		System.out.println("Checkmate!\n" + (isWhiteWinner ? "White" : "Black") + " Wins");
		new CheckmateGUI(gui, isWhiteWinner);
	}

	/**
	 * Creates a PawnChangeGUI.
	 */
	protected void pawnChangeInit(int row, int column) {
		new PawnChangeGUI(row, column, this);
	}

	/**
	 * Sends the results of a pawnChange to the board.
	 * 
	 * @param row the row of pawn to change
	 * @param column the column of pawn to change
	 * @param type the name of the selected piece
	 */
	public void pawnChange(int row, int column, Pieces piece) {
		board.pawnChange(row, column, piece);
	}

	/**
	 * Tests if the first click is valid and if so caches the Piece in that square.
	 * 
	 * @param row the row of the clicked square
	 * @param column the column of the clicked square
	 * @return true if the click is valid, false otherwise
	 */
	public boolean firstClick(int row, int column) {
		selectedPiece = board.getPieceAt(row, column);
		//If piece exists
		if(selectedPiece != null)
			//If piece is correct color
			if(selectedPiece.isWhite() == isWhiteTurn)
				return true;
		//If color of piece is invalid
			else {
				System.out.println("wrong color");
				return false;
			}
		//If square is empty
		else {
			System.out.println("empty square");
			return false;
		}
	}

	/**
	 * Tests if the second click is a valid move.
	 * If it is valid, the move is performed.
	 * 
	 * @param row the row of the clicked square
	 * @param column the column of the clicked square
	 */
	public void secondClick(int row, int column) {
		if(board.clone().testMove(board.getPieceAt(selectedPiece.getRow(), selectedPiece.getColumn()), row, column)) {
			System.out.println("valid move");
			board.makeMove(selectedPiece, row, column);
			//Switches to the next turn
			isWhiteTurn = !isWhiteTurn;
			System.out.println("\n" + (isWhiteTurn ? "White's" : "Black's") + " turn:");
		}
		else //If the second square clicked is not a valid spot for the piece from the first click to move to
			System.out.println("invalid move");
	}

}
