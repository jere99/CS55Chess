package jere99.chess.frontEnd;

import java.awt.Color;

import jere99.chess.backEnd.Game;
import jere99.chess.reference.Labels;

/**
 * The GUI for the startup screen.
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class StartupScreenGUI extends GenericLabelGUI {

	/**
	 * Keeps track of whether or not a settings menu has already been created in order to prevent multiple settings menus from existing simultaneously.
	 */
	private static boolean settingsMenuCreated = false;
	/**
	 * The width of the frame.
	 */
	private static final int FRAME_WIDTH = 1127;
	/**
	 * The height of the frame.
	 */
	private static final int FRAME_HEIGHT = 865;
	/**
	 * The width of the buttons on this GUI.
	 */
	private static final int BUTTON_WIDTH = 250;
	/**
	 * The height of the buttons on this GUI.
	 */
	private static final int BUTTON_HEIGHT = 30;
	/**
	 * The horizontal spacing between the edge of the frame and the buttons.
	 */
	private static final int HORIZONTAL_OFFSET = 585;
	/**
	 * The vertical spacing between the top of the frame and the first button.
	 */
	private static final int VERTICAL_OFFSET = 145;
	/**
	 * The vertical spacing between the buttons.
	 */
	private static final int VERTICAL_SPACING = 70;

	public StartupScreenGUI() {
		//Name the window
		super("Chromatic Chess");

		//Sets the size, (width, height)
		setSize(FRAME_WIDTH, FRAME_HEIGHT);

		//Background image
		setContentPane(Labels.STARTUP_SCREEN);

		//Creating the buttons
		StartupScreenButton[] buttons = {
				new StartupScreenButton("New Game"),
				new StartupScreenButton("Exit"),
				new StartupScreenButton("Color Scheme")};

		//Configuring locations and sizes of buttons (x,y,width,height)
		//Adding the buttons to the JFrame
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].setBounds(HORIZONTAL_OFFSET, VERTICAL_OFFSET + VERTICAL_SPACING * i, BUTTON_WIDTH, BUTTON_HEIGHT);
			add(buttons[i]);
		}

		//Last step: Set window to be visible
		setVisible(true);
	}

	/**
	 * Starts a new Game, opens a new SettingsMenuGUI, or terminates the program depending on which button has been clicked.
	 * Should be called every time a button on this StartupScreenGUI is clicked.
	 * 
	 * @param b the button that was clicked
	 */
	@Override
	protected void buttonClick(GenericButton b) {
		StartupScreenButton button = (StartupScreenButton) b;
		switch(button.displayName) {
		case "New Game":
			new Game(); //Start the game
			dispose(); //Dispose of the GUI
			break;
		case "Color Scheme":
			if(!settingsMenuCreated) {
				new SettingsMenuGUI(); //Create a new SettingsMenu GUI
				settingsMenuCreated = true; //Prevent a second SettingsMenu from being created
			}
			break;
		case "Exit":
			System.out.println("Terminating program");
			System.exit(0); //Terminate the program
		}
	}

	/**
	 * Allows for a new SettingsMenuGUI to be created after the current one has closed.
	 */
	protected static void SettingsMenuClosed() {
		settingsMenuCreated = false;
	}
	
	/**
	 * A button on a StartupScreenGUI.
	 * 
	 * @author Kevin
	 * @author JeremiahDeGreeff
	 */
	private class StartupScreenButton extends GenericLabelButton {

		/**
		 * The text displayed on the button.
		 */
		private final String displayName;

		/**
		 * @param displayName the text to be displayed on the button
		 */
		private StartupScreenButton(String displayName) {
			this.displayName = displayName;
			
			//Set the button to show the text
			setText(displayName);
			
			//Set Foreground/Background colors
			setForeground(Color.CYAN);
			setBackground(Color.white);
		}
		
	}

}
