package jere99.chess.frontEnd;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jere99.chess.backEnd.Game;
import jere99.chess.reference.Pieces;

/**
 * A GUI that represents a chess board.
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class BoardGUI extends GenericPanelGUI {
	
	/**
	 * The height and width of all the buttons on this GUI.
	 */
	private static final int BUTTON_SIZE = 100;
	/**
	 * The game that this GUI represents.
	 */
	private final Game game;
	/**
	 * All the buttons on this GUI.
	 */
	private final BoardButton[][] buttons = new BoardButton[8][8];
	/**
	 * The first button that the user clicked.
	 */
	private BoardButton firstClick;
	
	/**
	 * @param game the game whose board this GUI represents
	 */
	public BoardGUI(Game game) {		
		//Name the window
		super("Chess");
		
		this.game = game;
		
		//Sets the size, (width, height)
		setSize(BUTTON_SIZE * 8, BUTTON_SIZE * 8);
		
		//Create new grid layout
		panel.setLayout(new GridLayout(8,8));
		
		//Load the correct piece icons
		Pieces.loadIcons();
		
		//Add the buttons
		for(int r = 0; r < 8; r++)
			for(int c = 0; c < 8; c++) {
				buttons[r][c] = new BoardButton(this, r, c);
				panel.add(buttons[r][c]);
				updateSquare(r, c);
			}
		
		//Last step: Set window to be visible
		setVisible(true);
	}
	
	/**
	 * Calls different methods on the game object based on whether or not this is the first click.
	 * Will be called every time a button on this BoardGUI is clicked.
	 * 
	 * @param e the ActionEvent corresponding to a button click on this GUI
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		BoardButton button = (BoardButton) e.getSource();
		System.out.println((firstClick == null ? "First" : "Second") + " Click: " + button.row + ", " + button.column);
		if(firstClick == null) {
			if(game.firstClick(button.row, button.column)) {
				firstClick = button;
				firstClick.highlight();
			}
		} else {
			game.secondClick(button.row, button.column);
			firstClick.resetColor();
			firstClick = null;
		}
	}
	
	/**
	 * Updates the icon of the specified square.
	 * 
	 * @param row the row of the square to be updated
	 * @param column the column of the square to be updated
	 * @param icon the icon to update to
	 */
	public void updateSquare(int row, int column) {
		System.out.println("Updating image at: " + row + ", " + column);
		buttons[row][column].setIcon(game.getIconForSquare(row, column));
	}
	
	/** 
	 * A button that represents a square on the chess board of a BoardGUI object.
	 * 
	 * @author Kevin
	 * @author JeremiahDeGreeff
	 */
	private class BoardButton extends GenericButton {
		
		/**
		 * This buttons's row on the BoardGUI.
		 */
		private final int row;
		/**
		 * This buttons's column on the BoardGUI.
		 */
		private final int column;
		
		/**
		 * Creates a new button and gives it the correct initial values.
		 * 
		 * @param l the object who should listen for this button to be clicked
		 * @param row the buttons's row on the BoardGUI
		 * @param col the buttons's column on the BoardGUI
		 */
		private BoardButton(ActionListener l, int row, int column) {
			super(l);
			
			this.row = row;
			this.column = column;

			//Set Background Colors
			resetColor();
		}
		
		/**
		 * Sets the button's background color to the default.
		 */
		private void resetColor() {
			if((row + column) % 2 == 0)
				setBackground(Color.WHITE);
			else
				setBackground(Color.BLACK);
		}
		
		/**
		 * Highlights this button.
		 */
		private void highlight() {
			setBackground(Color.YELLOW);
		}
		
	}
	
}
