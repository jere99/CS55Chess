package jere99.chess.frontEnd;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jere99.chess.backEnd.Game;

/**
 * a GUI that pops up when the game has ended
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class CheckmateGUI extends JFrame {

	/**
	 * the main display component of the GUI
	 */
	private final JPanel PANEL = new JPanel();
	/**
	 * the GUI of the completed game
	 */
	private final BoardGUI BOARD_GUI;
	
	/**
	 * creates a pop-up window after checkmate occurs
	 * @param boardGUI the GUI of the completed game
	 * @param isWhiteWinner which player has won true for white, false for black
	 */
	public CheckmateGUI(BoardGUI boardGUI, Boolean isWhiteWinner) {
		//Name the window
		super((isWhiteWinner ? "white" : "black") + " wins!");
		
		BOARD_GUI = boardGUI;

		//Set the size of the window
		setSize(500,75);

		//Don't allow window to be resized
		setResizable(false);

		//Default program end
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Create the necessary buttons and add to JPanel
		PANEL.add(new CheckmateButton("New Game"));
		PANEL.add(new CheckmateButton("Main Menu"));
		PANEL.add(new CheckmateButton("Exit"));

		//Add the JPanel to the GUI
		add(PANEL);

		//Last step: Set window to be visible
		setVisible(true);
	}
	
	/**
	 * This method is run every time a button is clicked
	 * @param button the button that was clicked
	 */
	protected void buttonClick(CheckmateButton button) {
		BOARD_GUI.dispose(); //close old game
		dispose(); //close this GUI
		switch(button.DISPLAY_NAME) {
		case "Main Menu":
			new StartupScreenGUI(); //create a new startup screen
			break;
		case "New Game":
			System.out.println("\n\n\n"); //create space in console between old game and new game
			new Game(); //start a new game
			break;
		case "Exit":
			System.out.println("Terminating program");
			System.exit(0); //terminate everything
		}
	}
	
}
