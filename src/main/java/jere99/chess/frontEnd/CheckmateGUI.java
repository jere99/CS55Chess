package jere99.chess.frontEnd;

import java.awt.event.ActionEvent;

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
	 * The width of the frame.
	 */
	private static final int FRAME_WIDTH = 352;
	/**
	 * The height of the frame.
	 */
	private static final int FRAME_HEIGHT = 64;
	
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
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		//Create the necessary buttons and add to JPanel
		panel.add(new GenericButton("New Game", this));
		panel.add(new GenericButton("Main Menu", this));
		panel.add(new GenericButton("Exit", this));
		
		//Last step: Set window to be visible
		setVisible(true);
	}
	
	/**
	 * Creates a new StartupScreenGUI, creates a new Game, or terminates the program depending on what button has been pressed.
	 * Will be called every time a button on this CheckmateGUI is clicked.
	 * 
	 * @param e the ActionEvent corresponding to a button click on this GUI
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//close old game
		boardGUI.dispose();
		//close this GUI
		dispose();
		
		switch(e.getActionCommand()) {
		case "Main Menu":
			//create a new startup screen
			new StartupScreenGUI();
			break;
		case "New Game":
			//create space in console between old game and new game
			System.out.println("\n\n\n");
			//start a new game
			new Game();
			break;
		case "Exit":
			//terminate everything
			System.out.println("Terminating program");
			System.exit(0); 
		}
	}
	
}
