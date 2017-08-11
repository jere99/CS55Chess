package jere99.chess.frontEnd;

import java.awt.Color;

import jere99.chess.backEnd.Game;
import jere99.chess.reference.Labels;

/**
 * The GUI for the startup screen
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class StartupScreenGUI extends GenericLabelGUI {

	/**
	 * Boolean to keep track of whether or not a settings menu has already been created
	 * in order to prevent multiple settings menus from existing simultaneously
	 */
	private static boolean settingsMenuCreated = false;
	/**
	 * the width of the frame
	 */
	private static final int FRAME_WIDTH = 1127;
	/**
	 * the height of the frame
	 */
	private static final int FRAME_HEIGHT = 865;
	/**
	 * the width of the buttons on this GUI
	 */
	private static final int BUTTON_WIDTH = 250;
	/**
	 * the height of the buttons on this GUI
	 */
	private static final int BUTTON_HEIGHT = 30;
	/**
	 * the horizontal spacing between the edge of the frame and the buttons
	 */
	private static final int HORIZONTAL_OFFSET = 585;
	/**
	 * the vertical spacing between the top of the frame and the first button
	 */
	private static final int VERTICAL_OFFSET = 145;
	/**
	 * the vertical spacing between the buttons
	 */
	private static final int VERTICAL_SPACING = 70;

	/**
	 * Creates a new window with all functional buttons and correct background
	 */
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
	 * This method is run every time a button is clicked
	 * @param b the button that was clicked
	 */
	@Override
	protected void buttonClick(GenericButton b) {
		StartupScreenButton button = (StartupScreenButton) b;
		switch(button.DISPLAY_NAME) {
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
	 * Allows for a new SettingsMenuGUI to be created after the current one has closed
	 */
	protected static void SettingsMenuClosed() {
		settingsMenuCreated = false;
	}
	
	/**
	 * Buttons that appear on the StartupScreenGUI
	 * 
	 * @author Kevin
	 * @author JeremiahDeGreeff
	 */
	private class StartupScreenButton extends GenericLabelButton {

		/**
		 * text displayed on the button, also used to identify the button
		 */
		protected final String DISPLAY_NAME;

		/**
		 * creates a button for the StartupScreenGUI
		 * @param displayName the text to be displayed on the button
		 */
		public StartupScreenButton(String displayName) {
			DISPLAY_NAME = displayName;
			
			//Set the button to show the text
			setText(DISPLAY_NAME);
			
			//Set Foreground/Background colors
			setForeground(Color.CYAN);
			setBackground(Color.white);
		}
		
	}

}
