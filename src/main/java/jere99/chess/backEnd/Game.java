package jere99.chess.backEnd;

import jere99.chess.backEnd.pieces.Piece;
import jere99.chess.frontEnd.BoardGUI;
import jere99.chess.frontEnd.CheckmateGUI;
import jere99.chess.frontEnd.PawnChangeGUI;
import jere99.chess.reference.Icons;

/**
 * an instance of a chess game
 * also the interface between the board and the GUIs of the project
 * 
 * @author JeremiahDeGreeff
 */
public class Game {

	/**
	 * the board for this game
	 */
	private final Board BOARD;
	/**
	 * the GUI for this game
	 */
	private final BoardGUI GUI;

	/**
	 * whose turn it is currently: true if white, false if black
	 */
	private boolean isWhiteTurn = true;
	/**
	 * the first piece that the user clicks
	 */
	private Piece firstClick;

	/**
	 * creates a new Game with a board and a GUI
	 */
	public Game() {
		System.out.println("New Game");
		BOARD = new Board(this);
		GUI = new BoardGUI(this);
		System.out.println("\nWhite's Turn");
	}

	/**
	 * updates square at the passed coordinates on the BoardGUI based on the current state of the board
	 * @param row the row of the square to update
	 * @param column the column of the square to update
	 */
	public void updateSquare(int row, int column) {
		if(BOARD.getPieceAt(row, column) != null)
			GUI.updateSquare(row, column, Icons.getIcon(BOARD.getPieceAt(row, column).getPieceID()));
	}

	/**
	 * creates a CheckmateGUI
	 */
	protected void checkmateInit(boolean isWhiteWinner) {
		System.out.println("Checkmate!\n" + (isWhiteWinner ? "White" : "Black") + " Wins");
		new CheckmateGUI(GUI, isWhiteWinner);
	}

	/**
	 * creates a PawnChangeGUI
	 */
	protected void pawnChangeInit(int row, int column) {
		new PawnChangeGUI(row, column, this);
	}

	/**
	 * sends the results of a pawnChange to the board
	 * @param row row of pawn to change
	 * @param col column of pawn to change
	 * @param type name of the selected piece
	 */
	public void pawnChange(int row, int column, String type) {
		BOARD.pawnChange(row, column, type);
	}

	/**
	 * tests if the first click is valid and if so stores its information
	 * @param row the row of the clicked square
	 * @param column the column of the clicked square
	 * @return true if the click is valid, false otherwise
	 */
	public boolean firstClick(int row, int column) {
		firstClick = BOARD.getPieceAt(row, column);
		//If piece exists
		if(firstClick != null)
			//If piece is correct color
			if(firstClick.isWhite() == isWhiteTurn)
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
	 * tests if the second click is a valid move and if so performs the move
	 * @param row the row of the clicked square
	 * @param column the column of the clicked square
	 */
	public void secondClick(int row, int column) {
		if (firstClick.move(row, column)) {
			System.out.println("valid move");
			//Switches to the next turn
			isWhiteTurn = !isWhiteTurn;
			System.out.println("\n" + (isWhiteTurn ? "White's" : "Black's") + " turn:");
		}
		//If the second square clicked is not a valid spot for the piece from the first click to move to
		else
			System.out.println("invalid move");
	}

}
