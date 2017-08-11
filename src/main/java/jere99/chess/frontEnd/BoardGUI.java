package jere99.chess.frontEnd;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;

import jere99.chess.backEnd.Game;
import jere99.chess.reference.Pieces;

/**
 * a GUI that represents a chess board
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class BoardGUI extends GenericPanelGUI {
	
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
		
		//Last step: Set window to be visible
		setVisible(true);
	}
	
	/**
	 * This method is run every time a button is clicked
	 * Calls different methods on the GAME object based on whether or not this is the first click
	 * @param b the button that was clicked
	 */
	@Override
	protected void buttonClick(GenericButton b) {
		BoardButton button = (BoardButton) b;
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
	
	/** 
	 * a button that represents a square on the chess board of a BoardGUI object
	 * 
	 * @author Kevin
	 * @author JeremiahDeGreeff
	 */
	private class BoardButton extends GenericPanelButton {
		
		/**
		 * this buttons's row on the BoardGUI
		 */
		protected final int ROW;
		/**
		 * this buttons's column on the BoardGUI
		 */
		protected final int COLUMN;
		
		/**
		 * Creates a new button and gives it the correct initial values
		 * @param row the buttons's row on the BoardGUI
		 * @param col the buttons's column on the BoardGUI
		 */
		protected BoardButton(int row, int column) {
			ROW = row;
			COLUMN = column;

			//Set Background Colors
			resetColor();
		}
		
		/**
		 * Sets the button's background color to the default
		 */
		protected void resetColor() {
			if((ROW + COLUMN) % 2 == 0)
				setBackground(Color.WHITE);
			else
				setBackground(Color.BLACK);
		}
		
		/**
		 * highlights this button
		 */
		protected void highlight() {
			setBackground(Color.YELLOW);
		}
		
	}
	
}
