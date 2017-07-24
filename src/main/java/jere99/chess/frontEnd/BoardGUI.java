package jere99.chess.frontEnd;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jere99.chess.backEnd.Game;
import jere99.chess.reference.Pieces;

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
		Pieces.loadIcons();

		//Add the buttons
		for (int r = 0; r < 8; r ++)
			for(int c = 0; c < 8; c ++) {
				BUTTONS[r][c] = new BoardButton(r, c);
				PANEL.add(BUTTONS[r][c]);
				updateSquare(r, c, GAME.getIconForSquare(r, c));
			}

		add(PANEL);

		//Last step: Set window to be visible
		setVisible(true);
	}

	/**
	 * This method is run every time a button is clicked
	 * Calls different methods on the GAME object based on whether or not this is the first click
	 * @param button the button that was clicked
	 */
	protected void buttonClick(BoardButton button) {
		System.out.println((isFirstClick ? "First" : "Second") + " Click: " + button.ROW + ", " + button.COLUMN);
		if(isFirstClick) {
			if(GAME.firstClick(button.ROW, button.COLUMN)) {
				firstClick = button;
				firstClick.highlight();
				isFirstClick = false;
			}
		} else {
			GAME.secondClick(button.ROW, button.COLUMN);
			firstClick.resetColor();
			isFirstClick = true;
		}
	}

	/**
	 * Updates the icon of the square at the passed coordinates based on what is currently present on the board
	 * @param row the row of the square to be updated
	 * @param column the column of the square to be updated
	 * @param icon the icon to update to
	 */
	public void updateSquare(int row, int column, ImageIcon icon) {
		System.out.println("Updating image at: " + row + ", " + column);
		BUTTONS[row][column].setIcon(icon);
	}

}
