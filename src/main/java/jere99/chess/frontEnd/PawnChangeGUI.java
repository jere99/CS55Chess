package jere99.chess.frontEnd;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jere99.chess.backEnd.Game;

/**
 * a GUI pops up when a pawn is to be promoted
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class PawnChangeGUI extends JFrame {

	/**
	 * the main display component of the GUI
	 */
	private final JPanel PANEL = new JPanel();
	/**
	 * the Game in which this promotion is occurring
	 */
	private final Game GAME;
	/**
	 * the row where this promotion is occurring
	 */
	private final int ROW;
	/**
	 * the column where this promotion is occurring
	 */
	private final int COLUMN;

	/**
	 * creates a pop-up window which asks the user what piece they would like to promote their pawn into
	 * @param row the row where this promotion is occurring
	 * @param column the column where this promotion is occurring
	 * @param board the board on which this promotion is occurring
	 */
	public PawnChangeGUI(int row, int column, Game game) {
		//Name the window
		super("Pawn Promotion");

		ROW = row;
		COLUMN = column;
		GAME = game;

		//Set the size of the window
		setSize(400,75);

		//Don't allow window to be resized
		setResizable(false);

		//Default program end
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Create the necessary buttons and add them to the JPanel
		PANEL.add(new PawnChangeButton("Knight"));
		PANEL.add(new PawnChangeButton("Bishop"));
		PANEL.add(new PawnChangeButton("Rook"));
		PANEL.add(new PawnChangeButton("Queen"));

		//Add the JPanel to the GUI
		add(PANEL);

		//Last step: Set window to be visible
		setVisible(true);
	}

	/**
	 * This method is run every time a button is clicked
	 * @param button the button that was clicked
	 */
	public void buttonClick(PawnChangeButton button) {
		System.out.println("Promoting to " + button.DISPLAY_NAME);

		//promote the pawn into the selected piece
		GAME.pawnChange(ROW, COLUMN, button.DISPLAY_NAME);

		//close this GUI
		dispose();
	}
}
