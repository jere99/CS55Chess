package jere99.chess.frontEnd;

import javax.swing.JFrame;

import jere99.chess.backEnd.Game;
import jere99.chess.reference.Labels;

/**
 * The GUI for the startup screen
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class StartupScreenGUI extends JFrame {

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

		//Allows window to be resized
		setResizable(false);

		//Default program end
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Background image
		setContentPane(Labels.STARTUP_SCREEN);

		//Setting JFrame to Absolute Layout so that elements may be positioned
		setLayout(null);

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
	 * @param button the button that was clicked
	 */
	protected void buttonClick(StartupScreenButton button) {
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

}
