package jere99.chess.frontEnd;

import jere99.chess.backEnd.Game;

/**
 * A GUI that pops up when the game has ended.
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class CheckmateGUI extends GenericPanelGUI {
	
	/**
	 * The GUI of the completed game.
	 */
	private final BoardGUI boardGUI;
	
	/**
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
	 * Creates a new StartupScreenGUI, creates a new Game, or terminates the program depending on what button has been pressed.
	 * Should be called every time a button on this CheckmateGUI is clicked.
	 * 
	 * @param b the button that was clicked
	 */
	@Override
	protected void buttonClick(GenericButton b) {
		CheckmateButton button = (CheckmateButton) b;
		boardGUI.dispose(); //close old game
		dispose(); //close this GUI
		switch(button.displayName) {
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
	 * A button on a CheckmateGUI object.
	 * 
	 * @author Kevin
	 * @author JeremiahDeGreeff
	 */
	private class CheckmateButton extends GenericPanelButton {
		
		/**
		 * The text on the button.
		 */
		private final String displayName;
		
		/**
		 * @param displayName the text to be put on the button
		 */
		private CheckmateButton(String displayName) {
			this.displayName = displayName;
			
			setText(displayName);
			
			setBorderPainted(true);
		}

	}
	
}
