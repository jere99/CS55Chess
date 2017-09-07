package jere99.chess.backEnd;

import javax.swing.ImageIcon;

import jere99.chess.backEnd.pieces.Piece;
import jere99.chess.frontEnd.BoardGUI;
import jere99.chess.frontEnd.CheckmateGUI;
import jere99.chess.frontEnd.PawnChangeGUI;
import jere99.chess.reference.Pieces;

/**
 * an instance of a chess game
 * also the interface between the board and the GUIs of the project
 * 
 * @author JeremiahDeGreeff
 */
public class Game implements Cloneable {

	/**
	 * the board for this game
	 */
	private final Board board = new Board(this);
	/**
	 * the GUI for this game
	 */
	private final BoardGUI gui = new BoardGUI(this);

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
		System.out.println("New Game\n\nWhite's Turn");
	}
	
	@Override
	public Game clone() {
		Game newGame = new Game();
		newGame.board.copyBoard(board);
		newGame.refreshGUI();
		return newGame;
	}
	
	/**
	 * returns the ImageIcon corresponding to the current state of the passed square
	 * @param row the row of the square
	 * @param column the column of the square
	 * @return the ImageIcon corresponding to the peice in the square
	 */
	public ImageIcon getIconForSquare(int row, int column) {
		return board.getPieceAt(row, column) != null ? Pieces.getIcon(board.getPieceAt(row, column)) : null;
	}

	/**
	 * updates square at the passed coordinates on the BoardGUI based on the current state of the board
	 * @param row the row of the square to update
	 * @param column the column of the square to update
	 */
	protected void updateSquare(int row, int column) {
		gui.updateSquare(row, column, getIconForSquare(row, column));
	}
	
	/**
	 * Updates every square on the BoardGUI.
	 */
	protected void refreshGUI() {
		for(int row = 0; row < 8; row++)
			for(int column = 0; column < 8; column++)
				gui.updateSquare(row, column, getIconForSquare(row, column));
	}

	/**
	 * creates a CheckmateGUI
	 */
	protected void checkmateInit(boolean isWhiteWinner) {
		System.out.println("Checkmate!\n" + (isWhiteWinner ? "White" : "Black") + " Wins");
		new CheckmateGUI(gui, isWhiteWinner);
	}

	/**
	 * creates a PawnChangeGUI
	 */
	protected void pawnChangeInit(int row, int column) {
		new PawnChangeGUI(row, column, this, isWhiteTurn);
	}

	/**
	 * sends the results of a pawnChange to the board
	 * @param row row of pawn to change
	 * @param col column of pawn to change
	 * @param type name of the selected piece
	 */
	public void pawnChange(int row, int column, Pieces piece) {
		board.pawnChange(row, column, piece);
	}

	/**
	 * tests if the first click is valid and if so stores its information
	 * @param row the row of the clicked square
	 * @param column the column of the clicked square
	 * @return true if the click is valid, false otherwise
	 */
	public boolean firstClick(int row, int column) {
		firstClick = board.getPieceAt(row, column);
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
