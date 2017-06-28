package main.java.jere99.chess.frontEnd;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * a GUI that pops up when the game has ended
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class CheckmateGUI extends JFrame{

	/**
	 * the main display component of the GUI
	 */
	private JPanel p = new JPanel();
	/**
	 * array of all the buttons on this GUI
	 */
	private CheckmateButton[] buttons = new CheckmateButton[3];
	/**
	 * the GUI of the completed game
	 */
	private final BoardGUI BOARD_GUI;
	
	/**
	 * creates a pop-up window after checkmate occurs
	 * 
	 * @param boardGUI the GUI of the completed game
	 * @param winner which player has won: either "White" or "Black"
	 */
	public CheckmateGUI(BoardGUI boardGUI, String winner)
	{

		//Name the window
		super(winner + " wins!");
		
		BOARD_GUI = boardGUI;

		//Sets the size of the window
		setSize(500,75);

		//Allows window to be resized
		setResizable(false);

		//Default program end
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Create the necessary buttons
		buttons[0] = new CheckmateButton("New Game", this);
		buttons[1] = new CheckmateButton("Main Menu", this);
 		buttons[2] = new CheckmateButton("Exit", this);

		//Add the necessary buttons to the JPanel
		for (CheckmateButton e : buttons)
			p.add(e);

		//Implements the JPanel to the GUI
		add(p);

		//Last step: Set window to be visible
		setVisible(true);

	}
	
	/**
	 * This method is run every time a button is clicked
	 * @param button the button that was clicked
	 */
	public void buttonClick(CheckmateButton button) {
		//if "New Game" is clicked
		if(button.getDisplayName().equals("New Game")) {
			BOARD_GUI.dispose(); //close old game
			dispose(); //close this GUI
			System.out.println("\n\n\n"); //create space in console between old game and new game
			StartupScreenGUI.newGame(); //start a new game
		}
		//if "Exit" is clicked
		else if (button.getDisplayName().equals("Exit")) {
			BOARD_GUI.dispose(); //close old game
			dispose(); //close this GUI
			System.exit(0); //terminate everything
		}
		//if "Main Menu" is clicked
		else if (button.getDisplayName().equals("Main Menu")) {
			BOARD_GUI.dispose(); //close old game
			dispose(); //close this GUI
			new StartupScreenGUI(); //create a new startup screen
		}
	}
}
