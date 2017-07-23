package jere99.chess.backEnd;

import jere99.chess.frontEnd.BoardGUI;

/**
 * an instance of a chess game
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
	
	public Game() {
		BOARD = new Board(this);
		GUI = new BoardGUI(this);
	}
	
	/**
	 * @return the board for this game
	 */
	public Board getBoard() {
		return BOARD;
	}
	
	/**
	 * @return the GUI for this game
	 */
	public BoardGUI getGUI() {
		return GUI;
	}
	
	/**
	 * @return whose turn it is currently: true if white, false if black
	 */
	public boolean isWhiteTurn() {
		return isWhiteTurn;
	}
	
	/**
	 * Changes turn to other player
	 */
	public void nextTurn() {
		isWhiteTurn = !isWhiteTurn;
		if(isWhiteTurn)
			System.out.println("\nWhite's turn:");
		else
			System.out.println("\nBlack's turn:");
	}
}
