package jere99.chess.frontEnd;

import jere99.chess.backEnd.Game;

/**
 * a GUI that pops up when the game has ended
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class CheckmateGUI extends GenericPanelGUI {
	
	/**
	 * the GUI of the completed game
	 */
	private final BoardGUI boardGUI;
	
	/**
	 * creates a pop-up window after checkmate occurs
	 * @param boardGUI the GUI of the completed game
	 * @param isWhiteWinner which player has won true for white, false for black
	 */
	public CheckmateGUI(BoardGUI boardGUI, Boolean isWhiteWinner) {
		//Name the window
		super((isWhiteWinner ? "white" : "black") + " wins!");
		
		this.boardGUI = boardGUI;
		
		//Set the size of the window
		setSize(352, 64);
		
		//Create the necessary buttons and add to JPanel
		panel.add(new CheckmateButton("New Game"));
		panel.add(new CheckmateButton("Main Menu"));
		panel.add(new CheckmateButton("Exit"));
		
		//Last step: Set window to be visible
		setVisible(true);
	}
	
	/**
	 * This method is run every time a button is clicked
	 * @param b the button that was clicked
	 */
	@Override
	protected void buttonClick(GenericButton b) {
		CheckmateButton button = (CheckmateButton) b;
		boardGUI.dispose(); //close old game
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
	
	/**
	 * a button on a CheckmateGUI object
	 * 
	 * @author Kevin
	 * @author JeremiahDeGreeff
	 */
	private class CheckmateButton extends GenericPanelButton {
		
		/**
		 * the text on the button
		 */
		protected final String DISPLAY_NAME;
		
		/**
		 * @param displayName the text to be put on the button
		 */
		protected CheckmateButton(String displayName) {
			DISPLAY_NAME = displayName;
			
			setText(displayName);
			
			setBorderPainted(true);
		}

	}
	
}
