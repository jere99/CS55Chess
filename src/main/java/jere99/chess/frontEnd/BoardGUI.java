package jere99.chess.frontEnd;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jere99.chess.backEnd.Game;
import jere99.chess.reference.Icons;

/**
 * a GUI that represents a chess board
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class BoardGUI extends JFrame {

	/**
	 * the main display component of the GUI
	 */
	private final JPanel PANEL = new JPanel();
	/**
	 * the game that this GUI represents
	 */
	private final Game GAME;
	/**
	 * 2D array of all the buttons on this GUI
	 */
	private final BoardButton[][] BUTTONS = new BoardButton[8][8];
	/**
	 * the first button that the user clicks
	 */
	private BoardButton firstClick;
	/**
	 * true if no square has been selected yet, false otherwise
	 */
	private boolean isFirstClick = true;

	/**
	 * Creates a window with an 8x8 grid layout
	 * Initializes all 64 of the buttons with correct background colors
	 * @param game the game whose board this GUI represents
	 */
	public BoardGUI(Game game) {		
		//Name the window
		super("Chess");

		GAME = game;

		//Sets the size, (width, height)
		setSize(800,800);

		//Allows window to be resized
		setResizable(false);

		//Default program end
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Create new grid layout
		PANEL.setLayout(new GridLayout(8,8));

		//Load the correct piece icons
		Icons.loadIcons();

		//Add the buttons
		for (int r = 0; r < 8; r ++)
			for(int c = 0; c < 8; c ++) {
				BUTTONS[r][c] = new BoardButton(r, c);
				PANEL.add(BUTTONS[r][c]);
				updateSquare(r, c);
			}

		add(PANEL);

		//Last step: Set window to be visible
		setVisible(true);
	}

	/**
	 * This method is run every time a button is clicked
	 * Calls private helper methods based on whether or not this is the first click
	 * @param button the button that was clicked
	 */
	protected void buttonClick(BoardButton button) {
		if(isFirstClick)
			firstClick(button);
		else
			secondClick(button);
	}

	/**
	 * Stores the information of the first click if it is valid
	 * Otherwise reports error to the console
	 * @param button the button which was clicked
	 * @return if the click is valid
	 */
	private boolean firstClick(BoardButton button) {
		//Prints the coordinates of the selected button
		System.out.println("First Click: " + button.ROW + ", " + button.COLUMN);

		//If piece exists
		if(GAME.getBoard().getPieceAt(button.ROW, button.COLUMN) != null)
			//If piece is correct color
			if(GAME.getBoard().getPieceAt(button.ROW, button.COLUMN).isWhite() == GAME.isWhiteTurn()) {
				//valid click: the next click will run the second click method
				firstClick = button;
				firstClick.highlight();
				isFirstClick = false;
				return true;
			}
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
	 * Moves the piece from the first click to the square of the second click if it is a valid move
	 * @param button the button which was clicked
	 */
	private void secondClick(BoardButton button) {
		//Prints the coordinates of the selected button
		System.out.println("Second Click: " + button.ROW + ", " + button.COLUMN);

		//If the second square clicked is a valid spot for the piece from the first click to move to
		if (GAME.getBoard().getPieceAt(firstClick.ROW, firstClick.COLUMN).move(button.ROW, button.COLUMN)) {
			System.out.println("valid move");
			//Switches to the next turn
			GAME.nextTurn();
		}
		//If the second square clicked is not a valid spot for the piece from the first click to move to
		else
			System.out.println("invalid move");

		//Reset the background color of the first button
		firstClick.resetColor();

		//Reset the first click
		isFirstClick = true;
	}

	/**
	 * Updates the icon of the square at the passed coordinates based on what is currently present on the board
	 * @param row the row of the square to be updated
	 * @param column the column of the square to be updated
	 */
	public void updateSquare(int row, int column) {
		System.out.println("Updating image at: " + row + ", " + column);
		String id = "";
		if(GAME.getBoard().getPieceAt(row, column) != null)
			id = GAME.getBoard().getPieceAt(row, column).getPieceID();
		BUTTONS[row][column].setIcon(Icons.getIcon(id));
	}

}
